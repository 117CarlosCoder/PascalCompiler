
package com.compiladores.Expresiones.Variables;

import com.compiladores.Abstracto.Instruccion;
import com.compiladores.Excepciones.Errores;
import com.compiladores.Simbolo.Arbol;
import com.compiladores.Simbolo.TablaSimbolos;
import com.compiladores.Simbolo.Tipo;
import com.compiladores.Simbolo.TipoDato;

public class AcessoVar extends Instruccion{
    private  String id;

    public AcessoVar(String id, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
    }
    
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = tabla.getVariable(this.id);
        if (valor == null) {
            return new Errores("SEMANTICA", "Variable no existente",this.linea, this.columna);
        }
        this.tipo.setTipo(valor.getTipo().getTipo());
        return valor.getValor();
    }
    
}
