/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.model;

/**
 *
 * @author salvador
 */
public class Stream {
    private String alias;
    private int baseStream;
    private String template;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getBaseStream() {
        return baseStream;
    }

    public void setBaseStream(int baseStream) {
        this.baseStream = baseStream;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "Streams{" + "alias=" + alias + ", baseStream=" + baseStream + ", template=" + template + '}';
    }
        
}
