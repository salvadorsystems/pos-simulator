package com.sanms.siso.eft.entity;

/**
 *
 * @author salvador
 */
public class FileConfigEntity {

    private String workPath;
    private String templatesFile;
    private String generatorsFile;
    private String parametersFile;
    private String logLevel;
    private String prefix;
    private String tcpFile;
    private String securityFile;
    private String statisticFile;
    private String reportFlag;
    private String reportPath;

    public FileConfigEntity(String workPath, String templatesFile, String generatorsFile, String parametersFile, String logLevel, String prefix, String tcpFile, String securityFile, String statisticFile, String reportFlag, String reportPath) {
        this.workPath = workPath;
        this.templatesFile = templatesFile;
        this.generatorsFile = generatorsFile;
        this.parametersFile = parametersFile;
        this.logLevel = logLevel;
        this.prefix = prefix;
        this.tcpFile = tcpFile;
        this.securityFile = securityFile;
        this.statisticFile = statisticFile;
        this.reportFlag = reportFlag;
        this.reportPath = reportPath;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public String getTemplatesFile() {
        return templatesFile;
    }

    public void setTemplatesFile(String templatesFile) {
        this.templatesFile = templatesFile;
    }

    public String getGeneratorsFile() {
        return generatorsFile;
    }

    public void setGeneratorsFile(String generatorsFile) {
        this.generatorsFile = generatorsFile;
    }

    public String getParametersFile() {
        return parametersFile;
    }

    public void setParametersFile(String parametersFile) {
        this.parametersFile = parametersFile;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getTcpFile() {
        return tcpFile;
    }

    public void setTcpFile(String tcpFile) {
        this.tcpFile = tcpFile;
    }

    public String getSecurityFile() {
        return securityFile;
    }

    public void setSecurityFile(String securityFile) {
        this.securityFile = securityFile;
    }

    public String getStatisticFile() {
        return statisticFile;
    }

    public void setStatisticFile(String statisticFile) {
        this.statisticFile = statisticFile;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    @Override
    public String toString() {
        return "ProcessorConfig{" + "workPath=" + workPath + ", templatesFile=" + templatesFile + ", generatorsFile=" + generatorsFile + ", parametersFile=" + parametersFile + ", logLevel=" + logLevel + ", prefix=" + prefix + ", tcpFile=" + tcpFile + ", securityFile=" + securityFile + ", statisticFile=" + statisticFile + ", reportFlag=" + reportFlag + ", reportPath=" + reportPath + '}';
    }
    
    
    
}
