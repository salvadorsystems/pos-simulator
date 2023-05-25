/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.FullReport;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.proxy.Proxy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class Worker extends SwingWorker<Integer, Object[]> {

    private static final Logger log = Logger.getLogger(Worker.class);
    private String txnName;
    private int NumIns;
    private int numTxn;
    private int posIns;
    private Proxy[] listProxy;
    private String parametrosPath;
    private String templatePath;
    private String configPath;
    private List<Stream> listStream;
    private List<String> listThreadId;
    private List<Integer> listSocketId;
    private ArrayList<FullReport> listFullReport;

    private final DefaultTableModel tableModelRequest;
    private final DefaultTableModel tableModelResponse;
    private final DefaultTableModel tableModelStatus;

    private final TableColumnModel columnModelRequest;
    private final TableColumnModel columnModelResponse;
    private final TableColumnModel columnModelStatus;

    private InstanceManager execute[];

    public Worker(Proxy[] listProxy, String parametrosPath, String templatePath, List<Stream> listStream, DefaultTableModel tableModelRequest,
            DefaultTableModel tableModelResponse, DefaultTableModel tableModelStatus,
            TableColumnModel columnModelRequest, TableColumnModel columnModelResponse, TableColumnModel columnModelStatus) {
        this.listProxy = listProxy;
        this.parametrosPath = parametrosPath;
        this.templatePath = templatePath;
        this.listStream = listStream;
        this.tableModelRequest = tableModelRequest;
        this.tableModelResponse = tableModelResponse;
        this.tableModelStatus = tableModelStatus;
        this.columnModelRequest = columnModelRequest;
        this.columnModelResponse = columnModelResponse;
        this.columnModelStatus = columnModelStatus;
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

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }        

    @Override
    protected Integer doInBackground() throws Exception {
        listThreadId = new ArrayList<>();
        listSocketId = new ArrayList<>();
        execute = new InstanceManager[getNumIns()];
        publish();
         for (int i = 0; i < getNumIns(); i++) {
            execute[i] = new InstanceManager(i, "[Hilo " + i + "]", getParametrosPath(), getTemplatePath(),
                    getListStream(), tableModelRequest, tableModelResponse, tableModelStatus,
                    columnModelRequest, columnModelResponse, columnModelStatus);
            execute[i].setConfigPath(getConfigPath());
            execute[i].setTxnName(getTxnName());
            execute[i].setNumTxn(getNumTxn());
            execute[i].setProxy(listProxy[i]);
            execute[i].setListThreadId(listThreadId);
            execute[i].setListSocketId(listSocketId);
            execute[i].start();
            listThreadId.add("" + execute[i].getId());
            listSocketId.add(listProxy[i].hashCode());
            posIns = i; 
         }

        for (int i = 0; i < getNumIns(); i++) {
            do {

            } while (execute[i].isAlive());
        }

        log.info("Hilo Principal finalizado.");
        JOptionPane.showMessageDialog(null, "El mensaje enviado con exito");

        return 0;
    }

    @Override
    protected void process(final List<Object[]> rows) {
        ProcesarArchivos.setTable("status",tableModelStatus, columnModelStatus, null,listThreadId,listSocketId);
    }

    public void generarReportePDF() throws JRException, IOException {
        execute[posIns].generarReportePDF();
    }

    public void generarReporteXLS() throws JRException, IOException {
        execute[posIns].generarReporteXLS();
    }

    public void generateFullReport() throws JRException, IOException  {        
        ProcesarArchivos.generateReport("FUL","RESULT",null,tableModelStatus);
    }
}
