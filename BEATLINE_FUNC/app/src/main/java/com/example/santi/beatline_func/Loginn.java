package com.example.santi.beatline_func;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Loginn extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Boolean coincide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);
        Email = (EditText)findViewById(R.id.Email);
        Password = (EditText)findViewById(R.id.Contraseña);
    }

    public void Login (View view) {
        String Mail = Email.getText().toString();
        String Contraseña = Password.getText().toString();

        if(!(Mail.equals("") || Contraseña.equals("")))
        {
            if (MainActivity.usuario_logeado.getLogin() == true)
            {
                String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Get2";
                Persona p = new Persona();
                p.setEmail(Mail);
                p.setContraseña(Contraseña);
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                System.out.println(gson.toJson(p));
                new Loginn.ConectarAPITask().execute("POST",urlApi, gson.toJson(p));
                Intent Activity;
                Activity = new Intent(this,MainActivity.class);
                startActivity(Activity);
            }
            else
            {
                Toast toast1;
                toast1 = Toast.makeText(Loginn.this, "Usuario y/o contraseña incorrectos.", Toast.LENGTH_SHORT);
                toast1.show();
                Password.setText("");
            }
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(Loginn.this, "No deje campos en blanco.", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    ArrayList<Persona> parseResultGSON(String resultado) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Persona[] arr = gson.fromJson(resultado, Persona[].class);
        return new ArrayList<>(Arrays.asList(arr));
    }

    private class ConectarAPITask extends AsyncTask<String, Void, ArrayList<Persona>> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected void onPostExecute(ArrayList<Persona> p) {
            super.onPostExecute(p);
<<<<<<< HEAD
            MainActivity.usuario_logeado.setLogin(p.get(0).getLogin());
=======
            MainActivity.usuario_logeado.setLogin(p.getLogin());
>>>>>>> 731539760113f1fdecf58b5b58dfc6cc27809810
            String Mail = Email.getText().toString();
            String Contraseña = Password.getText().toString();
            if (p.get(0).getLogin() == true)
            {
                MainActivity.usuario_logeado.setNombre(p.get(0).getNombre());
                MainActivity.usuario_logeado.setApellido(p.get(0).getApellido());
                MainActivity.usuario_logeado.setFechaNac(p.get(0).getFechaNac());
                MainActivity.usuario_logeado.setDescripcion(p.get(0).getDescripcion());
                MainActivity.usuario_logeado.setInfluencias(p.get(0).getInfluencias());
                MainActivity.usuario_logeado.setGenero(p.get(0).getGenero());
                MainActivity.usuario_logeado.setInstrumentos(p.get(0).getInstrumentos());
                MainActivity.usuario_logeado.setEmail(Mail);
                MainActivity.usuario_logeado.setContraseña(Contraseña);
                MainActivity.usuario_logeado.setTodosGeneros(p.get(0).getTodosInstrumentos());
                MainActivity.usuario_logeado.setTodosInstrumentos(p.get(0).getTodosInstrumentos());
                MainActivity.usuario_logeado.setTodosInfluencias(p.get(0).getTodosInstrumentos());
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
                String strResultado = response.body().string();
                ArrayList<Persona> p = parseResultGSON(strResultado);
            } catch (IOException e) {
                Log.d("Error :", e.getMessage());
                return;

            }
        }

        @Override
        protected ArrayList<Persona> doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];

            if (method.equals("POST")) {
                String json = params[2];
                postPersona(urlApi, json);
            }
            return null;
        }

        /*private ArrayList<Persona> getPersona(String urlApi) {
            String strResultado;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                strResultado = response.body().string();
                ArrayList<Persona> p = parseResultGSON(strResultado);
                return p;
            }
            catch (IOException e){
                return null;

            }
        }*/

        /*private Persona parsearResultado(String respuesta)   {
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
        }*/
        }
    }

