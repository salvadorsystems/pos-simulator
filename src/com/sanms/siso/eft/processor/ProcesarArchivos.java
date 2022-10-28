package com.sanms.siso.eft.processor;

import com.google.gson.Gson;
import com.sanms.siso.eft.model.Generator;
import com.sanms.siso.eft.model.Operacion;
import com.sanms.siso.eft.view.ProcesosMC;
import com.sft.core.configuration.ConfigurationProperties;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author salvador
 */
public class ProcesarArchivos {

    public String executeProcessCCE(String nombreTxn, int pid) {
        String respuesta = null;

        return respuesta;
    }

    public void setup() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static String[] listConfigFiles(String path) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        ProcesosMC.jListConfig.setModel(modelo);
        String[] pathnames;
        File f = new File(path);

        FilenameFilter filter = (File f1, String name) -> name.endsWith(".json");

        pathnames = f.list(filter);
        for (String pathname : pathnames) {
            modelo.addElement(pathname);
        }
        return pathnames;
    }

    public static void listarOperaciones(Operacion operacion){
        DefaultListModel<String> modelo = new DefaultListModel<>();
        ProcesosMC.jListTxn.setModel(modelo);
        List<Generator> listGenerator = operacion.getGenerators();
        for (Generator generator : listGenerator) {
            modelo.addElement(generator.getDetail());
        }               
    }

    public static String convertJsonToString(String path) {
        String fichero = "";
        // Gson gson = new Gson();
        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return fichero;
    }

    public static Object jsonConvertClass(Object object, String fichero) {
        Gson gson = new Gson();
        object = gson.fromJson(fichero, Object.class);

        return object;
    }

    public static boolean isJSONValid(String jsonInString) {
        Gson gson = new Gson();
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }
}
