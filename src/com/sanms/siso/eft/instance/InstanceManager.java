package com.sanms.siso.eft.instance;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.processor.ParametrosOperacion;
import com.sanms.siso.eft.processor.Worker;
import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.proxy.ProxyCommResult;
import com.sanms.siso.eft.proxy.ProxyResult;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.view.ProcesosMC;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import com.sanms.siso.formatter.Field;
import com.sanms.siso.formatter.Template;
import com.sanms.siso.tools.TemplateTool;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.util.ResourceUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class InstanceManager extends Thread {
    
    private static final Logger log = Logger.getLogger(InstanceManager.class);
    
    private String txnName;
    private int numTxn;
    private int instance;
    private JTable table;
    private JTable tableResponse;
    private List<Object[]> listObject;
    private ArrayList<Field> listField;
    private ArrayList<Field> listFieldResponse;
    private SimpleDateFormat time;
    private boolean running;
    
    private String rutaParametros;
    private List<Stream> listStream;
    private int errorCount = 0;
    private int succesCount = 0;

    private String template;
    private String rutaTemplate;
    
    private Proxy proxy;
    
    public InstanceManager(String rutaParametros, String rutaTemplate, List<Stream> listStream) {
        this.rutaParametros = rutaParametros;
        this.rutaTemplate = rutaTemplate;
        this.listStream = listStream;
    }
    
    public InstanceManager() {
    }
    
    public String getTxnName() {
        return txnName;
    }
    
    public void setTxnName(String txnName) {
        this.txnName = txnName;
    }
    
    public int getNumTxn() {
        return numTxn;
    }
    
    public void setNumTxn(int numTxn) {
        this.numTxn = numTxn;
    }
    
    public int getInstance() {
        return instance;
    }
    
    public void setInstance(int instance) {
        this.instance = instance;
    }
    
    public JTable getTable() {
        return table;
    }
    
    public void setTable(JTable table) {
        this.table = table;
    }
    
    public JTable getTableResponse() {
        return tableResponse;
    }
    
    public void setTableResponse(JTable tableResponse) {
        this.tableResponse = tableResponse;
    }
    
    public List<Object[]> getListObject() {
        return listObject;
    }
    
    public void setListObject(List<Object[]> listObject) {
        this.listObject = listObject;
    }
    
    public SimpleDateFormat getTime() {
        return time;
    }
    
    public void setTime(SimpleDateFormat time) {
        this.time = time;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public String getRutaParametros() {
        return rutaParametros;
    }
    
    public String getRutaTemplate() {
        return rutaTemplate;
    }
    
    public List<Stream> getListStream() {
        return listStream;
    }

    public Proxy getProxy() {
        return proxy;
    }
    
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
    
    public String getTemplate() {
        
        return template;
    }
    
    public void setTemplate(String template) {
        this.template = template;
    }
    
    @Override
    public void run() {
        for (int count = 0; count < getNumTxn(); count++) {
            if (execute() == 0) {
                JOptionPane.showMessageDialog(null, "El mensaje se envio correctamente");
                log.info("El mensaje se envio correctamente");
                ProcesosMC.jMenuPDF.setEnabled(true);
                ProcesosMC.jMenuXLS.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Ups!, No se pudo completar el envio");
                log.info("Ups!, No se pudo completar el envio");
            }
            try {
                Worker worker = new Worker();
                worker.setTable(table);
                worker.setTableResponse(tableResponse);
                worker.setListObject(getListObject());
                worker.setInstance(getInstance());
                worker.setTxn(count);
                worker.setsNroTerm("1");
                worker.setTitle("");
                worker.setNroTxnOk(succesCount);
                worker.setNroTxnError(errorCount);
                worker.setRespCode(0);
                worker.setListField(listField);
                worker.setListFieldResponse(listFieldResponse);
                worker.execute();
                setRunning(true);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("Proceso Finalizado");
        }
    }
    
    public int execute() {
        String plantilla = null;
        String request = null;
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion(getRutaParametros());
        Map<String, Map<String, Map<String, String>>> templateMapList = TemplateTool.setup(getRutaTemplate());
        Map<String, Map<String, Map<String, String>>> templateMapListResponse = TemplateTool.setup(getRutaTemplate());
        Template req;
        try {
            req = parametrosOperacion.obtenerParametrosCmpl(getListStream(), getRutaTemplate());
            request = req.generateStream();
            log.info("SRQ : " + "[" + request + "]");
        } catch (FileNotFoundException ex) {
            log.error(ex);
        }
        
        for (Stream stream : getListStream()) {
            plantilla = stream.getTemplate();
        }
        ProcesosMC.txtRequerimiento.setText(request);
        Template reqFormat = TemplateTool.createTemplate(templateMapList, plantilla);
        reqFormat.saveFromBuffer(request);
        
        listField = reqFormat.getFieldList();
        
        ProxyResult apiResult = new ProxyResult();
        ProxyCommResult resultProxy = getProxy().process(request, apiResult);
        log.info("SRS : " + "[" + resultProxy.getStringResponse() + "]");
        
        Template reqFormatResponse = TemplateTool.createTemplate(templateMapListResponse, plantilla);
        reqFormatResponse.saveFromBuffer(resultProxy.getStringResponse());
        listFieldResponse = reqFormatResponse.getFieldList();
        
        ProcesosMC.txtRespuesta.setText(resultProxy.getStringResponse());
        return resultProxy.getResult();
    }
    
    public void generarReportePDF() throws JRException, IOException {
        
        log.info("Se solicito generar PDF");
        File file = ResourceUtils.getFile(Constantes.RUTA_PLANTILLA_PDF);
        final JasperReport report = (JasperReport) JRLoader.loadObject(file);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("txnName", getTxnName());
        parameters.put("dsInvoice", new JRBeanCollectionDataSource(listFieldResponse, false));
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
        String encodedString = Base64.getEncoder().encodeToString(reporte);        
        File filePDF = new File("../simulador-procesosmc/reportes/reporte.pdf");        
        try ( FileOutputStream fos = new FileOutputStream(filePDF);) {
            byte[] decoder = Base64.getDecoder().decode(encodedString);            
            fos.write(decoder);
            JOptionPane.showMessageDialog(null, "PDF Se Genero Correctamente en la siguiente Ruta: \n" + filePDF.getCanonicalPath());            
            log.info("PDF Se Genero Correctamente en la siguiente Ruta: " + filePDF.getCanonicalPath());
        } catch (IOException err) {
            JOptionPane.showMessageDialog(null, "Error al generar PDF: " + err); 
            log.error("Error al generar PDF: " + err);
        }
        
    }
    
    public void generarReporteXLS() throws JRException, IOException {        
        log.info("Se solicito generar EXCEL");
        File file = ResourceUtils.getFile(Constantes.RUTA_PLANTILLA_XLS);
        final JasperReport report = (JasperReport) JRLoader.loadObject(file);
        HashMap<String, Object> parameters = new HashMap<>();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        parameters.put("txnName", getTxnName());
        parameters.put("dsInvoice", new JRBeanCollectionDataSource(listFieldResponse, false));
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
        File fileXLS = new File("../simulador-procesosmc/reportes/reporte.xls");
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
