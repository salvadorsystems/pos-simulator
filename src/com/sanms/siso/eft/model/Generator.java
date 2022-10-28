/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.model;

import java.util.List;

/**
 *
 * @author salvador
 */
public class Generator {
    private String detail;
    private List<Stream> streams;
    private Stream streamRsp;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public Stream getStreamRsp() {
        return streamRsp;
    }

    public void setStreamRsp(Stream streamRsp) {
        this.streamRsp = streamRsp;
    }
        
}
