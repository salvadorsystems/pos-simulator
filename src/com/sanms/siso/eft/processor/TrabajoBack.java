/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.proxy.Proxy;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/**
 *
 * @author CANVIA
 */
public class TrabajoBack extends SwingWorker<Integer, Object[]> {

    private String txnName;
    private int NumIns;
    private int numTxn;
    private JTable tableRequest;
    private JTable tableResponse;
    private Proxy[] listProxy;
    private String parametrosPath;
    private String templatePath;
    private List<Stream> listStream;

    public TrabajoBack(Proxy[] listProxy, String parametrosPath, String templatePath, List<Stream> listStream) {
        this.listProxy = listProxy;
        this.parametrosPath = parametrosPath;
        this.templatePath = templatePath;
        this.listStream = listStream;
    }

    public String getTxnName() {
        return txnName;
    }

    public void setTxnName(String txnName) {
        this.txnName = txnName;
    }

    public int getNumIns() {
        return NumIns;
    }

    public void setNumIns(int NumIns) {
        this.NumIns = NumIns;
    }

    public int getNumTxn() {
        return numTxn;
    }

    public void setNumTxn(int numTxn) {
        this.numTxn = numTxn;
    }

    public JTable getTableRequest() {
        return tableRequest;
    }

    public void setTableRequest(JTable tableRequest) {
        this.tableRequest = tableRequest;
    }

    public JTable getTableResponse() {
        return tableResponse;
    }

    public void setTableResponse(JTable tableResponse) {
        this.tableResponse = tableResponse;
    }

    public Proxy[] getListProxy() {
        return listProxy;
    }

    public void setListProxy(Proxy[] listProxy) {
        this.listProxy = listProxy;
    }

    public String getParametrosPath() {
        return parametrosPath;
    }

    public void setParametrosPath(String parametrosPath) {
        this.parametrosPath = parametrosPath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public List<Stream> getListStream() {
        return listStream;
    }

    public void setListStream(List<Stream> listStream) {
        this.listStream = listStream;
    }
        
    @Override
    protected Integer doInBackground() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
