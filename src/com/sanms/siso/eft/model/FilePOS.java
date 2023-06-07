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
public class FilePOS {
    private static final Logger log = Logger.getLogger(FilePOS.class);
    private String workHost;
    private String workPath;
    private String workParent;

    public FilePOS(String workHost, String workPath, String workParent) {     
        this.workHost = workHost;
        this.workPath = workPath;
        this.workParent = workParent;        
    }

    public FilePOS() {
        
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

    public String getWorkHost() {
        return workHost;
    }

    @Override
    public String toString() {
        return "ArchivoRuta{" + "workhost=" + workHost + ", workPath=" + workPath + ", workParent=" + workParent + '}';
    }

    public static void setConfigWorkPath(String host, String path, String parent) {
        Gson gson = new Gson();
         
        FilePOS processorWorkPath = new FilePOS(host, path, parent);
        String json = gson.toJson(processorWorkPath);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(Constantes.PATH_CFG_POS))) {
            bw.write(json);
            log.info("Fichero Modificado");            
        } catch (IOException ex) {
            log.error("Error: " + ex); 
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

}
