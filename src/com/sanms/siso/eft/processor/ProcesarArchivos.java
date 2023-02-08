package com.sanms.siso.eft.processor;

import com.google.gson.Gson;
import com.sanms.siso.eft.model.Generator;
import com.sanms.siso.eft.model.Operacion;
import com.sanms.siso.eft.view.ProcesosMC;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import org.apache.log4j.Logger;

/**
 *
 * @author salvador
 */
public class ProcesarArchivos {

    private static final Logger log = Logger.getLogger(ProcesarArchivos.class);

    private ProcesarArchivos() {
        // Do nothing because of X and Y.
    }

    public static String[] listarArchivosConfiguracion(String path) {
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

    public static void listarOperaciones(Operacion operacion) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        ProcesosMC.jListTxn.setModel(modelo);
        List<Generator> listGenerator = operacion.getGenerators();
        for (Generator generator : listGenerator) {
            modelo.addElement(generator.getDetail());
        }
    }

    public static StringBuilder convertJsonToString(String path) {
        StringBuilder fichero = new StringBuilder();
        try ( BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero.append(linea);
            }
        } catch (FileNotFoundException ex) {
            log.error(ex);            
        } catch (IOException ex) {
            
            log.error(ex);   
        }
        return fichero;
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
