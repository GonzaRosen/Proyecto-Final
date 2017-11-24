package com.example.santi.beatline_func;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PerfilUsuario extends Activity{
    TextView Nomape;
    TextView Desc;
    TextView Genero;
    TextView Influ;
    TextView Instru;
    TextView Ubicacion;
    ArrayList<UsuariosBusqueda> Juan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilusu);
        Intent intent = getIntent();

        Nomape = (TextView) findViewById(R.id.nomape);
        Desc = (TextView) findViewById(R.id.descripcion);
        Influ = (TextView) findViewById(R.id.influencias);
        Genero = (TextView) findViewById(R.id.generos);
        Instru = (TextView) findViewById(R.id.instrumentos);
        Ubicacion = (TextView) findViewById(R.id.Ubicacion);

        Nomape.setText(MainActivity.usuario_logeado.getNombre() + " " + MainActivity.usuario_logeado.getApellido());
        Desc.setText("Descripción: " + MainActivity.usuario_logeado.getDescripcion());
        Influ.setText("Influencias: " + MainActivity.usuario_logeado.getInfluencias());
        Genero.setText("Géneros: " + MainActivity.usuario_logeado.getGenero());
        Instru.setText("Instrumentos: " + MainActivity.usuario_logeado.getInstrumentos());
        Ubicacion.setText("Ubicacion: " + MainActivity.usuario_logeado.getUbicacion());
    }

    public void volver (View vista)
    {
        Intent Activity = new Intent(getBaseContext(), MenuPrincipal.class);
        startActivity(Activity);
    }
    public void editar (View view)
    {
        Intent Activity = new Intent(getBaseContext(), EditarPerfil.class);
        startActivity(Activity);
    }
}