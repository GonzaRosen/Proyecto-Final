package com.example.santi.beatline_func;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdapterPersona extends ArrayAdapter<UsuariosBusqueda> {
    private ArrayList<UsuariosBusqueda> ListaPersonas;
    private Context context;
    private BtnClickListener mClickListener = null;

    AdapterPersona(Context context, ArrayList<UsuariosBusqueda> usuariosBusquedas, BtnClickListener listener) {
        super(context, 0, usuariosBusquedas);
        this.ListaPersonas = usuariosBusquedas;
        this.context = context;
        mClickListener = listener;
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
            view = inflater.inflate(R.layout.lista_persona, viewGroup, false);
        }
        TextView Nombre = (TextView) view.findViewById(R.id.Nombre);
        TextView Ubicacion = (TextView) view.findViewById(R.id.Distancia);
        TextView Instrumento = (TextView) view.findViewById(R.id.Instrumento);
        TextView Genero = (TextView) view.findViewById(R.id.Genero);
        TextView Influencia = (TextView) view.findViewById(R.id.Influencia);
        Button Seguir = (Button) view.findViewById(R.id.btnSeguir);

        UsuariosBusqueda ub = getItem(position);
        Nombre.setText(ub.getNombre() + " " + ub.getApellido() + ", ");
        Ubicacion.setText(ub.getUbicacion());
        Instrumento.setText(ub.getInstrumentos() + ", ");
        Genero.setText(ub.getGeneros() + ", ");
        Influencia.setText(ub.getInfluencias());

        Seguir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mClickListener != null)
                    mClickListener.onBtnClick(position);
            }
        });

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
