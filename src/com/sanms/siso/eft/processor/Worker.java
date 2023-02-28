/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.FullReport;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.utils.Constantes;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

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
            execute[i].setTxnName(getTxnName());
            execute[i].setNumTxn(getNumTxn());
            execute[i].setProxy(listProxy[i]);
            execute[i].setListThreadId(listThreadId);
            execute[i].setListSocketId(listSocketId);
            listThreadId.add("" + execute[i].getId());
            listSocketId.add(listProxy[i].hashCode());
            posIns = i;
        }

        for (int i = 0; i < getNumIns(); i++) {
            execute[i].start();
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
        tableModelStatus.setNumRows(listThreadId.size());
        tableModelStatus.setColumnCount(9);
        columnModelStatus.getColumn(0).setPreferredWidth(5);
        columnModelStatus.getColumn(1).setPreferredWidth(15);
        columnModelStatus.getColumn(2).setPreferredWidth(50);
        columnModelStatus.getColumn(3).setPreferredWidth(50);
        columnModelStatus.getColumn(4).setPreferredWidth(50);
        columnModelStatus.getColumn(5).setPreferredWidth(8);
        columnModelStatus.getColumn(6).setPreferredWidth(50);
        columnModelStatus.getColumn(7).setPreferredWidth(8);
        columnModelStatus.getColumn(8).setPreferredWidth(20);
        for (int i = 0; i < listThreadId.size(); i++) {
            tableModelStatus.setValueAt(i + 1, i, 0);
            tableModelStatus.setValueAt(listThreadId.get(i), i, 1);
            tableModelStatus.setValueAt(listSocketId.get(i), i, 2);
            tableModelStatus.setValueAt("Conectado", i, 3);
        }
    }

    public void generarReportePDF() throws JRException, IOException {

        execute[posIns].generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        execute[posIns].generarReporteXLS();
    }

    public void generateFullReport() throws FileNotFoundException, JRException {
        listFullReport = new ArrayList<>();
        for (int i = 0; i < tableModelStatus.getRowCount(); i++) {
            FullReport fullReport = new FullReport(tableModelStatus.getValueAt(i, 0).toString(), tableModelStatus.getValueAt(i, 1).toString(), tableModelStatus.getValueAt(i, 2).toString(),
                    tableModelStatus.getValueAt(i, 3).toString(), tableModelStatus.getValueAt(i, 4).toString(), tableModelStatus.getValueAt(i, 5).toString(), tableModelStatus.getValueAt(i, 6).toString(), tableModelStatus.getValueAt(i, 7).toString(),
                    tableModelStatus.getValueAt(i, 8).toString());
            listFullReport.add(fullReport);
        }
        log.info("Se solicito generar EXCEL");
        File file = ResourceUtils.getFile(Constantes.RUTA_PLANTILLA_FULL);
        final JasperReport report = (JasperReport) JRLoader.loadObject(file);
        HashMap<String, Object> parameters = new HashMap<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        parameters.put("txnName", "Total Results");
        parameters.put("dsInvoice", new JRBeanCollectionDataSource(listFullReport, false));
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        byte[] reporte = baos.toByteArray();
        String encodedString = Base64.getEncoder().encodeToString(reporte);
        File fileXLS = new File("../simulador-procesosmc/reportes/fullReport.xls");
        try ( FileOutputStream fos = new FileOutputStream(fileXLS);) {
            byte[] decoder = Base64.getDecoder().decode(encodedString);
            fos.write(decoder);
            JOptionPane.showMessageDialog(null, "Excel generado correctamente en la siguiente Ruta: \n" + fileXLS.getCanonicalPath());
            log.info("Excel generado correctamente en la siguiente Ruta: " + fileXLS.getCanonicalPath());
        } catch (IOException err) {
            JOptionPane.showMessageDialog(null, "Error al generar Excel: " + err);
            log.error("Error al generar Excel: " + err);
        }
    }
}
