
package com.compiladores.Instrucciones.Sentencias.Transferencia;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;


public class BREAK extends Instruccion{

    public BREAK( int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
    }

    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        return null;
    }
    
}
