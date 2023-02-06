package com.sanms.siso.eft.view;

import com.google.gson.Gson;
import com.sanms.siso.eft.model.ArchivoConfiguracion;
import com.sanms.siso.eft.processor.ProcesarArchivos;
import com.sanms.siso.eft.model.ArchivoHost;
import com.sanms.siso.eft.model.ArchivoRuta;
import com.sanms.siso.eft.model.Generator;
import com.sanms.siso.eft.model.Operacion;
import com.sanms.siso.eft.model.Stream;
import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.sanms.siso.eft.proxy.ProxySocket;
import com.sanms.siso.eft.utils.Constantes;
import com.sanms.siso.eft.utils.Errores;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author salvador
 */
public final class ProcesosMC extends javax.swing.JFrame {

    static String apiHost;
    static String apiPort;
    private int connectClient = -1;
    private String ruta;

    private String txnName;
    ProxySocket socketProxy;
    ViewHost windowTCPIP = new ViewHost(this, true);
    ArchivoConfiguracion archivoConfiguracion;
    Operacion operacion;
    List<Stream> listStreams;
    List<Generator> listGenerator;

    public ProcesosMC() {
        initComponents();
        this.socketProxy = new ProxySocket(jTable1, jTable2);
        initWorkSpace();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RESPUESTA = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtRequerimiento = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtRespuesta = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
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
        BtnSendMessage = new javax.swing.JButton();
        panelConfiguration2 = new javax.swing.JPanel();
        btnCargar = new javax.swing.JButton();
        txtPath = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListConfig = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTxn = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuConfig = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenu();
        jMenuPDF = new javax.swing.JMenuItem();
        jMenuXLS = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RESPUESTA.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de transacción"));

        txtRequerimiento.setEditable(false);
        txtRequerimiento.setColumns(20);
        txtRequerimiento.setLineWrap(true);
        txtRequerimiento.setRows(5);
        jScrollPane5.setViewportView(txtRequerimiento);

        txtRespuesta.setEditable(false);
        txtRespuesta.setColumns(20);
        txtRespuesta.setLineWrap(true);
        txtRespuesta.setRows(5);
        jScrollPane6.setViewportView(txtRespuesta);

        jLabel1.setText("Requerimiento");

        jLabel2.setText("Respuesta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        RESPUESTA.addTab("TRAMA ISO", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CAMPO", "ISO", "TIPO", "LONG", "DATOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);
        }

        RESPUESTA.addTab("REQUERIMIENTO", jScrollPane1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CAMPO", "ISO", "TIPO", "LONG", "DATOS"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        RESPUESTA.addTab("RESPUESTA", jScrollPane4);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelConfigurationLayout.setVerticalGroup(
            panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfigurationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        BtnSendMessage.setText("Enviar");
        BtnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSendMessageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConfiguration1Layout = new javax.swing.GroupLayout(panelConfiguration1);
        panelConfiguration1.setLayout(panelConfiguration1Layout);
        panelConfiguration1Layout.setHorizontalGroup(
            panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addComponent(BtnOpenCloseSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imgConn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addComponent(lblTCPIP, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
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
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addComponent(BtnOpenCloseSocket, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelConfiguration2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cliente"));
        panelConfiguration2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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
                .addGroup(panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(panelConfiguration2Layout.createSequentialGroup()
                        .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        menuConfig.setText("Configuraciones");

        jMenuItem1.setText("Configurar TCP/IP");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuConfig.add(jMenuItem1);

        jMenuItem2.setText("Seleccionar Transaccion");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuConfig.add(jMenuItem2);

        jMenuBar1.add(menuConfig);

        jMenu1.setText("Ejecutar");

        jMenuItem3.setText("Conectar");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText(" Enviar");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        Reportes.setText("Reportes");

        jMenuPDF.setText("Generar Reporte PDF");
        jMenuPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPDFActionPerformed(evt);
            }
        });
        Reportes.add(jMenuPDF);

        jMenuXLS.setText("Genera Reporte EXCEL");
        jMenuXLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuXLSActionPerformed(evt);
            }
        });
        Reportes.add(jMenuXLS);

        jMenuBar1.add(Reportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelConfiguration1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(RESPUESTA))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConfiguration1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RESPUESTA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (ProcesarArchivos.isJSONValid(ProcesarArchivos.convertJsonToString(path))) {
            try {
                ArchivoHost processorHost = gson.fromJson(ProcesarArchivos.convertJsonToString(path), ArchivoHost.class);
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
        configuracionComponentes();
        ArchivoRuta processorWorkPath = new ArchivoRuta();
        processorWorkPath.setWorkPath(Constantes.RUTA_HOST_CONFIG);
        processorWorkPath.setWorkParent(Constantes.RUTA_HOST);
        windowTCPIP.path = processorWorkPath.getWorkPath();
        txtPath.setText(processorWorkPath.getWorkPath());
        ruta = processorWorkPath.getWorkParent();
        getconfigHost(processorWorkPath.getWorkPath());
        ProcesarArchivos.listConfigFiles(processorWorkPath.getWorkParent());
        jListConfig.setSelectedIndex(0);
        setListTxn();
        jListTxn.setSelectedIndex(0);
    }

    private void configuracionComponentes() {
        imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.RUTA_IMG_OFF)));
        btnTCPIP.setIcon(setIcono(Constantes.RUTA_IMG_IPADRESS, btnTCPIP));
        txtNumIns.setEnabled(false);
        BtnSendMessage.setEnabled(false);
        jMenuPDF.setEnabled(false);
        jMenuXLS.setEnabled(false);
        txtNumIns.setText("1");
        txtNumTxn.setText("1");
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
                            BtnSendMessage.setEnabled(true);
                            if (socketProxy.openSocketAny() != 0) {
                                connectClient = -1;
                            }
                        } else {
                            socketProxy.closeSocket();
                            BtnSendMessage.setEnabled(false);
                            connectClient = -1;
                            ProcesosMC.jMenuPDF.setEnabled(false);
                            ProcesosMC.jMenuXLS.setEnabled(false);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("error: " + e);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1000.getMensaje());
                }
            } else {
                JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1002.getMensaje());
            }
        } else {
            JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1003.getMensaje());
        }
    }//GEN-LAST:event_BtnOpenCloseSocketActionPerformed

    private void btnTCPIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCPIPActionPerformed
        windowTCPIP.setVisible(true);
    }//GEN-LAST:event_btnTCPIPActionPerformed

    private void BtnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSendMessageActionPerformed
        // TODO add your handling code here:
        socketProxy.setTnxName(txnName);
        socketProxy.setListStream(listStreams);
        socketProxy.setParametersPath(ruta + "\\" + archivoConfiguracion.getWorkPath() + "\\" + archivoConfiguracion.getParametersFile());
        socketProxy.setTemplatesPath(ruta + "\\" + archivoConfiguracion.getWorkPath() + "\\" + archivoConfiguracion.getTemplatesFile());
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
        chooserHost();
    }//GEN-LAST:event_btnCargarActionPerformed

    private void chooserHost() {
        try {
            JFileChooser jf = new JFileChooser(Constantes.BASE_SISOROOT);
            jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jf.setMultiSelectionEnabled(false);
            jf.showOpenDialog(this);
            File seleccion_ruta = jf.getSelectedFile();
            if (seleccion_ruta != null) {
                ruta = seleccion_ruta.getParent();
                ProcesarArchivos.listConfigFiles(seleccion_ruta.getParent());
                ArchivoRuta.setConfigWorkPath(seleccion_ruta.getAbsolutePath(), seleccion_ruta.getParent());
                getconfigHost(seleccion_ruta.getAbsolutePath());
                windowTCPIP.path = seleccion_ruta.getAbsolutePath();
                txtPath.setText(seleccion_ruta.getAbsolutePath());
            }
        } catch (HeadlessException e) {
        }
    }

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
    }//GEN-LAST:event_jListTxnMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        chooserHost();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuPDFActionPerformed
        try {
            // TODO add your handling code here:
            socketProxy.generarReportePDF();
        } catch (JRException ex) {
            Logger.getLogger(ProcesosMC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesosMC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuPDFActionPerformed

    private void jMenuXLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuXLSActionPerformed
        try {
            // TODO add your handling code here:
            socketProxy.generarReporteXLS();
        } catch (JRException ex) {
            Logger.getLogger(ProcesosMC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProcesosMC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuXLSActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        windowTCPIP.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public void setListtxn2() {
        //String pathConfig = jListTxn.getSelectedValue();
        txnName = jListTxn.getSelectedValue();
        listGenerator = operacion.getGenerators();
        for (Generator generator : listGenerator) {
            if (generator.getDetail().equalsIgnoreCase(txnName)) {
                listStreams = generator.getStreams();
            }
        }
        System.out.println("" + listStreams.toString());
        System.out.println("-->2 " + txnName);
    }

    public void setListTxn() {
        String rutaFileConfig = ruta + "\\" + jListConfig.getSelectedValue();
        Gson gson = new Gson();
        if (ProcesarArchivos.isJSONValid(ProcesarArchivos.convertJsonToString(rutaFileConfig))) {
            archivoConfiguracion = gson.fromJson(ProcesarArchivos.convertJsonToString(rutaFileConfig), ArchivoConfiguracion.class);
            if (archivoConfiguracion != null) {
                String rutaFileOpe = ruta + "\\" + archivoConfiguracion.getWorkPath() + "\\" + archivoConfiguracion.getGeneratorsFile();
                if (ProcesarArchivos.isJSONValid(ProcesarArchivos.convertJsonToString(rutaFileOpe))) {
                    operacion = gson.fromJson(ProcesarArchivos.convertJsonToString(rutaFileOpe), Operacion.class);
                    ProcesarArchivos.listarOperaciones(operacion);
                } else {
                    JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1006.getMensaje() + "\nRuta: " + rutaFileOpe);
                }
            } else {
                clearFields();
            }
        } else {
            JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1006.getMensaje());
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
                PropertyConfigurator.configure(Constantes.RUTA_LOG4J_PROPERTIES);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton BtnOpenCloseSocket;
    public static javax.swing.JButton BtnSendMessage;
    private javax.swing.JCheckBox CboxNumI;
    private javax.swing.JTabbedPane RESPUESTA;
    private javax.swing.JMenu Reportes;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnTCPIP;
    public static javax.swing.JLabel imgConn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JList<String> jListConfig;
    public static javax.swing.JList<String> jListTxn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    public static javax.swing.JMenuItem jMenuPDF;
    public static javax.swing.JMenuItem jMenuXLS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JLabel lblPort;
    public static javax.swing.JLabel lblTCPIP;
    private javax.swing.JMenu menuConfig;
    private javax.swing.JPanel panelConfiguration;
    private javax.swing.JPanel panelConfiguration1;
    private javax.swing.JPanel panelConfiguration2;
    private javax.swing.JTextField txtNumIns;
    private javax.swing.JTextField txtNumTxn;
    private javax.swing.JTextField txtPath;
    public static javax.swing.JTextArea txtRequerimiento;
    public static javax.swing.JTextArea txtRespuesta;
    // End of variables declaration//GEN-END:variables
}
