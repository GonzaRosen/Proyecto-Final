package com.example.santi.beatline_func;


public class Persona {
    private int Id;
    private String Nombre;
    private String Apellido;
    private String FechaNac;
    private String Usuario;
    private String Email;
    private String Contraseña;

    public int getId() { return Id; }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }

    public String getUsuario() { return Usuario; }

    public void setUsuario(String usuario) { Usuario = usuario; }

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
}