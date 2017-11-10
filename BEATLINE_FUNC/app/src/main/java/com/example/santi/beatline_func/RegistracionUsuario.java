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
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistracionUsuario extends Activity {

    EditText Nombre;
    EditText Apellido;
    CalendarView FechaNac;
    EditText Email;
    EditText Pass;
    EditText Ubicacion;

    CheckBox Terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_regusu);

            Terminos = (CheckBox)findViewById(R.id.ChkBox);
            Nombre = (EditText)findViewById(R.id.Nombre);
            Apellido = (EditText)findViewById(R.id.Apellido);
            FechaNac = (CalendarView)findViewById(R.id.FechaNac);
            Email = (EditText)findViewById(R.id.Email);
            Pass = (EditText)findViewById(R.id.Contraseña);
            Ubicacion = (EditText)findViewById(R.id.Ubicacionn);

        }
    }

        public void Volver (View view)
    {
        Intent Activity;
        Activity = new Intent(this,MainActivity.class);
        startActivity(Activity);
    }

    public void Continuar (View view) {

        String Edit1 = Nombre.getText().toString();
        String Edit2 = Apellido.getText().toString();
        String Edit3 = Email.getText().toString();
        String Edit4 = Pass.getText().toString();
        String Edit5 = Ubicacion.getText().toString();

        if(!(Edit1.equals("") || Edit2.equals("") || Edit3.equals("") || Edit4.equals("") || Edit5.equals("") || FechaNac.isSelected()))
        {
            if (Terminos.isChecked())
            {
                Persona p = new Persona();
                p.setNombre(Edit1);
                p.setApellido(Edit2);
                p.setEmail(Edit3);
                p.setContraseña(Edit4);
                p.setUbicacion(Edit5);

                Intent Activity;
                Activity = new Intent(this,RegistracionPreferencias.class);
                startActivity(Activity);
            }
            else
            {
                Toast toast1;
                toast1 = Toast.makeText(RegistracionUsuario.this, "Debe aceptar los terminos y condiciones para completar la registración", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }
        else
        {
            Toast toast1;
            toast1 = Toast.makeText(RegistracionUsuario.this, "No deje campos en blanco", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
}