
package com.compiladores.Expresiones.Relacionales;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;


public class Relacionales extends Instruccion{

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresRelacionales operacionr;
    private Instruccion operandoUnico;


    public Relacionales(Instruccion operando1, Instruccion operando2, OperadoresRelacionales operacionr, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacionr = operacionr;
    }
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null;
        
        opIzq = this.operando1.interpretar(arbol, tabla);
        if (opIzq instanceof Errores) {
                return opIzq;
        }
        opDer = this.operando2.interpretar(arbol, tabla);
        if (opDer instanceof Errores) {
                return opDer;
        }
        

        return switch (operacionr) {
            case  IGUALACION  ->
                this.igualacion(opIzq, opDer);
            case DIFERENCIA ->
                this.diferencia(opIzq, opDer); 
            case MENORQ ->
                this.menorQue(opIzq, opDer);
            case MENORIGQ ->
                this.menorIgualQue(opIzq, opDer);
            case MAYORQ ->
                this.mayorQue(opIzq, opDer);
            case MAYORIGQ ->
                this.mayorIgualQue(opIzq, opDer);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.columna);
        };
    }
    
    public Object igualacion(Object op1, Object op2 ) {
        Igualacion igualacion = new Igualacion(operando1, operando2, operacionr, linea, columna);
        return igualacion.igualacion(op1, op2, this.tipo);
    }
    
    public Object diferencia(Object op1, Object op2 ) {
        Diferenciacion diferencia = new Diferenciacion(operando1, operando2, operacionr, linea, columna);
        return diferencia.diferencia(op1, op2, this.tipo);
    }
    
    public Object menorQue(Object op1, Object op2 ) {
        Menor menor = new Menor(operando1, operando2, operacionr, linea, columna);
        return menor.menorQue(op1, op2, this.tipo);
    }
    
    public Object menorIgualQue(Object op1, Object op2 ) {
        Menor menor = new Menor(operando1, operando2, operacionr, linea, columna);
        return menor.menorIgualQue(op1, op2, this.tipo);
    }
    
    public Object mayorQue(Object op1, Object op2 ) {
        Mayor mayor = new Mayor(operando1, operando2, operacionr, linea, columna);
        return mayor.mayorQue(op1, op2, this.tipo);
    }
    
    public Object mayorIgualQue(Object op1, Object op2 ) {
        Mayor mayor = new Mayor(operando1, operando2, operacionr, linea, columna);
        return mayor.mayorIgualQue(op1, op2, this.tipo);
    }
}
