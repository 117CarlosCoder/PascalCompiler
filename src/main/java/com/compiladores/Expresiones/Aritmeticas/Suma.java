
package com.compiladores.Expresiones.Aritmeticas;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class Suma extends Instruccion{

    private final Instruccion operando1;
    private final Instruccion operando2; 
    private OperadoresAritmeticos operacion;
    
   
    public Suma(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }
    
    public Object suma(Object op1, Object op2, Tipo tipo){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1) + (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.ENTERO);
                        int sumaAscii = 0;
                        try{
                            String palabra =  (String) op2;
                            op2 = palabra.toCharArray();
                            char[] charArray = (char[]) op2;
                            

                            for (char c : charArray) {
                                sumaAscii += (int) c;
                            }
                        }catch(Exception e){
                            System.out.println("Error de conversion " +e);
                            sumaAscii = (int)((char)op2);
                        }
                        return (int) op1 + sumaAscii;
                    }
                    case TipoDato.CADENA -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
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
                        return (double) op1 + (double) ((int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        int sumaAscii = 0;
                        try{
                            String palabra =  (String) op2;
                            op2 = palabra.toCharArray();
                            char[] charArray = (char[]) op2;
                            

                            for (char c : charArray) {
                                sumaAscii += (int) c;
                            }
                        }catch(Exception e){
                            System.out.println("Error de conversion " +e);
                            sumaAscii = (int)((char)op2);
                        }
                        
                        return (double) op1 + (double) sumaAscii;
                    }
                    case TipoDato.CADENA -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                int sumaAscii = 0;
                try{
                    String palabra =  (String) op1;
                    char[] charAr = palabra.toCharArray();
                    char[] charArray = (char[]) charAr;
                    

                    for (char c : charArray) {
                        sumaAscii += (int) c;
                    }   
                }catch(Exception e){
                    System.out.println("Error de conversion a string es char" + e);
                    sumaAscii = (int)((char)op1);                    
                }
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.ENTERO);
                        return sumaAscii + (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int)sumaAscii) + (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case TipoDato.CADENA -> {
                         tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.CADENA);
                        String resultado;
                        try{
                            String palabra =  (String) op2;
                            op2 = palabra.toCharArray();
                            char[] charArray = (char[]) op2;
                            
                            StringBuilder builder = new StringBuilder();
                            for (char c : charArray) {
                                builder.append(c);
                            }
                            resultado = builder.toString();
                        }catch(Exception e){
                            System.out.println("Error de conversion " +e);
                            resultado = op2.toString();
                        }
                        
                        return op1.toString() + resultado;
                    }
                    case TipoDato.CADENA -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case TipoDato.BOOLEANO -> {
                        tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
                
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);

            }
        }
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
