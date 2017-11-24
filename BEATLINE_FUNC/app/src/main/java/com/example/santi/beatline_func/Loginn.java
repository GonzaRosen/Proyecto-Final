package com.example.santi.beatline_func;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Loginn extends AppCompatActivity {

    EditText Email;
    EditText Password;

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
        Persona p = new Persona();
        if(!(Mail.equals("") || Contraseña.equals("")))
        {
            String urlApi = "http://thebealineproject.azurewebsites.net/api/usuarios/Get2" + "?Email=" + Mail + "&Password=" + Contraseña;
            new Loginn.ConectarAPITask().execute("GET",urlApi);
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(Loginn.this, "No deje campos en blanco.", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }

    /*ArrayList<Persona> parseResultGSON(String resultado) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Persona[] arr = gson.fromJson(resultado, Persona[].class);
        return new ArrayList<>(Arrays.asList(arr));
    }
*/
    private class ConectarAPITask extends AsyncTask<String, Void, String> {
        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected String doInBackground(String... params) {

            String method = params[0];
            String urlApi = params[1];

            if (method.equals("GET")) {
                //String json = params[2];
                return getPersona(urlApi);
            }
            return null;
        }


        private String getPersona(String urlApi) {
            String strResultado;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                strResultado = response.body().string();

                return strResultado;
            } catch (IOException e) {
                return null;

            }
        }


        private Persona postPersona(String urlApi, String json) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(urlApi)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String strResultado = response.body().string();
                return parsearResultado(strResultado);
            } catch (IOException e) {
                Log.d("Error :", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String res) {
            super.onPostExecute(res);


            if (res.compareTo("") != 0)
            {
                Persona p = parsearResultado(res);
                String Mail = Email.getText().toString();
                String Contraseña = Password.getText().toString();
                MainActivity.usuario_logeado.setNombre(p.getNombre());
                MainActivity.usuario_logeado.setApellido(p.getApellido());
                MainActivity.usuario_logeado.setFechaNac(p.getFechaNac());
                MainActivity.usuario_logeado.setDescripcion(p.getDescripcion());
                MainActivity.usuario_logeado.setInfluencias(p.getInfluencias());
                MainActivity.usuario_logeado.setGenero(p.getGenero());
                MainActivity.usuario_logeado.setInstrumentos(p.getInstrumentos());
                MainActivity.usuario_logeado.setEmail(Mail);
                MainActivity.usuario_logeado.setContraseña(Contraseña);
                MainActivity.usuario_logeado.setUbicacion(p.getUbicacion());
                MainActivity.usuario_logeado.setDescripcion(p.getDescripcion());
                MainActivity.usuario_logeado.setIdUsuario(p.getIdUsuario());
                Intent intent = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(intent);
            }
            else
            {

                Toast.makeText(Loginn.this, "Usuario y/o contraseña incorrectos, compruebe sus credenciales", Toast.LENGTH_SHORT).show();
                Password.setText("");
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

    public void regis (View view)
    {
        Intent Activity = new Intent(getBaseContext(),RegistracionUsuario.class);
        startActivity(Activity);
    }
}

