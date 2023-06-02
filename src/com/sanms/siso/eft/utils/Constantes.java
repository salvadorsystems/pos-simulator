/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.utils;

/**
 *
 * @author CANVIA
 */
public class Constantes {

    public Constantes() {
    }
    public static final String BACK = "../";
    public static final String ENVIROMENT_BASE = "pos-simulator/";
    public static final String PATH_CFG_POS = BACK.concat(ENVIROMENT_BASE).concat("/pos.json");
    public static final String PATH_IMG_OFF = BACK.concat("img/img1.png");
    public static final String PATH_IMG_ON = BACK.concat("img/img2.png");
    public static final String PATH_IMG_IPADRESS = BACK.concat("img/ipAdress9.png");
    public static final String PATH_PLANTILLA_PDF = BACK.concat(ENVIROMENT_BASE).concat("src/resources/reports/report_PDF.jasper");
    public static final String PATH_PLANTILLA_XLS = BACK.concat(ENVIROMENT_BASE).concat("src/resources/reports/report_XLS.jasper");
    public static final String PATH_PLANTILLA_FULL = BACK.concat(ENVIROMENT_BASE).concat("src/resources/reports/report_XLS_Full.jasper");
    public static final String PATH_LOG4J_PROPERTIES = BACK.concat(ENVIROMENT_BASE).concat("src/resources/log4j.properties");
    public static final String PATH_OUTPUT_REPORT_PDF = BACK.concat(ENVIROMENT_BASE).concat("report/report.pdf");
    public static final String PATH_OUTPUT_REPORT_XLS = BACK.concat(ENVIROMENT_BASE).concat("report/report.xls");
    public static final String PATH_OUTPUT_REPORT_FULL_XLS = BACK.concat(ENVIROMENT_BASE).concat("report/full_report.xls");
}
