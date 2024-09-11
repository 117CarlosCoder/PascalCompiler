package com.compiladores.Instrucciones;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import java.util.LinkedList;

public class Readln extends Instruccion {
    public LinkedList<String> expresiones;
    public Readln(LinkedList<String> expresiones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresiones = expresiones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        arbol.Print("Readln Correctamente");
        return null;
    }
}
