/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.model;

/**
 *
 * @author salvador
 */
public class FullReport {
    private String number;
    private String thread;
    private String socket;
    private String status;
    private String hReq;
    private String countReq;
    private String hresp;
    private String countResp;
    private String totalTime;

    public FullReport(String number, String thread, String socket, String status, String hReq, String countReq, String hresp, String countResp, String totalTime) {
        this.number = number;
        this.thread = thread;
        this.socket = socket;
        this.status = status;
        this.hReq = hReq;
        this.countReq = countReq;
        this.hresp = hresp;
        this.countResp = countResp;
        this.totalTime = totalTime;
    }        

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String gethReq() {
        return hReq;
    }

    public void sethReq(String hReq) {
        this.hReq = hReq;
    }

    public String getCountReq() {
        return countReq;
    }

    public void setCountReq(String countReq) {
        this.countReq = countReq;
    }

    public String getHresp() {
        return hresp;
    }

    public void setHresp(String hresp) {
        this.hresp = hresp;
    }

    public String getCountResp() {
        return countResp;
    }

    public void setCountResp(String countResp) {
        this.countResp = countResp;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
    
}
