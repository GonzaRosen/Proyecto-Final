package com.example.santi.beatline_func;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.io.IOException;
import java.net.ResponseCache;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CRUD {
        String urlDeApi = "http://templateapiort.azurewebsites.net/api/persona/";
    public void  GET (int id)
    {
        String urlGet = urlDeApi+ "id";
        new ConectarAPITask().execute("GET", urlGet);
    }
    public void POST (int id)
    {
        Persona p = new Persona();
        p.setId(Integer.valueOf(id_vw.getText().toString()));
        p.setNombre(nombre_vw.getText().toString());
        p.setFechaNac(fechanac_vw.getText().toString());
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(p));

        new ConectarAPITask().execute("POST",urlDeApi, gson.toJson(p));
    }



                break;
            case R.id.btnPut:
                Log.d("Push", "put");
                break;
            case R.id.btnDelete:
                Log.d("Push", "delete");
                break;
            case R.id.viewAll:
                Log.d("Get All", urlDeApi);
                FragmentManager fm = getFragmentManager();
                ViewAllFragment viewAllFragment = new ViewAllFragment();
                viewAllFragment.show(fm,"View all people");
                break;

        }
    }


    private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected Persona doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];
            String resultado;

            if (method.equals("GET")) {
                return  getPersona(urlApi);
            }


            if (method.equals("POST")) {
                String json = params[2];
                postPersona(urlApi, json);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Persona persona) {
            super.onPostExecute(persona);
            //Log.d("ope :",persona.getNombre());
            if (persona != null) {
                nombre_vw.setText(persona.getNombre());
                fechanac_vw.setText(persona.getFechaNac());
            }

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

        private Persona getPersona(String urlApi) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                Persona persona = parsearResultado(response.body().string());
                return persona;
            }
            catch (IOException e){
                Log.d("Error :", e.getMessage());
                return null;

            }
        }


        private Persona parsearResultado(String respuesta)   {
            if (respuesta == null || respuesta.length()==0)
                return null;

            Log.d("Respuesta:", respuesta);

            try {
                Gson gson = new Gson();
                Persona p = gson.fromJson(respuesta, Persona.class);
                Log.d("Persona nombre:", p.getNombre());
                return p;

            }
            catch (Exception e) {
                Log.d("Error :", e.getMessage());
                return null;
            }

        }

    }
}
