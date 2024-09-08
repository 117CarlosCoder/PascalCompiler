
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

public class FOR extends Instruccion{
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public FOR(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }
    
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var nuevabtabla = new TablaSimbolos(tabla);
        nuevabtabla.setNombre(tabla.getNombre() + "-FOR");
        
        
        var res1 = this.asignacion.interpretar(arbol, nuevabtabla);
        if(res1 instanceof Errores){
            return res1;
        }
        
        var cond = this.condicion.interpretar(arbol, nuevabtabla);
        if(cond instanceof Errores){
            return cond;
        }
        
        if (this.condicion.tipo.getTipo() != TipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "La condicion debe ser de tipo bool", this.linea, this.columna);
        }
        
        var nuevatabla2 = new TablaSimbolos(nuevabtabla);
        nuevatabla2.setNombre(tabla.getNombre() + "-FOR");
        tabla.setTablasTotales(nuevatabla2);
        
        while ((boolean) this.condicion.interpretar(arbol, nuevabtabla)) {            
            
            
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
            
            
            
            var actVar = this.actualizacion.interpretar(arbol, nuevabtabla);
            
            if (actVar instanceof Errores) {
                return actVar;
            }
            
            
            
        }
        
        
        
        return null;
    }
    
}
