package com.compiladores.AnalizadorSintactico;

import com.compiladores.AnalizadorLexico.Lexico;

import java.io.StringReader;

/**
 *
 * @author carlosl
 */
public class Analizador {

    public Analizador() {
    }
    
    public void interpretar(String Entrada) {

        parser pars;
        try {
            System.out.println("Texto de Entrada  \n" + Entrada);
            if(!Entrada.isEmpty()){
                StringReader stringReader = new StringReader(Entrada);
                pars = new parser(new Lexico(stringReader));
                pars.parse();
            }
        } catch (Exception ex) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println("Causa: "+ex.getCause());
        }
    }
}
