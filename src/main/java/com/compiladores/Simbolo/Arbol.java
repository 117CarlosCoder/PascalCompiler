
package com.compiladores.Simbolo;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Instrucciones.Procedure;

import java.util.LinkedList;

public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private TablaSimbolos tablaGlobal;
    private LinkedList<Errores> errores;
    private LinkedList<Instruccion> funciones;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new TablaSimbolos();
        this.errores = new LinkedList<>();
        this.funciones =  new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public TablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(TablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }
    
    public void Print(String valor) {
        this.consola += valor + "\n";
    }

    public void addFunciones(Instruccion funcion){
        /*if (this.getFuncion(funcion.)) {

        }*/
        this.funciones.add(funcion);
    }

    public Instruccion getFuncion(String id){
        for (var i : this.funciones) {
            if (i instanceof Procedure metodo) {
                if (metodo.id.equalsIgnoreCase(id)) {
                    return i;
                }
            }
        }
        return null;
    }
}
