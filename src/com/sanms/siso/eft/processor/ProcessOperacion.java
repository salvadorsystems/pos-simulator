/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.formatter.Template;
import com.sanms.siso.tools.TemplateTool;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.log4j.Logger;

/**
 *
 * @author Salvador
 */
public class ProcessOperacion {

    private static final Logger log = Logger.getLogger(ProcessOperacion.class);
    private String parametersPath = "";
    private Map<String, Map<String, Map<String, String>>> templateMapList;
    private Map<String, String> parametersMapList;

    public ProcessOperacion(String parametersPath) {
        this.parametersPath = parametersPath;
    }
    
    @SuppressWarnings("null")
    private HashMap<String, String> getParameters(String txnName, String configPath) throws ParserConfigurationException {
        HashMap<String, String> map = new HashMap<>();
        File file = new File(parametersPath);
        DocumentBuilder dcb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = null;
        try {
            doc = dcb.parse(file);
            doc.getDocumentElement().normalize();
        } catch (SAXException | IOException ex) {
            log.error(ProcessOperacion.class.getName() + "->" + ex);
        }
        NodeList nodeParent = doc.getElementsByTagName("request");
        for (int i = 0; i < nodeParent.getLength(); i++) {
            Element eElementParent = (Element) nodeParent.item(i);
            if (txnName.equalsIgnoreCase(eElementParent.getAttribute("token").trim())) {
                NodeList nodeChildLevel = nodeParent.item(i).getChildNodes();
                for (int j = 0; j < nodeChildLevel.getLength(); j++) {
                    if (nodeChildLevel.item(j).getNodeName().equals("#comment") || nodeChildLevel.item(j).getNodeName().equals("#text")) {
                        continue;
                    }
                    map.putAll(saveTransactionData(nodeChildLevel, j, configPath));
                }
            }
        }
        return map;
    }

    private HashMap<String, String> saveTransactionData(NodeList nodeChildLevel, int j, String configPath) {
        HashMap<String, String> map = new HashMap<>();
        String value = nodeChildLevel.item(j).getTextContent();
        String parameters;
        String[] listParameters;
        try {
            if (value.contains("(")) {
                parameters = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
                listParameters = parameters.split(";");                
                if (listParameters.length > 1) {
                    switch (value.substring(value.indexOf(""), value.indexOf("("))) {
                        case "date":
                            String txnDate = sysTime(configPath+"/"+listParameters[0], listParameters[1]);
                            map.put(nodeChildLevel.item(j).getNodeName(), txnDate);
                            break;
                        case "time":
                            String txnTime = sysTime(configPath+"/"+listParameters[0], listParameters[1]);
                            map.put(nodeChildLevel.item(j).getNodeName(), txnTime);
                            break;
                        case "sequence":
                            String trace = secuence(configPath+"/"+listParameters[0], listParameters[1], listParameters[2]);
                            map.put(nodeChildLevel.item(j).getNodeName(), trace);
                            break;
                        case "read":
                            String read = read(configPath+"/"+listParameters[0], listParameters[1]);
                            map.put(nodeChildLevel.item(j).getNodeName(), read);
                            break;
                        case "":
                            break;
                        default:
                    }
                } else {                    
                    switch (value.substring(value.indexOf(""), value.indexOf("("))) {
                        case "date":
                            String txnDate = new SimpleDateFormat(listParameters[0]).format(new Date());
                            map.put(nodeChildLevel.item(j).getNodeName(), txnDate);
                            break;
                        case "time":
                            String txnTime = new SimpleDateFormat(listParameters[0]).format(new Date());
                            map.put(nodeChildLevel.item(j).getNodeName(), txnTime);
                            break;
                    }
                }
            } else {
                map.put(nodeChildLevel.item(j).getNodeName(), nodeChildLevel.item(j).getTextContent());
            }
        } catch (InterruptedException | IOException ex) {
            log.error(ProcessOperacion.class.getName() + "->" + ex);
            Thread.currentThread().interrupt();
        }
        return map;
    }

