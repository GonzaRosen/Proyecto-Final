package com.example.santi.beatline_func;

import android.content.Intent;
import android.media.UnsupportedSchemeException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class EditarPerfil extends AppCompatActivity {

    EditText Nombre, Apellido, Descripcion, Influencias, Generos, Instrumentos;
    Spinner SpInstrumentos, SpInfluencias, SpUbicacion, SpGenero;
    ArrayAdapter<String> AdapterIns, AdapterGen, AdapterInf, AdapterUbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        Nombre = (EditText) findViewById(R.id.ENombre);
        Apellido = (EditText) findViewById(R.id.EApellido);
        Descripcion = (EditText) findViewById(R.id.EDescripcion);
        Influencias = (EditText) findViewById(R.id.EInfluencias);
        Generos = (EditText) findViewById(R.id.EGeneros);
        Instrumentos = (EditText) findViewById(R.id.EInstrumentos);

        Nombre.setText(MainActivity.usuario_logeado.getNombre());
        Apellido.setText(MainActivity.usuario_logeado.getApellido());
        Descripcion.setText(MainActivity.usuario_logeado.getDescripcion());
        Instrumentos.setText(MainActivity.usuario_logeado.getInstrumentos());
        Influencias.setText(MainActivity.usuario_logeado.getInfluencias());
        Generos.setText(MainActivity.usuario_logeado.getGenero());

        SpInstrumentos = (Spinner) findViewById(R.id.spInstru);
        SpGenero = (Spinner) findViewById(R.id.spGen);
        SpInfluencias = (Spinner) findViewById(R.id.spInf);
        SpUbicacion = (Spinner) findViewById(R.id.spUbi);

        Generos.setEnabled(false);
        Instrumentos.setEnabled(false);
        Influencias.setEnabled(false);

        AdapterIns = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosIns);
        AdapterGen = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosGen);
        AdapterInf = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosInf);
        AdapterUbi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosUbi);

        AdapterIns.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AdapterGen.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AdapterInf.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        AdapterUbi.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        SpInstrumentos.setAdapter(AdapterIns);
        SpGenero.setAdapter(AdapterGen);
        SpInfluencias.setAdapter(AdapterInf);
        SpUbicacion.setAdapter(AdapterUbi);

        SpInstrumentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = SpInstrumentos.getSelectedItemPosition();
                String text = SpInstrumentos.getItemAtPosition(pos).toString();
                Instrumentos.setText(Instrumentos.getText() + " " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = SpGenero.getSelectedItemPosition();
                String text = SpGenero.getItemAtPosition(pos).toString();
                Generos.setText(Generos.getText() + " " + text);
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
                Influencias.setText(Influencias.getText() + " " + text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*SpUbicacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = SpUbicacion.getSelectedItemPosition();
                String text = SpUbicacion.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    public void limpiar (View view)
    {
        Button btn = (Button)view;

        switch (btn.getId())
        {
            case R.id.bins: Instrumentos.setText("");
            break;
            case R.id.bgen: Generos.setText("");
            break;
            case R.id.binf: Influencias.setText("");
            break;
        }
    }

    public void editarperfil (View view)
    {
        String strGeneros = Generos.getText().toString();
        String strInfluencias = Influencias.getText().toString();
        String strInstrumentos = Instrumentos.getText().toString();
        String strDescripcion = Descripcion.getText().toString();
        String strUbicacion = SpUbicacion.getSelectedItem().toString();
        String strNombre = Nombre.getText().toString();
        String strApellido = Apellido.getText().toString();

        if(!(strGeneros.equals("") || strInfluencias.equals("") || strInstrumentos.equals("") || strDescripcion.equals("") || strUbicacion.equals("")) || strNombre.equals("") || strApellido.equals(""))
        {
            MainActivity.usuario_logeado.setNombre(strNombre);
            MainActivity.usuario_logeado.setApellido(strApellido);
            MainActivity.usuario_logeado.setInstrumentos(strInstrumentos);
            MainActivity.usuario_logeado.setGenero(strGeneros);
            MainActivity.usuario_logeado.setInfluencias(strInfluencias);
            MainActivity.usuario_logeado.setDescripcion(strDescripcion);
            MainActivity.usuario_logeado.setUbicacion(strUbicacion);
            String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Pepe";
            Editar p = new Editar();
            p.setGenero(strGeneros);
            p.setInfluencias(strInfluencias);
            p.setInstrumentos(strInstrumentos);
            p.setDescripcion(strDescripcion);
            p.setNombre(strNombre);
            p.setApellido(strApellido);
            p.setUbicacion(strUbicacion);
            p.setIdUsuario(MainActivity.usuario_logeado.getIdUsuario());
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            System.out.println(gson.toJson(p));
            new EditarPerfil.ConectarAPITask().execute("PUT",urlApi, gson.toJson(p));
            Toast.makeText(getBaseContext(), "¡Se han guardado sus cambios!", Toast.LENGTH_LONG).show();
            Intent Activity;
            Activity = new Intent(this,PerfilUsuario.class);
            startActivity(Activity);
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(EditarPerfil.this, "No deje campos en blanco.", Toast.LENGTH_SHORT);
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

            if (method.equals("PUT")) {
                String json = params[2];
                postPersona(urlApi, json);
                Log.d("a",method + " , " + urlApi);
            }
            return null;
        }

        private void postPersona(String urlApi, String json) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(urlApi)
                    .put(body)
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
