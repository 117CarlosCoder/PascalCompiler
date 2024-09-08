
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


public class IF extends Instruccion{
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;
    private Instruccion instruccion;
    private LinkedList<Instruccion> instruccioneselse;
    private Instruccion elseif;

    public IF(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    public IF(Instruccion condicion, Instruccion instruccion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccion = instruccion;
    }
    
    public IF(Instruccion condicion, LinkedList<Instruccion> instrucciones, LinkedList<Instruccion> instruccioneselse, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.instruccioneselse = instruccioneselse;
    }

    public IF(Instruccion condicion, LinkedList<Instruccion> instrucciones, Instruccion elseif, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
        this.elseif = elseif;
    }
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }
        
        if(this.condicion.tipo.getTipo() != TipoDato.BOOLEANO){
            return new Errores("SEMANTICO", "Expresion invalida",this.linea, this.columna);
        }
        
        var nuevaTabla =  new TablaSimbolos(tabla);
        nuevaTabla.setNombre(tabla.getNombre() + "-IF");
        tabla.setTablasTotales(nuevaTabla);

        System.out.println(cond);
        System.out.println(this.instruccion);

        if ((boolean) cond) {
            if (this.instruccion != null){
                if (this.instruccion instanceof BREAK) {
                    return this.instruccion;
                }
                if (this.instruccion instanceof CONTINUE) {
                    return this.instruccion;
                }
                var resultado = this.instruccion.interpretar(arbol, nuevaTabla);

                if (resultado instanceof Declaracion) {
                    ((Declaracion) (resultado)).setEntorno("if");
                }

                if (resultado instanceof BREAK) {
                    return resultado;
                }
                if (resultado instanceof CONTINUE) {
                    return resultado;
                }
            }
            if (this.instrucciones != null) {
                for (var i : this.instrucciones) {
                    if (i instanceof Declaracion) {
                        ((Declaracion) (i)).setEntorno("if");
                    }
                    if (i instanceof BREAK) {
                        return i;
                    }
                    if (i instanceof CONTINUE) {
                        return i;
                    }
                    
                    var resultado = i.interpretar(arbol, nuevaTabla);
                    
                    if (resultado instanceof Declaracion) {
                        ((Declaracion) (resultado)).setEntorno("if");
                    }
                    
                    if (resultado instanceof BREAK) {
                        return resultado;
                    }
                    if (resultado instanceof CONTINUE) {
                        return resultado;
                    }

                }
            }
            
        }
        if (instruccioneselse != null && !(boolean) cond){
            for (var i :  this.instruccioneselse) {
                var resultado = i.interpretar(arbol, nuevaTabla);
                if (i instanceof Declaracion) {
                    ((Declaracion) (i)).setEntorno("if");
                }
                if (resultado instanceof BREAK) {
                    return null;
                }
                if (resultado instanceof CONTINUE) {
                    break;
                }

            }
        }
        if (elseif != null) {
            var nuevoIf = (IF)elseif;
            IF aIF = new IF(nuevoIf.condicion, nuevoIf.instrucciones,nuevoIf.elseif, nuevoIf.linea, nuevoIf.columna);           
            aIF.interpretar(arbol, tabla);
        }
        
        return null;
    }

}
