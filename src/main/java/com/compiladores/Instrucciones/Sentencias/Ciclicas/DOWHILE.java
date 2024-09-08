
package com.compiladores.Instrucciones.Sentencias.Ciclicas;

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

public class DOWHILE extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public DOWHILE(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var nuevatabla = new TablaSimbolos(tabla);
        nuevatabla.setNombre(tabla.getNombre() + "-DOWHILE");

        var cond = this.condicion.interpretar(arbol, nuevatabla);
        if (cond instanceof Errores) {
            return cond;
        }

        if (this.condicion.tipo.getTipo() != TipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "la condicion", this.linea, this.columna);
        }

        var nuevatabla2 = new TablaSimbolos(tabla);
        nuevatabla2.setNombre(tabla.getNombre() + "-DOWHILE");
        tabla.setTablasTotales(nuevatabla2);

        do {

            for (Instruccion instruccion : instrucciones) {

                 if (instruccion instanceof Declaracion) {
                    ((Declaracion) (instruccion)).setEntorno("dowhile");
                }
                

                if (instruccion instanceof CONTINUE) {
                        break;
                    }
                    
                    if (instruccion instanceof BREAK) {
                        return null;
                    }
                

                var resIns = instruccion.interpretar(arbol, nuevatabla2);
                
                if (resIns instanceof Declaracion) {
                    ((Declaracion) (resIns)).setEntorno("dowhile");
                }
                
                if (resIns instanceof CONTINUE) {
                    break;
                }

                if (resIns instanceof BREAK) {
                    return null;
                }
            }
        } while ((boolean) this.condicion.interpretar(arbol, nuevatabla));

        return null;

    }

}
