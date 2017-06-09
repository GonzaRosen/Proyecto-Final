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
import java.util.StringTokenizer;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CRUD {
    int idA;
    String NombreA, ApellidoA, UsuarioA, FechaNacA, EmailA, ContraseñaA;
        String urlDeApi = "http://beatlineproject.azurewebsites.net/api/persona";
    public void  GET (int id)
    {
        String urlGet = urlDeApi+ "id";
        new ConectarAPITask().execute("GET", urlGet);
    }
    public void POST (int Id, String Nombre, String Apellido, String Usuario, String FechaNac, String Email, String Contraseña)
    {
        Persona p = new Persona();
        p.setId(Integer.valueOf(Id));
        p.setNombre(Nombre);
        p.setApellido(Apellido);
        p.setUsuario(Usuario);
        p.setEmail(Email);
        p.setContraseña(Contraseña);
        p.setFechaNac(FechaNac);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(p));
        new ConectarAPITask().execute("POST",urlDeApi, gson.toJson(p));
    }


    private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected Persona doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];
            String Resultado;

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
            if (persona != null) {
                NombreA = persona.getNombre();
                FechaNacA = persona.getFechaNac();
                ApellidoA = persona.getApellido();
                UsuarioA = persona.getUsuario();
                EmailA = persona.getEmail();
                ContraseñaA = persona.getContraseña();
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
