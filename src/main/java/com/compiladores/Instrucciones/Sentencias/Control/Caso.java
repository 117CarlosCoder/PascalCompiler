
package com.compiladores.Instrucciones.Sentencias.Control;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Instrucciones.Declaracion;
import com.compiladores.Instrucciones.Sentencias.Transferencia.BREAK;
import com.compiladores.Instrucciones.Sentencias.Transferencia.CONTINUE;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import java.util.LinkedList;

public class Caso extends Instruccion{
    private Instruccion expresion;
    private LinkedList<Instruccion> instrucciones;
    private boolean banderadefault;

    public Caso(Instruccion expresion, LinkedList<Instruccion> instrucciones, boolean banderadefault, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
        this.banderadefault = banderadefault;
    }

   
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var nuevaTabla =  new TablaSimbolos(tabla);
        nuevaTabla.setNombre(tabla.getNombre() + "-MATCH-CASE");
        tabla.setTablasTotales(nuevaTabla);
        if (expresion == null && !banderadefault) {
            for (var i :  this.instrucciones) {
                if (i instanceof Declaracion) {
                    ((Declaracion) (i)).setEntorno("matchcase");
                }
                if (i instanceof BREAK) {
                    return null;
                }
                if (i instanceof CONTINUE) {
                        break;
                    }
                
                var resultado = i.interpretar(arbol, nuevaTabla);
                
                if (resultado instanceof Declaracion) {
                    ((Declaracion) (resultado)).setEntorno("matchcase");
                }

                if (resultado instanceof BREAK) {
                    return resultado;
                }
                if (resultado instanceof CONTINUE) {
                        break;
                    }
            }
            return null;
        }
        
        var exp = this.expresion.interpretar(arbol, tabla);
        if (exp instanceof Errores) {
            return exp;
        }
       
        if (banderadefault) {
            for (var i :  this.instrucciones) {
                if (i instanceof Declaracion) {
                    ((Declaracion) (i)).setEntorno("matchcase");
                }
                if (i instanceof BREAK) {
                    return null;
                }
                if (i instanceof CONTINUE) {
                        break;
                    }
                var resultado = i.interpretar(arbol, nuevaTabla);
                if (resultado instanceof Declaracion) {
                    ((Declaracion) (resultado)).setEntorno("matchcase");
                }

                if (resultado instanceof BREAK) {
                    return null;
                }
                if (resultado instanceof CONTINUE) {
                        break;
                    }
            }
        }
       
        return null;
    }

    public Instruccion getExpresion() {
        return expresion;
    }

    public boolean isBanderadefault() {
        return banderadefault;
    }
    
    
}
