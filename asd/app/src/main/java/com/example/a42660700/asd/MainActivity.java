package com.example.a42660700.asd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a42660700.asd.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Regusu (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), RegistracionUsuario.class);
        startActivity(Activity);

    }
    public void Filtros (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), FiltrosBusqueda.class);
        startActivity(Activity);
    }
    public void PerfilU (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), PerfilUsuario.class);
        startActivity(Activity);
    }
}