    @SuppressWarnings("null")
    public Template getParameterPlug(List<Stream> listStream, String rutaTemplate, String configPath) throws FileNotFoundException {
        HashMap<String, String> hMac = null;
        try {
            hMac = getParameters("Macros",configPath);
        } catch (ParserConfigurationException ex) {
            log.error(ProcessOperacion.class
                    .getName() + "->" + ex);
        }
        parametersMapList = new HashMap<>();
        templateMapList = TemplateTool.setup(rutaTemplate);
        Template req = null;
        Template reqOrig = null;
        String origData = "";
        for (Stream stream : listStream) {
            req = TemplateTool.createTemplate(templateMapList, stream.getTemplate());

            HashMap<String, String> hmReq = null;
            try {
                if (stream.getAlias().contains("OriginalData")) {
                    reqOrig = TemplateTool.createTemplate(templateMapList, stream.getTemplate());
                    origData = "";
                    hmReq = getParameters(stream.getAlias(), configPath);
                    for (Map.Entry<String, String> entry : hmReq.entrySet()) {
                        reqOrig.saveValue(entry.getKey(), entry.getValue());
                        origData = reqOrig.generateStream();
                    }

                } else {
                    hmReq = getParameters(stream.getAlias(), configPath);
                }

            } catch (ParserConfigurationException ex) {
                log.error(ProcessOperacion.class
                        .getName() + "->" + ex);
            }
            parametersMapList.putAll(hmReq);
            for (Map.Entry<String, String> entry : parametersMapList.entrySet()) {
                Object val = entry.getValue();
                for (Map.Entry<String, String> entry1 : hMac.entrySet()) {
                    Object key1 = entry1.getKey();
                    if (val.equals("{" + key1 + "}")) {
                        parametersMapList.put(entry.getKey(), entry1.getValue());
                    }
                }
                if ("originalData".equalsIgnoreCase(entry.getKey())) {
                    parametersMapList.put(entry.getKey(), origData);
                }
                String key = entry.getKey();
                String value = entry.getValue();
                req.saveValue(key, value);
            }
        }
        return req;
    }

    public String sysdate(String filename, String format) throws IOException, InterruptedException {
        String sysDate = new SimpleDateFormat(format).format(new Date());
        writeOnFile(filename, sysDate);
        return sysDate;
    }

    public String sysTime(String filename, String format) throws IOException, InterruptedException {
        String sysTime = new SimpleDateFormat(format).format(new Date());
        writeOnFile(filename, sysTime);
        return sysTime;
    }

    public String systime(String filename, String format) throws IOException, InterruptedException {
        String sysTime = new SimpleDateFormat(format).format(new Date());
        writeOnFile(filename, sysTime);
        return sysTime;
    }

    public String read(String file, String lengh) {
        String value = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(file);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                value = linea.substring(0, Integer.parseInt(lengh));
            }
        } catch (IOException e) {
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }

        return value;
    }

    public String secuence(String filename, String length, String step) {
        log.debug("ParametersSimulator.secuence(): inicio");

        String formatValue = "";
        try {
            //Si el archivo no existe se creara uno nuevo
            File existFile = new File(filename);
            if (existFile.createNewFile()) {
                log.debug("Archivo de Secuencia creado correctamente");
            }
            //Se accede al archivo creado,
            formatValue = generateSequence(filename, length, step);
        } catch (IOException | NumberFormatException ex) {
            log.error(ex);
        }
        log.debug("ParametersSimulator.secuence(): fin");
        return formatValue;
    }

    private String generateSequence(String filename, String length, String step) {
        int currentValue;
        String formatValue = "";

        FileChannel fileChannel;
        try ( RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            fileChannel = file.getChannel();
            FileLock lock = null;
            //Pondra en cola a cualquier hilo o proceso que intente acceder al archivo.
            lock = checkFileLocked(lock, fileChannel);
            String current = file.readLine();
            if (!StringUtils.isNoneEmpty(current)) {
                current = "0";
            }
            currentValue = Integer.parseInt(current);
            currentValue += Integer.parseInt(step);
            formatValue = StringUtils.leftPad(("" + currentValue), Integer.parseInt(length), "0");
            file.seek(0);
            file.writeBytes(formatValue);
            Thread.sleep(500);

            //libera el archivo para ser usado por cualquier otro proceso
            lock.release();
            fileChannel.close();

        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcessOperacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            java.util.logging.Logger.getLogger(ProcessOperacion.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }

        return formatValue;
    }

    /*Manteniendo en espera a cualquier proceso que quiera acceder al archivo bloqueado hasta que pueda acceder*/
    private FileLock checkFileLocked(FileLock fileLock, FileChannel fileChanel) {
        while (fileLock == null) {
            try {
                fileLock = fileChanel.tryLock();
            } catch (final OverlappingFileLockException | IOException e) {
                log.debug(e);
            }
        }
        return fileLock;
    }

    private boolean writeOnFile(String filename, String content) throws IOException, InterruptedException {
        log.debug("ParametersSimulator.writeOnFile(): inicio");
        boolean success;

        File fileNew = new File(filename);

        if (fileNew.createNewFile()) {
            log.debug("Archivo creado correctamente");
        }

        FileWriter fw = new FileWriter(filename);
        try ( BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
        }
        Thread.sleep(500);
        success = true;
        return success;
    }
}
