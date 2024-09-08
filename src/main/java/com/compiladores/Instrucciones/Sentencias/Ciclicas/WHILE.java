

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

public class WHILE extends Instruccion{
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public WHILE(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
       
        var nuevatabla = new TablaSimbolos(tabla);
        nuevatabla.setNombre(tabla.getNombre() + "-WHILE");
        
        var cond = this.condicion.interpretar(arbol, nuevatabla);
        if (cond instanceof Errores) {
            return cond;
        }
        
        if (this.condicion.tipo.getTipo() != TipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "la condicion", this.linea, this.columna);
        }
 
        var nuevatabla2 = new TablaSimbolos(tabla);
         
        tabla.setTablasTotales(nuevatabla2);
        
        
        while ((boolean) this.condicion.interpretar(arbol, nuevatabla)) {            
            
            nuevatabla2.setNombre(tabla.getNombre() + "-WHILE");
            for (Instruccion instruccion : instrucciones) {
                if (instruccion instanceof Declaracion) {
                    ((Declaracion) (instruccion)).setEntorno("while");
                }
                if (instruccion instanceof CONTINUE) {
                    System.out.println("continue");
                    break;
                }
                if (instruccion instanceof BREAK) {
                    System.out.println("break");
                    return null;
                }
                
                
                
                
                var resIns = instruccion.interpretar(arbol, nuevatabla2);
                if (resIns instanceof Declaracion) {
                    ((Declaracion) (resIns)).setEntorno("while");
                }
                if (resIns instanceof CONTINUE) {
                    System.out.println("continue");
                    break;
                }
                if (resIns instanceof BREAK) {
                    System.out.println("break");
                    return null;
                }
                
                
            }
            
            
        }
        
       
        
        return null;
    }
    
}
