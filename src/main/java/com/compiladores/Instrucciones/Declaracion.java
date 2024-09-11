/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.Instrucciones;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Expresiones.Nativo;
import com.compiladores.Simbolo.*;

import java.util.LinkedList;

public class Declaracion extends Instruccion{
    
    public String identificador;
    public String mutabilidad;
    public Instruccion valor;
    public String entorno ="global";
    public String TipoDec = "";
    public String vincial = "";
    public String vfinal = "";
    public LinkedList<Instruccion> valores;
    //public LinkedList<Simbolo> simbolos;
    public LinkedList<Simbolo> simbolos2;

    public Declaracion(String mutabilidad , String identificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = valor;
    }

    public Declaracion(String mutabilidad , String identificador, Instruccion valor, Tipo tipo, String entorno, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = valor;
        this.entorno = entorno;
        System.out.println("Entorno ingresado : " + entorno);
    }

    public Declaracion(String mutabilidad , String identificador, Instruccion valor, int linea, int columna) {
        super(valor.tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = valor;
    }

    public Declaracion(String mutabilidad , String identificador, Instruccion valor,String entorno, int linea, int columna) {
        super(valor.tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valor = valor;
        this.entorno = entorno;
        System.out.println("Entorno ingresado : " + entorno);
    }
    
    public Declaracion(String mutabilidad ,String identificador, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.tipo = tipo;
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
    }

    public Declaracion(String mutabilidad ,String identificador, Tipo tipo,String entorno, int linea, int columna) {
        super(tipo, linea, columna);
        this.tipo = tipo;
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.entorno = entorno;
        System.out.println("Entorno ingresado : " + entorno);
    }

    public Declaracion(String mutabilidad, String identificador,Tipo tipo, LinkedList<Instruccion> valores, String TipoDec, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.valores = valores;
        this.TipoDec = TipoDec;
    }

    public Declaracion(String mutabilidad, String identificador, Tipo tipo, String TipoDec, String vinicial , String vfinal, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.mutabilidad = mutabilidad;
        this.TipoDec = TipoDec;
        this.vincial = vinicial;
        this.vfinal = vfinal;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Simbolo[] simbolos = null;


        System.out.println("Tipo declaracion: " + TipoDec);
       if (TipoDec.equals("vector")) {

           if (!vfinal.isEmpty()){
               System.out.println("Vfinal: " + vfinal);
               simbolos = new Simbolo[Integer.parseInt(vfinal)+1];
           }

/*           for (int i = 0; i < Integer.parseInt(vfinal); i++) {


           }*/
           boolean mutabilidadVar = this.mutabilidad.equals("var");
           Simbolo simb = new Simbolo(this.tipo, this.mutabilidad, this.identificador, null, this.linea, this.columna, mutabilidadVar, entorno);
           assert simbolos != null;
           simbolos[0] = simb;
           for (int i = 1; i < simbolos.length; i++) {
               simbolos[i] = new Simbolo(this.tipo, this.mutabilidad, this.identificador, null, this.linea, this.columna, mutabilidadVar, entorno);
           }
           System.out.println("Valor agregado a la lista");
           System.out.println("Vector a tabla ");
           System.out.println(simbolos.length);
           boolean creacion = tabla.setVariablesVector(simbolos);
           System.out.println("Vector agregado ");
           if (!creacion) {
               return new Errores("SEMANTICO", "Vector ya existente", this.linea, this.columna);
           }

           return null;
       }

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