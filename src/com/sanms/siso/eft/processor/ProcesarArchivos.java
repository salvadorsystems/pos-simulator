package com.sanms.siso.eft.processor;

import com.google.gson.Gson;
import com.sanms.siso.eft.model.FullReport;
import com.sanms.siso.eft.model.Generator;
import com.sanms.siso.eft.model.Operacion;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.view.ProcesosMC;
import com.sanms.siso.formatter.Field;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

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
        String[] pathnames = null;
        if (!path.isEmpty() || !path.equalsIgnoreCase("")) {
            File f = new File(path);
            FilenameFilter filter = (File f1, String name) -> name.endsWith(".json");
            pathnames = f.list(filter);
            for (String pathname : pathnames) {
                modelo.addElement(pathname);
            }
        } else {
            modelo.clear();
        }
        return pathnames;
    }

    public static void listarOperaciones(Operacion operacion) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        ProcesosMC.jListTxn.setModel(modelo);

        if (operacion == null) {
            modelo.clear();
        } else {
            List<Generator> listGenerator = operacion.getGenerators();
            for (Generator generator : listGenerator) {
                modelo.addElement(generator.getDetail());
            }
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

    public static void generateReport(String flag, String getTxnName, ArrayList<Field> listFieldResponse, DefaultTableModel tableModelStatus) throws JRException, IOException {
        log.info("Se solicito generar " + flag);
        File file;
        final JasperReport report;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("txnName", getTxnName);
        parameters.put("dsInvoice", new JRBeanCollectionDataSource(listFieldResponse, false));
        ByteArrayOutputStream baos;
        JasperPrint jasperPrint;
        String encodedString;
        File filePDF;
        File fileXLS;
        JRXlsExporter exporter;
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        byte[] decoder;
        switch (flag) {
            case "PDF":
                file = ResourceUtils.getFile(Constantes.RUTA_PLANTILLA_PDF);
                report = (JasperReport) JRLoader.loadObject(file);
                jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
                encodedString = Base64.getEncoder().encodeToString(reporte);
                filePDF = new File("../simulador-procesosmc/reportes/reporte.pdf");
                try ( FileOutputStream fos = new FileOutputStream(filePDF)) {
                    decoder = Base64.getDecoder().decode(encodedString);
                    fos.write(decoder);
                    JOptionPane.showMessageDialog(null, "PDF Se Genero Correctamente en la siguiente Ruta: \n" + filePDF.getCanonicalPath());
                    log.info("PDF Se Genero Correctamente en la siguiente Ruta: " + filePDF.getCanonicalPath());
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Error al generar PDF: " + err);
                    log.error("Error al generar PDF: " + err);
                }
                break;
            case "XLS":
                file = ResourceUtils.getFile(Constantes.RUTA_PLANTILLA_XLS);
                report = (JasperReport) JRLoader.loadObject(file);
                jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                exporter = new JRXlsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                baos = new ByteArrayOutputStream();
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                reporte = baos.toByteArray();
                encodedString = Base64.getEncoder().encodeToString(reporte);
                fileXLS = new File("../simulador-procesosmc/reportes/reporte.xls");
                try ( FileOutputStream fos = new FileOutputStream(fileXLS);) {
                    decoder = Base64.getDecoder().decode(encodedString);
                    fos.write(decoder);
                    JOptionPane.showMessageDialog(null, "Excel generado correctamente en la siguiente Ruta: \n" + fileXLS.getCanonicalPath());
                    log.info("Excel generado correctamente en la siguiente Ruta: " + fileXLS.getCanonicalPath());
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Error al generar Excel: " + err);
                    log.error("Error al generar Excel: " + err);
                }
                break;
            case "FUL":
                ArrayList<FullReport> listFullReport;
                file = ResourceUtils.getFile(Constantes.RUTA_PLANTILLA_FULL);
                report = (JasperReport) JRLoader.loadObject(file);
                listFullReport = new ArrayList<>();
                for (int i = 0; i < tableModelStatus.getRowCount(); i++) {
                    FullReport fullReport = new FullReport(tableModelStatus.getValueAt(i, 0).toString(), tableModelStatus.getValueAt(i, 1).toString(), tableModelStatus.getValueAt(i, 2).toString(),
                            tableModelStatus.getValueAt(i, 3).toString(), tableModelStatus.getValueAt(i, 4).toString(), tableModelStatus.getValueAt(i, 5).toString(), tableModelStatus.getValueAt(i, 6).toString(), tableModelStatus.getValueAt(i, 7).toString(),
                            tableModelStatus.getValueAt(i, 8).toString());
                    listFullReport.add(fullReport);
                }
                baos = new ByteArrayOutputStream();
                parameters.put("dsInvoice", new JRBeanCollectionDataSource(listFullReport, false));
                jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

                exporter = new JRXlsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                exporter.setConfiguration(configuration);
                exporter.exportReport();

                reporte = baos.toByteArray();
                encodedString = Base64.getEncoder().encodeToString(reporte);
                fileXLS = new File("../simulador-procesosmc/reportes/fullReport.xls");
                try ( FileOutputStream fos = new FileOutputStream(fileXLS);) {
                    decoder = Base64.getDecoder().decode(encodedString);
                    fos.write(decoder);
                    JOptionPane.showMessageDialog(null, "Excel generado correctamente en la siguiente Ruta: \n" + fileXLS.getCanonicalPath());
                    log.info("Excel generado correctamente en la siguiente Ruta: " + fileXLS.getCanonicalPath());
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Error al generar Excel: " + err);
                    log.error("Error al generar Excel: " + err);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void setTable(String flag, DefaultTableModel tableModel, TableColumnModel columnModel, ArrayList<Field> listField, List<String> listThreadId, List<Integer> listSocketId) {
        switch (flag) {
            case "txn":
                tableModel.setNumRows(listField.size());
                tableModel.setColumnCount(5);
                columnModel.getColumn(0).setPreferredWidth(120);
                columnModel.getColumn(1).setPreferredWidth(50);
                columnModel.getColumn(2).setPreferredWidth(50);
                columnModel.getColumn(3).setPreferredWidth(60);
                columnModel.getColumn(4).setPreferredWidth(200);
                int j = 0;
                for (Field field : listField) {
                    if (!field.getValue().isEmpty()) {
                        tableModel.setValueAt(field.getAlias(), j, 0);
                        tableModel.setValueAt(field.getIsoBit(), j, 1);
                        tableModel.setValueAt(field.getFormat(), j, 2);
                        tableModel.setValueAt(field.getValueSize(), j, 3);
                        tableModel.setValueAt(field.getValue(), j, 4);
                        j++;
                    }
                }
                break;
            case "status":
                tableModel.setNumRows(listThreadId.size());
                tableModel.setColumnCount(9);
                columnModel.getColumn(0).setPreferredWidth(5);
                columnModel.getColumn(1).setPreferredWidth(15);
                columnModel.getColumn(2).setPreferredWidth(50);
                columnModel.getColumn(3).setPreferredWidth(50);
                columnModel.getColumn(4).setPreferredWidth(50);
                columnModel.getColumn(5).setPreferredWidth(8);
                columnModel.getColumn(6).setPreferredWidth(50);
                columnModel.getColumn(7).setPreferredWidth(8);
                columnModel.getColumn(8).setPreferredWidth(20);
                for (int i = 0; i < listThreadId.size(); i++) {
                    tableModel.setValueAt(i + 1, i, 0);
                    tableModel.setValueAt(listThreadId.get(i), i, 1);
                    tableModel.setValueAt(listSocketId.get(i), i, 2);
                    tableModel.setValueAt("Conectado", i, 3);
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
