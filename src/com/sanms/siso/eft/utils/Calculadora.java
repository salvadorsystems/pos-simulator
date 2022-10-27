/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author salvador
 */
public class Calculadora {

    public static void main(String[] args) {
        readfile();
    }

    public static void readfile() {

        String[] pathnames;
        File f = new File("C:\\SISO\\txnclient\\CMACICA\\config\\BCMastercCard_Remote");

// This filter will only include files ending with
        FilenameFilter filter = (File f1, String name) -> name.endsWith(".config");

        pathnames = f.list(filter);
        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
        }
    }

}
