package com.sanms.siso.eft.proxy;

import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.instance.InstanceManager;
import com.sanms.siso.eft.utils.EnumErrores;
import com.sanms.siso.eft.view.ProcesosMC;
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
    private int numberTxn;
    private String apiHost;
    private String apiPort;

    public int getNumIns() {
        return numIns;
    }

    public void setNumIns(int numIns) {
        this.numIns = numIns;
    }

    public int getNumberTxn() {
        return numberTxn;
    }

    public void setNumberTxn(int numberTxn) {
        this.numberTxn = numberTxn;
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

    public int openSocketAny() {
        proxyTest = new Proxy[numIns];
        for (int i = 0; i < numIns; i++) {
            proxyTest[i] = new Proxy();
            connectSocket = proxyTest[i].setup(apiHost, apiPort, false, 30);
            if (connectSocket == 0) {
                System.out.println("Conexion Abierta");              
                ProcesosMC.imgConn.setIcon(new ImageIcon(getClass().getResource("../img/img2.png")));
                ProcesosMC.jButton1.setText("Desconectar");
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
        ProcesosMC.jButton1.setText("Conectar");
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
            execute[i] = new InstanceManager(parentGroup, "Thread [" + i + "]");
            execute[i].setNumTxn(numberTxn);
            execute[i].setProxy(proxyTest[i]);
            System.out.println("proxy :" + proxyTest[i]);
            execute[i].start();
            System.out.println("enviado" + execute[i]);
        }
    }

}
