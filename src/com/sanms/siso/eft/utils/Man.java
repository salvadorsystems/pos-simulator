/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author salvador
 */
public class Man {

    public static void main(String[] args) {

        try {

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            Date fechaUltimaSincro = format.parse("16/09/1995");
            Date fechaActual = format.parse("19/09/1995");;

            int dias = (int) ((fechaActual.getTime() - fechaUltimaSincro.getTime()) / 86400000);

            System.out.println("Hay " + dias + " dias de diferencia");
        } catch (Exception e) {
        }

    }

}
