
package com.compiladores.Expresiones.Relacionales;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class Diferenciacion extends Instruccion{

    private final Instruccion operando1;
    private final Instruccion operando2;
   
    public Diferenciacion(Instruccion operando1, Instruccion operando2, OperadoresRelacionales operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
    }

     public Object diferencia(Object op1, Object op2, Tipo tipo){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 != (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        String palabra =  (String) op2;
                        op2 = palabra.toCharArray();
                        char[] charArray = (char[]) op2;
                        int sumaAscii = 0;

                        for (char c : charArray) {
                            sumaAscii += (int) c;
                        }
                        return (int) op1 != sumaAscii;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Diferencia erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 != (double) ((int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        String palabra =  (String) op2;
                        op2 = palabra.toCharArray();
                        char[] charArray = (char[]) op2;
                        int sumaAscii = 0;

                        for (char c : charArray) {
                            sumaAscii += (int) c;
                        }
                        return (double) op1 != (double) sumaAscii;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Diferencia erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                  switch (tipo2) {
                      case TipoDato.BOOLEANO -> {
                          tipo.setTipo(TipoDato.BOOLEANO);
                          return Boolean.parseBoolean(op1.toString()) != Boolean.parseBoolean(op2.toString());
                      }

                      default -> {
                          return new Errores("SEMANTICO", "Diferencia erronea", this.linea, this.columna);
                      }
                  }
            }
            case TipoDato.CARACTER -> {
                String palabra =  (String) op1;
                char[] charAr = palabra.toCharArray();
                char[] charArray = (char[]) charAr;
                int sumaAscii = 0;

                for (char c : charArray) {
                    sumaAscii += (int) c;
                }
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return sumaAscii != (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return sumaAscii != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return !op1.toString().equals(op2.toString());
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Diferencia erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2) {
                    case TipoDato.CADENA -> {
                        tipo.setTipo(TipoDato.BOOLEANO);
                        return !op1.toString().equals(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Diferencia erronea", this.linea, this.columna);
                    }
                }
                
            }
            default -> {
                return new Errores("SEMANTICO", "Diferencia erronea", this.linea, this.columna);

            }
        }
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
