package com.example.santi.beatline_func;

import android.widget.BaseAdapter;

import java.util.ArrayList;


public class AdapterPersona extends BaseAdapter {
    private ArrayList<String> ListaPersonas;

    public AdapterPersona(ArrayList<String> Personas)

    {
        ListaPersonas = Personas;

    }

    public int getCount() {
        return ListaPersonas.size();
    }

    public String getItem(int pos) {
        String Ret = ListaPersonas.get(pos);
        return Ret;
    }
}
