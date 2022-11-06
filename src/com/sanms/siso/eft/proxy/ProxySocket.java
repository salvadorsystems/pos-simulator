package com.sanms.siso.eft.proxy;

import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.model.ArchivoConfiguracion;
import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.utils.EnumErrores;
import com.sanms.siso.eft.view.ProcesosMC;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author salvador
 */
public class ProxySocket {

    private InstanceManager execute[];
    private int connectSocket = -1;
    private Proxy proxyTest[];
    private int numIns;
    private int numTxn;
    private String apiHost;
    private String apiPort;
    private String parametersPath;
    private String templatesPath;
    private List<Stream> listStream;

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
                System.out.println("Conexion Abierta");              
                ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource("../img/img2.png")));
                ProcesosMC.BtnOpenCloseSocket.setText("Desconectar");
                ProcesosMC.lblTCPIP.setText(apiHost);
                ProcesosMC.lblPort.setText(apiPort);
            } else {
                JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1004.getMensaje(),
                        "Error de conexión", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connectSocket;
    }

    public void closeSocket() {
        System.out.println("Conexion Cerrada");      
        ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource("../img/img1.png")));
        ProcesosMC.BtnOpenCloseSocket.setText("Conectar");
        ProcesosMC.lblTCPIP.setText("0.0.0.0");
        ProcesosMC.lblPort.setText("00");
        for (int i = 0; i < numIns; i++) {
            proxyTest[i].release();
        }
    }

    public void sendMessageSocket() {
        ThreadGroup parentGroup = new ThreadGroup("Parent Thread");
        execute = new InstanceManager[numIns];
        for (int i = 0; i < numIns; i++) {
            execute[i] = new InstanceManager(parentGroup, "Thread [" + i + "]",parametersPath,templatesPath,listStream);
            execute[i].setNumTxn(numTxn);
            execute[i].setProxy(proxyTest[i]);
            System.out.println("proxy :" + proxyTest[i]);
            execute[i].start();
            System.out.println("enviado" + execute[i]);
        }
    }

}
