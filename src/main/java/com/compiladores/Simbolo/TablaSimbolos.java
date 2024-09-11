
package com.compiladores.Simbolo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class TablaSimbolos {
    private LinkedList<TablaSimbolos> tablasTotales;
    private TablaSimbolos tablaAnt;
    private HashMap<String, Object> tablaAct;
    private String nombre;

    public TablaSimbolos() {
        this.tablasTotales = new LinkedList<>();
        this.tablaAct = new HashMap<>();
        this.nombre = "";
    }

    public TablaSimbolos(TablaSimbolos tablaAnt) {
        this.tablasTotales = new LinkedList<>();
        this.tablaAnt = tablaAnt;
        this.tablaAct = new HashMap<>();
        this.nombre = "";
    }

    public TablaSimbolos getTablaAnt() {
        return tablaAnt;
    }

    public void setTablaAnt(TablaSimbolos tablaAnt) {
        this.tablaAnt = tablaAnt;
    }

    public HashMap<String, Object> getTablaAct() {
        return tablaAct;
    }

    public void setTablaAct(HashMap<String, Object> tablaAct) {
        this.tablaAct = tablaAct;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean setVariablesVector(Simbolo[] simbolo){
        Simbolo busqueda = (Simbolo) this.tablaAct.get(simbolo[0].getId().toLowerCase());
        System.out.println("Entorno : " + simbolo[0].getEntorno());
        if (busqueda == null) {
            this.tablaAct.put(simbolo[0].getId().toLowerCase(), simbolo);
            return true;
        }



        return false;
    }


    public boolean setVariable(Simbolo simbolo){
        Simbolo busqueda = (Simbolo) this.tablaAct.get(simbolo.getId().toLowerCase());
        System.out.println("Entorno : " + simbolo.getEntorno());
        if (busqueda == null) {
            this.tablaAct.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }



        return false;
    }
    
    public Simbolo getVariable(String ID){
        for (TablaSimbolos i  = this;  i != null; i= i.getTablaAnt()) {
            //Simbolo busqueda = (Simbolo) i.tablaAct.get(ID.toLowerCase());
            Simbolo busqueda = null;
            if (i.tablaAct.get(ID.toLowerCase()) instanceof Simbolo simbolo) {
                busqueda  = simbolo;
            }
            if (busqueda != null) {
                return busqueda;
            }
        }
        
        return null;
    }

    public Simbolo getVariableVector(String ID ,int index){
        for (TablaSimbolos i  = this;  i != null; i= i.getTablaAnt()) {
            Simbolo busqueda = null;
            System.out.println("Id  =  " + ID + " Index = " + index);
            System.out.println(i);
            System.out.println(i.tablaAct.get(ID.toLowerCase()));
            if (i.tablaAct.get(ID.toLowerCase()) instanceof  Simbolo[] simbolos) {
                System.out.println(Arrays.toString(simbolos));
                busqueda  = simbolos[index];
            }

            if (busqueda != null) {
                return busqueda;
            }
        }

        return null;
    }

    public LinkedList<TablaSimbolos> getTablasTotales() {
        return tablasTotales;
    }

    public void setTablasTotales(TablaSimbolos tablasTotales) {
        this.tablasTotales.add(tablasTotales);
    }
    
    
    
}
