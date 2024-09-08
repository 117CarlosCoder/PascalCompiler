
package com.compiladores.Expresiones.Aritmeticas;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class Potencia extends Instruccion{

    private final Instruccion operando1;
    private final Instruccion operando2;
   
    public Potencia(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
    }
    
    public Object potencia(Object op1, Object op2, Tipo tipo) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.ENTERO);
                        return (int)Math.pow((int)op1,(int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((int)op1,(double)op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)op1,(double)op2);
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)op1,(double)op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Potencia erronea", this.linea, this.columna);

            }
        }
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
