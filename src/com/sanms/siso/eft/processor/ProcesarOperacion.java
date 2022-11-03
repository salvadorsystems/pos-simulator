/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.tools.TemplateTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author salvador
 */
public class ProcesarOperacion {

    ParametrosOperacion parametrosOperacion;
    Map<String, Map<String, Map<String, String>>> templateMapList;
    private List<Stream> listStream;
    private int pid;
    private String rutaParametros;

    public void setup(String rutaParametros, List<Stream> listStream, int pid) {
        this.rutaParametros = rutaParametros;
        this.listStream = listStream;
        this.pid = pid;
    }

    public List<Stream> getListStream() {
        return listStream;
    }

    public void setListStream(List<Stream> listStream) {
        this.listStream = listStream;
    }

    public String getRutaParametros() {
        return rutaParametros;
    }

    public void setRutaParametros(String rutaParametros) {
        this.rutaParametros = rutaParametros;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String ConstruirTrama() throws ParserConfigurationException, SAXException, IOException, FileNotFoundException, InterruptedException {
        parametrosOperacion = new ParametrosOperacion(rutaParametros);

        templateMapList = TemplateTool.setup(rutaParametros);        

        HashMap<String, String> hmReq = parametrosOperacion.obtenerParametrosCmpl(listStream, pid);
            
        System.out.println(hmReq);
        return "";
    }

}
