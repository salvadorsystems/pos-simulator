/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.utils.Errores;
import com.sanms.siso.formatter.Template;
import com.sanms.siso.tools.TemplateTool;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
import javax.swing.JOptionPane;
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
public class ParametrosOperacion {

    private static final Logger log = Logger.getLogger(ParametrosOperacion.class);
    private String rutaParametros = "";
    Map<String, Map<String, Map<String, String>>> templateMapList;
    Map<String, String> params;

    public ParametrosOperacion(String rutaParametros) {
        this.rutaParametros = rutaParametros;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @SuppressWarnings("null")
    private HashMap<String, String> obtenerParametros(String txnName) throws ParserConfigurationException {
        HashMap<String, String> map = new HashMap<>();
        File file = new File(rutaParametros);
        DocumentBuilder dcb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = null;
        try {
            doc = dcb.parse(file);
            doc.getDocumentElement().normalize();
        } catch (SAXException | IOException ex) {
            log.error(ParametrosOperacion.class.getName() + "->" + ex);
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
                    map.putAll(registroDatosTransaccion(nodeChildLevel, j));
                }
            }
        }
        return map;
    }

    private HashMap<String, String> registroDatosTransaccion(NodeList nodeChildLevel, int j) {
        HashMap<String, String> map = new HashMap<>();
        String value = nodeChildLevel.item(j).getTextContent();
        String parameters;
        String[] listParameters;
        try {
            if (value.contains("(")) {
                parameters = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
                listParameters = parameters.split(";");
                if (listParameters.length < 5) {
                    switch (value.substring(value.indexOf(""), value.indexOf("("))) {
                        case "date":
                            String txnDate = sysdate(listParameters[0], listParameters[1]);
                            map.put(nodeChildLevel.item(j).getNodeName(), txnDate);
                            break;
                        case "time":
                            String txnTime = systime(listParameters[0], listParameters[1]);
                            map.put(nodeChildLevel.item(j).getNodeName(), txnTime);
                            break;
                        case "sequence":
                            String trace = secuence(listParameters[0], listParameters[1], listParameters[2]);
                            map.put(nodeChildLevel.item(j).getNodeName(), trace);
                            break;
                        case "":
                            break;
                        default:
                    }
                } else {
                    JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1007.getMensaje() + nodeChildLevel.item(j).getNodeName());
                }
            } else {
                map.put(nodeChildLevel.item(j).getNodeName(), nodeChildLevel.item(j).getTextContent());
            }
        } catch (InterruptedException | IOException ex) {
            log.error(ParametrosOperacion.class.getName() + "->" + ex);
            Thread.currentThread().interrupt();
        }
        return map;
    }

    @SuppressWarnings("null")
    public Template obtenerParametrosCmpl(List<Stream> listStream, String rutaTemplate) throws FileNotFoundException {
        HashMap<String, String> hMac = null;
        try {
            hMac = obtenerParametros("Macros");
        } catch (ParserConfigurationException ex) {
            log.error(ParametrosOperacion.class
                    .getName() + "->" + ex);
        }
        params = new HashMap<>();
        templateMapList = TemplateTool.setup(rutaTemplate);
        Template req = null;
        for (Stream stream : listStream) {
            req = TemplateTool.createTemplate(templateMapList, stream.getTemplate());
            HashMap<String, String> hmReq = null;
            try {
                hmReq = obtenerParametros(stream.getAlias());

            } catch (ParserConfigurationException ex) {
                log.error(ParametrosOperacion.class
                        .getName() + "->" + ex);
            }
            params.putAll(hmReq);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Object val = entry.getValue();
                for (Map.Entry<String, String> entry1 : hMac.entrySet()) {
                    Object key1 = entry1.getKey();
                    if (val.equals("{" + key1 + "}")) {
                        params.put(entry.getKey(), entry1.getValue());
                    }
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

    public String systime(String filename, String format) throws IOException, InterruptedException {
        String sysTime = new SimpleDateFormat(format).format(new Date());
        writeOnFile(filename, sysTime);
        return sysTime;
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
            formatValue = generaSecuencia(filename, length, step);
        } catch (IOException | NumberFormatException ex) {
            log.error(ex);
        }
        log.debug("ParametersSimulator.secuence(): fin");
        return formatValue;
    }

    private String generaSecuencia(String filename, String length, String step) {
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
            java.util.logging.Logger.getLogger(ParametrosOperacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            java.util.logging.Logger.getLogger(ParametrosOperacion.class.getName()).log(Level.SEVERE, null, ex);
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
