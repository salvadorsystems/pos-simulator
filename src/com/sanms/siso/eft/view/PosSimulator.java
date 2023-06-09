package com.sanms.siso.eft.view;

import com.google.gson.Gson;
import com.sanms.siso.eft.model.FileConfig;
import com.sanms.siso.eft.processor.ProcessFile;
import com.sanms.siso.eft.model.FileHost;
import com.sanms.siso.eft.model.FilePOS;
import com.sanms.siso.eft.model.Generator;
import com.sanms.siso.eft.model.Operation;
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
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author salvador
 */
public final class PosSimulator extends javax.swing.JFrame {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PosSimulator.class);
    static String ipAdress;
    static String portHost;
    private boolean valor = false;
    private String enviromentPath;
    Gson gson = new Gson();

    private String txnName;
    ProxySocket socketProxy;
    HostConfig windowTCPIP = new HostConfig(this, true);
    FileConfig archivoConfiguracion;
    Operation operacion;
    List<Stream> listStreams;
    List<Generator> listGenerator;
    FilePOS processorWorkPath;

    public PosSimulator() {
        initComponents();
        this.socketProxy = new ProxySocket((DefaultTableModel) jTable1.getModel(), (DefaultTableModel) jTable2.getModel(),
                (DefaultTableModel) jTable3.getModel(), jTable1.getColumnModel(), jTable2.getColumnModel(),
                jTable3.getColumnModel());
        initConfig();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        panelConfiguration = new javax.swing.JPanel();
        btnTCPIP = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        num_instances = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        num_ship = new javax.swing.JTextField();
        CboxNumI = new javax.swing.JCheckBox();
        panelConfiguration1 = new javax.swing.JPanel();
        imgConn = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ip_adress = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        btnSendMessage = new javax.swing.JButton();
        panelConfiguration2 = new javax.swing.JPanel();
        btnCargar = new javax.swing.JButton();
        txtPath = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListConfig = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListTxn = new javax.swing.JList<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuConfig = new javax.swing.JMenu();
        jm_tcpip = new javax.swing.JMenuItem();
        jm_custom = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jm_connect = new javax.swing.JMenuItem();
        jm_sendMessage = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenu();
        jMenuPDF = new javax.swing.JMenuItem();
        jMenuXLS = new javax.swing.JMenuItem();
        jm_reportStatus = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        RESPUESTA.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Transaction data"));

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

        jLabel1.setText("Request");

        jLabel2.setText("Response");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        RESPUESTA.addTab("ISO Message", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CAMPO", "ISO", "TIPO", "LONG", "DATOS"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        RESPUESTA.addTab("Request", jScrollPane4);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CAMPO", "ISO", "TIPO", "LONG", "DATOS"
            }
        ));
        jScrollPane7.setViewportView(jTable2);

        RESPUESTA.addTab("Response", jScrollPane7);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Thread", "Socket", "Status", "Request time", "Req", "Response time", "Res", "Total(ms) "
            }
        ));
        jScrollPane8.setViewportView(jTable3);

        RESPUESTA.addTab("Status", jScrollPane8);

        panelConfiguration.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Configure TCP/IP"));

        btnTCPIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTCPIPActionPerformed(evt);
            }
        });

        jLabel5.setText("Num Instances");

        jLabel6.setText("Num shipments");

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
                    .addComponent(num_instances, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(num_ship))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CboxNumI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelConfigurationLayout.setVerticalGroup(
            panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfigurationLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTCPIP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelConfigurationLayout.createSequentialGroup()
                        .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(num_instances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CboxNumI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(panelConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(num_ship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );

        panelConfiguration1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Connection"));

        jLabel7.setText("IP Server :");

        jLabel8.setText("Port Server :");

        ip_adress.setText("0.0.0.0");

        lblPort.setText("00");

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnSendMessage.setText("Send Message");
        btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessageActionPerformed(evt);
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
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imgConn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                                .addComponent(ip_adress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20))
                            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                                .addComponent(lblPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(panelConfiguration1Layout.createSequentialGroup()
                        .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelConfiguration1Layout.setVerticalGroup(
            panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ip_adress))
                .addGap(18, 18, 18)
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblPort))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelConfiguration1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConfiguration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgConn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelConfiguration2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Customer"));
        panelConfiguration2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnCargar.setText("Open");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        txtPath.setEditable(false);

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
                    .addComponent(jScrollPane3)
                    .addGroup(panelConfiguration2Layout.createSequentialGroup()
                        .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        panelConfiguration2Layout.setVerticalGroup(
            panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfiguration2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelConfiguration2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargar)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        menuConfig.setText("Settings");

        jm_tcpip.setText("Configure TCP/IP");
        jm_tcpip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_tcpipActionPerformed(evt);
            }
        });
        menuConfig.add(jm_tcpip);

        jm_custom.setText("Select Custom");
        jm_custom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_customActionPerformed(evt);
            }
        });
        menuConfig.add(jm_custom);

        jMenuBar1.add(menuConfig);

        jMenu1.setText("Execute");

        jm_connect.setText("Connect");
        jm_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_connectActionPerformed(evt);
            }
        });
        jMenu1.add(jm_connect);

        jm_sendMessage.setText("Send Message");
        jMenu1.add(jm_sendMessage);

        jMenuBar1.add(jMenu1);

        Reportes.setText("Reports");

        jMenuPDF.setText("Generate Report - PDF");
        jMenuPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuPDFActionPerformed(evt);
            }
        });
        Reportes.add(jMenuPDF);

        jMenuXLS.setText("Generate Report - EXCEL");
        jMenuXLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuXLSActionPerformed(evt);
            }
        });
        Reportes.add(jMenuXLS);

        jm_reportStatus.setText("Generate Status Report - EXCEL");
        jm_reportStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jm_reportStatusActionPerformed(evt);
            }
        });
        Reportes.add(jm_reportStatus);

        jMenuBar1.add(Reportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RESPUESTA)
                    .addComponent(panelConfiguration1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelConfiguration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelConfiguration1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConfiguration2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RESPUESTA))
                .addGap(15, 15, 15))
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
        if (ProcessFile.isJSONValid(ProcessFile.convertJsonToString(path).toString())) {
            try {
                FileHost processorHost = gson.fromJson(ProcessFile.convertJsonToString(path).toString(), FileHost.class);
                if (processorHost != null && processorHost.getIpHost() != null) {
                    ipAdress = processorHost.getIpHost();
                    portHost = String.valueOf(processorHost.getPort());
                    windowTCPIP.ip_adress.setText(processorHost.getIpHost());
                    windowTCPIP.port_host.setText(String.valueOf(processorHost.getPort()));
                    windowTCPIP.time_out.setText(String.valueOf(processorHost.getTimeout()));
                } else {
                    initComponent();
                    txtPath.setText(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1008.getMensaje());
                }
            } catch (com.google.gson.JsonSyntaxException ex) {
                log.info("error :" + ex);
            }
        } else {
            initComponent();
            txtPath.setText(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1008.getMensaje());
        }
    }

    private void initConfig() {
        initComponent();
         processorWorkPath = gson.fromJson(ProcessFile.convertJsonToString(Constantes.PATH_CFG_POS).toString(), FilePOS.class);
         windowTCPIP.path = processorWorkPath.getWorkPath();
        if (ProcessFile.isJSONValid(ProcessFile.convertJsonToString(Constantes.PATH_CFG_POS).toString())) {
            File f = new File(processorWorkPath.getWorkPath());
            if (f.exists()) {
                txtPath.setText(processorWorkPath.getWorkParent());
                enviromentPath = processorWorkPath.getWorkParent();
                getconfigHost(processorWorkPath.getWorkPath());
                ProcessFile.listarArchivosConfiguracion(processorWorkPath.getWorkParent());
                jListConfig.setSelectedIndex(0);
                setListTxn();
                jListTxn.setSelectedIndex(0);
            } else {
                btnConnect.setEnabled(false);
                txtPath.setText(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1009.getMensaje());
            }
        } else {
            log.info(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1006.getMensaje());
        }
    }

    private void initComponent() {
        imgConn.setIcon(new ImageIcon(getClass().getResource(Constantes.PATH_IMG_OFF)));
        btnTCPIP.setIcon(setIcono(Constantes.PATH_IMG_IPADRESS, btnTCPIP));
        num_instances.setEnabled(false);
        btnSendMessage.setEnabled(false);
        jm_sendMessage.setEnabled(false);
        jm_connect.setEnabled(false);
        jMenuPDF.setEnabled(false);
        jMenuXLS.setEnabled(false);
        jm_reportStatus.setEnabled(false);
        num_instances.setText("1");
        num_ship.setText("1");
        ipAdress = "";
        portHost = "";
        windowTCPIP.ip_adress.setText("");
        windowTCPIP.port_host.setText("");
        windowTCPIP.time_out.setText("");
        btnConnect.setEnabled(false);
        btnSendMessage.setEnabled(false);
        ProcessFile.listarOperaciones(null);
        ProcessFile.listarArchivosConfiguracion("");
    }

    private void btnTCPIPActionPerformed(java.awt.event.ActionEvent evt) {
        windowTCPIP.setVisible(true);
    }

    private void CboxNumIActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (CboxNumI.isSelected() == true) {
            num_instances.setEnabled(true);
            num_instances.requestFocus();
        } else {
            num_instances.setText("1");
            num_instances.setEnabled(false);
        }

    }

    private void CboxNumIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CboxNumIMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CboxNumIMouseClicked

    //@SuppressWarnings("null")
    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        chooseCustomer();
    }//GEN-LAST:event_btnCargarActionPerformed

    private void chooseCustomer() {
        try {
            JFileChooser jf = new JFileChooser(processorWorkPath.getWorkParent());
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jf.setMultiSelectionEnabled(false);
            jf.showOpenDialog(this);
            File seleccion_ruta = jf.getSelectedFile();
            String hostPath = seleccion_ruta.getAbsolutePath() + "\\" + processorWorkPath.getWorkHost();
            enviromentPath = seleccion_ruta.getAbsolutePath();
            FilePOS.setConfigWorkPath(processorWorkPath.getWorkHost(), hostPath, enviromentPath);
            File file = new File(hostPath);
            if (file.exists()) {
                ProcessFile.listarArchivosConfiguracion(enviromentPath);
                jListConfig.setSelectedIndex(0);
                setListTxn();
                jListTxn.setSelectedIndex(0);
                getconfigHost(hostPath);
                windowTCPIP.path = hostPath;
                txtPath.setText(enviromentPath);
                processorWorkPath.setWorkParent(seleccion_ruta.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1008.getMensaje());
                txtPath.setText(Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1009.getMensaje());
                processorWorkPath.setWorkParent(seleccion_ruta.getAbsolutePath());
                initComponent();
            }
        } catch (HeadlessException e) {
            log.debug("->" + e);
        }
    }

    private void connectToHost() {
        valor = !valor;
        if (valor) {
            socketProxy.setupFront(Integer.parseInt(num_instances.getText()),Integer.parseInt(num_ship.getText()));
            socketProxy.openSocketAny(ipAdress, portHost);
        } else {
            socketProxy.closeSocket();
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

    private void jm_customActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_customActionPerformed
        // TODO add your handling code here:
        chooseCustomer();
    }//GEN-LAST:event_jm_customActionPerformed

    private void jMenuPDFActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // TODO add your handling code here:
            socketProxy.reportPDF();
        } catch (JRException ex) {
            Logger.getLogger(PosSimulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PosSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jMenuXLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuXLSActionPerformed
        try {
            // TODO add your handling code here:
            socketProxy.reportXLS();
        } catch (JRException ex) {
            Logger.getLogger(PosSimulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PosSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuXLSActionPerformed

    private void jm_tcpipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_tcpipActionPerformed
        // TODO add your handling code here:
        windowTCPIP.setVisible(true);
    }//GEN-LAST:event_jm_tcpipActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
        connectToHost();
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        // TODO add your handling code here:
        socketProxy.setupBack(enviromentPath, archivoConfiguracion.getWorkPath(), archivoConfiguracion.getParametersFile(), archivoConfiguracion.getTemplatesFile());
        socketProxy.sendMessageSocket(txnName, listStreams);
    }//GEN-LAST:event_btnSendMessageActionPerformed

    private void jm_reportStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_reportStatusActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            socketProxy.fullReport();
        } catch (JRException | IOException ex) {
            Logger.getLogger(PosSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jm_reportStatusActionPerformed

    private void jm_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jm_connectActionPerformed
        // TODO add your handling code here:
        connectToHost();
    }//GEN-LAST:event_jm_connectActionPerformed

    public void setListtxn2() {
        txnName = jListTxn.getSelectedValue();
        listGenerator = operacion.getGenerators();
        for (Generator generator : listGenerator) {
            if (generator.getDetail().equalsIgnoreCase(txnName)) {
                listStreams = generator.getStreams();
            }
        }
        log.info("Select: " + txnName);
    }

    public void setListTxn() {
        String rutaFileConfig = enviromentPath + "\\" + jListConfig.getSelectedValue();
        Gson gson = new Gson();
        if (ProcessFile.isJSONValid(ProcessFile.convertJsonToString(rutaFileConfig).toString())) {
            archivoConfiguracion = gson.fromJson(ProcessFile.convertJsonToString(rutaFileConfig).toString(), FileConfig.class);
            if (archivoConfiguracion != null && archivoConfiguracion.getWorkPath() != null && archivoConfiguracion.getGeneratorsFile() != null) {
                String rutaFileOpe = enviromentPath + "\\" + archivoConfiguracion.getWorkPath() + "\\" + archivoConfiguracion.getGeneratorsFile();
                if (ProcessFile.isJSONValid(ProcessFile.convertJsonToString(rutaFileOpe).toString())) {
                    operacion = gson.fromJson(ProcessFile.convertJsonToString(rutaFileOpe).toString(), Operation.class);
                    ProcessFile.listarOperaciones(operacion);
                    activeComponents();
                    setListtxn2();
                } else {
                    JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1006.getMensaje() + "\nRuta: " + rutaFileOpe);
                }
            } else {
                initComponent();
            }
        } else {
            JOptionPane.showMessageDialog(null, Errores.ERROR_VALIDACION_OBLIGATORIEDAD_1006.getMensaje());
            initComponent();
        }
    }

    private void activeComponents() {
        btnConnect.setEnabled(true);
        jm_connect.setEnabled(true);
        jListTxn.setSelectedIndex(0);
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
            java.util.logging.Logger.getLogger(PosSimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PosSimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PosSimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PosSimulator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PosSimulator().setVisible(true);
                PropertyConfigurator.configure(Constantes.PATH_LOG4J_PROPERTIES);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JCheckBox CboxNumI;
    private javax.swing.JTabbedPane RESPUESTA;
    private javax.swing.JMenu Reportes;
    public static javax.swing.JButton btnCargar;
    public static javax.swing.JButton btnConnect;
    public static javax.swing.JButton btnSendMessage;
    public static javax.swing.JButton btnTCPIP;
    public static javax.swing.JLabel imgConn;
    public static javax.swing.JLabel ip_adress;
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
    public static javax.swing.JMenuItem jMenuPDF;
    public static javax.swing.JMenuItem jMenuXLS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    public static javax.swing.JMenuItem jm_connect;
    public static javax.swing.JMenuItem jm_custom;
    public static javax.swing.JMenuItem jm_reportStatus;
    public static javax.swing.JMenuItem jm_sendMessage;
    public static javax.swing.JMenuItem jm_tcpip;
    public static javax.swing.JLabel lblPort;
    private javax.swing.JMenu menuConfig;
    private javax.swing.JTextField num_instances;
    public static javax.swing.JTextField num_ship;
    private javax.swing.JPanel panelConfiguration;
    private javax.swing.JPanel panelConfiguration1;
    private javax.swing.JPanel panelConfiguration2;
    private javax.swing.JTextField txtPath;
    public static javax.swing.JTextArea txtRequerimiento;
    public static javax.swing.JTextArea txtRespuesta;
    // End of variables declaration//GEN-END:variables
}
