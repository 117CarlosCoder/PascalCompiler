
package com.compiladores.Instrucciones.Sentencias.Control;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import java.util.LinkedList;


public class CASE extends Instruccion{
    private Instruccion expresion;
    private LinkedList<Caso> casos;
    private Caso defaultcaso;

    public CASE(Instruccion expresion, LinkedList<Caso> casos, Caso defaultcaso, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.casos = casos;
        this.defaultcaso = defaultcaso;
    }
    
    public CASE(Instruccion expresion, LinkedList<Caso> casos, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.casos = casos;
    }
    
    public CASE(Instruccion expresion, Caso defaultcaso, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.defaultcaso = defaultcaso;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (casos != null) {
            for (Caso caso : casos) {
                System.out.println("Caso1 " + caso.getExpresion().interpretar(arbol, tabla) + " " +caso.getExpresion().tipo.getTipo());
                System.out.println("Caso2 " + expresion.interpretar(arbol, tabla) + " " +expresion.tipo.getTipo());
                System.out.println("Resultado " + caso.getExpresion().interpretar(arbol, tabla).toString().equals(expresion.interpretar(arbol, tabla).toString()));
                if (caso.getExpresion().interpretar(arbol, tabla).toString().equals(expresion.interpretar(arbol, tabla).toString())) { 
                    caso.interpretar(arbol, tabla);
                    return null;
                }

            }
           
        }
        
        if (defaultcaso != null) {
             defaultcaso.interpretar(arbol, tabla);
        }
        
        return null;
    }
    
}
