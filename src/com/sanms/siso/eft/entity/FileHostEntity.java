package com.sanms.siso.eft.entity;

/**
 *
 * @author salvador
 */
public class FileHostEntity {
    
    private String localHost;
    private String remoteHost;
    private int port;
    private int timeout;

    public FileHostEntity() {
    }

    
    
    public FileHostEntity(String localHost, String remoteHost, int port, int timeout) {
        this.localHost = localHost;
        this.remoteHost = remoteHost;
        this.port = port;
        this.timeout = timeout;
    }

    public String getLocalHost() {
        return localHost;
    }

    public void setLocalHost(String localHost) {
        this.localHost = localHost;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
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
        return "ProcessorHost{" + "localHost=" + localHost + ", remoteHost=" + remoteHost + ", port=" + port + ", timeout=" + timeout + '}';
    }
    
    
}
