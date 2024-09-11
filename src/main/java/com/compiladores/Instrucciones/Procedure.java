/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.compiladores.Instrucciones;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Instrucciones.Sentencias.Transferencia.BREAK;
import com.compiladores.Instrucciones.Sentencias.Transferencia.CONTINUE;
//import com.compiladores.Instrucciones.Sentencias.Transferencia.RETURN;
//import com.compiladores.Instrucciones.Sentencias.Transferencia.RETURNEXPRESION;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author carlosl
 */
public class Procedure extends Instruccion{

    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;
    public Instruccion expresion;

    public Procedure(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, int linea, int col) {
        super(new Tipo(TipoDato.VOID), linea, col);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }

    public Procedure(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones, Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.expresion = expresion;
    }


    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        System.out.println("Metodos " + this.id);
        System.out.println("Metodos " + this.parametros);
        System.out.println("Metodos " + this.instrucciones);
        


        for (var i : instrucciones) {

            if (i instanceof BREAK || i instanceof CONTINUE ) {
                return new Errores("SEMANTICO", "Esta expresion es solo para ciclos ",this.linea, this.columna);
            }

            /*if (i instanceof RETURN) {

                return null;
            }

            if (i instanceof RETURNEXPRESION) {
                return i;
            }*/
            System.out.println("Iniciardo interpretacion");
            var resultado =  i.interpretar(arbol, tabla);

            System.out.println("Resultado en metodo " + resultado);

            if (resultado instanceof Errores) {
                return new Errores("SEMANTICO", "Expresion invalida",this.linea, this.columna);
            }

            if (resultado instanceof BREAK || resultado instanceof CONTINUE ) {
                return new Errores("SEMANTICO", "Esta expresion es solo para ciclos ",this.linea, this.columna);
            }

           /* if (resultado instanceof RETURN) {

                return null;
            }


            if (resultado instanceof RETURNEXPRESION) {
                System.out.println("Rrtorno de esultado en metodo " + resultado);
                return resultado;
            }*/
            /*if (resultado instanceof RETURNEXPRESION) {

                //var valorn = i.interpretar(arbol, tabla);
                System.out.println("return expresion metodo 2" + i);
                this.expresion = i;
                System.out.println("return expresion metodo 2" + i);
                System.out.println("Metodos *** 2 " + this.expresion);
                return resultado;
            }*/

        }
        
        /*if (this.expresion != null) {
            System.out.println("111111111111111111111");
            System.out.println("Entrando en expresion ");
            var valorn = this.expresion.interpretar(arbol, tabla);
            System.out.println("Valor desde funcion " + valorn);
            while (valorn instanceof Nativo nativo) {
                valorn = nativo.interpretar(arbol, tabla);
            }
            this.expresion = new Nativo(valorn, this.tipo, this.linea, this.columna);
            System.out.println("2222222222222222222222");
            return this.expresion;
        }*/
        arbol.Print("Procedure Correctamente");
        return null;
    }
    
}
