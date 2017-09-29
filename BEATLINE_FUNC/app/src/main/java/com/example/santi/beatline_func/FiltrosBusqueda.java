package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FiltrosBusqueda extends Activity {
    EditText Genero, Instrumento, EdadMin, EdadMax, DistanciaMin, DistanciaMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_filtro);
        Genero = (EditText)findViewById(R.id.Genero);
        Instrumento = (EditText)findViewById(R.id.Instrumento);
        EdadMin = (EditText)findViewById(R.id.EdadMin);
        EdadMax = (EditText)findViewById(R.id.EdadMax);
        DistanciaMin = (EditText)findViewById(R.id.DistanciaMin);
        DistanciaMax = (EditText)findViewById(R.id.DistanciaMax);
    }

    public void Buscar(View vista)
    {
        Filtros f = new Filtros();
        String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Get2";

        ArrayList<Persona> p = new ArrayList<>();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(f));
        new FiltrosBusqueda.ConectarAPITask().execute("POST",urlApi, gson.toJson(f));
        Intent Activity;
        Activity = new Intent(this,MainActivity.class);
        startActivity(Activity);
    }

    private class ConectarAPITask extends AsyncTask<String, Void,  Filtros> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected void onPostExecute(Filtros f) {
            super.onPostExecute(f);
            String strGenero = Genero.getText().toString();
            String strInstrumento = Instrumento.getText().toString();
            String strEdadMin = EdadMin.getText().toString();
            String strEdadMax = EdadMax.getText().toString();
            String strDistanciaMin = DistanciaMin.getText().toString();
            String strDistanciaMax = DistanciaMax.getText().toString();

            if (strGenero.equals("") || strInstrumento.equals("") ||strEdadMin.equals("") ||strEdadMax.equals("") ||strDistanciaMin.equals("") ||strDistanciaMax.equals(""))
            {
                Toast t = Toast.makeText(FiltrosBusqueda.this, "No deje campos en blanco", Toast.LENGTH_LONG);
                t.show();
            }
            else
            {
                f.setInstrumentos(strInstrumento);
                f.setGeneros(strGenero);
                f.setEdadMaxima(strEdadMax);
                f.setEdadMinima(strEdadMin);
                f.setDistanciaMinima(strDistanciaMin);
                f.setDistanciaMaxima(strDistanciaMax);
            }

        }

        private void postFiltros(String urlApi, String json) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(urlApi)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return;
            } catch (IOException e) {
                Log.d("Error :", e.getMessage());
                return;

            }
        }

        @Override
        protected Filtros doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];

            if (method.equals("POST")) {
                String json = params[2];
                postFiltros(urlApi, json);
            }
            return null;
        }

        private Filtros getFiltros(String urlApi) {
            String strResultado;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                strResultado = response.body().string();
                Filtros f = parsearResultado(strResultado );
                return f;
            }
            catch (IOException e){
                return null;

            }
        }

        private Filtros parsearResultado(String respuesta)   {
            if (respuesta == null || respuesta.length()==0)
                return null;
            try {
                Gson gson = new Gson();
                Filtros[] p = gson.fromJson(respuesta, Filtros[].class);
                return p[0];

            }
            catch (Exception e) {
                return null;
            }

        }

    }

    public void link1(View Vista)
    {
        Toast toast1;
        toast1 = Toast.makeText(FiltrosBusqueda.this, "Vista agregar instrumento nuevo", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void link2(View Vista)
    {
        Toast toast1;
        toast1 = Toast.makeText(FiltrosBusqueda.this, "Vista agregar género nuevo", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void Volver (View vista)
    {
        Intent Activity;
        Activity = new Intent(this, MainActivity.class);
        startActivity(Activity);
    }
}

