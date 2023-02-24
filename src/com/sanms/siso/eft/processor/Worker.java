/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.proxy.Proxy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

/**
 *
 * @author CANVIA
 */
public class Worker extends SwingWorker<Integer, Object[]> {

    private static final Logger log = Logger.getLogger(Worker.class);
    private String txnName;
    private int NumIns;
    private int numTxn;
    private int posIns;
    private JTable tableRequest;
    private JTable tableResponse;
    private JTable tableStatus;
    private Proxy[] listProxy;
    private String parametrosPath;
    private String templatePath;
    private List<Stream> listStream;
    private List<String> listThreadId;
    private List<Integer> listSocketId;

    private DefaultTableModel tableModelRequest;
    private DefaultTableModel tableModelResponse;
    private DefaultTableModel tableModelStatus;

    private TableColumnModel columnModelRequest;
    private TableColumnModel columnModelResponse;

    private InstanceManager execute[];
    //private InstanceManager execute;

    public Worker(Proxy[] listProxy, String parametrosPath, String templatePath, List<Stream> listStream) {
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

    public JTable getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(JTable tableStatus) {
        this.tableStatus = tableStatus;
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
        tableModelRequest = (DefaultTableModel) tableRequest.getModel();
        tableModelResponse = (DefaultTableModel) tableResponse.getModel();
        tableModelStatus = (DefaultTableModel) tableStatus.getModel();
        columnModelRequest = getTableRequest().getColumnModel();
        columnModelResponse = getTableResponse().getColumnModel();
        listThreadId = new ArrayList<>();
        listSocketId = new ArrayList<>();
        execute = new InstanceManager[getNumIns()];
        publish();
        for (int i = 0; i < getNumIns(); i++) {
            execute[i] = new InstanceManager(i, "[Hilo " + i + "]", getParametrosPath(), getTemplatePath(), getListStream());
            execute[i].setTxnName(getTxnName());
            execute[i].setNumTxn(getNumTxn());
            execute[i].setProxy(listProxy[i]);
            execute[i].setListThreadId(listThreadId);
            execute[i].setListSocketId(listSocketId);
            execute[i].setTableModelRequest(tableModelRequest);
            execute[i].setTableModelResponse(tableModelResponse);
            execute[i].setTableModelStatus(tableModelStatus);
            execute[i].setColumnModelRequest(columnModelRequest);
            execute[i].setColumnModelResponse(columnModelResponse);
            listThreadId.add("" + execute[i].getId());
            listSocketId.add(listProxy[i].hashCode());
            posIns = i;
        }

        for (int i = 0; i < getNumIns(); i++) {
            try {
                execute[i].start();
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }
            posIns = i;
        }

        for (int i = 0; i < getNumIns(); i++) {
            do {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException exc) {
                    log.info("Hilo principal interrumpido.");
                }
            } while (execute[i].isAlive());
        }

        log.info("Hilo Principal finalizado.");
        JOptionPane.showMessageDialog(null, "El mensaje enviado con exito");        

        return 0;
    }

    @Override
    protected void process(final List<Object[]> rows) {
        tableModelStatus.setNumRows(listThreadId.size());
        for (int i = 0; i < listThreadId.size(); i++) {
            tableModelStatus.setValueAt(listThreadId.get(i), i, 0);
            tableModelStatus.setValueAt(listSocketId.get(i), i, 1);
        }
    }

    public void generarReportePDF() throws JRException, IOException {

        execute[posIns].generarReportePDF();
        //execute.generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        execute[posIns].generarReporteXLS();
    }
}
