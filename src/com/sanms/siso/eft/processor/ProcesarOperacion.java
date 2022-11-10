/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.formatter.Field;
import com.sanms.siso.formatter.Template;
import com.sanms.siso.tools.TemplateTool;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
    private String rutaTemplate;

    public void setup(String rutaParametros, String rutaTemplate, List<Stream> listStream, int pid) {
        this.rutaParametros = rutaParametros;
        this.rutaTemplate = rutaTemplate;
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

        templateMapList = TemplateTool.setup(rutaTemplate);

        Template req1 = TemplateTool.createTemplate(templateMapList, "isoProcesoMC");
        Template req = parametrosOperacion.obtenerParametrosCmpl(listStream, rutaTemplate, pid);

        String respuesta = req.generateStream();
        System.out.println("fff: " + req.generateStream());
        System.out.println("fgg :" + parametrosOperacion.getParams());
        req1.saveFromBuffer(respuesta);
        System.out.println("fff11: " + req1.generateStream());
        System.out.println("fff12: " + req1.getValue("iccData"));
        ArrayList<Field> listField = req1.getFieldList();
        System.out.println("FIELD : " + req1.getFieldList());
        for (int i = 0; i < listField.size(); i++) {

            System.out.println("N° "+i+"-"+listField.get(i).getAlias()+"-"+listField.get(i).getValue());
        }
        /*for (Map.Entry<String, String> entry : parametrosOperacion.getParams().entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if(!req1.getValue(key).isEmpty()){
                System.out.println(key+"-"+val);
            } else {
                
            }
        }   */
        System.out.println(respuesta);
        return respuesta;
    }

}
