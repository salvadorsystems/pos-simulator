package com.sanms.siso.eft.instance;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.processor.ParametrosOperacion;
import com.sanms.siso.eft.processor.Worker;
import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.proxy.ProxyCommResult;
import com.sanms.siso.eft.proxy.ProxyResult;
import com.sanms.siso.eft.view.ProcesosMC;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JTable;
import com.sanms.siso.formatter.Field;
import com.sanms.siso.formatter.Template;
import com.sanms.siso.tools.TemplateTool;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

/**
 *
 * @author salvador
 */
public class InstanceManager extends Thread {

    private int connect;
    private int threads;
    private int numTxn;
    private int instance;
    public JTable table;
    public JTable tableResponse;
    public List<Object[]> listObject;
    public ArrayList<Field> listField ;
    public ArrayList<Field> listFieldResponse ;
    private SimpleDateFormat time;
    private boolean running;

    private String rutaParametros;
    private List<Stream> listStream;
    private int errorCount = 0;
    private int succesCount = 0;

    private String apiHost = "";
    private String apiPort = "";
    private int apiTimeOut = 0;
    private String template;
    private String rutaTemplate;

    private Proxy proxy;

    public InstanceManager(ThreadGroup tg, String name, String rutaParametros, String rutaTemplate, List<Stream> listStream) {
        this(tg, name);
        this.rutaParametros = rutaParametros;
        this.rutaTemplate = rutaTemplate;
        this.listStream = listStream;
    }

    public InstanceManager() {
    }

    public InstanceManager(ThreadGroup tg, String name) {
        super(tg, name);
    }

    public int getConnect() {
        return connect;
    }

    public void setConnect(int connect) {
        this.connect = connect;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
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

    public void setRutaParametros(String rutaParametros) {
        this.rutaParametros = rutaParametros;
    }

    public String getRutaTemplate() {
        return rutaTemplate;
    }

    public void setRutaTemplate(String rutaTemplate) {
        this.rutaTemplate = rutaTemplate;
    }

    public List<Stream> getListStream() {
        return listStream;
    }

    public void setListStream(List<Stream> listStream) {
        this.listStream = listStream;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getSuccesCount() {
        return succesCount;
    }

    public void setSuccesCount(int succesCount) {
        this.succesCount = succesCount;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getApiPort() {
        return apiPort;
    }

    public void setApiPort(String apiPort) {
        this.apiPort = apiPort;
    }

    public int getApiTimeOut() {
        return apiTimeOut;
    }

    public void setApiTimeOut(int apiTimeOut) {
        this.apiTimeOut = apiTimeOut;
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
        int txn = getNumTxn();
        int result = 0;
        for (int count = 0; count < txn; count++) {
            try {

                result = execute();
                sleep(1500);
            } catch (Exception e) {
            }
            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Envio Exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "No se completo el envio");
            }

            try {
                Worker worker = new Worker();
                worker.setTable(table);
                worker.setTableResponse(tableResponse);                
                worker.setListObject(getListObject());
                worker.setInstance(getInstance());
                worker.setThread(getThreads());
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
            }

            System.out.println("Finalizado");
        }
    }

    public int execute() throws ParserConfigurationException, SAXException, IOException, FileNotFoundException, InterruptedException {
        int pid = this.threads;
        String template = null;
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion(rutaParametros);
        Map<String, Map<String, Map<String, String>>> templateMapList = TemplateTool.setup(rutaTemplate);
        Map<String, Map<String, Map<String, String>>> templateMapListResponse = TemplateTool.setup(rutaTemplate);
        Template req = parametrosOperacion.obtenerParametrosCmpl(listStream, rutaTemplate, pid);
        String request = req.generateStream();
        
        for (Stream stream : listStream) {
            template = stream.getTemplate();
        }
        ProcesosMC.txtRequerimiento.setText(request);
        Template reqFormat = TemplateTool.createTemplate(templateMapList,template );
        reqFormat.saveFromBuffer(request);
        
        listField = reqFormat.getFieldList();
        
        ProxyResult apiResult = new ProxyResult();
        ProxyCommResult resultProxy = proxy.process(request, apiResult);
        System.out.println("Response: " + resultProxy.getStringResponse());
        
        Template reqFormatResponse = TemplateTool.createTemplate(templateMapListResponse,template );
        reqFormatResponse.saveFromBuffer(resultProxy.getStringResponse());
        listFieldResponse = reqFormatResponse.getFieldList();
        
        ProcesosMC.txtRespuesta.setText(resultProxy.getStringResponse());
        System.out.println("Enviando");
        return resultProxy.getResult();
    }
    
    public void generarReportePDF() throws FileNotFoundException, JRException, IOException{
        
        System.out.println("Generar PEFT");
        File file = ResourceUtils.getFile("C:\\Repositorios\\Java\\UNS\\simulador-procesosmc\\src\\resources\\reportes\\ReportePDF.jasper");
        final JasperReport report = (JasperReport) JRLoader.loadObject(file);
        HashMap<String, Object> parameters = new HashMap<>();
        
        parameters.put("dsInvoice", new JRBeanCollectionDataSource(listFieldResponse, false));
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
        String encodedString = Base64.getEncoder().encodeToString(reporte);
        //System.out.println("PDF BITS: " + encodedString);

        File filePDF = new File("../simulador-procesosmc/ReporteTransaccion.pdf");

        try ( FileOutputStream fos = new FileOutputStream(filePDF);) {
            byte[] decoder = Base64.getDecoder().decode(encodedString);

            fos.write(decoder);
            JOptionPane.showMessageDialog(null, "PDF File Saved");
            System.out.println("PDF Se Genero Correctamente");
        }
        
    }

}
