package com.example.santi.beatline_func;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PerfilUsuario extends Activity{
    TextView NombreG;
    TextView NombreC;
    TextView Desc;
    TextView EdadG;
    TextView EdadC;
    TextView Genero;
    TextView Influ;
    TextView Instru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilusu);

        Intent intent = getIntent();

        TextView txt1 = (TextView) findViewById(R.id.nombrechiquito);
        TextView txt2 = (TextView) findViewById(R.id.nombregrande);
        txt1.bringToFront();

        ImageView cuadrodesc = (ImageView) findViewById(R.id.recuadro);
        cuadrodesc.setImageResource(R.drawable.cuadro);

        NombreG = (TextView) findViewById(R.id.nombregrande);
        NombreC = (TextView) findViewById(R.id.nombrechiquito);
        Desc = (TextView) findViewById(R.id.Descripcion);
        EdadG = (TextView) findViewById(R.id.edadgrande);
        EdadC = (TextView) findViewById(R.id.edadchiquito);
        Influ = (TextView) findViewById(R.id.influsu);
        Genero = (TextView) findViewById(R.id.generousu);
        Instru = (TextView) findViewById(R.id.instrusu);

        //Persona oPersona = new Persona();
        //Desc.setText(oPersona.getNombre());

        String urlDeApi = "http://beatlineproject.azurewebsites.net/api/usuarios";

            String urlGet = urlDeApi + "/Get?Nombre=Santiago";
            new ConectarAPITask().execute("GET", urlGet);
    }

        private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
            public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            @Override
            protected void onPostExecute(Persona p) {
                super.onPostExecute(p);
                NombreG.setText(p.getNombre());
                NombreC.setText(p.getNombre());
                EdadG.setText(p.getFechaNac());
                EdadC.setText(p.getFechaNac());
                Desc.setText(p.getDescripcion());
                Influ.setText(p.getInfluencias());
                Genero.setText(p.getDescripcion());
                Instru.setText(p.getDescripcion());
            }

            @Override
            protected Persona doInBackground(String... params) {

                String method = params[0];
                String urlApi = params[1];
                String Resultado;

                if (method.equals("GET")) {
                    return  getPersona(urlApi);
                }
                return null;
            }

            private Persona getPersona(String urlApi) {
    String strResultado;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(urlApi)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    strResultado = response.body().string();
                    Log.d("Santi", strResultado );
                    Persona p = parsearResultado(strResultado );
                    return p;
                }
                catch (IOException e){
                    return null;

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
