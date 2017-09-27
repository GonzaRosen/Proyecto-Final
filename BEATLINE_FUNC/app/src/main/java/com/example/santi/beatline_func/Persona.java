package com.example.santi.beatline_func;


import android.support.v4.media.MediaDescriptionCompat;
import android.util.Log;

import javax.crypto.spec.DESKeySpec;

public class Persona {
    private int Id;
    private String Nombre;
    private String Apellido;
    private String FechaNac;
    private String Email;
    private String Contraseña;
    private String Descripcion;
    private String Influencias;
    private String[] Genero;
    private String[] Instrumentos;
    private Boolean Login;

    public int getId() { return Id; }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }

    public String getFechaNac() { return FechaNac; }

    public void setFechaNac(String fechaNac) {
        FechaNac = fechaNac;
    }

    public String getApellido() { return Apellido; }

    public void setApellido(String apellido) { Apellido = apellido; }

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

    public String getContraseña() { return Contraseña; }

    public void setContraseña(String contraseña) { Contraseña = contraseña; }

    public String getDescripcion() { return Descripcion; }

    public void setDescripcion(String descripcion) { Descripcion = descripcion; }

    public String getGenero() { return Genero; }

    public void setGenero(String genero) { Genero = genero; }

    public String getInfluencias() { return Influencias; }

    public void setInfluencias(String influencias) { Influencias = influencias; }

    public String getInstrumentos() { return Instrumentos; }

    public void setInstrumentos(String instrumentos) { Instrumentos = instrumentos; }

    public Boolean getLogin() { return Login; }

    public void setLogin(Boolean login) { Login = login; }
}