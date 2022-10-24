/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.entity;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author salvador
 */
public class FileWorkPathEntity {

    private String workPath;
    private String workParent;

    public FileWorkPathEntity(String workPath, String workParent) {
        this.workPath = workPath;
        this.workParent = workParent;
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
        FileWorkPathEntity processorWorkPath = new FileWorkPathEntity(path, parent);
        String json = gson.toJson(processorWorkPath);
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("../SimulatorProcesos/ProcesosMC.json"))) {
            bw.write(json);
            System.out.println("Fichero Modificado");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
    }

}
