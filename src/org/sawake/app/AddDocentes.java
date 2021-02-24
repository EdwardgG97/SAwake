package org.sawake.app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.sawake.controller.DocentesJpaController;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Docentes;

public class AddDocentes extends javax.swing.JFrame {

    private boolean editarDocente;
    private Docentes newDocente;
    private List<Docentes> listDocentes;
    private DocentesJpaController doceController;
    private DefaultTableModel tableModel;

    public AddDocentes() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.editarDocente = false;
        this.tblDocentes.getTableHeader().setReorderingAllowed(false);
        doceController = new DocentesJpaController();
        listDocentes = doceController.findDocentesEntities();
        if (listDocentes.isEmpty()) {
            tableModel = (DefaultTableModel) tblDocentes.getModel();
            tblDocentes.setModel(tableModel);
        } else {
            this.tblDocentes.setModel(setTableModel());
        }
    }

    public DefaultTableModel setTableModel() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Nombres", "Apellidos", "Documento", "Telefono"}) {
            Class[] types = new Class[]{String.class, String.class, Long.class, Long.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        if (!listDocentes.isEmpty() && listDocentes != null) {
            for (Docentes doce : listDocentes) {
                modelo.addRow(new Object[]{doce.getNomDoc(), doce.getApeDoc(), doce.getDocDoc(), doce.getTelDoc()});
            }
        }
        return modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgAddDocentes = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        txtNombreDocente = new javax.swing.JTextField();
        txtApellidoDocente = new javax.swing.JTextField();
        txtDocumentoDocente = new javax.swing.JTextField();
        txtTelefonoDocente = new javax.swing.JTextField();
        txtEmailDocente = new javax.swing.JTextField();
        txtLoginDocente = new javax.swing.JTextField();
        txtPassDocente = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSaveNewDocente = new javax.swing.JButton();
        buttonGroupSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAddDocente = new javax.swing.JButton();
        btnEditarDocente = new javax.swing.JButton();
        btnEliminarDocente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocentes = new javax.swing.JTable();

        dlgAddDocentes.setModal(true);
        dlgAddDocentes.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Nombres:");

        jLabel3.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Documento:");

        jLabel5.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Sexo:");

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Telefono:");

        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("email:");

        jLabel8.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Login:");

        jLabel9.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Contraseña:");

        buttonGroupSexo.add(rbMasculino);
        rbMasculino.setSelected(true);
        rbMasculino.setText("Masculino");

        buttonGroupSexo.add(rbFemenino);
        rbFemenino.setText("Femenino");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbMasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(rbFemenino)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreDocente)
                            .addComponent(txtApellidoDocente)
                            .addComponent(txtDocumentoDocente))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmailDocente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonoDocente))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassDocente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLoginDocente))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDocumentoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rbMasculino)
                    .addComponent(rbFemenino))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefonoDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmailDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtLoginDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtPassDocente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel4.setBackground(new java.awt.Color(86, 138, 215));

        btnSaveNewDocente.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveNewDocente.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        btnSaveNewDocente.setForeground(new java.awt.Color(102, 102, 102));
        btnSaveNewDocente.setText("Guardar");
        btnSaveNewDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNewDocenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnSaveNewDocente)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(btnSaveNewDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgAddDocentesLayout = new javax.swing.GroupLayout(dlgAddDocentes.getContentPane());
        dlgAddDocentes.getContentPane().setLayout(dlgAddDocentesLayout);
        dlgAddDocentesLayout.setHorizontalGroup(
            dlgAddDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgAddDocentesLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dlgAddDocentesLayout.setVerticalGroup(
            dlgAddDocentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setTitle("SA Docentes");
        setResizable(false);
        setSize(new java.awt.Dimension(679, 384));

        jPanel1.setBackground(new java.awt.Color(86, 138, 215));

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("Docentes");

        btnAddDocente.setBackground(new java.awt.Color(255, 255, 255));
        btnAddDocente.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnAddDocente.setForeground(new java.awt.Color(102, 102, 102));
        btnAddDocente.setText("Crear Nuevo Docente");
        btnAddDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDocenteActionPerformed(evt);
            }
        });

        btnEditarDocente.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarDocente.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnEditarDocente.setForeground(new java.awt.Color(102, 102, 102));
        btnEditarDocente.setText("Editar Docente");
        btnEditarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDocenteActionPerformed(evt);
            }
        });

        btnEliminarDocente.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarDocente.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnEliminarDocente.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminarDocente.setText("Eliminar Docente");
        btnEliminarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDocenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(54, 54, 54))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminarDocente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddDocente)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnEditarDocente)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(btnAddDocente)
                .addGap(46, 46, 46)
                .addComponent(btnEditarDocente)
                .addGap(46, 46, 46)
                .addComponent(btnEliminarDocente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Documento", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDocentes);
        if (tblDocentes.getColumnModel().getColumnCount() > 0) {
            tblDocentes.getColumnModel().getColumn(0).setResizable(false);
            tblDocentes.getColumnModel().getColumn(1).setResizable(false);
            tblDocentes.getColumnModel().getColumn(2).setResizable(false);
            tblDocentes.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDocenteActionPerformed
        this.txtNombreDocente.setText("");
        this.txtApellidoDocente.setText("");
        this.txtDocumentoDocente.setText("");
        this.txtTelefonoDocente.setText("");
        this.txtEmailDocente.setText("");
        this.txtLoginDocente.setText("");
        this.txtPassDocente.setText("");
        this.dlgAddDocentes.pack();
        this.dlgAddDocentes.setModal(true);
        this.dlgAddDocentes.setLocationRelativeTo(null);
        this.dlgAddDocentes.setVisible(true);
    }//GEN-LAST:event_btnAddDocenteActionPerformed

    private void btnEditarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDocenteActionPerformed
        if (tblDocentes.getRowCount() != 0) {
            if (tblDocentes.getSelectedRow() > -1) {
                Long docEst = (Long) this.tblDocentes.getValueAt(tblDocentes.getSelectedRow(), 2);
                for (Docentes doce : listDocentes) {
                    long doc = doce.getDocDoc();
                    if (docEst == doc) {
                        this.newDocente = doce;
                        this.txtNombreDocente.setText(doce.getNomDoc());
                        this.txtApellidoDocente.setText(doce.getApeDoc());
                        String documento = String.valueOf(doce.getDocDoc());
                        this.txtDocumentoDocente.setText(documento);
                        if (doce.getSexDoc().equals("M")) {
                            this.rbMasculino.setSelected(true);
                        } else {
                            this.rbFemenino.setSelected(true);
                        }
                        String telefono = String.valueOf(doce.getTelDoc());
                        this.txtTelefonoDocente.setText(telefono);
                        this.txtEmailDocente.setText(doce.getEmailDoc());
                        this.txtLoginDocente.setText(doce.getLogDoc());
                        this.txtPassDocente.setText(doce.getPassDoc());
                        this.dlgAddDocentes.pack();
                        this.dlgAddDocentes.setModal(true);
                        this.dlgAddDocentes.setLocationRelativeTo(null);
                        this.dlgAddDocentes.setVisible(true);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un Docente de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditarDocenteActionPerformed

    private void btnEliminarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDocenteActionPerformed
        try {
            if (tblDocentes.getRowCount() != 0) {
                if (tblDocentes.getSelectedRow() > -1) {
                    Long docEst = (Long) this.tblDocentes.getValueAt(tblDocentes.getSelectedRow(), 2);
                    for (Docentes doce : listDocentes) {
                        long doc = doce.getDocDoc();
                        if (docEst == doc) {
                            this.doceController.destroy(doce.getIdDoc());
                            JOptionPane.showMessageDialog(this, "Docente eliminado Correctamente");
                            DefaultTableModel modelo = (DefaultTableModel) tblDocentes.getModel();
                            modelo.removeRow(tblDocentes.getSelectedRow());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un Docente de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "El estudiante está ligado a otras tablas y no es posible eliminar, Por favor verificar.", "¡Problemas al Eliminar!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddDocentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarDocenteActionPerformed

    private void btnSaveNewDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNewDocenteActionPerformed
        try {
            if (editarDocente) {                
                this.newDocente.setNomDoc(txtNombreDocente.getText());
                this.newDocente.setApeDoc(txtApellidoDocente.getText());
                long documento = Long.getLong(txtDocumentoDocente.getText());
                this.newDocente.setDocDoc(documento);
                if (this.rbMasculino.isSelected()) {
                    this.newDocente.setSexDoc("M");
                } else {
                    this.newDocente.setSexDoc("F");
                }
                long telefono = Long.getLong(txtTelefonoDocente.getText());
                this.newDocente.setTelDoc(telefono);
                this.newDocente.setEmailDoc(txtEmailDocente.getText());
                this.newDocente.setLogDoc(txtLoginDocente.getText());
                this.newDocente.setPassDoc(txtPassDocente.getText());
                this.doceController.edit(this.newDocente);
            } else {
                this.newDocente = new Docentes();
                this.newDocente.setNomDoc(txtNombreDocente.getText());
                this.newDocente.setApeDoc(txtApellidoDocente.getText());
                long documento = Long.getLong(txtDocumentoDocente.getText());
                this.newDocente.setDocDoc(documento);
                if (this.rbMasculino.isSelected()) {
                    this.newDocente.setSexDoc("M");
                } else {
                    this.newDocente.setSexDoc("F");
                }
                long telefono = Long.getLong(txtTelefonoDocente.getText());
                this.newDocente.setTelDoc(telefono);
                this.newDocente.setEmailDoc(txtEmailDocente.getText());
                this.newDocente.setLogDoc(txtLoginDocente.getText());
                this.newDocente.setPassDoc(txtPassDocente.getText());
                this.doceController.create(this.newDocente);
            }
        } catch (Exception ex) {
            Logger.getLogger(AddDocentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveNewDocenteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDocente;
    private javax.swing.JButton btnEditarDocente;
    private javax.swing.JButton btnEliminarDocente;
    private javax.swing.JButton btnSaveNewDocente;
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JDialog dlgAddDocentes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tblDocentes;
    private javax.swing.JTextField txtApellidoDocente;
    private javax.swing.JTextField txtDocumentoDocente;
    private javax.swing.JTextField txtEmailDocente;
    private javax.swing.JTextField txtLoginDocente;
    private javax.swing.JTextField txtNombreDocente;
    private javax.swing.JTextField txtPassDocente;
    private javax.swing.JTextField txtTelefonoDocente;
    // End of variables declaration//GEN-END:variables
}
