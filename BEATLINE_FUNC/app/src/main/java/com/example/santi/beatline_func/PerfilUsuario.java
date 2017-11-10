package com.example.santi.beatline_func;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PerfilUsuario extends Activity{
    TextView Nombre;
    TextView Apellido;
    TextView Desc;
    TextView FechaNac;
    TextView Genero;
    TextView Influ;
    TextView Instru;
    TextView Ubicacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilusu);
        Intent intent = getIntent();

        Nombre = (TextView) findViewById(R.id.nombre);
        Apellido = (TextView) findViewById(R.id.apellido);
        Desc = (TextView) findViewById(R.id.descripcion);
        FechaNac = (TextView) findViewById(R.id.edad);
        Influ = (TextView) findViewById(R.id.influencias);
        Genero = (TextView) findViewById(R.id.generos);
        Instru = (TextView) findViewById(R.id.instrumentos);
        Ubicacion = (TextView) findViewById(R.id.Ubicacion);

        Nombre.setText("Nombre: " + MainActivity.usuario_logeado.getNombre());
        Apellido.setText("Apellido: " + MainActivity.usuario_logeado.getApellido());
        FechaNac.setText("Fecha de Nacimiento: " + MainActivity.usuario_logeado.getFechaNac());
        Desc.setText("Descripción: " + MainActivity.usuario_logeado.getDescripcion());
        Influ.setText("Influencias: " + MainActivity.usuario_logeado.getInfluencias());
        Genero.setText("Géneros: " + MainActivity.usuario_logeado.getGenero());
        Instru.setText("Instrumentos: " + MainActivity.usuario_logeado.getInstrumentos());
        Ubicacion.setText("Ubicacion: " + MainActivity.usuario_logeado.getUbicacion());
    }
}