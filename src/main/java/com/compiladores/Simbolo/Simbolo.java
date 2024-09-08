
package com.compiladores.Simbolo;


public class Simbolo {
    private Tipo tipo;
    private String tipoDeclaracion;
    private String id;
    private Object valor;
    private int linea;
    private int columna;
    private boolean mutabilidad;
    private String entorno = "";

    public Simbolo(Tipo tipo, String tipoDeclaracion, String id, Object valor, int linea, int columna, boolean mutabilidad, String entorno) {
        this.tipo = tipo;
        this.tipoDeclaracion = tipoDeclaracion;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.mutabilidad = mutabilidad;
        this.entorno = entorno;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean isMutabilidad() {
        return mutabilidad;
    }

    public void setMutabilidad(boolean mutabilidad) {
        this.mutabilidad = mutabilidad;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getTipoDeclaracion() {
        return tipoDeclaracion;
    }

    public void setTipoDeclaracion(String tipoDeclaracion) {
        this.tipoDeclaracion = tipoDeclaracion;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }

    
    
}
