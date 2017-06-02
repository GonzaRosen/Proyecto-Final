package com.example.santi.beatline_func;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class PerfilUsuario extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_perfilusu);

        Intent intent = getIntent();

        ImageView retrato = (ImageView) findViewById(R.id.retrato);
        retrato.setImageResource(R.drawable.retrato_u);

        TextView txt1 = (TextView) findViewById(R.id.nombrechiquito);
        TextView txt2 = (TextView) findViewById(R.id.nombregrande);
        txt1.bringToFront();

        ImageView cuadrodesc = (ImageView) findViewById(R.id.recuadro);
        cuadrodesc.setImageResource(R.drawable.cuadro);
    }

}
