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
public class TrabajoBack extends SwingWorker<Integer, Object[]> {

    private static final Logger log = Logger.getLogger(TrabajoBack.class);
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

    //private InstanceManager execute[];
    private InstanceManager execute;

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

        sendMessageSocket();
        publish((Object[]) null);
        return 0;
    }

    @Override
    protected void process(final List<Object[]> rows) {
//        DefaultTableModel tableModel = (DefaultTableModel) getTableStatus().getModel();
//        TableColumnModel columnModel = getTableStatus().getColumnModel();
//        tableModel.setNumRows(listSocketId.size());
//        tableModel.setColumnCount(5);
//
//        for (int i = 0; i < listSocketId.size(); i++) {
//
//            tableModel.setValueAt(listThreadId.get(i), i, 0);
//            tableModel.setValueAt(listSocketId.get(i), i, 1);
//
//        }

    }

    public void sendMessageSocket() {
        //execute = new InstanceManager[getNumIns()];
        listThreadId = new ArrayList<>();
        listSocketId = new ArrayList<>();
        for (int i = 0; i < getNumIns(); i++) {
            execute = new InstanceManager("[Hilo " + i + "]", getParametrosPath(), getTemplatePath(), getListStream());
            execute.setTxnName(getTxnName());
            execute.setNumTxn(getNumTxn());
            execute.setProxy(listProxy[i]);
            execute.setListThreadId(listThreadId);
            execute.setListSocketId(listSocketId);
            execute.setTableRequest(getTableRequest());
            execute.setTableResponse(getTableResponse());
            execute.setTableStatus(getTableStatus());
            execute.start();
            listThreadId.add("" + execute.getId());
            listSocketId.add(listProxy[i].hashCode());
            posIns = i;
        }
        for (int i = 0; i < getNumIns(); i++) {
            do {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException exc) {
                    log.info("Hilo principal interrumpido.");
                }
            } while (execute.isAlive());
            log.info("Hilo Principal finalizado.");
        }
        JOptionPane.showMessageDialog(null, "El mensaje enviado con exito");
    }

    public void generarReportePDF() throws JRException, IOException {

        //execute[posIns].generarReportePDF();
        execute.generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        execute.generarReporteXLS();
    }
}
