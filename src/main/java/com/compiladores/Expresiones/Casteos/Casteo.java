
package com.compiladores.Expresiones.Casteos;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;


public class Casteo extends Instruccion {
    private Tipo operando1;
    private Instruccion operando2;

    public Casteo(Tipo operando1, Instruccion operando2, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null;
        opIzq = this.operando1;
        if (opIzq instanceof Errores) {
            return opIzq;
        }
        opDer = this.operando2.interpretar(arbol, tabla);
        if (opDer instanceof Errores) {
            return opDer;
        }
        return this.cast(opIzq, opDer);
    }
    
    public Object cast(Object op1,Object op2) {
        var tipo1 = this.operando1.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        System.out.println("Tipo operadores " + tipo1 + "    " + tipo2);
        
        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                       tipo.setTipo(TipoDato.ENTERO);
                       return (int) ((int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                       tipo.setTipo(TipoDato.ENTERO);
                       return (int) ((double)op2);
                    }
                    case TipoDato.CARACTER -> {
                       tipo.setTipo(TipoDato.ENTERO);
                        int sumaAscii = 0;
                        try {
                            String palabra = (String) op2;
                            op2 = palabra.toCharArray();
                            char[] charArray = (char[]) op2;

                            for (char c : charArray) {
                                sumaAscii += (int) c;
                            }
                        } catch (Exception e) {
                            System.out.println("Error de conversion " + e);
                            sumaAscii = (int) ((char) op2);
                        }
                       return sumaAscii;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Casteo a entero erroneo", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                       tipo.setTipo(TipoDato.DECIMAL);
                       return (double) ((int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                       tipo.setTipo(TipoDato.DECIMAL);
                       return (double) ((double)op2);
                    }
                    case TipoDato.CARACTER -> {
                       tipo.setTipo(TipoDato.DECIMAL);
                        int sumaAscii = 0;
                        try {
                            String palabra = (String) op2;
                            op2 = palabra.toCharArray();
                            char[] charArray = (char[]) op2;

                            for (char c : charArray) {
                                sumaAscii += (int) c;
                            }
                        } catch (Exception e) {
                            System.out.println("Error de conversion " + e);
                            sumaAscii = (int) ((char) op2);
                        }
                        return (double)sumaAscii;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Casteo a decimal erroneo", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2) {
                    case TipoDato.CADENA -> {
                       tipo.setTipo(TipoDato.CADENA);
                       return (String) ((String)op2);
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Casteo a cadena erroneo", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                       tipo.setTipo(TipoDato.CARACTER);
                        System.out.println("valor " + (char) ((int)op2));
                       return (char) ((int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                       tipo.setTipo(TipoDato.CARACTER);
                       System.out.println("valor " +op2);
                       return (char) ((double)op2);
                    }
                    case TipoDato.CARACTER -> {
                       tipo.setTipo(TipoDato.CARACTER);
                       System.out.println("valor " +op2);
                       return (char) ((char)op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Casteo a caracter erroneo", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                       tipo.setTipo(TipoDato.BOOLEANO);
                       return (boolean) ((boolean)op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Casteo a booleano erroneo", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Casteo erroneo", this.linea, this.columna);

            }
        }
    }

    
}

