package com.example.santi.beatline_func;

import java.lang.reflect.Array;

/**
 * Created by 42660700 on 25/8/2017.
 */

public class Filtros {
    private String[] Instrumentos;
    private String[] Generos;
    private String[] Influencias;
    private int EdadMinima;
    private int EdadMaxima;
    private int DistanciaMinima;
    private int DistanciaMaxima;

    public String[] getInstrumentos() { return Instrumentos; }

    public void setInstrumentos(String[] instrumentos) { Instrumentos = instrumentos; }

    public String[] getGeneros() { return Generos; }

    public void setGeneros(String[] generos) { Generos = generos; }

    public String[] getInfluencias() { return Influencias; }

    public void setInfluencias(String[] influencias) { Influencias = influencias; }

    public int getEdadMinima() { return EdadMinima; }

    public void setEdadMinima(int edadMinima) { EdadMinima = edadMinima; }

    public int getEdadMaxima() { return EdadMaxima; }

    public void setEdadMaxima(int edadMaxima) { EdadMaxima = edadMaxima; }

    public int getDistanciaMinima() { return DistanciaMinima; }

    public void setDistanciaMinima(int distanciaMinima) { DistanciaMinima = distanciaMinima; }

    public int getGetDistanciaMaxima() { return DistanciaMaxima; }

    public void setGetDistanciaMaxima(int distanciaMaxima) { DistanciaMaxima = distanciaMaxima; }

}
