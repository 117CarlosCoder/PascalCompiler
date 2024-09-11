
package com.compiladores.Expresiones.Variables;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class AcessoVar extends Instruccion{
    private  String id;
    private Instruccion index;

    public AcessoVar(String id, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
    }

    public AcessoVar(String id,Instruccion index, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.index = index;
    }
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {

        if (this.index != null ) {
            var valorindex =  this.index.interpretar(arbol, tabla);
            if (valorindex instanceof Errores) {
                return valorindex;
            }

            var valor = tabla.getVariableVector(this.id, (int)valorindex);
            var valor2 = tabla.getVariable(this.id);

            if (valor == null && valor2 == null) {
                System.out.println("Error aqui");
                return new Errores("SEMANTICA", "Variable no existente", this.linea, this.columna);
            }

            if(valor != null){
                this.tipo.setTipo(valor.getTipo().getTipo());
                return valor.getValor();
            }

            if(valor2 != null){
                this.tipo.setTipo(valor2.getTipo().getTipo());
                return valor2.getValor();
            }


        }


        var valor = tabla.getVariable(this.id);
        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente",this.linea, this.columna);
        }
        this.tipo.setTipo(valor.getTipo().getTipo());
        arbol.Print("Acceso Correctamente");
        return valor.getValor();
    }
    
}
