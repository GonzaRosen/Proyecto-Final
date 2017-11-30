package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PerfilUsuario extends Activity{
    TextView Nomape;
    TextView Desc;
    TextView Genero;
    TextView Influ;
    TextView Instru;
    TextView Ubicacion;
    ListView LvSeguidos;
    AdapterSeguidos adapterSeguidos;
    ArrayList<UsuariosBusqueda> Juan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilusu);
        Intent intent = getIntent();

        Nomape = (TextView) findViewById(R.id.nomape);
        Desc = (TextView) findViewById(R.id.descripcion);
        Influ = (TextView) findViewById(R.id.influencias);
        Genero = (TextView) findViewById(R.id.generos);
        Instru = (TextView) findViewById(R.id.instrumentos);
        Ubicacion = (TextView) findViewById(R.id.Ubicacion);
        LvSeguidos = (ListView) findViewById(R.id.listaSeguidos);

        Nomape.setText(MainActivity.usuario_logeado.getNombre() + " " + MainActivity.usuario_logeado.getApellido());
        Desc.setText("Descripción: " + MainActivity.usuario_logeado.getDescripcion());
        Influ.setText("Influencias: " + MainActivity.usuario_logeado.getInfluencias());
        Genero.setText("Géneros: " + MainActivity.usuario_logeado.getGenero());
        Instru.setText("Instrumentos: " + MainActivity.usuario_logeado.getInstrumentos());
        Ubicacion.setText("Ubicacion: " + MainActivity.usuario_logeado.getUbicacion());

        String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/GetS?IdUsuario=" + MainActivity.usuario_logeado.getIdUsuario() + "&Nombre=" + MainActivity.usuario_logeado.getNombre();
        new PerfilUsuario.ConectarAPITask().execute(urlApi);
        LvSeguidos.setAdapter(adapterSeguidos);
    }

    public void volver (View vista)
    {
        Intent Activity = new Intent(getBaseContext(), MenuPrincipal.class);
        startActivity(Activity);
    }
    public void editar (View view)
    {
        Intent Activity = new Intent(getBaseContext(), EditarPerfil.class);
        startActivity(Activity);
    }
    public void mostrar (View view)
    {
        boolean visible = false;
        if (visible == false)
        {
            LvSeguidos.setVisibility(View.VISIBLE);
            visible = true;
        }
        else
        {
            LvSeguidos.setVisibility(View.INVISIBLE);
            visible = false;
        }
    }

    private class ConectarAPITask extends AsyncTask<String, Void, ArrayList<UsuariosBusqueda>> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected ArrayList<UsuariosBusqueda> doInBackground(String... params) {

            String urlApi = params[0];
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                Juan = parsearResultado(response.body().string());
                Log.d("Juanchi", Juan.toString());
                return Juan;
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "es null", Toast.LENGTH_LONG).show();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<UsuariosBusqueda> usuariosBusquedas) {
            super.onPostExecute(usuariosBusquedas);
            adapterSeguidos.notifyDataChanged(Juan);
        }

        private ArrayList<UsuariosBusqueda> parsearResultado(String respuesta) {
            if (respuesta == null || respuesta.length() == 0)
                return null;
            try {
                Gson gson = new Gson();

                return new ArrayList<>(Arrays.asList(gson.fromJson(respuesta, UsuariosBusqueda[].class)));
            } catch (Exception e) {
                return null;
            }

        }

    }
}