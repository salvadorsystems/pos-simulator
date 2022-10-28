/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.utils.EnumErrores;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author CANVIA
 */
public class ParametrosOperacion {

    static Map<String, String> obtenerParametros(String nombreTxn, int pid) {
        Map<String, String> map = null;      
        try {
            if (nombreTxn.isEmpty()) {
                JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1005.getMensaje());
            }
            
        } catch (Exception e) {
        }
        return map;
    }

    private String pathFile;

    public ParametrosOperacion(String pathFile) {
        this.pathFile = pathFile;
    }

}
