package com.example.a42660700.asd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RegistracionUsuario extends Activity {

    Spinner spinnerdia,spinnermes,spinneraño;

    String [] opcDia = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    String [] opcMes = new String[] {"Enero", "Febrero", "Marzo", "Mayo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String [] opcAño = new String[] {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000"};
    String[] dias,meses,años;
    private boolean firstTime = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
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
}
