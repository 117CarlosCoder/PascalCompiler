
package com.compiladores.Instrucciones.Sentencias.Control;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Expresiones.Variables.AsignacionVar;
import com.compiladores.Instrucciones.Declaracion;
import com.compiladores.Instrucciones.Sentencias.Transferencia.BREAK;
import com.compiladores.Instrucciones.Sentencias.Transferencia.CONTINUE;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import java.util.LinkedList;

public class FOR extends Instruccion{
    private String idvariable;
    private Instruccion vinicial;
    private Instruccion vfinal;
    private LinkedList<Instruccion> instrucciones;

    public FOR(String idvariable, Instruccion vinicial, Instruccion vfinal, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.idvariable = idvariable;
        this.vinicial = vinicial;
        this.vfinal = vfinal;
        this.instrucciones = instrucciones;
    }
    
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var nuevabtabla = new TablaSimbolos(tabla);
        nuevabtabla.setNombre(tabla.getNombre() + "-FOR");

        var variable = tabla.getVariable(idvariable);

        if (variable.getTipo().getTipo() != TipoDato.ENTERO){
            return new Errores("SEMANTICO", "Error variable debe ser de valor entero", this.linea, this.columna);
        }

        var var1 = this.vinicial.interpretar(arbol, nuevabtabla);

        if(var1 instanceof Errores){
            return var1;
        }

        if (!(var1 instanceof Integer)) {
            return new Errores("SEMANTICO", "Error variable debe ser de valor entero", this.linea, this.columna);
        }

        var var2 = this.vfinal.interpretar(arbol, nuevabtabla);

        if(var2 instanceof Errores){
            return var2;
        }

        if (!(var2 instanceof Integer)) {
            return new Errores("SEMANTICO", "Error variable debe ser de valor entero", this.linea, this.columna);
        }

        
        var nuevatabla2 = new TablaSimbolos(nuevabtabla);
        nuevatabla2.setNombre(tabla.getNombre() + "-FOR");
        tabla.setTablasTotales(nuevatabla2);


        for (int i = (int)var1; i <= (int) var2; i++) {

            new AsignacionVar(idvariable,String.valueOf(i), this.linea, this.columna);

            for (Instruccion instruccion : instrucciones) {
                if (instruccion != null) {

                    if (instruccion instanceof Declaracion) {
                        ((Declaracion) (instruccion)).setEntorno("for");
                    }

                    if (instruccion instanceof CONTINUE) {
                        break;
                    }

                    if (instruccion instanceof BREAK) {
                        return null;
                    }


                    var resIns = instruccion.interpretar(arbol, nuevatabla2);

                    if (resIns instanceof Declaracion) {
                        ((Declaracion) (resIns)).setEntorno("for");
                    }

                    if (resIns instanceof CONTINUE) {
                        break;
                    }

                    if (resIns instanceof BREAK) {
                        return null;
                    }


                }

            }
        }
        
        
        return null;
    }
    
}
