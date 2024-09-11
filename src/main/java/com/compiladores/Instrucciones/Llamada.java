
package com.compiladores.Instrucciones;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
//import com.compiladores.Instrucciones.Sentencias.Transferencia.RETURNEXPRESION;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

import java.util.LinkedList;

public class Llamada extends Instruccion {
    
    public String id;
    public LinkedList<Instruccion> parametros;
    public Instruccion valorR;
    public TablaSimbolos nuevaTabla;

    public Llamada(String id, LinkedList<Instruccion> parametros, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.parametros = parametros;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var busqueda = arbol.getFuncion(id);
        if (nuevaTabla != null) {
        tabla.setTablasTotales(nuevaTabla);    
        }
        
        if (busqueda == null ) {
            return new Errores("SEMANTICO", "Funcion no existente", this.linea, this.columna);
        }
        
        
        
        if (busqueda instanceof Procedure metodo) {
            
            nuevaTabla = new TablaSimbolos(arbol.getTablaGlobal());
            nuevaTabla.setNombre("-LLamada-Metodo"+ this.id);
            if (metodo.parametros.size() != this.parametros.size()) {
                return new Errores("SEMANTICO", "Parametros erroneos", this.linea, this.columna);
            }
            nuevaTabla.setNombre(tabla.getNombre() + "-Llamada");
            //tabla.setTablasTotales(nuevaTabla);
            for (int i = 0; i < this.parametros.size(); i++) {
                var identificador = (String) metodo.parametros.get(i).get("id");
                var valor =  this.parametros.get(i);
                var tipo2 = (Tipo) metodo.parametros.get(i).get("tipo");
                var declaracionParametro = new Declaracion("var",identificador, tipo2, this.linea, this.columna);
                var resultado  = declaracionParametro.interpretar(arbol, nuevaTabla);
                
                
                if (resultado instanceof Errores) {
                    return resultado;
                }
                
                var valorinterpretado = valor.interpretar(arbol, tabla);
                
                if (valorinterpretado instanceof Errores) {
                    return valorinterpretado;
                }
                
                var variable = nuevaTabla.getVariable(identificador);
                
                if (variable == null) {
                    return new Errores("SEMANTICO", "Error declaracion de parametros", this.linea, this.columna);
                }
                
                if (variable.getTipo().getTipo() != valor.tipo.getTipo()) {
                    return new Errores("SEMANTICO", "Error en tipo de parametro", this.linea, this.columna);
                }
                
                variable.setValor(valorinterpretado);
                
                
            }
            
            
            System.out.println("Tabla " + nuevaTabla.getTablaAct());
            //System.out.println("Tabla valor 1 " + ((Simbolo)nuevaTabla.getTablaAct().get("a")).getValor());
            //System.out.println("Tabla valor 2 " + ((Simbolo)nuevaTabla.getTablaAct().get("b")).getValor());
            var resultadoFuncion = metodo.interpretar(arbol, nuevaTabla);
            
            if (resultadoFuncion instanceof Errores) {
                return resultadoFuncion;
            }
            
            
            /*if (resultadoFuncion instanceof RETURNEXPRESION returnexpresion) {
                    System.out.println("return expresion metodo" + returnexpresion);
                    var valorn = returnexpresion.interpretar(arbol, nuevaTabla);
                    System.out.println("return expresion metodo" + valorn);
                    //metodo.expresion = instruccion;
                    System.out.println("return expresion metodo" + valorn);
                    this.tipo.setTipo(returnexpresion.tipo.getTipo());
                    System.out.println("2L************************2L");
                    return valorn;
                
            }*/
            
            
            
           /*for (Instruccion instruccion : metodo.instrucciones) {
               System.out.println("1L************************1L");
               System.out.println("Instruccion en llamada " + instruccion);
                if (instruccion instanceof RETURNEXPRESION returnexpresion) {
                    System.out.println("return expresion metodo" + returnexpresion);
                    var valorn = returnexpresion.interpretar(arbol, nuevaTabla);
                    System.out.println("return expresion metodo" + valorn);
                    //metodo.expresion = instruccion;
                    System.out.println("return expresion metodo" + valorn);
                    this.tipo.setTipo(returnexpresion.tipo.getTipo());
                    System.out.println("2L************************2L");
                    return valorn;
                }
                System.out.println("2L************************2L");
            }*/
            
            
            /*if (metodo.expresion != null) {
                valorR =  metodo.expresion;
                var varornn =valorR.interpretar(arbol, tabla) ;
                if (valorR instanceof AcessoVar) {
                    System.out.println("Valor desde llamada " + varornn );
                }
                
               return metodo.expresion;
            }*/
            
        }
        
        return null;
    }
    
}
