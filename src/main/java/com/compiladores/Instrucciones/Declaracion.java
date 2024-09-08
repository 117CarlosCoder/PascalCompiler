/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.Instrucciones;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Expresiones.Nativo;
import com.compiladores.Simbolo.*;

public class Declaracion extends Instruccion{
    
    public String identificador;
    public String mutabilidad;
    public Instruccion valor;
    public String entorno ="global";

    public Declaracion(String mutabilidad , String identificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = valor;
    }

    public Declaracion(String mutabilidad , String identificador, Instruccion valor, int linea, int columna) {
        super(valor.tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = valor;
    }
    
    public Declaracion(String mutabilidad ,String identificador, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.tipo = tipo;
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {

        if(this.valor == null){
            if(this.tipo.getTipo()== TipoDato.ENTERO){
                this.valor = new Nativo(0, new Tipo(TipoDato.ENTERO), 0, 0);
            }
            if(this.tipo.getTipo()== TipoDato.DECIMAL){
                this.valor = new Nativo(0.0, new Tipo(TipoDato.DECIMAL), 0, 0);
            }
            if(this.tipo.getTipo()== TipoDato.CADENA){
                this.valor = new Nativo("", new Tipo(TipoDato.CADENA), 0, 0);
            }
            if(this.tipo.getTipo()== TipoDato.CARACTER){
                this.valor = new Nativo('0', new Tipo(TipoDato.CARACTER), 0, 0);
            }
            if(this.tipo.getTipo()== TipoDato.BOOLEANO){
                this.valor = new Nativo(true, new Tipo(TipoDato.BOOLEANO), 0, 0);
            }
        }
        var interpretarValor = this.valor.interpretar(arbol, tabla);
        if (interpretarValor instanceof Errores){
            return interpretarValor;
        }
        
        if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
            return new Errores("SEMANTICO", "Tipos erroneos", this.linea, this.columna);
        }

        System.out.println("Mutabilidad: " + this.mutabilidad);
        boolean mutabilidadVar = this.mutabilidad.equals("var") || this.mutabilidad.equals("type");
        Simbolo simb = new Simbolo(this.tipo,this.mutabilidad, this.identificador, interpretarValor,this.linea, this.columna,mutabilidadVar, entorno);
        
        boolean creacion = tabla.setVariable(simb);
        
        if (simb.getEntorno().equals("while") && !creacion) {
            var variable = tabla.getVariable(simb.getId());
            variable.setValor(simb.getValor());
        }

        if (simb.getEntorno().equals("for") && !creacion) {
            var variable = tabla.getVariable(simb.getId());
            variable.setValor(simb.getValor());
        }

        if (simb.getEntorno().equals("dowhile") && !creacion) {
            var variable = tabla.getVariable(simb.getId());
            variable.setValor(simb.getValor());
        }

        if (simb.getEntorno().equals("if") && !creacion) {
            var variable = tabla.getVariable(simb.getId());
            variable.setValor(simb.getValor());
        }

        if (simb.getEntorno().equals("matchcase") && !creacion) {
            var variable = tabla.getVariable(simb.getId());
            variable.setValor(simb.getValor());
        }

        if (!creacion) {
            return new Errores("SEMANTICO", "Variable ya existente", this.linea, this.columna);
        }

        return null;
    }


    public String getEntorno() {
        return entorno;
    }

    public Instruccion getValor() {
        return valor;
    }



    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }
}