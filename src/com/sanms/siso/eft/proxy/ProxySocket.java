package com.sanms.siso.eft.proxy;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.processor.Worker;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.utils.Errores;
import com.sanms.siso.eft.view.ProcesosMC;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class ProxySocket {

    private static final Logger log = Logger.getLogger(ProxySocket.class);
    private Worker tarea;
    private String tnxName;
    private int connectSocket = -1;
    private Proxy proxy[];
    private int numIns;
    private int numTxn;
    private String apiHost;
    private String apiPort;
    private String parametersPath;
    private String templatesPath;
    private List<Stream> listStream;
    private final DefaultTableModel tableModelRequest;
    private final DefaultTableModel tableModelResponse;
    private final DefaultTableModel tableModelStatus;

    private final TableColumnModel columnModelRequest;
    private final TableColumnModel columnModelResponse;
    private final TableColumnModel columnModelStatus;

    public ProxySocket(DefaultTableModel tableModelRequest, DefaultTableModel tableModelResponse, DefaultTableModel tableModelStatus,
            TableColumnModel columnModelRequest, TableColumnModel columnModelResponse, TableColumnModel columnModelStatus) {
        this.tableModelRequest = tableModelRequest;
        this.tableModelResponse = tableModelResponse;
        this.tableModelStatus = tableModelStatus;
        this.columnModelRequest = columnModelRequest;
        this.columnModelResponse = columnModelResponse;
        this.columnModelStatus = columnModelStatus;
    }

    public String getTnxName() {
        return tnxName;
    }

    public void setTnxName(String tnxName) {
        this.tnxName = tnxName;
    }

    public int getNumIns() {
        return numIns;
    }

    public void setNumIns(int numIns) {
        this.numIns = numIns;
    }

    public int getNumTxn() {
        return numTxn;
    }

    public void setNumTxn(int numTxn) {
        this.numTxn = numTxn;
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

    public String getParametersPath() {
        return parametersPath;
    }

    public void setParametersPath(String parametersPath) {
        this.parametersPath = parametersPath;
    }

    public List<Stream> getListStream() {
        return listStream;
    }

    public void setListStream(List<Stream> listStream) {
        this.listStream = listStream;
    }

    public String getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(String templatesPath) {
        this.templatesPath = templatesPath;
    }

    public int openSocketAny() {
        proxy = new Proxy[getNumIns()];
        for (int i = 0; i < getNumIns(); i++) {
            proxy[i] = new Proxy();
            connectSocket = proxy[i].setup(getApiHost(), getApiPort(), false, 30);
            if (connectSocket == 0) {
                ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_ON)));
                ProcesosMC.btnConnect.setText("Desconectar");                
                ProcesosMC.lblTCPIP.setText(getApiHost());
                ProcesosMC.lblPort.setText(getApiPort());
            } else {
                log.debug(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1004.getMensaje());
                JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1004.getMensaje(),
                        "Error de conexión", JOptionPane.ERROR_MESSAGE);               
                break;
            }
        }
        return connectSocket;
    }

    public void closeSocket() {
        ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_OFF)));
        ProcesosMC.btnConnect.setText("Conectar");
        ProcesosMC.lblTCPIP.setText("0.0.0.0");
        ProcesosMC.lblPort.setText("00");
        for (int i = 0; i < getNumIns(); i++) {            
            proxy[i].release();
        }
        for (int i = 0; i < tableModelStatus.getRowCount(); i++) {
            tableModelStatus.setValueAt("Desconectado", i, 3);
        }
    }

    public void enviarMensajeSocket() {
        tarea = new Worker(proxy, getParametersPath(), getTemplatesPath(), getListStream(), tableModelRequest, tableModelResponse, tableModelStatus,
                columnModelRequest, columnModelResponse, columnModelStatus);
        tarea.setTxnName(getTnxName());
        tarea.setNumIns(getNumIns());
        tarea.setNumTxn(getNumTxn());
        tarea.execute();
    }

    public void generarReportePDF() throws JRException, IOException {

        tarea.generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        tarea.generarReporteXLS();
    }

    public void generateFullReport() throws FileNotFoundException, JRException {
        tarea.generateFullReport();
    }
}
