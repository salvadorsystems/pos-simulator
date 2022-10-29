package com.sanms.siso.eft.instance;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.processor.ProcesarOperacion;
import com.sanms.siso.eft.proxy.Proxy;
import com.sanms.siso.eft.proxy.ProxyCommResult;
import com.sanms.siso.eft.proxy.ProxyResult;
import com.sanms.siso.eft.view.ProcesosMC;
import com.sft.core.socket.DefaultSocketClient;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import com.sanms.siso.eft.utils.MiHilo;

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
    public List<Object[]> listObject;
    private SimpleDateFormat time;
    private boolean running;

    private String rutaParametros;
    private List<Stream> listStream;
    private int errorCount = 0;
    private int succesCount = 0;

    private String apiHost = "";
    private String apiPort = "";
    private int apiTimeOut = 0;

    private Proxy proxy;

    public InstanceManager(ThreadGroup tg, String name, String rutaParametros, List<Stream> listStream) {
        this(tg, name);
        this.rutaParametros = rutaParametros;
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

    @Override
    public void run() {
        int txn = getNumTxn();
        for (int count = 0; count < txn; count++) {
            try {

                execute();
                sleep(1500);
            } catch (Exception e) {
            }
            //proxy.release();
            System.out.println("Finalizado");
        }
    }

    public void execute() {
        int pid = this.threads;
        ProcesarOperacion processor = new ProcesarOperacion();
        processor.setup(rutaParametros);
        String result = "";
        try {
            result = processor.ConstruirTrama(listStream, pid);
        } catch (Exception ex) {
            throw ex;
        }

        ProxyResult apiResult = new ProxyResult();
        //ProxyCommResult resultProxy = proxy.process("0200E23A440188E08002000000000400010016459334000207305943000007251538170010041538170725072559420511100000007963110000004040400000000098122340880299999490000   PROCESOS MC PRUEBAS SA                  604012CC200035477620108012321000349631  0021530150299999429900401", apiResult);
        ProxyCommResult resultProxy = proxy.process(result, apiResult);
        System.out.println("Result: " + resultProxy.getStringResponse());
        System.out.println("Enviando");

    }

}
