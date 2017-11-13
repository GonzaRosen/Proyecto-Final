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
    private ArrayList<UsuariosBusqueda> ListaPersonas;
    Context context;

    AdapterPersona(Context context, ArrayList<UsuariosBusqueda> usuariosBusquedas) {
        this.ListaPersonas = usuariosBusquedas;
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
    public void setPersonas(UsuariosBusqueda[] usuariosBusquedas) {
        this.ListaPersonas = new ArrayList<>(Arrays.asList(usuariosBusquedas));
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

        UsuariosBusqueda ub = ListaPersonas.get(position);
        Nombre.setText(ub.getNombre() + " ");
        Apellido.setText(ub.getNombre() + ", ");
        Ubicacion.setText(ub.getUbicacion());
        Instrumento.setText(ub.getInstrumento() + ", ");
        Genero.setText(ub.getGenero() + ", ");
        Influencia.setText(ub.getInfluencia());
        Email.setText(ub.getEmail());
        return view;
    }
}
