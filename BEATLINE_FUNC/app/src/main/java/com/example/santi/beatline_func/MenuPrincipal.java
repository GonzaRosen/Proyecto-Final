package com.example.santi.beatline_func;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends Activity {

    Button Perfil, Buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void Perfil (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), PerfilUsuario.class);
        startActivity(Activity);
    }

    public void Buscar (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), FiltrosBusqueda.class);
        startActivity(Activity);
    }

    public void Cerrar (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), Loginn.class);
        startActivity(Activity);
    }
}
