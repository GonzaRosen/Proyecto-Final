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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistracionUsuario extends Activity {

    Spinner spinnerdia,spinnermes,spinneraño;

    String [] opcDia = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String [] opcMes = new String[] {"Enero", "Febrero", "Marzo", "Mayo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String [] opcAño = new String[] {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000"};
    String[] dias,meses,años;
    private boolean firstTime = true;

    EditText Nombre;
    EditText Apellido;
    EditText Usuario;
    Spinner Dia;
    Spinner Mes;
    Spinner Año;
    EditText Email;
    EditText Pass;

    CheckBox Terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            Terminos = (CheckBox)findViewById(R.id.ChkBox);
            Nombre = (EditText)findViewById(R.id.Nombre);
            Apellido = (EditText)findViewById(R.id.Apellido);
            Usuario = (EditText)findViewById(R.id.Usuario);
            Dia = (Spinner) findViewById(R.id.SpinnerDia);
            Mes = (Spinner)findViewById(R.id.SpinnerMes);
            Año = (Spinner)findViewById(R.id.SpinnerAño);
            Email = (EditText)findViewById(R.id.Email);
            Pass = (EditText)findViewById(R.id.Contraseña);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_regusu);
            Intent Activity = getIntent();
            spinnerdia = (Spinner) findViewById(R.id.SpinnerDia);
            dias = getResources().getStringArray(R.array.dias);
            ArrayAdapter<String> aaDia = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, dias);
            aaDia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerdia.setAdapter(aaDia);
            spinnerdia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                     @Override
                                                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                         if (firstTime = true)
                                                         {
                                                             firstTime = false;
                                                         }
                                                         else{
                                                             Toast.makeText(getApplicationContext(), dias[position], Toast.LENGTH_LONG).show();
                                                         }
                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> parent)
                                                     {

                                                     }
                                                 }
            );


            spinnermes = (Spinner) findViewById(R.id.SpinnerMes);
            meses = getResources().getStringArray(R.array.meses);
            ArrayAdapter<String> aaMes = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, meses);
            aaMes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnermes.setAdapter(aaMes);
            spinnermes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (firstTime = true)
                    {
                        firstTime = false;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), meses[position], Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });


            spinneraño = (Spinner) findViewById(R.id.SpinnerAño);
            años = getResources().getStringArray(R.array.años);
            ArrayAdapter<String> aaAño = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, años);
            aaAño.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnermes.setAdapter(aaAño);
            spinnermes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (firstTime = true)
                    {
                        firstTime = false;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), años[position], Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {
                }
            });
        }
    }

        public void Volver (View view)
    {
        Intent Activity;
        Activity = new Intent(this,MainActivity.class);
        startActivity(Activity);
    }

        public void Grabar (View view) {

            if (Terminos.isChecked())
            {
            String urlApi = "http://beatlineproject.azurewebsites.net/api/usuarios";
            String urlPost = urlApi + "";

            Persona p = new Persona();
            p.setNombre(.getText().ToString());
                p.setApellido().getText().toString());
            p.(.getText().ToString());
                p.(.getText().ToString());
                p.(.getText().ToString());
                p.(.getText().ToString());



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
