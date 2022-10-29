/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import java.util.List;
import java.util.Map;

/**
 *
 * @author salvador
 */
public class ProcesarOperacion {

    ParametrosOperacion parametrosOperacion;
    Map<String, Map<String, Map<String, String>>> templateMapList;

    public void setup(String rutaParametros) {
        parametrosOperacion = new ParametrosOperacion(rutaParametros);
        //templateMapList = 
    }

    public String ConstruirTrama(List<Stream> listStream, int pid) {

        Map<String, String> params = null;

        try {
            params = parametrosOperacion.obtenerParametros(listStream, pid);
        } catch (Exception e) {
        }

        return "";
    }

}
