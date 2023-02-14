/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.model;

import com.google.gson.Gson;
import com.sanms.siso.eft.utils.Constantes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class ArchivoRuta {
    private static final Logger log = Logger.getLogger(ArchivoRuta.class);
    private String workPath;
    private String workParent;

    public ArchivoRuta(String workPath, String workParent) {
        this.workPath = workPath;
        this.workParent = workParent;
    }

    public ArchivoRuta() {
        
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public String getWorkParent() {
        return workParent;
    }

    public void setWorkParent(String workParent) {
        this.workParent = workParent;
    }

    @Override
    public String toString() {
        return "ProcessorWorkPath{" + "workPath=" + workPath + ", workParent=" + workParent + '}';
    }    

    public static void setConfigWorkPath(String path, String parent) {
        Gson gson = new Gson();
        ArchivoRuta processorWorkPath = new ArchivoRuta(path, parent);
        String json = gson.toJson(processorWorkPath);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.RUTA_HOST))) {
            bw.write(json);
            log.info("Fichero Modificado");            
        } catch (IOException ex) {
            log.error("Error: " + ex); 
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

}
