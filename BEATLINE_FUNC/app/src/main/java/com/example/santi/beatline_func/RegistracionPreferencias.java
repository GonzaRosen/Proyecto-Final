package com.example.santi.beatline_func;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistracionPreferencias extends AppCompatActivity{

    EditText Genero;
    EditText Influencia;
    EditText Instrumento;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracion_preferencias);

        Genero = (EditText)findViewById(R.id.gene);
        Influencia = (EditText)findViewById(R.id.influ);
        Instrumento = (EditText)findViewById(R.id.instru);

    }

    public void registrar(View view)
    {
        String strGenero = Genero.getText().toString();
        String strInfluencia = Influencia.getText().toString();
        String strInstrumento = Instrumento.getText().toString();

        if(!(strGenero.equals("") || strInfluencia.equals("") || strInstrumento.equals("")))
        {
            String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Post";
            Persona p = new Persona();
            p.setGenero(strGenero);
            p.setInfluencias(strInfluencia);
            p.setInstrumentos(strInstrumento);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(p));
            new ConectarAPITask().execute("POST",urlApi, gson.toJson(p));
            Intent Activity;
            Activity = new Intent(this,MainActivity.class);
            startActivity(Activity);
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(RegistracionPreferencias.this, "No deje campos en blanco.", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected Persona doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];
            String Resultado;

            if (method.equals("POST")) {
                String json = params[2];
                postPersona(urlApi, json);
            }
            return null;
        }

        private void postPersona(String urlApi, String json) {
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
        protected void onPostExecute(Persona persona) {
            super.onPostExecute(persona);
            if (persona != null) {

            }

        }

        private Persona parsearResultado(String respuesta)   {
            if (respuesta == null || respuesta.length()==0)
                return null;
            try {
                Gson gson = new Gson();
                Persona[] p = gson.fromJson(respuesta, Persona[].class);
                return p[0];

            }
            catch (Exception e) {
                return null;
            }

        }

    }

}
