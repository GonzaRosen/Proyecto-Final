package com.example.santi.beatline_func;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class AdapterPersona extends BaseAdapter {
    private ArrayList<Persona> ListaPersonas;
    Context context;

    AdapterPersona(Context context, ArrayList<Persona> personas) {
        this.ListaPersonas = personas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ListaPersonas.size();
    }

    @Override
    public Object getItem(int position) {
        return ListaPersonas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setPersonas(Persona[] personas) {
        this.ListaPersonas = new ArrayList<>(Arrays.asList(personas));
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_persona, viewGroup, false);
        }

        TextView Apellido = (TextView) view.findViewById(R.id.Apellido);
        TextView Nombre = (TextView) view.findViewById(R.id.Nombre);
        TextView Ubicacion = (TextView) view.findViewById(R.id.Distancia);
        TextView Instrumento = (TextView) view.findViewById(R.id.Instrumento);
        TextView Genero = (TextView) view.findViewById(R.id.Genero);
        TextView Influencia = (TextView) view.findViewById(R.id.Influencia);
        TextView Email =(TextView) view.findViewById(R.id.E_Mail);

        Persona p = ListaPersonas.get(position);
        Nombre.setText(p.getNombre() + " ");
        Apellido.setText(p.getNombre() + ", ");
        Ubicacion.setText(p.getUbicacion());
        Instrumento.setText(p.getInstrumentos() + ", ");
        Genero.setText(p.getGenero() + ", ");
        Influencia.setText(p.getInfluencias());
        Email.setText(p.getEmail());
        return view;

    }
}
