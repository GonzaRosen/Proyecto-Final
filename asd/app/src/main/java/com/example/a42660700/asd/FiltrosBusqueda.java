package com.example.a42660700.asd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.a42660700.asd.R;

public class FiltrosBusqueda extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_filtro);
    }
    public void Buscar(View vista)
    {
        Toast toast1;
        toast1 = Toast.makeText(FiltrosBusqueda.this, "Comienza la busqueda", Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void link1(View Vista)
    {
        Toast toast1;
        toast1 = Toast.makeText(FiltrosBusqueda.this, "Vista agregar instrumento nuevo", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void link2(View Vista)
    {
        Toast toast1;
        toast1 = Toast.makeText(FiltrosBusqueda.this, "Vista agregar género nuevo", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void Volver (View vista)
    {
        Intent Activity;
        Activity = new Intent(this, MainActivity.class);
        startActivity(Activity);
    }
}

