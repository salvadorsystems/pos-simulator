/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanms.siso.eft.processor;

import com.sanms.siso.eft.model.Stream;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.utils.EnumErrores;
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
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
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

    public HashMap<String, String> obtenerParametros(String txnName, int pid) throws ParserConfigurationException, SAXException, IOException, FileNotFoundException, InterruptedException {
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
            if (txnName.equalsIgnoreCase(token.trim())) {
                NodeList nodeChildLevel = nodeParent.item(i).getChildNodes();
                for (int j = 0; j < nodeChildLevel.getLength(); j++) {
                    if (nodeChildLevel.item(j).getNodeName().equals("#comment")) {
                        continue;
                    }
                    if (nodeChildLevel.item(j).getNodeName().equals("#text")) {
                        continue;
                    }
                    String value = nodeChildLevel.item(j).getTextContent();
                    String parameters;
                    String[] listParameters;
                    if (value.contains("(")) {
                        int inicio = value.indexOf("");
                        int fin = value.indexOf("(");
                        String values = value.substring(inicio, fin);
                        switch (values) {
                            case "dateMMDD":
                                String txnDate = sysdate(Constantes.BASE_URL_TMP + "mastercard.date", "MMdd", "", "", pid);
                                map.put(nodeChildLevel.item(j).getNodeName(), txnDate);
                                break;
                            case "timeHHMMSS":
                                parameters = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
                                listParameters = parameters.split(";");
                                if (listParameters.length < 2) {
                                    String txnTime = systime(Constantes.BASE_URL_CFG + listParameters[0], "HHmmss", "", "", pid);
                                    map.put(nodeChildLevel.item(j).getNodeName(), txnTime);
                                } else {
                                    JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1007.getMensaje() + nodeChildLevel.item(j).getNodeName());
                                }
                                break;
                            case "sequence":
                                parameters = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
                                listParameters = parameters.split(";");
                                if (listParameters.length < 4) {
                                    String trace = secuence(Constantes.BASE_URL_CFG + listParameters[0], listParameters[1], listParameters[2], "", pid);
                                    map.put(nodeChildLevel.item(j).getNodeName(), trace);
                                } else {
                                    JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1007.getMensaje() + nodeChildLevel.item(j).getNodeName());
                                }
                                break;
                            case "read":
                                parameters = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
                                listParameters = parameters.split(";");
                                if (listParameters.length < 4) {                                    
                                    String localTime = reader(Constantes.BASE_URL_CFG + listParameters[0], "HHmmss", listParameters[2], "", pid);
                                    map.put(nodeChildLevel.item(j).getNodeName(), localTime);
                                } else {
                                    JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1007.getMensaje() + nodeChildLevel.item(j).getNodeName());
                                }
                                break;
                            case "":
                                break;
                            default:
                        }                        
                    } else {
                        map.put(nodeChildLevel.item(j).getNodeName(), nodeChildLevel.item(j).getTextContent());
                    }
                }
            }
        }
        return map;
    }

    public HashMap<String, String> obtenerParametrosCmpl(List<Stream> listStream, int pid) throws ParserConfigurationException, SAXException, IOException, FileNotFoundException, InterruptedException {
        HashMap<String, String> hMac = obtenerParametros("Macros", pid);
        HashMap<String, String> params = new HashMap<>();
        for (Stream stream : listStream) {
            HashMap<String, String> hmReq = obtenerParametros(stream.getAlias(), pid);
            params.putAll(hmReq);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Object val = entry.getValue();
            for (Map.Entry<String, String> entry1 : hMac.entrySet()) {
                Object key1 = entry1.getKey();
                if (val.equals("{" + key1 + "}")) {
                    params.put(entry.getKey(), entry1.getValue());
                }
            }
        }
        return params;
    }

    public String sysdate(String filename, String format, String nonusage0, String nonusage1, int pid) throws IOException, FileNotFoundException, InterruptedException {
        String sysDate = new SimpleDateFormat(format).format(new Date());
        writeOnFile(filename, sysDate);
        return sysDate;
    }

    public String systime(String filename, String format, String nonusage0, String nonusage1, int pid) throws IOException, FileNotFoundException, InterruptedException {
        String sysTime = new SimpleDateFormat(format).format(new Date());
        writeOnFile(filename, sysTime);
        return sysTime;
    }

    public String secuence(String filename, String length, String step, String nonusage1, int pid) {
        //logger.debug("ParametersSimulator.secuence(): inicio");
        int currentValue = 0;
        String formatValue = "";
        try {
            //Si el archivo no existe se creara uno nuevo
            File existFile = new File(filename);
            existFile.createNewFile();

            //Se accede al archivo creado,
            RandomAccessFile file = new RandomAccessFile(filename, "rw");
            FileChannel fileChannel = file.getChannel();
            FileLock lock = null;

            //Pondra en cola a cualquier hilo o proceso que intente acceder al archivo.
            lock = CheckFileLocked(lock, fileChannel);

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
            try {
                //libera el archivo para ser usado por cualquier otro proceso
                lock.release();
            } catch (Exception ex) {
            }

            file.close();
            fileChannel.close();
        } catch (Exception ex) {
            throw new Error(ex);
        }
        //logger.debug("ParametersSimulator.secuence(): fin");
        return formatValue;
    }

    /*Manteniendo en espera a cualquier proceso que quiera acceder al archivo bloqueado hasta que pueda acceder*/
    private FileLock CheckFileLocked(FileLock fileLock, FileChannel fileChanel) {
        while (fileLock == null) {
            try {
                fileLock = fileChanel.tryLock();
            } catch (final OverlappingFileLockException | IOException e) {
            }
        }
        return fileLock;
    }

    public String reader(String filename, String format, String length, String nonusage1, int pid) {
        //logger.debug("ParametersSimulator.reader(): inicio");
        String currentValue = "";
        try {

            File file = new File(filename);
            if (!file.exists()) {
                return "";
            }
            String current = readFile(filename);
            if ("N".equalsIgnoreCase(format.trim())) {
                currentValue = StringUtils.leftPad(current, Integer.parseInt(length), "0");
            } else {
                currentValue = current;
            }
        } catch (Exception ex) {
            throw new Error(ex);
        }
        //logger.debug("ParametersSimulator.reader(): fin");
        return "" + currentValue;
    }

    /**
     * ****************************************************************************************************
     */
    private boolean writeOnFile(String filename, String content) throws FileNotFoundException, IOException, InterruptedException {
        //logger.debug("ParametersSimulator.writeOnFile(): inicio");
        boolean success = false;

        File fileNew = new File(filename);
        fileNew.createNewFile();

        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();

        Thread.sleep(500);
        success = true;
        return success;
    }

    private String readFile(String filename) {
        //logger.debug("ParametersSimulator.readFile(): inicio");
        File file = new File(filename);
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            file.createNewFile();
            br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                sb.append(str);
            }
        } catch (Exception ex) {
            sb.append("0");
        }
        //logger.debug("ParametersSimulator.readFile(): fin");
        return sb.toString();
    }

}
