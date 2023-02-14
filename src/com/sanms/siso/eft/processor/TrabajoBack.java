/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.proxy.Proxy;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
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
    private Proxy[] listProxy;
    private String parametrosPath;
    private String templatePath;
    private List<Stream> listStream;
    
    private InstanceManager execute[];

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

        
        return 0;
    }

     @Override
    protected void process(final List<Object[]> rows) {
        
        
        
    }
    
        public void sendMessageSocket() {
        execute = new InstanceManager[getNumIns()];
        System.out.println("Listado Proxys : " + listProxy);
        for (int i = 0; i < getNumIns(); i++) {
            execute[i] = new InstanceManager("[Hilo " + i + "]", getParametrosPath(), getTemplatePath(), getListStream());
            execute[i].setTxnName(getTxnName());
            execute[i].setNumTxn(getNumTxn());
            execute[i].setProxy(listProxy[i]);
            execute[i].setTable(getTableRequest());
            execute[i].setTableResponse(getTableResponse());
            execute[i].start();
            posIns = i;
        }
        for (int i = 0; i < getNumIns(); i++) {
            do {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException exc) {
                    System.out.println("Hilo principal interrumpido.");
                }
            } while (execute[i].isAlive());
            log.info("Hilo Principal finalizado.");
        }
        JOptionPane.showMessageDialog(null, "El mensaje enviado con exito");
    }
        
            public void generarReportePDF() throws JRException, IOException {

        execute[posIns].generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        execute[posIns].generarReporteXLS();
    }
}
