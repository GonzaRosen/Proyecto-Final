package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistracionUsuario extends Activity {

    EditText Nombre;
    EditText Apellido;
    EditText Usuario;
    CalendarView FechaNac;
    EditText Email;
    EditText Pass;

    CheckBox Terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_regusu);

            Terminos = (CheckBox)findViewById(R.id.ChkBox);
            Nombre = (EditText)findViewById(R.id.Nombre);
            Apellido = (EditText)findViewById(R.id.Apellido);
            Usuario = (EditText)findViewById(R.id.Usuario);
            FechaNac = (CalendarView)findViewById(R.id.FechaNac);
            Email = (EditText)findViewById(R.id.Email);
            Pass = (EditText)findViewById(R.id.Contraseña);


        }
    }

        public void Volver (View view)
    {
        Intent Activity;
        Activity = new Intent(this,MainActivity.class);
        startActivity(Activity);
    }

    public void Continuar (View view) {

        String Edit1 = Nombre.getText().toString();
        String Edit2 = Apellido.getText().toString();
        String Edit3 = Usuario.getText().toString();
        String Edit4 = Email.getText().toString();
        String Edit5 = Pass.getText().toString();

        if(!(Edit1.equals("") || Edit2.equals("") || Edit3.equals("") || Edit4.equals("") || Edit5.equals("")))
        {
            if (Terminos.isChecked())
            {
                String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/post";
                Persona p = new Persona();
                p.setNombre(Edit1);
                p.setApellido(Edit2);
                p.setEmail(Edit4);
                //p.setFechaNac(FechaNac.getDate());
                p.setContraseña(Edit5);

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
                toast1 = Toast.makeText(RegistracionUsuario.this, "Debe aceptar los terminos y condiciones para completar la registración", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(RegistracionUsuario.this, "No deje campos en blanco", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected void onPostExecute(Persona persona) {
            super.onPostExecute(persona);
            super.onPostExecute(persona);
            //Log.d("ope :",persona.getNombre());
            if (persona != null) {

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
            String Resultado;

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