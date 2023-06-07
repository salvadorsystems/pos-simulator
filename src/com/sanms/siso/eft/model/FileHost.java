package com.sanms.siso.eft.model;

/**
 *
 * @author salvador
 */
public class FileHost {
    
    private String ipHost;
    private int port;
    private int timeout;

    public FileHost() {
    }

    
    
    public FileHost(String ipHost, int port, int timeout) {
        this.ipHost = ipHost;
        this.port = port;
        this.timeout = timeout;
    }

    public String getIpHost() {
        return ipHost;
    }

    public void setIpHost(String ipHost) {
        this.ipHost = ipHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "ArchivoHost{" + "ipHost=" + ipHost + ", port=" + port + ", timeout=" + timeout + '}';
    }
    
}
