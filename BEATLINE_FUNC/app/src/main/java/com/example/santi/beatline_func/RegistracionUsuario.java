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
import java.util.ArrayList;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistracionUsuario extends Activity {

    EditText Nombre;
    EditText Apellido;
    EditText Email;
    EditText Pass;
    EditText ConfPass;
    CheckBox Terminos;
    Spinner SpUbicacion, Dia, Mes, Año;
    ArrayAdapter<String> AdapterUbi, AdapterDia, AdapterMes, AdapterAño;
    public static ArrayList<String> DatosDia, DatosMes, DatosAño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_regusu);

            Terminos = (CheckBox)findViewById(R.id.ChkBox);
            Nombre = (EditText)findViewById(R.id.Nombre);
            Apellido = (EditText)findViewById(R.id.Apellido);
            Email = (EditText)findViewById(R.id.Email);
            Pass = (EditText)findViewById(R.id.Contraseña);
            ConfPass = (EditText)findViewById(R.id.confirmar);
            SpUbicacion = (Spinner) findViewById(R.id.spUbicacionn);
            Dia = (Spinner)findViewById(R.id.dia);
            Mes = (Spinner)findViewById(R.id.mes);
            Año = (Spinner)findViewById(R.id.año);

            DatosDia = new ArrayList<>();

            DatosDia.add("1");
            DatosDia.add("2");
            DatosDia.add("3");
            DatosDia.add("4");
            DatosDia.add("5");
            DatosDia.add("6");
            DatosDia.add("7");
            DatosDia.add("8");
            DatosDia.add("9");
            DatosDia.add("10");
            DatosDia.add("11");
            DatosDia.add("12");
            DatosDia.add("13");
            DatosDia.add("14");
            DatosDia.add("15");
            DatosDia.add("16");
            DatosDia.add("17");
            DatosDia.add("18");
            DatosDia.add("19");
            DatosDia.add("20");
            DatosDia.add("21");
            DatosDia.add("22");
            DatosDia.add("23");
            DatosDia.add("24");
            DatosDia.add("25");
            DatosDia.add("26");
            DatosDia.add("27");
            DatosDia.add("28");
            DatosDia.add("29");
            DatosDia.add("30");
            DatosDia.add("31");

            DatosMes = new ArrayList<>();

            DatosMes.add("1");
            DatosMes.add("2");
            DatosMes.add("3");
            DatosMes.add("4");
            DatosMes.add("5");
            DatosMes.add("6");
            DatosMes.add("7");
            DatosMes.add("8");
            DatosMes.add("9");
            DatosMes.add("10");
            DatosMes.add("11");
            DatosMes.add("12");

            DatosAño = new ArrayList<>();

            DatosAño.add("2000");
            DatosAño.add("1999");
            DatosAño.add("1998");
            DatosAño.add("1997");
            DatosAño.add("1996");
            DatosAño.add("1995");
            DatosAño.add("1994");
            DatosAño.add("1993");
            DatosAño.add("1992");
            DatosAño.add("1991");
            DatosAño.add("1990");
            DatosAño.add("1989");
            DatosAño.add("1988");
            DatosAño.add("1987");
            DatosAño.add("1986");
            DatosAño.add("1985");
            DatosAño.add("1984");
            DatosAño.add("1983");
            DatosAño.add("1982");
            DatosAño.add("1981");
            DatosAño.add("1980");
            DatosAño.add("1979");
            DatosAño.add("1978");
            DatosAño.add("1977");
            DatosAño.add("1976");
            DatosAño.add("1975");
            DatosAño.add("1974");
            DatosAño.add("1973");
            DatosAño.add("1972");
            DatosAño.add("1971");
            DatosAño.add("1970");
            DatosAño.add("1969");
            DatosAño.add("1968");
            DatosAño.add("1967");
            DatosAño.add("1966");
            DatosAño.add("1965");
            DatosAño.add("1964");
            DatosAño.add("1963");
            DatosAño.add("1962");
            DatosAño.add("1961");
            DatosAño.add("1960");

            AdapterUbi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.DatosUbi);
            AdapterDia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DatosDia);
            AdapterMes = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DatosMes);
            AdapterAño = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DatosAño);

            AdapterUbi.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            AdapterDia.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            AdapterMes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            AdapterAño.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            SpUbicacion.setAdapter(AdapterUbi);
            Dia.setAdapter(AdapterDia);
            Mes.setAdapter(AdapterMes);
            Año.setAdapter(AdapterAño);


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
        String SpinnerU = SpUbicacion.getSelectedItem().toString();
        String Edit6 = ConfPass.getText().toString();
        String SpinnerDia = Dia.getSelectedItem().toString();
        String SpinnerMes = Mes.getSelectedItem().toString();
        String SpinnerAño = Año.getSelectedItem().toString();

        if(!(Edit1.equals("") || Edit2.equals("") || Edit3.equals("") || Edit4.equals("") || SpinnerU.equals("")))
        {
            if (Terminos.isChecked())
            {
                if (Edit4.equals(Edit6))
                {
                    String FechaNac = SpinnerDia + "/" + SpinnerMes + "/" + SpinnerAño;
                    Bundle bundle = new Bundle();
                    bundle.putString("Nombre",Edit1);
                    bundle.putString("Apellido", Edit2);
                    bundle.putString("Email", Edit3);
                    bundle.putString("Pass", Edit4);
                    bundle.putString("Ubicacion", SpinnerU);
                    bundle.putString("Fecha", FechaNac);

                    Intent Activity;
                    Activity = new Intent(this,RegistracionPreferencias.class);
                    Activity.putExtras(bundle);
                    startActivity(Activity);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Las contraseñas deben coincidir", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getBaseContext(), "Debe aceptar los terminos y condiciones para completar la registración", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getBaseContext(), "No deje campos en blanco", Toast.LENGTH_SHORT).show();
        }
    }

    public void logear (View view)
    {
        Intent Activity = new Intent(getBaseContext(),Loginn.class);
        startActivity(Activity);
    }
}