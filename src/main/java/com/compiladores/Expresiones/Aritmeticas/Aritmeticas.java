
package com.compiladores.Expresiones.Aritmeticas;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.*;

public class Aritmeticas  extends Instruccion{
    
    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    public Aritmeticas(Instruccion operandoUnico, OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
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

        return switch (operacion) {
            case SUMA ->
                this.suma(opIzq, opDer);
            case RESTA ->
                this.resta(opIzq, opDer);
            case MULTIPLICACION ->
                this.multiplicacion(opIzq, opDer);
            case DIVISION ->
                this.division(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            case MODULO ->
                this.modulo(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.columna);
        };
    }
    
    public Object suma(Object op1, Object op2 ) {
        Suma suma = new Suma(operando1, operando2,operacion, linea, columna);
        return suma.suma(op1, op2, this.tipo);
    }
    
    public Object resta(Object op1, Object op2 ) {
        Resta resta = new Resta(operando1, operando2, operacion, linea, columna);
        return resta.resta(op1, op2,this.tipo);
    }
    
    public Object multiplicacion(Object op1, Object op2 ) {
        Multiplicacion multiplicacion = new Multiplicacion(operando1, operando2, operacion, linea, columna);
        return multiplicacion.multiplicacion(op1, op2, this.tipo);
    }
    
     public Object division(Object op1, Object op2 ) {
        Division division = new Division(operando1, operando2, operacion, linea, columna);
        return division.division(op1, op2, this.tipo);
    }

    public Object potencia(Object op1, Object op2 ) {
        Potencia potencia = new Potencia(operando1, operando2, operacion, linea, columna);
        return potencia.potencia(op1, op2, this.tipo);
    }
    
    public Object modulo(Object op1, Object op2 ) {
        Modulo mod = new Modulo(operando1, operando2, operacion, linea, columna);
        return mod.mod(op1, op2, this.tipo);
    }
     
    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case TipoDato.ENTERO -> {
                this.tipo.setTipo(TipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case TipoDato.DECIMAL -> {
                this.tipo.setTipo(TipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Operador no valido para negación aritmetica", this.linea, this.columna);
            }
        }
    }
    
}
