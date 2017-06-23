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
    TextView Desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilusu);

        Intent intent = getIntent();

        ImageView retrato = (ImageView) findViewById(R.id.retrato);
        retrato.setImageResource(R.drawable.retrato_u);

        TextView txt1 = (TextView) findViewById(R.id.nombrechiquito);
        TextView txt2 = (TextView) findViewById(R.id.nombregrande);
        txt1.bringToFront();

        ImageView cuadrodesc = (ImageView) findViewById(R.id.recuadro);
        cuadrodesc.setImageResource(R.drawable.cuadro);

        Desc = (TextView) findViewById(R.id.Descripcion);
        //Persona oPersona = new Persona();
        //Desc.setText(oPersona.getNombre());

        String urlDeApi = "http://templateapiort.azurewebsites.net/api/persona";

            String urlGet = urlDeApi + "/4"               ;
            new ConectarAPITask().execute("GET", urlGet);
    }

        private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
            public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

            @Override
            protected void onPostExecute(Persona p) {
                super.onPostExecute(p);
                Desc.setText(p.getNombre());
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
                    Persona p = gson.fromJson(respuesta, Persona.class);
                    return p;

                }
                catch (Exception e) {
                    return null;
                }

            }

        }
    }
