package com.example.santi.beatline_func;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class AdapterPersona extends ArrayAdapter<Persona> {
    public AdapterPersona(@NonNull Context context, @LayoutRes int resource, ArrayAdapter<Persona> personas) {
        super(context, resource);
        this.personas = personas;
    }

    ArrayAdapter<Persona> personas;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Persona persona = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_lista_personas, parent, false);
        }


        nombreApelldoEdad.setText(usuario.getNombre() + " " + usuario.getApellido());
        if(usuario.getIdCredencial() == 0) {
            credencial.setText("Administrador");
        } else if (usuario.getIdCredencial() == 1) {
            credencial.setText("Jefe de área");
        } else {
            credencial.setText("Médico");
        }

        matricula.setText("Matrícula: " + usuario.getMatricula());


        ImageButton editar = (ImageButton)convertView.findViewById(R.id.editAbm);
        if (editar != null) {
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario u = getItem(position);

                    FragmentTransaction ft = f.getFragmentManager().beginTransaction();
                    datosUsuarios fr = new datosUsuarios();

                    Bundle bundle = new Bundle();
                    bundle.putInt("idUsuario", u.getIdUsuario());
                    bundle.putString("nombre", u.getNombre());
                    bundle.putString("apellido", u.getApellido());
                    bundle.putInt("matricula", u.getMatricula());
                    bundle.putString("hospital", u.getHospital());
                    bundle.putInt("credencial", u.getIdCredencial());

                    fr.setArguments(bundle);

                    fr.editarONuevo = 0;
                    ft.replace(R.id.fragment_container, fr, "datosUsuariosTag");
                    ft.addToBackStack("datosUsuariosTag");
                    ft.commit();
                }
            });
        }
}

