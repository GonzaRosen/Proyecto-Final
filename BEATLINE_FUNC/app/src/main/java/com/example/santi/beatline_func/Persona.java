package com.example.santi.beatline_func;

/**
 * Created by 42660700 on 7/6/2017.
 */

public class Persona {
    private int Id;
    private String Nombre;
    private String FechaNac;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(String fechaNac) {
        FechaNac = fechaNac;
    }
}