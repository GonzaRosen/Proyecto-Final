package com.example.santi.beatline_func;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AdapterPersona extends ArrayAdapter<UsuariosBusqueda> {
    private ArrayList<UsuariosBusqueda> ListaPersonas;
    private Context context;

    AdapterPersona(Context context, ArrayList<UsuariosBusqueda> usuariosBusquedas) {
        super(context, 0, usuariosBusquedas);
        this.ListaPersonas = usuariosBusquedas;
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_persona, viewGroup, false);
        }
        TextView Nombre = (TextView) view.findViewById(R.id.Nombre);
        TextView Ubicacion = (TextView) view.findViewById(R.id.Distancia);
        TextView Instrumento = (TextView) view.findViewById(R.id.Instrumento);
        TextView Genero = (TextView) view.findViewById(R.id.Genero);
        TextView Influencia = (TextView) view.findViewById(R.id.Influencia);
        TextView Email =(TextView) view.findViewById(R.id.E_Mail);


        UsuariosBusqueda ub = getItem(position);
        Nombre.setText(ub.getNombre() + " " + ub.getApellido() + ", ");
        Ubicacion.setText(ub.getUbicacion());
        Instrumento.setText(ub.getInstrumentos() + ", ");
        Genero.setText(ub.getGeneros() + ", ");
        Influencia.setText(ub.getInfluencias());
        Email.setText(ub.getEmail());
        return view;
    }

    public void notifyDataChanged(List<UsuariosBusqueda> list){
        this.ListaPersonas.clear();
        this.ListaPersonas.addAll(list);
        Toast.makeText(context, String.valueOf(ListaPersonas.size()) ,Toast.LENGTH_LONG).show();
        this.notifyDataSetChanged();
    }
}
