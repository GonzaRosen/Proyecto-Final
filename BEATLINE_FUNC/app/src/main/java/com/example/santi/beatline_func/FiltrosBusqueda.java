package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FiltrosBusqueda extends Activity {
    EditText Genero, Instrumento, Influencia, Ubicacion;
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView Logo;
    Button volver, buscar;
    LinearLayout layout;
    AdapterPersona adapterPersona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_filtro);
        Genero = (EditText)findViewById(R.id.Genero);
        Instrumento = (EditText)findViewById(R.id.Instrumento);
        Influencia = (EditText)findViewById(R.id.Influenciaa);
        Ubicacion = (EditText)findViewById(R.id.Ubicacionn);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.tvInstrumento);
        tv3 = (TextView) findViewById(R.id.tvInfluencia);
        tv4 = (TextView) findViewById(R.id.tvGenero);
        tv5 = (TextView) findViewById(R.id.tvUbiacion);
        Logo = (ImageView) findViewById(R.id.Logo);
        volver = (Button) findViewById(R.id.Volver);
        buscar =(Button) findViewById(R.id.Buscar);
    }

    public void Buscar(View vista)
    {
        String strGenero = Genero.getText().toString();
        String strInstrumento = Instrumento.getText().toString();
        String strInfluencia = Influencia.getText().toString();
        String strUbicacion = Ubicacion.getText().toString();
        if (strGenero.equals("") || strInstrumento.equals("") ||strInfluencia.equals("") ||strUbicacion.equals(""))
        {
            Toast t = Toast.makeText(FiltrosBusqueda.this, "No deje campos en blanco", Toast.LENGTH_LONG);
            t.show();
        }
        else
        {
            String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Get?Ubicacion=" + strUbicacion + "&Instrumentos=" + strInstrumento + "&Generos=" + strGenero + "&Influencias=" + strInfluencia;
            new ConectarAPITask().execute("GET", urlApi);
            Genero.setVisibility(View.GONE);
            Instrumento.setVisibility(View.GONE);
            Influencia.setVisibility(View.GONE);
            Ubicacion.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            tv4.setVisibility(View.GONE);
            tv5.setVisibility(View.GONE);
            Logo.setVisibility(View.GONE);
            volver.setVisibility(View.GONE);
            buscar.setVisibility(View.GONE);
            ListView lv = (ListView) findViewById(R.id.Lista);
            adapterPersona = new AdapterPersona(getBaseContext(),new ArrayList<UsuariosBusqueda>());
            lv.setAdapter(adapterPersona);
        }


    }

    private class ConectarAPITask extends AsyncTask<String, Void, UsuariosBusqueda[]> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected UsuariosBusqueda[] doInBackground(String... params) {

            String urlApi = params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                UsuariosBusqueda[] usuariosBusquedas = parsearResultado(response.body().string());
                return usuariosBusquedas;
            }
            catch (IOException e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(UsuariosBusqueda[] usuariosBusquedas) {
            super.onPostExecute(usuariosBusquedas);
            adapterPersona.setPersonas(usuariosBusquedas);
        }

        private UsuariosBusqueda[] parsearResultado(String respuesta)   {
            if (respuesta == null || respuesta.length()==0)
                return null;
            try {
                Gson gson = new Gson();
                UsuariosBusqueda[] usuariosBusquedas = gson.fromJson(respuesta, UsuariosBusqueda[].class);
                return usuariosBusquedas;

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
    public void link3(View Vista)
    {
        Toast toast1;
        toast1 = Toast.makeText(FiltrosBusqueda.this, "Vista agregar influencia nueva", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void Volver (View vista)
    {
        Intent Activity;
        Activity = new Intent(this, MainActivity.class);
        startActivity(Activity);
    }
}

