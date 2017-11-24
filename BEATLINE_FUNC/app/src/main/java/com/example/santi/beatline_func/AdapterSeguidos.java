package com.example.santi.beatline_func;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdapterSeguidos extends ArrayAdapter<UsuariosBusqueda> {
    private ArrayList<UsuariosBusqueda> ListaPersonas;
    private Context context;
    private BtnClickListener mClickListener = null;

    AdapterSeguidos(Context context, ArrayList<UsuariosBusqueda> usuariosBusquedas) {
        super(context, 0, usuariosBusquedas);
        this.ListaPersonas = usuariosBusquedas;
        this.context = context;
    }

    public int getId(int posicion) {
        UsuariosBusqueda ub =ListaPersonas.get(posicion);
        if (ub !=null)
            return ub.getIdUsuario();
        return 0;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_seguidos, viewGroup, false);
        }
        TextView Nombre = (TextView) view.findViewById(R.id.Nombre);
        TextView Ubicacion = (TextView) view.findViewById(R.id.Ubicacion);
        TextView Instrumento = (TextView) view.findViewById(R.id.Instrumento);
        TextView Genero = (TextView) view.findViewById(R.id.Genero);
        TextView Influencia = (TextView) view.findViewById(R.id.Influencia);
        TextView Mail = (TextView) view.findViewById(R.id.Mail);

        UsuariosBusqueda ub = getItem(position);
        Nombre.setText(ub.getNombre() + " " + ub.getApellido() + ",");
        Ubicacion.setText(ub.getUbicacion());
        Instrumento.setText(ub.getInstrumentos());
        Genero.setText(ub.getGeneros());
        Influencia.setText(ub.getInfluencias());
        Mail.setText(ub.getEmail());

        return view;
    }

    public void notifyDataChanged(List<UsuariosBusqueda> list){
        try
        {
            this.ListaPersonas.clear();
            this.ListaPersonas.addAll(list);
            Toast.makeText(context, String.valueOf(ListaPersonas.size()), Toast.LENGTH_LONG).show();
            this.notifyDataSetChanged();
        }
        catch (Exception a)
        {
            Toast.makeText(getContext(),"No hay usuarios que coincidan con la búsqueda", Toast.LENGTH_LONG).show();
        }
    }
}
