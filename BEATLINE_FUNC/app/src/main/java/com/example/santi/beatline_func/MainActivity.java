package com.example.santi.beatline_func;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static Persona usuario_logeado = new Persona();
    public static UsuariosBusqueda usuarios = new UsuariosBusqueda();

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

    public void Login (View view)
    {
        Intent Activity = new Intent(getApplicationContext(), Loginn.class);
        startActivity(Activity);
    }
}
