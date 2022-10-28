package com.sanms.siso.eft.view;

import com.google.gson.Gson;
import com.sanms.siso.eft.model.ArchivoConfiguracion;
import com.sanms.siso.eft.processor.ProcessorFiles;
import com.sanms.siso.eft.model.ArchivoHost;
import com.sanms.siso.eft.model.ArchivoRuta;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.sanms.siso.eft.proxy.ProxySocket;
import com.sanms.siso.eft.utils.EnumErrores;
import java.awt.HeadlessException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author salvador
 */
public final class ProcesosMC extends javax.swing.JFrame {

    static String apiHost;
    static String apiPort;
    private int connectClient = -1;
    private String path;

    ProxySocket socketProxy = new ProxySocket();
    ViewHost windowTCPIP = new ViewHost(this, true);
    ArchivoConfiguracion fileConfigEntity;
    
    public ProcesosMC() {
        initComponents();
        initWorkSpace();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panelConfiguration = new javax.swing.JPanel();
        btnTCPIP = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtNumIns = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumTxn = new javax.swing.JTextField();
        CboxNumI = new javax.swing.JCheckBox();
        panelConfiguration1 = new javax.swing.JPanel();
        BtnOpenCloseSocket = new javax.swing.JButton();
        imgConn = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTCPIP = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        panelConfiguration2 = new javax.swing.JPanel();
        btnCargar = new javax.swing.JButton();
        txtPath = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListConfig = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTxn = new javax.swing.JList<>();
        BtnSendMessage = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("tab3", jScrollPane1);
        jTabbedPane1.addTab("tab1", jTabbedPane2);

        panelConfiguration.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configure la IP e Instancias"));

        btnTCPIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTCPIPActionPerformed(evt);
            }
        });

        jLabel5.setText("Nro Instancias: ");

        jLabel6.setText("Nro de Envios:");

        CboxNumI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CboxNumIMouseClicked(evt);
            }
        });
        CboxNumI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboxNumIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConfigurationLayout = new javax.swing.GroupLayout(panelConfiguration);
        panelConfiguration.setLayout(panelConfigurationLayout);
        panelConfigurationLayout.setHorizontalGroup(
            panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTCPIP, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumIns, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(txtNumTxn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CboxNumI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelConfigurationLayout.setVerticalGroup(
            panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfigurationLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTCPIP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelConfigurationLayout.createSequentialGroup()
                        .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CboxNumI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtNumIns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNumTxn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );

        panelConfiguration1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Conexion"));

        BtnOpenCloseSocket.setText("Conectar");
        BtnOpenCloseSocket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOpenCloseSocketActionPerformed(evt);
            }
        });

        jLabel7.setText("SERVER IP :");

        jLabel8.setText("PUERTO :");

        lblTCPIP.setText("0.0.0.0");

        lblPort.setText("00");

        javax.swing.GroupLayout panelConfiguration1Layout = new javax.swing.GroupLayout(panelConfiguration1);
        panelConfiguration1.setLayout(panelConfiguration1Layout);
        panelConfiguration1Layout.setHorizontalGroup(
            panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnOpenCloseSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imgConn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addComponent(lblTCPIP, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addComponent(lblPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelConfiguration1Layout.setVerticalGroup(
            panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblTCPIP))
                        .addGap(18, 18, 18)
                        .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblPort)))
                    .addComponent(imgConn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnOpenCloseSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelConfiguration2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cargar archivo y seleccionar una operacion"));

        btnCargar.setText("Abrir");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        txtPath.setEditable(false);

        jListConfig.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListConfigMouseClicked(evt);
            }
        });
        jListConfig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListConfigKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jListConfig);

        jListTxn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListTxnMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jListTxn);

        javax.swing.GroupLayout panelConfiguration2Layout = new javax.swing.GroupLayout(panelConfiguration2);
        panelConfiguration2.setLayout(panelConfiguration2Layout);
        panelConfiguration2Layout.setHorizontalGroup(
            panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguration2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(panelConfiguration2Layout.createSequentialGroup()
                        .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPath, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelConfiguration2Layout.setVerticalGroup(
            panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargar)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        BtnSendMessage.setText("Enviar");
        BtnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSendMessageActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConfiguration2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelConfiguration1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConfiguration1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(panelConfiguration2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(306, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Icon setIcono(String url, JButton boton) {
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        int ancho = boton.getWidth() - 10;
        int alto = boton.getHeight() - 10;
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        return icono;
    }

    private void getconfigHost(String path) {
        Gson gson = new Gson();
        if (ProcessorFiles.isJSONValid(ProcessorFiles.jsonFile(path))) {
            try {
                ArchivoHost processorHost = gson.fromJson(ProcessorFiles.jsonFile(path), ArchivoHost.class);
                if (processorHost != null) {
                    apiHost = processorHost.getRemoteHost();
                    apiPort = String.valueOf(processorHost.getPort());
                    windowTCPIP.txtIpLocal.setText(processorHost.getLocalHost());
                    windowTCPIP.txtIpRemoto.setText(processorHost.getRemoteHost());
                    windowTCPIP.txtPort.setText(String.valueOf(processorHost.getPort()));
                    windowTCPIP.txtTimeout.setText(String.valueOf(processorHost.getTimeout()));
                } else {
                    clearFields();
                }
            } catch (com.google.gson.JsonSyntaxException ex) {
                System.out.println("error :" + ex);
            }
        } else {
            clearFields();
        }
    }

    private void clearFields() {
        apiHost = "";
        apiPort = "";
        windowTCPIP.txtIpLocal.setText("");
        windowTCPIP.txtIpRemoto.setText("");
        windowTCPIP.txtPort.setText("");
        windowTCPIP.txtTimeout.setText("");
    }

    private void initWorkSpace() {
        imgConn.setIcon(new ImageIcon(getClass().getResource("../img/img1.png")));
        btnTCPIP.setIcon(setIcono("../img/ipAdress9.png", btnTCPIP));
        txtNumIns.setEnabled(false);
        txtNumIns.setText("1");
        txtNumTxn.setText("1");
        /**String fichero;
        Gson gson = new Gson();
        fichero = ProcessorFiles.jsonFile("../SimulatorProcesos/ProcesosMC.json");
        FileWorkPathEntity processorWorkPath = gson.fromJson(fichero, FileWorkPathEntity.class);
        fichero = ProcessorFiles.jsonFile("../simulador-procesosmc/ProcesosMC.json");**/        
        ArchivoRuta processorWorkPath = new ArchivoRuta();
        processorWorkPath.setWorkPath("../simulador-procesosmc/MastercCard/host.js");
        processorWorkPath.setWorkParent("../simulador-procesosmc/MastercCard");
        windowTCPIP.path = processorWorkPath.getWorkPath();
        txtPath.setText(processorWorkPath.getWorkPath());     
        path = processorWorkPath.getWorkParent();
        getconfigHost(processorWorkPath.getWorkPath());
        ProcessorFiles.listConfigFiles(processorWorkPath.getWorkParent());
        jListConfig.setSelectedIndex(0);        
        setListTxn();
        jListTxn.setSelectedIndex(0);        
    }

    private void BtnOpenCloseSocketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOpenCloseSocketActionPerformed

        if (!apiHost.isEmpty()) {
            if (validateField(txtNumIns.getText()) && validateField(txtNumTxn.getText())) {
                if (validateNumber(txtNumIns.getText()) && validateNumber(txtNumTxn.getText())) {
                    try {
                        connectClient++;
                        if (connectClient == 0) {
                            socketProxy.setApiHost(apiHost);
                            socketProxy.setApiPort(apiPort);
                            socketProxy.setNumIns(Integer.parseInt(txtNumIns.getText()));
                            socketProxy.setNumTxn(Integer.parseInt(txtNumTxn.getText()));
                            if (socketProxy.openSocketAny() != 0) {
                                connectClient = -1;
                            }
                        } else {
                            socketProxy.closeSocket();
                            connectClient = -1;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error: " + e);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1000.getMensaje());
                }
            } else {
                JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1002.getMensaje());
            }
        } else {
            JOptionPane.showMessageDialog(null, EnumErrores.ERROR_VALIDACION_OBLIGATORIEDAD_1003.getMensaje());
        }
    }//GEN-LAST:event_BtnOpenCloseSocketActionPerformed

    private void btnTCPIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCPIPActionPerformed
        windowTCPIP.setVisible(true);
    }//GEN-LAST:event_btnTCPIPActionPerformed

    private void BtnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSendMessageActionPerformed
        // TODO add your handling code here:
        socketProxy.setFile(path);
        socketProxy.setFilePath(path);
        socketProxy.sendMessageSocket();
    }//GEN-LAST:event_BtnSendMessageActionPerformed

    private void CboxNumIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboxNumIActionPerformed
        // TODO add your handling code here:
        if (CboxNumI.isSelected() == true) {
            txtNumIns.setEnabled(true);
            txtNumIns.requestFocus();
        } else {
            txtNumIns.setText("1");
            txtNumIns.setEnabled(false);
        }

    }//GEN-LAST:event_CboxNumIActionPerformed

    private void CboxNumIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CboxNumIMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CboxNumIMouseClicked

    //@SuppressWarnings("null")
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jf = new JFileChooser("C:\\SISO");
            jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jf.setMultiSelectionEnabled(false);
            jf.showOpenDialog(this);
            File seleccion_ruta = jf.getSelectedFile();
            if (seleccion_ruta != null) {
                path = seleccion_ruta.getParent();
                ProcessorFiles.listConfigFiles(seleccion_ruta.getParent());
                ArchivoRuta.setConfigWorkPath(seleccion_ruta.getAbsolutePath(), seleccion_ruta.getParent());
                getconfigHost(seleccion_ruta.getAbsolutePath());
                windowTCPIP.path = seleccion_ruta.getAbsolutePath();
                txtPath.setText(seleccion_ruta.getAbsolutePath());
            }
        } catch (HeadlessException e) {
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void jListConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListConfigMouseClicked
        // TODO add your handling code here:
        setListTxn();
    }//GEN-LAST:event_jListConfigMouseClicked

    private void jListConfigKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListConfigKeyPressed

        setListTxn();
    }//GEN-LAST:event_jListConfigKeyPressed

    private void jListTxnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListTxnMouseClicked
        // TODO add your handling code here:
        setListtxn2();
        System.out.println("fileConfigEntity FF" + fileConfigEntity.getGeneratorsFile());

    }//GEN-LAST:event_jListTxnMouseClicked

    public void setListtxn2(){
        String pathConfig = jListTxn.getSelectedValue();
        System.out.println("--> " + pathConfig);
    }
    public void setListTxn() {        
        String pathConfig = path + "\\" + jListConfig.getSelectedValue();
        Gson gson = new Gson();
        if (ProcessorFiles.isJSONValid(ProcessorFiles.jsonFile(pathConfig))) {
            try {
                fileConfigEntity = gson.fromJson(ProcessorFiles.jsonFile(pathConfig), ArchivoConfiguracion.class);
                if (fileConfigEntity != null) {
                    //String pathConfig1 = path + "\\" + fileConfigEntity.getWorkPath() + "\\" + fileConfigEntity.getGeneratorsFile();
                    
                    ProcessorFiles.listTransactiones(path + "\\" + fileConfigEntity.getWorkPath() + "\\" + fileConfigEntity.getGeneratorsFile());
                    System.out.println("fileConfigEntity " + fileConfigEntity.getGeneratorsFile());
                    System.out.println("fileConfigEntity1 " + fileConfigEntity.getParametersFile());
                    System.out.println("PAT ORIGEN: "+ path);
                } else {
                    clearFields();
                }
            } catch (com.google.gson.JsonSyntaxException ex) {
                System.out.println("error :" + ex);
            }
        } else {
            clearFields();
        }
        jListTxn.setSelectedIndex(0);
        setListtxn2();
    }

    public boolean validateField(String number) {
        return !number.isEmpty();
    }

    public boolean validateNumber(String number) {
        return number.matches("[0-9]*");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProcesosMC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcesosMC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcesosMC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcesosMC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProcesosMC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton BtnOpenCloseSocket;
    public static javax.swing.JButton BtnSendMessage;
    private javax.swing.JCheckBox CboxNumI;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnTCPIP;
    public static javax.swing.JLabel imgConn;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JList<String> jListConfig;
    public static javax.swing.JList<String> jListTxn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JLabel lblPort;
    public static javax.swing.JLabel lblTCPIP;
    private javax.swing.JPanel panelConfiguration;
    private javax.swing.JPanel panelConfiguration1;
    private javax.swing.JPanel panelConfiguration2;
    private javax.swing.JTextField txtNumIns;
    private javax.swing.JTextField txtNumTxn;
    private javax.swing.JTextField txtPath;
    // End of variables declaration//GEN-END:variables
}
