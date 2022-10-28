/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.model;

import java.util.List;

/**
 *
 * @author salvador
 */
public class Operacion {
    private String detail;
    private List<Generator> generators; 

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Generator> getGenerators() {
        return generators;
    }

    public void setGenerators(List<Generator> generators) {
        this.generators = generators;
    }

    @Override
    public String toString() {
        return "Operacion{" + "detail=" + detail + ", generators=" + generators + '}';
    }
    
}
