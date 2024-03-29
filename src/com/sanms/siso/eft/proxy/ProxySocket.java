package com.sanms.siso.eft.proxy;

import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.utils.Errores;
import com.sanms.siso.eft.view.ProcesosMC;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author salvador
 */
public class ProxySocket {

    private String tnxName;
    private InstanceManager execute[];
    private int connectSocket = -1;
    private Proxy proxyTest[];
    private int numIns;
    private int numTxn;
    private int posIns;
    private String apiHost;
    private String apiPort;
    private String parametersPath;
    private String templatesPath;
    private List<Stream> listStream;
    public JTable table;
    public JTable tableResponse;

    public ProxySocket(JTable table, JTable tableResponse) {
        this.table = table;
        this.tableResponse = tableResponse;
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
        proxyTest = new Proxy[numIns];
        for (int i = 0; i < numIns; i++) {
            proxyTest[i] = new Proxy();
            connectSocket = proxyTest[i].setup(apiHost, apiPort, false, 30);
            if (connectSocket == 0) {
                System.out.println("Connexion Abierta");
                ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_ON)));
                ProcesosMC.BtnOpenCloseSocket.setText("Desconectar");
                ProcesosMC.lblTCPIP.setText(apiHost);
                ProcesosMC.lblPort.setText(apiPort);
            } else {
                JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1004.getMensaje(),
                        "Error de conexión", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connectSocket;
    }

    public void closeSocket() {
        System.out.println("Conexion Cerrada");
        ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_OFF)));
        ProcesosMC.BtnOpenCloseSocket.setText("Conectar");
        ProcesosMC.lblTCPIP.setText("0.0.0.0");
        ProcesosMC.lblPort.setText("00");
        for (int i = 0; i < numIns; i++) {
            proxyTest[i].release();
        }
    }

    public void sendMessageSocket() {
        execute = new InstanceManager[numIns];
        for (int i = 0; i < numIns; i++) {
            execute[i] = new InstanceManager(parametersPath, templatesPath, listStream);
            execute[i].setTxnName(tnxName);
            execute[i].setNumTxn(numTxn);
            execute[i].setProxy(proxyTest[i]);
            execute[i].setTable(table);
            execute[i].setTableResponse(tableResponse);
            execute[i].start();
            System.out.println("enviado" + execute[i]);
            posIns = i;
        }
    }

    public void generarReportePDF() throws JRException, IOException {

        execute[posIns].generarReportePDF();

    }

    public void generarReporteXLS() throws JRException, IOException {
        execute[posIns].generarReporteXLS();
    }

    public String getTnxName() {
        return tnxName;
    }

    public void setTnxName(String tnxName) {
        this.tnxName = tnxName;
    }
}
