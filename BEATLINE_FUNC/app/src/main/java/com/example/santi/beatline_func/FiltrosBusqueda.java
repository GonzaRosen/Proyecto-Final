//TODO: • Ordenamiento de los procesos; • Agregar descripcion a la Registracion/Mi perfil/Matching; • Dejar todo presentable (¿algún logo?); • Expandir lista (TUS MATCHEOS) ▬ {{VER MAS - USUARIO}}

package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class FiltrosBusqueda extends Activity {
    EditText Genero, Instrumento, Influencia, Ubicacion;
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView Logo;
    Button volver, buscar;
    LinearLayout layout;
    AdapterPersona adapterPersona;
    ListView lv;
    ArrayList<UsuariosBusqueda> Juan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_filtro);
        Genero = (EditText) findViewById(R.id.Genero);
        Instrumento = (EditText) findViewById(R.id.Instrumento);
        Influencia = (EditText) findViewById(R.id.Influencia);
        Ubicacion = (EditText) findViewById(R.id.Ubicacion);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.tvInstrumento);
        tv3 = (TextView) findViewById(R.id.tvInfluencia);
        tv4 = (TextView) findViewById(R.id.tvGenero);
        tv5 = (TextView) findViewById(R.id.tvUbicacion);
        Logo = (ImageView) findViewById(R.id.Logo);
        volver = (Button) findViewById(R.id.Volver);
        buscar = (Button) findViewById(R.id.Buscar);
        lv = (ListView) findViewById(R.id.Lista);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUbicacion = Ubicacion == null ? "" : Ubicacion.getText().toString();
                String strGenero = Genero == null ? "" : Genero.getText().toString();
                String strInstrumento = Instrumento == null ? "" : Instrumento.getText().toString();
                String strInfluencia = Influencia == null ? "" : Influencia.getText().toString();
                String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Get?Ubicacion=" + strUbicacion + "&Instrumentos=" + strInstrumento + "&Generos=" + strGenero + "&Influencias=" + strInfluencia;
                /*if (strUbicacion.length() > 0) {
                    urlApi+=  "Ubicacion=" + strUbicacion;
                }
                if (strUbicacion.length() == 0 && strGenero.length() != 0){
                    urlApi+= "Genero=" + strGenero;
                }
                else {
                    if (strGenero.length() != 0) {
                        urlApi += "&Genero=" + strGenero;
                    }
                }
                if (strGenero.length() == 0 && strUbicacion.length() == 0 && strInstrumento.length() != 0){
                    urlApi+= "Instrumento=" + strInstrumento;
                }
                else
                {
                    if (strInstrumento.length() != 0) {
                        urlApi += "&Instrumento=" + strInstrumento;
                    }
                }
                if (strUbicacion.length() == 0 && strGenero.length() == 0 && strInfluencia.length() == 0 && strInfluencia.length() != 0){
                    urlApi+= "Influencia=" + strInfluencia;
                }
                else
                {
                    if (strInfluencia.length() != 0) {
                        urlApi += "&Influencia=" + strInfluencia;
                    }
                }*/
                new ConectarAPITask().execute(urlApi);
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
                buscar.setVisibility(View.GONE);
                adapterPersona = new AdapterPersona(getApplicationContext(), new ArrayList<UsuariosBusqueda>());
                lv.setAdapter(adapterPersona);
            }
        });
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
                return Juan;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<UsuariosBusqueda> usuariosBusquedas) {
            super.onPostExecute(usuariosBusquedas);

            adapterPersona.notifyDataChanged(Juan);
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

    public void Volver (View vista)
    {
        Intent Activity = new Intent(getBaseContext(), MenuPrincipal.class);
        startActivity(Activity);
    }
}

