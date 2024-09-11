
package com.compiladores.Expresiones.Variables;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class AsignacionVar extends Instruccion{

    private String id;
    private String exp;
    private Instruccion index;
    private Instruccion expresion;

    public AsignacionVar(String id, Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.expresion = expresion;
    }
    
    public AsignacionVar(String id, String exp, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.exp = exp;
    }

    public AsignacionVar(String id, Instruccion index, Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.index = index;
        this.expresion = expresion;
    }

    public AsignacionVar(String id, Instruccion index, Instruccion expresion,Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.index = index;
        this.expresion = expresion;
    }
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {

        if (this.index != null ) {
            var valorindex =  this.index.interpretar(arbol, tabla);
            var variable = tabla.getVariableVector(id, (int)valorindex);
            if (variable == null) {
                return new Errores("SEMANTICO", "Variable no existente", this.linea, this.columna);
            }

            if (!variable.isMutabilidad()) {
                return new Errores("SEMANTICO", "La Constate no puede modificar su valor", this.linea, this.columna);
            }

            var nuevoValor = this.expresion.interpretar(arbol, tabla);
            if (nuevoValor instanceof Errores) {
                return nuevoValor;
            }

            if (variable.getTipo().getTipo() != this.expresion.tipo.getTipo()) {
                return new Errores("SEMANTICO", "Tipos erroneos en asignacion", this.linea, this.columna);
            }

            variable.setValor(nuevoValor);

            return null;

        }

        var variable = tabla.getVariable(id);
        
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no existente",this.linea, this.columna);
        }
        
        if (!variable.isMutabilidad()) {
           return new Errores("SEMANTICO", "La Constate no puede modificar su valor",this.linea, this.columna);
        }
        
        /*if (exp != null) {
                 if (exp.equals("incre")||exp.equals("decre")) {
                        if (variable.getTipo().getTipo() != TipoDato.ENTERO ||variable.getTipo().getTipo() != TipoDato.DECIMAL){
                            if (exp != null) {
                                 if (exp.equals("incre")) {
                                 return new Errores("SEMANTICO", "Error de tipo el incremento solo funciona con enteros o decimales",this.linea, this.columna); 
                                }
                                if (exp.equals("decre")) {
                                 return new Errores("SEMANTICO", "Error de tipo el decremento solo funciona con enteros o decimales",this.linea, this.columna); 

                                }
                             }
                        }
                 
                 }
        }*/
        
         if (variable.getTipo().getTipo() == TipoDato.ENTERO){
             if (exp != null) {
                 if (exp.equals("incre")) {
                 variable.setValor(Integer.parseInt(variable.getValor().toString())+1);
                     System.out.println("Caombio variable = " + variable.getValor());
                 return null; 
                }
                if (exp.equals("decre")) {
                   variable.setValor(Integer.parseInt(variable.getValor().toString())-1);
                   System.out.println("Caombio variable = " + variable.getValor());
                   return null; 
                }
             }
             
         }
         
         if (variable.getTipo().getTipo() == TipoDato.DECIMAL){
             if (exp != null) {
                 if (exp.equals("incre")) {
                 variable.setValor(((double)variable.getValor())+1.0);
                 return null; 
                }
                if (exp.equals("decre")) {
                   variable.setValor(((double)Integer.parseInt(variable.getValor().toString()))-1.0);
                   return null; 
                }
             }
             
         }
        
        var nuevoValor = this.expresion.interpretar(arbol, tabla);
        if (nuevoValor instanceof Errores) {
            return nuevoValor;
        }
        
        if (variable.getTipo().getTipo() != this.expresion.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipos erroneos en asignacion",this.linea, this.columna);
        }
        variable.setValor(nuevoValor);
        return null; 
    }
    
}
