/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author salvador
 */
public class Man {

    public static void main(String[] args) {
        Man man = new Man();
        //man.ConstruirTrama();
        String cc = man.realizarunsplitconindex(); 
        String fecha = "tmp/mastercard.trace;6;1";
        String[] fechaSeparada = cc.split(";");
        for (int i = 0; i < fechaSeparada.length; i++) {
            String string = fechaSeparada[i];
            System.out.println(string);
        }
    }

    public String  realizarunsplitconindex() {
        String colores = "sequence(tmp/mastercard.trace;6;1)";

        int inicio = colores.indexOf("(");
        int fin = colores.indexOf(")");
        String cont = colores.substring(inicio + 1, fin);
        //System.out.println(cont);
        return cont;
    }

    public String ConstruirTrama() {
        Map<String, String> params = new HashMap<>();

        for (int i = 0; i < 2; i++) {
            Map<String, String> hm1 = obtenerParametros(i);
            params.putAll(hm1);
        }

        for (int i = 0; i < 2; i++) {
            Map<String, String> hm1 = obtenerParametros1(i);
            params.putAll(hm1);
        }
        System.out.println(params);
        return "";
    }

    public Map<String, String> obtenerParametros(int i) {
        Map<String, String> hm1 = new HashMap<>();
        if (i == 1) {
            hm1.put("1", "A");
            hm1.put("2", "A");
        } else {
            hm1.put("3", "A");
            hm1.put("4", "A");
        }
        return hm1;
    }

    public Map<String, String> obtenerParametros1(int i) {
        Map<String, String> hm1 = new HashMap<>();
        if (i == 1) {
            hm1.put("5", "B");
            hm1.put("6", "B");
        } else {
            hm1.put("7", "B");
            hm1.put("8", "B");
        }
        return hm1;
    }

}
