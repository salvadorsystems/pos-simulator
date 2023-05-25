package com.sanms.siso.eft.instance;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.processor.ParametrosOperacion;
import com.sanms.siso.eft.processor.ProcesarArchivos;
import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.proxy.ProxyCommResult;
import com.sanms.siso.eft.proxy.ProxyResult;
import com.sanms.siso.eft.view.ProcesosMC;
import java.text.SimpleDateFormat;
import java.util.List;
import com.sanms.siso.formatter.Field;
import com.sanms.siso.formatter.Template;
import com.sanms.siso.tools.TemplateTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class InstanceManager extends Thread {

    private static final Logger log = Logger.getLogger(InstanceManager.class);

    private String txnName;
    private int numTxn;
    private String instance;
    private List<Object[]> listObject;
    private ArrayList<Field> listFieldRequest;
    private ArrayList<Field> listFieldResponse;
    private SimpleDateFormat time;    
    private long timeInit;
    private long timeEnd;
    private long diferencia = 0;

    private String rutaParametros;
    private List<Stream> listStream;
    private int i;
    private String template;
    private String rutaTemplate;
    private String configPath;

    private Proxy proxy;
    private List<String> listThreadId;
    private List<Integer> listSocketId;

    public DefaultTableModel tableModelRequest;
    public DefaultTableModel tableModelResponse;
    public DefaultTableModel tableModelStatus;
    private TableColumnModel columnModelRequest;
    private TableColumnModel columnModelResponse;
    private TableColumnModel columnModelStatus;

    public InstanceManager(int i, String instance, String rutaParametros, String rutaTemplate, List<Stream> listStream, DefaultTableModel tableModelRequest,
            DefaultTableModel tableModelResponse, DefaultTableModel tableModelStatus,
            TableColumnModel columnModelRequest, TableColumnModel columnModelResponse, TableColumnModel columnModelStatus) {
        this.i = i;
        this.instance = instance;
        this.rutaParametros = rutaParametros;
        this.rutaTemplate = rutaTemplate;
        this.listStream = listStream;
        this.tableModelRequest = tableModelRequest;
        this.tableModelResponse = tableModelResponse;
        this.tableModelStatus = tableModelStatus;
        this.columnModelRequest = columnModelRequest;
        this.columnModelResponse = columnModelResponse;
        this.columnModelStatus = columnModelStatus;
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

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
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

    public List<String> getListThreadId() {
        return listThreadId;
    }

    public void setListThreadId(List<String> listThreadId) {
        this.listThreadId = listThreadId;
    }

    public List<Integer> getListSocketId() {
        return listSocketId;
    }

    public void setListSocketId(List<Integer> listSocketId) {
        this.listSocketId = listSocketId;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
       
    @Override
    public void run() {
        for (int count = 0; count < getNumTxn(); count++) {
            if (execute(count, getConfigPath()) == 0) {
                try {
                    ProcesosMC.jMenuPDF.setEnabled(true);
                    ProcesosMC.jMenuXLS.setEnabled(true);
                    timeEnd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS").format(LocalDateTime.now())).getTime();
                    diferencia += (timeEnd - timeInit);
                    tableModelStatus.setValueAt(DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now()), i, 6);
                    tableModelStatus.setValueAt(count + 1, i, 7);
                    log.info("Instancia " + getInstance() + " mensaje enviado : " + count);
                } catch (ParseException ex) {
                    log.error(ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ups!, No se pudo completar el envio");
                log.info("Ups!, No se pudo completar el envio");
            }
            /**
             * Display Data in Request table
             */
            for (Field field : listFieldResponse) {
                if (field.getValue().equalsIgnoreCase("00")) {
                    field.setValue("00 (success)");
                }
                if (field.getValue().equalsIgnoreCase("83")) {
                    field.setValue(field.getValue() + " (unsupported host)");
                }
            }            
            ProcesarArchivos.setTable("txn",tableModelRequest, columnModelRequest, listFieldRequest,null,null);
            /**
             * Display Data in Response table
             */            
            ProcesarArchivos.setTable("txn",tableModelResponse, columnModelResponse, listFieldResponse,null,null);
        }
        tableModelStatus.setValueAt(diferencia, i, 8);
    }
    
    public void  executeService(){
        
        ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(getInstance()));
        
        
    }

    public int execute(int count, String configPath) {
        String plantilla = null;
        String request = null;
        ParametrosOperacion parametrosOperacion = new ParametrosOperacion(getRutaParametros());
        Map<String, Map<String, Map<String, String>>> templateMapList = TemplateTool.setup(getRutaTemplate());
        Map<String, Map<String, Map<String, String>>> templateMapListResponse = TemplateTool.setup(getRutaTemplate());
        Template templates;
        try {
            /**Prepara la trama**/
            templates = parametrosOperacion.obtenerParametrosCmpl(getListStream(), getRutaTemplate(), configPath);
            /**Generar Requerimiento*/
            request = templates.generateStream();
            log.info("SRQ : " + "[" + request + "]");
        } catch (FileNotFoundException ex) {
            log.error(ex);
        }

        for (Stream stream : getListStream()) {
            plantilla = stream.getTemplate();
        }
        ProcesosMC.txtRequerimiento.setText(request);
        templates = TemplateTool.createTemplate(templateMapList, plantilla);
        templates.saveFromBuffer(request);

        listFieldRequest = templates.getFieldList();
        try {
            timeInit = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS").format(LocalDateTime.now())).getTime();
        } catch (ParseException ex) {
            log.error(ex);
        }
        tableModelStatus.setValueAt(DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalDateTime.now()), i, 4);
        tableModelStatus.setValueAt(count + 1, i, 5);
        ProxyResult apiResult = new ProxyResult();
        /**Generar Respuesta**/
        ProxyCommResult resultProxy = getProxy().process(request, apiResult);
        log.info("SRS : " + "[" + resultProxy.getStringResponse() + "]");

        templates = TemplateTool.createTemplate(templateMapListResponse, plantilla);
        templates.saveFromBuffer(resultProxy.getStringResponse());
        listFieldResponse = templates.getFieldList();

        ProcesosMC.txtRespuesta.setText(resultProxy.getStringResponse());
        return resultProxy.getResult();
    }

    public void generarReportePDF() throws JRException, IOException {
        ProcesarArchivos.generateReport("PDF",getTxnName(), listFieldResponse, null);
    }

    public void generarReporteXLS() throws JRException, IOException {
        ProcesarArchivos.generateReport("XLS",getTxnName(), listFieldResponse, null);
    }
}
