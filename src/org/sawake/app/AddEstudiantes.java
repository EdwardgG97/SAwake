package org.sawake.app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Grupo;
import org.sawake.controller.EstudiantesJpaController;
import org.sawake.controller.GrupoJpaController;
import org.sawake.controller.exceptions.NonexistentEntityException;

public class AddEstudiantes extends javax.swing.JFrame {

    private String numGrupo;
    private boolean filtroEst, editarEstudiante;
    private Estudiantes estudiante;
    private Grupo grupo;
    private List<Grupo> listGrupo;
    private List<Estudiantes> listEstudiantes;
    private EstudiantesJpaController estController;
    private GrupoJpaController grupoController;
    private DefaultTableModel tableModel;

    public AddEstudiantes() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.filtroEst = false;
        this.editarEstudiante = false;
        this.tblEstudiantes.getTableHeader().setReorderingAllowed(false);
        this.estController = new EstudiantesJpaController();
        this.grupoController = new GrupoJpaController();
        this.listEstudiantes = estController.findEstudiantesEntities();
        this.listGrupo = grupoController.findGrupoEntities();
        //Llenar JComboBox Grupo
        jComboBoxGrupos.addItem("Todos");
        for (Grupo grupo : listGrupo) {
            String nameGrupo = grupo.getNumGrupo();
            jComboBoxGrupos.addItem(nameGrupo);
            jComboGrupo.addItem(nameGrupo);
        }
        if (listEstudiantes.isEmpty()) {
            tableModel = (DefaultTableModel) tblEstudiantes.getModel();
            tblEstudiantes.setModel(tableModel);
        } else {
            this.tblEstudiantes.setModel(setTableModel());
        }

    }

    public DefaultTableModel setTableModel() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Nombres", "Apellidos", "Documento", "Edad", "Sexo", "Telefono"}) {
            Class[] types = new Class[]{String.class, String.class, Long.class, Short.class, String.class, Long.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        if (!filtroEst) {
            for (Estudiantes est : listEstudiantes) {
                modelo.addRow(new Object[]{est.getNomEst(), est.getApeEst(), est.getDocEst(), est.getEdadEst(), est.getSexEst(), est.getTelEst()});
            }
        } else if (filtroEst && numGrupo != null) {
            for (Estudiantes est : listEstudiantes) {
                if (est.getGrupoId().getNumGrupo().equals(numGrupo)) {
                    modelo.addRow(new Object[]{est.getNomEst(), est.getApeEst(), est.getDocEst(), est.getEdadEst(), est.getSexEst(), est.getTelEst()});
                }
            }
        }
        return modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgNewEstudiante = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNewNombre = new javax.swing.JTextField();
        txtNewApellido = new javax.swing.JTextField();
        txtNewDocumento = new javax.swing.JTextField();
        txtNewEdad = new javax.swing.JTextField();
        txtNewTelefono = new javax.swing.JTextField();
        jComboGrupo = new javax.swing.JComboBox<>();
        rbMasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        btnSaveEstudiante = new javax.swing.JButton();
        buttonGroupSexo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxGrupos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnAddEstudiante = new javax.swing.JButton();
        btnEliminarEstudiante = new javax.swing.JButton();
        btnEditarEstudiante = new javax.swing.JButton();
        btnBuscarPorGrupos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();

        dlgNewEstudiante.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlgNewEstudiante.setResizable(false);
        dlgNewEstudiante.setSize(new java.awt.Dimension(493, 369));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nombres:");

        jLabel5.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Apellidos:");

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Documento:");

        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Edad:");

        jLabel8.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Sexo:");

        jLabel9.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Telefono:");

        jLabel10.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Grupo:");

        buttonGroupSexo.add(rbMasculino);
        rbMasculino.setSelected(true);
        rbMasculino.setText("Maculino");

        buttonGroupSexo.add(rbFemenino);
        rbFemenino.setText("Femenino");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNewNombre)
                    .addComponent(txtNewApellido)
                    .addComponent(txtNewDocumento)
                    .addComponent(txtNewEdad)
                    .addComponent(txtNewTelefono)
                    .addComponent(jComboGrupo, 0, 190, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbMasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbFemenino)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNewNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNewApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNewDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNewEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rbMasculino)
                    .addComponent(rbFemenino))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNewTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(86, 138, 215));

        btnSaveEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveEstudiante.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        btnSaveEstudiante.setForeground(new java.awt.Color(102, 102, 102));
        btnSaveEstudiante.setText("Guardar");
        btnSaveEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveEstudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSaveEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(btnSaveEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgNewEstudianteLayout = new javax.swing.GroupLayout(dlgNewEstudiante.getContentPane());
        dlgNewEstudiante.getContentPane().setLayout(dlgNewEstudianteLayout);
        dlgNewEstudianteLayout.setHorizontalGroup(
            dlgNewEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgNewEstudianteLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgNewEstudianteLayout.setVerticalGroup(
            dlgNewEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setTitle("SA Estudiantes");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(86, 138, 215));

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("Estudiantes");

        jComboBoxGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxGruposMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(231, 231, 231));
        jLabel2.setText("Grupos:");

        btnAddEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        btnAddEstudiante.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnAddEstudiante.setForeground(new java.awt.Color(102, 102, 102));
        btnAddEstudiante.setText("Crear Nuevo Estudiante");
        btnAddEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEstudianteActionPerformed(evt);
            }
        });

        btnEliminarEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarEstudiante.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnEliminarEstudiante.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminarEstudiante.setText("Eliminar Estudiante");
        btnEliminarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEstudianteActionPerformed(evt);
            }
        });

        btnEditarEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarEstudiante.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnEditarEstudiante.setForeground(new java.awt.Color(102, 102, 102));
        btnEditarEstudiante.setText("Editar Estudiante");
        btnEditarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEstudianteActionPerformed(evt);
            }
        });

        btnBuscarPorGrupos.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarPorGrupos.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnBuscarPorGrupos.setForeground(new java.awt.Color(102, 102, 102));
        btnBuscarPorGrupos.setText("Buscar");
        btnBuscarPorGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorGruposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxGrupos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnEliminarEstudiante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddEstudiante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnEditarEstudiante))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnBuscarPorGrupos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addComponent(btnAddEstudiante)
                .addGap(31, 31, 31)
                .addComponent(btnEditarEstudiante)
                .addGap(26, 26, 26)
                .addComponent(btnEliminarEstudiante)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnBuscarPorGrupos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombres", "Apellidos", "Documento", "Edad", "Sexo", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Short.class, java.lang.String.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEstudiantes);
        if (tblEstudiantes.getColumnModel().getColumnCount() > 0) {
            tblEstudiantes.getColumnModel().getColumn(0).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(1).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(2).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(3).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(4).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(5).setResizable(false);
        }

        lblTitle.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(102, 102, 102));
        lblTitle.setText("Estudiantes :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblTitle))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEstudianteActionPerformed
        this.txtNewNombre.setText("");
        this.txtNewApellido.setText("");
        this.txtNewDocumento.setText("");
        this.txtNewEdad.setText("");
        this.txtNewTelefono.setText("");
        this.editarEstudiante = false;
        dlgNewEstudiante.pack();
        dlgNewEstudiante.setModal(true);
        dlgNewEstudiante.setLocationRelativeTo(null);
        dlgNewEstudiante.setVisible(true);
    }//GEN-LAST:event_btnAddEstudianteActionPerformed

    private void btnEliminarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEstudianteActionPerformed
        try {
            if (tblEstudiantes.getRowCount() != 0) {
                if (tblEstudiantes.getSelectedRow() > -1) {
                    long docEst = (long) tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 2);
                    for (Estudiantes est : listEstudiantes) {
                        if (est.getDocEst() == docEst) {
                            estController.destroy(est.getIdEst());
                            JOptionPane.showMessageDialog(this, "Estudiante eliminado Correctamente");
                            DefaultTableModel modelo = (DefaultTableModel) tblEstudiantes.getModel();
                            modelo.removeRow(tblEstudiantes.getSelectedRow());
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un Estudiante de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "El estudiante está ligado a otras tablas y no es posible eliminar, Por favor verificar.", "¡Problemas al Eliminar!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarEstudianteActionPerformed

    private void btnSaveEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveEstudianteActionPerformed
        try {
            if (!"".equals(txtNewNombre.getText()) && !"".equals(txtNewApellido.getText()) && !"".equals(txtNewDocumento.getText()) && !"".equals(txtNewEdad.getText()) && !"".equals(txtNewTelefono.getText())) {
                if (editarEstudiante) {
                    this.estudiante.setNomEst(txtNewNombre.getText());
                    this.estudiante.setApeEst(txtNewApellido.getText());
                    long estDoc = Long.parseLong(txtNewDocumento.getText());
                    this.estudiante.setDocEst(estDoc);
                    short estEdad = Short.parseShort(txtNewEdad.getText());
                    this.estudiante.setEdadEst(estEdad);
                    if (this.rbMasculino.isSelected()) {
                        this.estudiante.setSexEst("M");
                    } else {
                        this.estudiante.setSexEst("F");
                    }
                    Long estTel = Long.parseLong(txtNewTelefono.getText());
                    this.estudiante.setTelEst(estTel);
                    for (Grupo grupo : listGrupo) {
                        if (jComboGrupo.getSelectedItem().toString().equals(grupo.getNumGrupo())) {
                            this.grupo = grupo;
                            this.estudiante.setGrupoId(this.grupo);
                        }
                    }
                    this.estController.edit(this.estudiante);
                    JOptionPane.showMessageDialog(this, "Estudiante editado Correctamente");
                } else {
                    this.estudiante.setNomEst(txtNewNombre.getText());
                    this.estudiante.setApeEst(txtNewApellido.getText());
                    long estDoc = Long.parseLong(txtNewDocumento.getText());
                    this.estudiante.setDocEst(estDoc);
                    short estEdad = Short.parseShort(txtNewEdad.getText());
                    this.estudiante.setEdadEst(estEdad);
                    if (this.rbMasculino.isSelected()) {
                        this.estudiante.setSexEst("M");
                    } else {
                        this.estudiante.setSexEst("F");
                    }
                    Long estTel = Long.parseLong(txtNewTelefono.getText());
                    this.estudiante.setTelEst(estTel);
                    for (Grupo grupo : listGrupo) {
                        if (jComboGrupo.getSelectedItem().toString().equals(grupo.getNumGrupo())) {
                            this.grupo = grupo;
                            this.estudiante.setGrupoId(this.grupo);
                        }
                    }
                    this.estController.create(this.estudiante);
                    JOptionPane.showMessageDialog(this, "Estudiante almacenado Correctamente");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El estudiante NO pudo ser Guardado.", "¡Problemas al Guardar!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddEstudiantes.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnSaveEstudianteActionPerformed

    private void jComboBoxGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxGruposMouseClicked
    }//GEN-LAST:event_jComboBoxGruposMouseClicked

    private void btnEditarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEstudianteActionPerformed
        if (tblEstudiantes.getRowCount() != 0) {
            if (tblEstudiantes.getSelectedRow() > -1) {
                Long docEst = (Long) this.tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 2);
                for (Estudiantes est : listEstudiantes) {
                    long documento = est.getDocEst();
                    if (documento == docEst) {
                        this.editarEstudiante = true;
                        this.estudiante = est;
                        txtNewNombre.setText(this.estudiante.getNomEst());
                        txtNewApellido.setText(this.estudiante.getApeEst());
                        String documentoEst = String.valueOf(this.estudiante.getDocEst());
                        txtNewDocumento.setText(documentoEst);
                        String edadEst = Short.toString(this.estudiante.getEdadEst());
                        txtNewEdad.setText(edadEst);
                        if (this.estudiante.getSexEst().equals("M")) {
                            this.rbMasculino.setSelected(true);
                        } else if (this.estudiante.getSexEst().equals("F")) {
                            this.rbFemenino.setSelected(true);
                        }
                        String telEst = String.valueOf(this.estudiante.getTelEst());
                        txtNewTelefono.setText(telEst);
                        this.dlgNewEstudiante.pack();
                        this.dlgNewEstudiante.setModal(true);
                        this.dlgNewEstudiante.setLocationRelativeTo(null);
                        this.dlgNewEstudiante.setVisible(true);
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un Estudiante de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditarEstudianteActionPerformed

    private void btnBuscarPorGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorGruposActionPerformed
        numGrupo = jComboBoxGrupos.getSelectedItem().toString();
        if (!numGrupo.equals("Todos")) {
            for (Grupo grupo : listGrupo) {
                if (grupo.getNumGrupo().equals(numGrupo)) {
                    filtroEst = true;
                    this.lblTitle.setText("Estudiantes del Grupo: " + numGrupo);
                    this.tblEstudiantes.setModel(setTableModel());
                }
            }
        } else {
            filtroEst = false;
            this.tblEstudiantes.setModel(setTableModel());
        }
    }//GEN-LAST:event_btnBuscarPorGruposActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEstudiante;
    private javax.swing.JButton btnBuscarPorGrupos;
    private javax.swing.JButton btnEditarEstudiante;
    private javax.swing.JButton btnEliminarEstudiante;
    private javax.swing.JButton btnSaveEstudiante;
    private javax.swing.ButtonGroup buttonGroupSexo;
    private javax.swing.JDialog dlgNewEstudiante;
    private javax.swing.JComboBox<String> jComboBoxGrupos;
    private javax.swing.JComboBox<String> jComboGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tblEstudiantes;
    private javax.swing.JTextField txtNewApellido;
    private javax.swing.JTextField txtNewDocumento;
    private javax.swing.JTextField txtNewEdad;
    private javax.swing.JTextField txtNewNombre;
    private javax.swing.JTextField txtNewTelefono;
    // End of variables declaration//GEN-END:variables
}
