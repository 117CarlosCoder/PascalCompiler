
package com.compiladores.Expresiones.Logicas;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Expresiones.Relacionales.OperadoresRelacionales;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class OPERADORES extends Instruccion{

    private final Instruccion operando1;
    private final Instruccion operando2;
   
    public OPERADORES(Instruccion operando1, Instruccion operando2, OperadoresRelacionales operacion, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
    }
    
    public Object or(Object op1, Object op2, Tipo tipo){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        System.out.println("Tipo operadores " + tipo1 + "    " + tipo2);
        
        switch (tipo1) {
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                       tipo.setTipo(TipoDato.BOOLEANO);
                       return Boolean.parseBoolean(op1.toString()) || Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "OR erroneo", this.linea, this.columna);
                    }
                }
            }
            
            default -> {
                return new Errores("SEMANTICO", "OR erroneo", this.linea, this.columna);

            }
        }
    }
    
    public Object and(Object op1, Object op2, Tipo tipo){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                       tipo.setTipo(TipoDato.BOOLEANO);
                       return Boolean.parseBoolean(op1.toString()) && Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "AND erroneo", this.linea, this.columna);
                    }
                }
            }
            
            default -> {
                return new Errores("SEMANTICO", "AND erroneo", this.linea, this.columna);

            }
        }
    }
    
    public Object xor(Object op1, Object op2, Tipo tipo){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return Boolean.parseBoolean(op1.toString()) != Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "XOR erroneo", this.linea, this.columna);
                    }
                }
            }
            
            default -> {
                return new Errores("SEMANTICO", "XOR erroneo", this.linea, this.columna);

            }
        }
    }
    
    public Object not(Object op1){
        var tipo1 = this.operando1.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.BOOLEANO -> {
                return !Boolean.valueOf(op1.toString());
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.columna);

            }
        }
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
