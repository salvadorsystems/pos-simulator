/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.utils.EnumErrores;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author CANVIA
 */
public class ParametrosOperacion {

    private String rutaParametros = "";

    public ParametrosOperacion(String rutaParametros) {
        this.rutaParametros = rutaParametros;
    }

    public HashMap<String, String> obtenerParametros(String txnName, int pid) throws ParserConfigurationException, SAXException, IOException {
        HashMap<String, String> map = null;
        if (txnName.isEmpty()) {
            JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1005.getMensaje());
        }
        File file = new File(rutaParametros);
        DocumentBuilder dcb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dcb.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodeParent = doc.getElementsByTagName("request");
        map = new HashMap<String, String>();
        for (int i = 0; i < nodeParent.getLength(); i++) {
            Element eElementParent = (Element) nodeParent.item(i);
            String token = eElementParent.getAttribute("token");
            if(txnName.equalsIgnoreCase(token.trim())){
                NodeList nodeChildLevel = nodeParent.item(i).getChildNodes();
                for (int j = 0; j < nodeChildLevel.getLength(); j++) {
                    if (nodeChildLevel.item(j).getNodeName().equals("#comment")) {
                        continue;
                    }
                    if (nodeChildLevel.item(j).getNodeName().equals("#text")) {
                        continue;
                    }
                    Element eElementChildLevel = (Element) nodeChildLevel.item(j);
                    if (eElementChildLevel.hasAttributes()) {
                        
                    }else{
                        map.put(nodeChildLevel.item(j).getNodeName(), nodeChildLevel.item(j).getTextContent());
                    }                    
                }
            }           
        }
        
                        
        return map;
    }
    public HashMap<String, String> obtenerParametrosCmpl(List<Stream> listStream, int pid) throws ParserConfigurationException, SAXException, IOException{        
        HashMap<String, String> hMac = obtenerParametros("Macros", pid);                
        HashMap<String, String> params = new HashMap<>();
        for (Stream stream : listStream) {
            HashMap<String, String> hmReq = obtenerParametros(stream.getAlias(), pid);
            params.putAll(hmReq);
        }        
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            for (Map.Entry<String, String> entry1 : hMac.entrySet()) {
                Object key1 = entry1.getKey();
                Object val1 = entry1.getValue();
                if (val.equals("{"+key1+"}")) {
                    params.put(entry.getKey(), entry1.getValue());
                }
            }
        }                
        return params;
    }
}
