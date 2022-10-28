/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.sanms.siso.eft.utils;

/**
 *
 * @author salvador
 */
public enum EnumErrores {
    ERROR_VALIDACION_OBLIGATORIEDAD_1000("1000", "Campo de instancias debe ser numerico"),
    ERROR_VALIDACION_OBLIGATORIEDAD_1002("1002", "Campo de instancias esta vacio"),
    ERROR_VALIDACION_OBLIGATORIEDAD_1003("1003", "IP no encontrado, ingresa la ip y puerto"),
    ERROR_VALIDACION_OBLIGATORIEDAD_1004("1004", "IP o PUEURTO Incorrecto"),
    ERROR_VALIDACION_OBLIGATORIEDAD_1005("1005", "Debe especificar la ruta del archivo de parametros."),
    ERROR_VALIDACION_OBLIGATORIEDAD_1006("1006", "Formato de Archivo Json no es valido.");

    private EnumErrores(String cod, String msg) {
        this.cod = cod;
        this.msg = msg;
    }

    private String cod;
    private String msg;

    public String getCodigo() {
        return cod;
    }

    public String getMensaje() {
        return msg;
    }

    public static String getMensaje(String cod) {
        String msg = "";
        for (EnumErrores enumHTTP : EnumErrores.values()) {
            if (enumHTTP.cod.equalsIgnoreCase(cod)) {
                msg = enumHTTP.msg;
                break;
            }
        }
        return msg;
    }
}
