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

                String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/APILOGIN";
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

    private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected void onPostExecute(Persona p, Preferencias pre) {
            super.onPostExecute(p);
            super.onPostExecute(pre);
            MainActivity.usuario_logeado.setLogin(p.getLogin());
            String Mail = Email.getText().toString();
            String Contraseña = Password.getText().toString();
            if (p.getLogin() == true)
            {
                MainActivity.usuario_logeado.setNombre(p.getNombre());
                MainActivity.usuario_logeado.setApellido(p.getApellido());
                MainActivity.usuario_logeado.setFechaNac(p.getFechaNac());
                MainActivity.usuario_logeado.setDescripcion(p.getDescripcion());
                MainActivity.usuario_logeado.setInfluencias(p.getInfluencias());
                MainActivity.usuario_logeado.setGenero(p.getGenero());
                MainActivity.usuario_logeado.setInstrumentos(p.getInstrumentos());
                MainActivity.usuario_logeado.setEmail(Mail);
                MainActivity.usuario_logeado.setContraseña(Contraseña);
                MainActivity.preferencias_usuario.setGeneros();
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

        @Override
        protected Persona doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];

            if (method.equals("POST")) {
                String json = params[2];
                postPersona(urlApi, json);
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
