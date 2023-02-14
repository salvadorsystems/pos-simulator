package com.sanms.siso.eft.proxy;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.processor.TrabajoBack;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.utils.Errores;
import com.sanms.siso.eft.view.ProcesosMC;
import java.io.IOException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class ProxySocket {

    private static final Logger log = Logger.getLogger(ProxySocket.class);
    private TrabajoBack tarea;
    private String tnxName;
    private InstanceManager execute[];
    private int connectSocket = -1;
    private Proxy proxy[];
    private int numIns;
    private int numTxn;
    private int posIns;
    private String apiHost;
    private String apiPort;
    private String parametersPath;
    private String templatesPath;
    private List<Stream> listStream;
    private JTable tableRequest;
    private JTable tableResponse;

    public ProxySocket(JTable tableRequest, JTable tableResponse) {
        this.tableRequest = tableRequest;
        this.tableResponse = tableResponse;
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
    
    public int openSocketAny() {
        proxy = new Proxy[getNumIns()];
        for (int i = 0; i < getNumIns(); i++) {
            proxy[i] = new Proxy();
            connectSocket = proxy[i].setup(getApiHost(), getApiPort(), false, 30);
            if (connectSocket == 0) {
                ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_ON)));
                ProcesosMC.BtnOpenCloseSocket.setText("Desconectar");
                ProcesosMC.lblTCPIP.setText(getApiHost());
                ProcesosMC.lblPort.setText(getApiPort());
            } else {
                log.debug(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1004.getMensaje());
                JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1004.getMensaje(),
                        "Error de conexión", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connectSocket;
    }

    public void closeSocket() {
        ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_OFF)));
        ProcesosMC.BtnOpenCloseSocket.setText("Conectar");
        ProcesosMC.lblTCPIP.setText("0.0.0.0");
        ProcesosMC.lblPort.setText("00");
        for (int i = 0; i < getNumIns(); i++) {
            proxy[i].release();
        }
    }

    public void sendMessageSocket() {
        execute = new InstanceManager[getNumIns()];
        System.out.println("Listado Proxys : " + proxy);
        for (int i = 0; i < getNumIns(); i++) {
            execute[i] = new InstanceManager("[Hilo " + i + "]", getParametersPath(), getTemplatesPath(), getListStream());
            execute[i].setTxnName(getTnxName());
            execute[i].setNumTxn(getNumTxn());
            execute[i].setProxy(proxy[i]);
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

    public void enviarMensajeSocket() {
        tarea = new TrabajoBack(proxy, getParametersPath(), getTemplatesPath(), getListStream());
        tarea.setTxnName(getTnxName());
        tarea.setNumIns(getNumIns());
        tarea.setNumTxn(getNumTxn());
        tarea.setTableRequest(getTableRequest());
        tarea.setTableResponse(getTableResponse());
        tarea.execute();
    }

    public void generarReportePDF() throws JRException, IOException {

        execute[posIns].generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        execute[posIns].generarReporteXLS();
    }

}
