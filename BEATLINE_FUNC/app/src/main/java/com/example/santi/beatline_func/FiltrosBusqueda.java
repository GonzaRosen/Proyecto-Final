package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class FiltrosBusqueda extends Activity {
    EditText Generos, Instrumentos, Influencias;
    Spinner SpInstrumentos, SpInfluencias, SpUbicacion, SpGenero;
    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView Logo;
    Button volver, buscar;
    LinearLayout layout;
    AdapterPersona adapterPersona;
    ListView lv;
    ArrayList<UsuariosBusqueda> Juan = new ArrayList<>();
    ArrayAdapter<String> AdapterIns, AdapterGen, AdapterInf, AdapterUbi;
    Seguir seguir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_filtro);
        Generos = (EditText) findViewById(R.id.Genero);
        Instrumentos = (EditText) findViewById(R.id.Instrumento);
        Influencias = (EditText) findViewById(R.id.Influencia);
        SpInstrumentos = (Spinner) findViewById(R.id.spInstrumentos);
        SpGenero = (Spinner) findViewById(R.id.spGeneros);
        SpInfluencias = (Spinner) findViewById(R.id.spInfluencias);
        SpUbicacion = (Spinner) findViewById(R.id.spUbicacion);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.tvInstrumento);
        tv3 = (TextView) findViewById(R.id.tvInfluencia);
        tv4 = (TextView) findViewById(R.id.tvGenero);
        tv5 = (TextView) findViewById(R.id.tvUbicacion);
        Logo = (ImageView) findViewById(R.id.Logo);
        volver = (Button) findViewById(R.id.Volver);
        buscar = (Button) findViewById(R.id.Buscar);
        lv = (ListView) findViewById(R.id.Lista);

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

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUbicacion = SpUbicacion.getSelectedItem().toString();
                String strGenero = Generos == null ? "" : Generos.getText().toString();
                String strInstrumento = Instrumentos == null ? "" : Instrumentos.getText().toString();
                String strInfluencia = Influencias == null ? "" : Influencias.getText().toString();
                String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Get?Ubicacion=" + strUbicacion + "&Instrumentos=" + strInstrumento + "&Generos=" + strGenero + "&Influencias=" + strInfluencia;
                new ConectarAPITask().execute(urlApi);
                Generos.setVisibility(View.GONE);
                Instrumentos.setVisibility(View.GONE);
                Influencias.setVisibility(View.GONE);
                tv1.setVisibility(View.GONE);
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                tv4.setVisibility(View.GONE);
                tv5.setVisibility(View.GONE);
                Logo.setVisibility(View.GONE);
                buscar.setVisibility(View.GONE);
                SpInstrumentos.setVisibility(View.GONE);
                SpInfluencias.setVisibility(View.GONE);
                SpGenero.setVisibility(View.GONE);
                SpUbicacion.setVisibility(View.GONE);

                adapterPersona = new AdapterPersona(getApplicationContext(), new ArrayList<UsuariosBusqueda>(),new BtnClickListener() {
                    @Override
                    public void onBtnClick(int position) {
                        // Call your function which creates and shows the dialog here
                        int id =adapterPersona.getId(position);
                        String urlSeguir = "http://thebealineproject.azurewebsites.net/api/usuarios/PostIUHU";
                        seguir.setIdSeguido(id);
                        seguir.setIdUsuario(MainActivity.usuario_logeado.getIdUsuario());
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        System.out.println(gson.toJson(seguir));
                        new FiltrosBusqueda.SeguirUsuario().execute("POST",urlSeguir, gson.toJson(seguir));
                        Toast.makeText(getApplicationContext(), "Mi id: " + MainActivity.usuario_logeado.getIdUsuario() + ", id a seguir: " + id, Toast.LENGTH_LONG).show();
                    }});
                lv.setAdapter(adapterPersona);
            }
        });

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

    private class SeguirUsuario extends AsyncTask<String, Void, String> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected String doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];
            String Resultado;

            if (method.equals("POST")) {
                String json = params[2];
                postPersona(urlApi, json);
            }
            return null;
        }

        private String postPersona(String urlApi, String json) {
            String strResultado;
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(urlApi)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                strResultado = response.body().string();
                return strResultado;
            } catch (IOException e) {
                Log.d("Error :", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);
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

    public void Volver (View vista)
    {
        Intent Activity = new Intent(getBaseContext(), MenuPrincipal.class);
        startActivity(Activity);
    }
}