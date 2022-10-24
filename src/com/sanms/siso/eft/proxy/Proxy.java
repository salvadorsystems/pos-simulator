/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanms.siso.eft.proxy;

import com.sft.core.socket.DefaultSocketClient;
import com.sft.core.socket.SocketClientFactory;
import com.sft.core.socket.util.ServerAddress;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author salvador
 */
public class Proxy extends ProxyDefinition {

    DefaultSocketClient socketProcessor;
    static AtomicLong sequence = new AtomicLong();
    ServerAddress sa;
    boolean fastConnect;
    private static Charset byteCharSet = Charset.defaultCharset();
    static SocketClientFactory SECPROXYFACTORY = SocketClientFactory.SOCKETCLIENTFACTORY_DEFAULT;

    public Proxy() {
        socketProcessor = null;
        sa = null;
        fastConnect = true;
    }

    public int setup(String host, String port, boolean fastconnect, int timeout) {
        socketProcessor = new DefaultSocketClient(SECPROXYFACTORY);
        sa = new ServerAddress();
        sa.setName(String.valueOf(this.hashCode()));
        sa.setHost(host);
        sa.setService(port);
        sa.setTimeout(timeout);
        fastConnect = fastconnect;
        if (!fastConnect) {
            if (!socketProcessor.connect(sa.getHost(), sa.getService(), true, true, sa.getTimeout())) {
                return TIBTEST_RESULT_ERROR_SETUP_CONNECT;
            }
        }
        return TIBTEST_RESULT_SUCCESS;
    }

    public void release() {
        if (!fastConnect) {
            if (socketProcessor != null && socketProcessor.isConnected()) {
                socketProcessor.close();
                socketProcessor = null;
                sa = null;
            }
        }
    }

    public ProxyCommResult process(String request, ProxyResult api) {
        return processComm(request, api);
    }

    synchronized ProxyCommResult processComm(String request, ProxyResult api) {
        byte[] byterequest = request.getBytes(byteCharSet);

        ProxyCommResult commResult = processComm(byterequest, api);

        return commResult;
    }

    synchronized ProxyCommResult processComm(byte[] byterequest, ProxyResult api) {
        ProxyCommResult commResult = new ProxyCommResult();

        // Controlar falla de comm y timeout a este nivel
        if (socketProcessor == null) {
            commResult.setResult(TIBTEST_RESPONSECODE_ERROR_INVALIDSTATE);
            api.setResult(TIBTEST_RESPONSECODE_ERROR_INVALIDSTATE);
            api.setResponseCode(TIBTEST_RESPONSECODE_ERROR_INVALIDSTATE);
        } else {
            if (!socketProcessor.isConnected()) {
                socketProcessor.close();

                if (!socketProcessor.connect(sa.getHost(), sa.getService(), true, true, sa.getTimeout())) {
                    commResult.setResult(TIBTEST_RESPONSECODE_ERROR_SYSTEMDOWN);

                    api.setResult(TIBTEST_RESPONSECODE_ERROR_SYSTEMDOWN);
                    api.setResponseCode(TIBTEST_RESPONSECODE_ERROR_SYSTEMDOWN);
                }
            }

            if (socketProcessor.isConnected()) {
                byte[] response;

                try {
                    socketProcessor.setTimeout(sa.getTimeout());
                } catch (IOException e) {

                }

                response = socketProcessor.processSendReceive(byterequest, byterequest.length);

                if (response != null) {
                    // Se asigna la cadena de bytes y se crea el string
                    commResult.setResponse(response, byteCharSet.name());
                    commResult.setResult(TIBTEST_RESPONSECODE_SUCCESS);

                    // La API indica que la se ha obtenido una respuesta no necesariamente
                    // indica que la respuesta tiene el formato adecuado
                    api.setResult(TIBTEST_RESPONSECODE_SUCCESS);
                    api.setResponseCode(TIBTEST_RESPONSECODE_SUCCESS);
                } else {
                    if (socketProcessor.hasCause()) {
                        if (socketProcessor.cause() instanceof SocketTimeoutException) {
                            api.setResult(TIBTEST_RESPONSECODE_ERROR_TIMEOUT);
                            api.setResponseCode(TIBTEST_RESPONSECODE_ERROR_TIMEOUT);
                        } else {
                            api.setResult(TIBTEST_RESPONSECODE_ERROR_COMM);
                            api.setResponseCode(TIBTEST_RESPONSECODE_ERROR_COMM);
                        }
                    } else {
                        api.setResult(TIBTEST_RESPONSECODE_ERROR_PROCESS);
                        api.setResponseCode(TIBTEST_RESPONSECODE_ERROR_PROCESS);
                    }
                    commResult.setResult(api.getResult());
                }

                if (fastConnect) {
                    socketProcessor.close();
                }
            }
        }
        return commResult;
    }

    public static Charset getByteCharSet() {
        return byteCharSet;
    }

    public static void setByteCharSet(Charset aByteCharSet) {
        byteCharSet = aByteCharSet;
    }

}
