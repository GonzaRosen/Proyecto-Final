package com.example.santi.beatline_func;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistracionPreferencias extends AppCompatActivity{

    EditText Genero;
    EditText Influencia;
    EditText Instrumento;
    EditText Descripcion;
    String Nombre, Apellido, Email, Pass, Ubicacion;
    Spinner SpInstrumentos, SpGeneros, SpInfluencias;
    ArrayAdapter<String> AdapterIns, AdapterGen, AdapterInf;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracion_preferencias);

        Bundle bundle = this.getIntent().getExtras();
        Nombre = bundle.getString("Nombre");
        Apellido = bundle.getString("Apellido");
        Email = bundle.getString("Email");
        Pass = bundle.getString("Pass");
        Ubicacion = bundle.getString("Ubicacion");

        Genero = (EditText)findViewById(R.id.gene);
        Influencia = (EditText)findViewById(R.id.influ);
        Instrumento = (EditText)findViewById(R.id.instru);
        Descripcion = (EditText)findViewById(R.id.desc);

        Genero.setEnabled(false);
        Instrumento.setEnabled(false);
        Influencia.setEnabled(false);

        SpInstrumentos = (Spinner)findViewById(R.id.spInstrumento);
        SpInfluencias = (Spinner)findViewById(R.id.spInfluencia);
        SpGeneros = (Spinner)findViewById(R.id.spGenero);

        AdapterIns = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosIns);
        AdapterGen = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosGen);
        AdapterInf = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosInf);

        AdapterIns.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AdapterGen.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AdapterInf.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        SpInstrumentos.setAdapter(AdapterIns);
        SpGeneros.setAdapter(AdapterGen);
        SpInfluencias.setAdapter(AdapterInf);


        SpInstrumentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = SpInstrumentos.getSelectedItemPosition();
                String text = SpInstrumentos.getItemAtPosition(pos).toString();
                Instrumento.setText(Instrumento.getText() + " " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpGeneros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = SpGeneros.getSelectedItemPosition();
                String text = SpGeneros.getItemAtPosition(pos).toString();
                Genero.setText(Genero.getText() + " " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpInfluencias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = SpInfluencias.getSelectedItemPosition();
                String text = SpInfluencias.getItemAtPosition(pos).toString();
                Influencia.setText(Influencia.getText() + " " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void registrar(View view)
    {
        String strGenero = Genero.getText().toString();
        String strInfluencia = Influencia.getText().toString();
        String strInstrumento = Instrumento.getText().toString();
        String strDescripcion = Descripcion.getText().toString();

        if(!(strGenero.equals("") || strInfluencia.equals("") || strInstrumento.equals("") || strDescripcion.equals("")))
        {
            String urlApi = "http://10.0.3.2:8080/api/usuarios/Post";
            Persona p = new Persona();
            p.setGenero(strGenero);
            p.setInfluencias(strInfluencia);
            p.setInstrumentos(strInstrumento);
            p.setDescripcion(strDescripcion);
            p.setNombre(Nombre);
            p.setApellido(Apellido);
            p.setEmail(Email);
            p.setContraseña(Pass);
            p.setUbicacion(Ubicacion);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(p));
            new ConectarAPITask().execute("POST",urlApi, gson.toJson(p));
            Toast.makeText(getBaseContext(), "¡Registración completada exitosamente!", Toast.LENGTH_LONG).show();
            Intent Activity;
            Activity = new Intent(this,MainActivity.class);
            startActivity(Activity);
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(RegistracionPreferencias.this, "No deje campos en blanco.", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    private class ConectarAPITask extends AsyncTask<String, Void,  Persona> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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
        protected void onPostExecute(Persona persona) {
            super.onPostExecute(persona);
            if (persona != null) {

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
