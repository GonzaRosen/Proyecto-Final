package com.example.santi.beatline_func;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Persona usuario_logeado = new Persona();
    public static UsuariosBusqueda usuarios = new UsuariosBusqueda();
    public static ArrayList<String> DatosIns, DatosGen, DatosInf, DatosUbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatosIns = new ArrayList<>();

        DatosIns.add("");
        DatosIns.add("Guitarra");
        DatosIns.add("Bajo");
        DatosIns.add("Flauta");
        DatosIns.add("Piano");
        DatosIns.add("Violin");
        DatosIns.add("Chelo");
        DatosIns.add("Teclado");
        DatosIns.add("Theremin");
        DatosIns.add("Mandolina");
        DatosIns.add("Batería");

        DatosGen = new ArrayList<>();

        DatosGen.add("");
        DatosGen.add("Rock");
        DatosGen.add("Pop");
        DatosGen.add("Country");
        DatosGen.add("Cumbia");
        DatosGen.add("Cuarteto");
        DatosGen.add("Bachata");
        DatosGen.add("Salsa");
        DatosGen.add("Reggaeton");
        DatosGen.add("Reggae");
        DatosGen.add("Electrónica");

        DatosInf = new ArrayList<>();

        DatosInf.add("");
        DatosInf.add("Maluma");
        DatosInf.add("Enrique Iglesias");
        DatosInf.add("Ricky Martin");
        DatosInf.add("Abel Pintos");
        DatosInf.add("La Vela Puerca");
        DatosInf.add("Rabalanca");
        DatosInf.add("Soda Estereo");
        DatosInf.add("El Cuarteto de Nos");
        DatosInf.add("Patricio Rey");
        DatosInf.add("SOAD");
        DatosInf.add("Queen");
        DatosInf.add("Guns n' Roses");
        DatosInf.add("Led Zeppelin");
        DatosInf.add("U2");
        DatosInf.add("Rolling Stones");
        DatosInf.add("The Beatles");
        DatosInf.add("AC/DC");
        DatosInf.add("Oasis");
        DatosInf.add("Bruno Mars");
        DatosInf.add("Pharrel Williams");
        DatosInf.add("Martin Garrix");
        DatosInf.add("Eminem");
        DatosInf.add("Katy Perry");
        DatosInf.add("Taylor Swift");
        DatosInf.add("Otros");

        DatosUbi =  new ArrayList<>();

        DatosUbi.add("");
        DatosUbi.add("Villa Crespo");
        DatosUbi.add("Caballito");
        DatosUbi.add("Almagro");
        DatosUbi.add("Palermo");
        DatosUbi.add("Belgrano");
        DatosUbi.add("Recoleta");
        DatosUbi.add("Flores");
        DatosUbi.add("Floresta");
        DatosUbi.add("Devoto");
        DatosUbi.add("Colegiales");
        DatosUbi.add("Paternal");
        DatosUbi.add("Nuñez");
        DatosUbi.add("Villa Urquiza");
        DatosUbi.add("Saavedra");
        DatosUbi.add("Balvanera");
        DatosUbi.add("Villa del Parque");
        DatosUbi.add("Chacarita");
        DatosUbi.add("Constitucion");
        DatosUbi.add("Puerto Madero");
        DatosUbi.add("Retiro");
        DatosUbi.add("San Nicolás");
        DatosUbi.add("Monserrat");
        DatosUbi.add("San Telmo");
        DatosUbi.add("Coghlan");
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
