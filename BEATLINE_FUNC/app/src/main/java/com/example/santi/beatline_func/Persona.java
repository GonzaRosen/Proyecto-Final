package com.example.santi.beatline_func;


import android.support.v4.media.MediaDescriptionCompat;
import android.util.Log;

import javax.crypto.spec.DESKeySpec;

public class Persona {
    private int Id;
    private String TodosInstrumentos;
    private String TodosGeneros;
    private String TodosInfluencias;
    private String Nombre;
    private String Apellido;
    private String Fecha_Nacimiento;
    private String Email;
    private String Password;
    private String Descripcion;
    private String Influencias;
<<<<<<< HEAD
    private String Generos;
    private String Instrumentos;
=======
    private String[] Genero;
    private String[] Instrumentos;
>>>>>>> 731539760113f1fdecf58b5b58dfc6cc27809810
    private Boolean Login;

    public int getId() { return Id; }

    public void setId(int id) { Id = id; }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }

    public String getFechaNac() { return Fecha_Nacimiento; }

    public void setFechaNac(String fechaNac) { Fecha_Nacimiento = fechaNac; }

    public String getApellido() { return Apellido; }

    public void setApellido(String apellido) { Apellido = apellido; }

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

    public String getContraseña() { return Password; }

    public void setContraseña(String password) { Password = password; }

    public String getDescripcion() { return Descripcion; }

    public void setDescripcion(String descripcion) { Descripcion = descripcion; }

    public String getGenero() { return Generos; }

    public void setGenero(String generos) { Generos = generos; }

    public String getInfluencias() { return Influencias; }

    public void setInfluencias(String influencias) { Influencias = influencias; }

    public String getInstrumentos() { return Instrumentos; }

    public void setInstrumentos(String instrumentos) { Instrumentos = instrumentos; }

    public Boolean getLogin() { return Login; }

    public void setLogin(Boolean login) { Login = login; }

    public String getTodosInstrumentos() { return TodosInstrumentos; }

    public void setTodosInstrumentos(String todosInstrumentos) { TodosInstrumentos = todosInstrumentos; }

    public String getTodosGeneros() { return TodosGeneros; }

    public void setTodosGeneros(String todosGeneros) { TodosGeneros = todosGeneros; }

    public String getTodosInfluencias() { return TodosInfluencias; }

    public void setTodosInfluencias(String todosInfluencias) { TodosInfluencias= todosInfluencias; }
}