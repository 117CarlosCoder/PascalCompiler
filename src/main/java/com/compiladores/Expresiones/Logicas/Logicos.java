
package com.compiladores.Expresiones.Logicas;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import static com.compiladores.Expresiones.Relacionales.OperadoresRelacionales.IGUALACION;

public class Logicos extends Instruccion{

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresLogicos operacionr;
    private Instruccion operandoUnico;
    
    public Logicos(Instruccion operandoUnico, OperadoresLogicos operacionr, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operacionr = operacionr;
        this.operandoUnico = operandoUnico;
    }

    public Logicos(Instruccion operando1, Instruccion operando2, OperadoresLogicos operacionr, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacionr = operacionr;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }
        

        System.out.println("Operadores " + opIzq +"  " + opDer);
        return switch (operacionr) {
            case  OR  ->
                this.or(opIzq, opDer);
            case  AND  ->
                this.and(opIzq, opDer);
            case  XOR  ->
                this.xor(opIzq, opDer);
            case  NOT  ->
                this.not(Unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.columna);
        };
    }
    
    public Object or(Object op1, Object op2 ) {
        OPERADORES compOR = new OPERADORES(operando1, operando2, IGUALACION, linea, columna); 
        return compOR.or(op1, op2, this.tipo);
    }
    
    public Object and(Object op1, Object op2 ) {
        OPERADORES compAND = new OPERADORES(operando1, operando2, IGUALACION, linea, columna); 
        this.tipo.setTipo(TipoDato.BOOLEANO);
        return compAND.and(op1, op2, this.tipo);
    }
    
    public Object xor(Object op1, Object op2 ) {
        OPERADORES compXOR = new OPERADORES(operando1, operando2, IGUALACION, linea, columna); 
        this.tipo.setTipo(TipoDato.BOOLEANO);
        return compXOR.xor(op1, op2, this.tipo);
    }
    
    public Object not(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case TipoDato.BOOLEANO -> {
                this.tipo.setTipo(TipoDato.BOOLEANO);
                return !Boolean.valueOf(op1.toString());
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.columna);
            }
        }
    }
    
}
