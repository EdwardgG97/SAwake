package org.sawake.app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.sawake.entity.AcuEst;
import org.sawake.entity.Acudientes;
import org.sawake.controller.AcuEstJpaController;
import org.sawake.controller.AcudientesJpaController;
import org.sawake.controller.EstudiantesJpaController;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Estudiantes;

public class AddAcudientes extends javax.swing.JFrame {

    private boolean editarAcudiente;
    private Acudientes acudiente;
    private AcuEst newAcuEst;
    private List<Estudiantes> listEstudiantes;
    private List<Acudientes> listAcudientes;
    private List<AcuEst> listAcuEst;
    private AcudientesJpaController acuController;
    private AcuEstJpaController acuestController;
    private EstudiantesJpaController estController;
    private DefaultTableModel tableModel;

    public AddAcudientes() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.tblAcudientes.getTableHeader().setReorderingAllowed(false);
        this.editarAcudiente = false;
        this.estController = new EstudiantesJpaController();
        this.acuController = new AcudientesJpaController();
        this.acuestController = new AcuEstJpaController();
        this.listAcudientes = this.acuController.findAcudientesEntities();
        if (listAcudientes.isEmpty()) {
            tableModel = (DefaultTableModel) tblAcudientes.getModel();
            this.tblAcudientes.setModel(tableModel);
        } else {
            this.tblAcudientes.setModel(setTableModel());
        }
    }

    public DefaultTableModel setTableModel() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Nombres", "Apellidos", "Documento", "Sexo", "Telefono", "Email"}) {
            Class[] types = new Class[]{String.class, String.class, Long.class, String.class, Long.class, String.class};

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
        for (Acudientes acu : listAcudientes) {
            modelo.addRow(new Object[]{acu.getNomAcu(), acu.getApeAcu(), acu.getDocAcu(), acu.getSexAcu(), acu.getTelAcu(), acu.getEmailAcu()});
        }

        return modelo;
    }

    public DefaultTableModel setTableModelAcuEst() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Nombres", "Apellidos", "Documento", "Edad"}) {
            Class[] types = new Class[]{String.class, String.class, Long.class, Short.class};

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
        for (AcuEst acuest : listAcuEst) {
            modelo.addRow(new Object[]{acuest.getEstId().getNomEst(), acuest.getEstId().getApeEst(), acuest.getEstId().getDocEst(), acuest.getEstId().getEdadEst()});
        }

        return modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgNewAcudiente = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreAcudiente = new javax.swing.JTextField();
        txtApellidoAcudiente = new javax.swing.JTextField();
        txtDocumentoAcudiente = new javax.swing.JTextField();
        txtTelefonoAcudiente = new javax.swing.JTextField();
        txtEmailAcudiente = new javax.swing.JTextField();
        btnSaveAcudientes = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtLoginAcudiente = new javax.swing.JTextField();
        txtPassAcudiente = new javax.swing.JTextField();
        radioButtonM = new javax.swing.JRadioButton();
        radioButtonF = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        btnAddEstAcargo = new javax.swing.JButton();
        btnDeleteEstAcargo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEstudiantesACargo = new javax.swing.JTable();
        buttonGroup = new javax.swing.ButtonGroup();
        dlgAddEstAcargo = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        btnAddEstudianteCargo = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboEstudiantes = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAcudientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAddAcudiente = new javax.swing.JButton();
        btnEditAcudiente = new javax.swing.JButton();
        btnEliminarAcudiente = new javax.swing.JButton();

        dlgNewAcudiente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlgNewAcudiente.setResizable(false);
        dlgNewAcudiente.setSize(new java.awt.Dimension(693, 300));

        jPanel3.setBackground(new java.awt.Color(86, 138, 215));

        jLabel8.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel8.setText("EDITAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel7.setText("Email:");

        btnSaveAcudientes.setBackground(new java.awt.Color(86, 138, 215));
        btnSaveAcudientes.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnSaveAcudientes.setForeground(new java.awt.Color(231, 231, 231));
        btnSaveAcudientes.setText("Guardar");
        btnSaveAcudientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAcudientesActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Login:");

        jLabel10.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Contraseña:");

        buttonGroup.add(radioButtonM);
        radioButtonM.setText("Masculino");

        buttonGroup.add(radioButtonF);
        radioButtonF.setText("Femenino");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(37, 37, 37))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(36, 36, 36)))
                        .addComponent(jLabel10)
                        .addComponent(jLabel6))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreAcudiente)
                    .addComponent(txtDocumentoAcudiente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtApellidoAcudiente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTelefonoAcudiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailAcudiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoginAcudiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassAcudiente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(radioButtonM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioButtonF)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnSaveAcudientes, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidoAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDocumentoAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(radioButtonM)
                            .addComponent(radioButtonF))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTelefonoAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnSaveAcudientes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmailAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtLoginAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPassAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Informacion Acudiente:", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btnAddEstAcargo.setBackground(new java.awt.Color(86, 138, 215));
        btnAddEstAcargo.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnAddEstAcargo.setForeground(new java.awt.Color(231, 231, 231));
        btnAddEstAcargo.setText("Agregar Estudiante a cargo");
        btnAddEstAcargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEstAcargoActionPerformed(evt);
            }
        });

        btnDeleteEstAcargo.setBackground(new java.awt.Color(86, 138, 215));
        btnDeleteEstAcargo.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnDeleteEstAcargo.setForeground(new java.awt.Color(231, 231, 231));
        btnDeleteEstAcargo.setText("Eliminar Estudiante a cargo");
        btnDeleteEstAcargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEstAcargoActionPerformed(evt);
            }
        });

        tblEstudiantesACargo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombres", "Apellidos", "Documento", "Edad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Short.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEstudiantesACargo);
        if (tblEstudiantesACargo.getColumnModel().getColumnCount() > 0) {
            tblEstudiantesACargo.getColumnModel().getColumn(0).setResizable(false);
            tblEstudiantesACargo.getColumnModel().getColumn(1).setResizable(false);
            tblEstudiantesACargo.getColumnModel().getColumn(2).setResizable(false);
            tblEstudiantesACargo.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteEstAcargo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddEstAcargo)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddEstAcargo)
                    .addComponent(btnDeleteEstAcargo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane.addTab(" Estudiantes a cargo:", jPanel5);

        javax.swing.GroupLayout dlgNewAcudienteLayout = new javax.swing.GroupLayout(dlgNewAcudiente.getContentPane());
        dlgNewAcudiente.getContentPane().setLayout(dlgNewAcudienteLayout);
        dlgNewAcudienteLayout.setHorizontalGroup(
            dlgNewAcudienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgNewAcudienteLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dlgNewAcudienteLayout.setVerticalGroup(
            dlgNewAcudienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane)
        );

        dlgAddEstAcargo.setTitle("Añadir Estudiante a cargo:");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnAddEstudianteCargo.setBackground(new java.awt.Color(86, 138, 215));
        btnAddEstudianteCargo.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnAddEstudianteCargo.setForeground(new java.awt.Color(231, 231, 231));
        btnAddEstudianteCargo.setText("Agregar");
        btnAddEstudianteCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEstudianteCargoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Seleccione:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(37, 37, 37)
                .addComponent(jComboEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(btnAddEstudianteCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnAddEstudianteCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout dlgAddEstAcargoLayout = new javax.swing.GroupLayout(dlgAddEstAcargo.getContentPane());
        dlgAddEstAcargo.getContentPane().setLayout(dlgAddEstAcargoLayout);
        dlgAddEstAcargoLayout.setHorizontalGroup(
            dlgAddEstAcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dlgAddEstAcargoLayout.setVerticalGroup(
            dlgAddEstAcargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SA Acudientes");
        setResizable(false);
        setSize(new java.awt.Dimension(824, 363));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblAcudientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombres", "Apellidos", "Documento", "Sexo", "Telefono", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tblAcudientes);
        if (tblAcudientes.getColumnModel().getColumnCount() > 0) {
            tblAcudientes.getColumnModel().getColumn(0).setResizable(false);
            tblAcudientes.getColumnModel().getColumn(1).setResizable(false);
            tblAcudientes.getColumnModel().getColumn(2).setResizable(false);
            tblAcudientes.getColumnModel().getColumn(3).setResizable(false);
            tblAcudientes.getColumnModel().getColumn(4).setResizable(false);
            tblAcudientes.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(86, 138, 215));

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("Acudientes");

        btnAddAcudiente.setBackground(new java.awt.Color(255, 255, 255));
        btnAddAcudiente.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnAddAcudiente.setForeground(new java.awt.Color(102, 102, 102));
        btnAddAcudiente.setText("Crear Acudiente");
        btnAddAcudiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAcudienteActionPerformed(evt);
            }
        });

        btnEditAcudiente.setBackground(new java.awt.Color(255, 255, 255));
        btnEditAcudiente.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnEditAcudiente.setForeground(new java.awt.Color(102, 102, 102));
        btnEditAcudiente.setText("Editar Acudiente");
        btnEditAcudiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAcudienteActionPerformed(evt);
            }
        });

        btnEliminarAcudiente.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarAcudiente.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnEliminarAcudiente.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminarAcudiente.setText("Eliminar Acudiente");
        btnEliminarAcudiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarAcudienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnEditAcudiente)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddAcudiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEliminarAcudiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(btnAddAcudiente)
                .addGap(34, 34, 34)
                .addComponent(btnEditAcudiente)
                .addGap(31, 31, 31)
                .addComponent(btnEliminarAcudiente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddAcudienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAcudienteActionPerformed
        this.txtNombreAcudiente.setText("");
        this.txtApellidoAcudiente.setText("");
        this.txtDocumentoAcudiente.setText("");
        this.txtTelefonoAcudiente.setText("");
        this.txtEmailAcudiente.setText("");
        this.jTabbedPane.setEnabledAt(1, false);
        this.editarAcudiente = false;
        this.acudiente = null;
        this.dlgNewAcudiente.pack();
        this.dlgNewAcudiente.setModal(true);
        this.dlgNewAcudiente.setLocationRelativeTo(null);
        this.dlgNewAcudiente.setVisible(true);
    }//GEN-LAST:event_btnAddAcudienteActionPerformed

    private void btnEditAcudienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAcudienteActionPerformed
        if (tblAcudientes.getRowCount() != 0) {
            if (tblAcudientes.getSelectedRow() > -1) {
                Long docAcu = (Long) this.tblAcudientes.getValueAt(tblAcudientes.getSelectedRow(), 2);
                for (Acudientes acu : listAcudientes) {
                    long documento = acu.getDocAcu();
                    if (documento == docAcu) {
                        this.acudiente = acu;
                        this.listAcuEst = acuestController.findEstudiantes(acu);
                        this.tblEstudiantesACargo.setModel(setTableModelAcuEst());
                        //OJO .... CREAR QUERY PARA TREAR LOS ESTUDIATES POR CADA ACUDIENTE. *acuestController*
                        this.txtNombreAcudiente.setText(this.acudiente.getNomAcu());
                        this.txtApellidoAcudiente.setText(this.acudiente.getApeAcu());
                        String documentoAcu = String.valueOf(this.acudiente.getDocAcu());
                        this.txtDocumentoAcudiente.setText(documentoAcu);
                        if (this.acudiente.getSexAcu().equals("M")) {
                            this.radioButtonM.setSelected(true);
                        } else {
                            this.radioButtonF.setSelected(true);
                        }
                        String telAcu = String.valueOf(this.acudiente.getTelAcu());
                        this.txtTelefonoAcudiente.setText(telAcu);
                        this.txtEmailAcudiente.setText(this.acudiente.getEmailAcu());
                        this.editarAcudiente = true;
                        this.jTabbedPane.setEnabledAt(1, true);
                        this.dlgNewAcudiente.pack();
                        this.dlgNewAcudiente.setModal(true);
                        this.dlgNewAcudiente.setLocationRelativeTo(null);
                        this.dlgNewAcudiente.setVisible(true);
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un Acudiente de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditAcudienteActionPerformed

    private void btnEliminarAcudienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarAcudienteActionPerformed
        try {
            if (tblAcudientes.getRowCount() != 0) {
                if (tblAcudientes.getSelectedRow() > -1) {
                    Long docAcu = (Long) this.tblAcudientes.getValueAt(tblAcudientes.getSelectedRow(), 2);
                    for (Acudientes acu : listAcudientes) {
                        long documento = acu.getDocAcu();
                        if (documento == docAcu) {
                            this.acuController.destroy(acu.getIdAcu());
                            this.acudiente = null;
                            DefaultTableModel modelo = (DefaultTableModel) tblAcudientes.getModel();
                            modelo.removeRow(tblAcudientes.getSelectedRow());
                            JOptionPane.showMessageDialog(this, "Acudiente eliminado Correctamente");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un Acudiente de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "El Acudiente está ligado a otras tablas y no es posible eliminar, Por favor verificar.", "¡Problemas al Eliminar!", JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(AddAcudientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarAcudienteActionPerformed

    private void btnSaveAcudientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAcudientesActionPerformed
        try {
            if (!"".equals(this.txtNombreAcudiente.getText()) && !"".equals(this.txtApellidoAcudiente.getText()) && !"".equals(this.txtDocumentoAcudiente.getText()) && !"".equals(this.txtTelefonoAcudiente.getText()) && !"".equals(this.txtEmailAcudiente.getText())) {
                if (acudiente == null) {
                    this.acudiente = new Acudientes();
                }
                if (editarAcudiente) {
                    acudiente.setNomAcu(this.txtNombreAcudiente.getText());
                    acudiente.setApeAcu(this.txtApellidoAcudiente.getText());
                    long documento = Long.getLong(this.txtDocumentoAcudiente.getText());
                    acudiente.setDocAcu(documento);
                    if (this.radioButtonM.isSelected()) {
                        acudiente.setSexAcu("M");
                    } else if (this.radioButtonF.isSelected()) {
                        acudiente.setSexAcu("F");
                    }
                    long telefono = Long.getLong(this.txtTelefonoAcudiente.getText());
                    acudiente.setTelAcu(telefono);
                    acudiente.setEmailAcu(this.txtEmailAcudiente.getText());
                    acudiente.setLogAcu(this.txtLoginAcudiente.getText());
                    acudiente.setPassAcu(this.txtPassAcudiente.getText());
                    this.acuController.edit(acudiente);
                    JOptionPane.showMessageDialog(this, "Acudiente editado Correctamente");
                } else {
                    acudiente.setNomAcu(this.txtNombreAcudiente.getText());
                    acudiente.setApeAcu(this.txtApellidoAcudiente.getText());
                    Long documento = Long.getLong(this.txtDocumentoAcudiente.getText());
                    acudiente.setDocAcu(documento);
                    if (this.radioButtonM.isSelected()) {
                        acudiente.setSexAcu("M");
                    } else if (this.radioButtonF.isSelected()) {
                        acudiente.setSexAcu("F");
                    }
                    long telefono = Long.getLong(this.txtTelefonoAcudiente.getText());
                    acudiente.setTelAcu(telefono);
                    acudiente.setEmailAcu(this.txtEmailAcudiente.getText());
                    acudiente.setLogAcu(this.txtLoginAcudiente.getText());
                    acudiente.setPassAcu(this.txtPassAcudiente.getText());
                    this.acuController.create(acudiente);
                    this.editarAcudiente = true;
                    this.jTabbedPane.setEnabledAt(1, true);
                    JOptionPane.showMessageDialog(this, "Acudiente almacenado Correctamente");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se permiten campos vacios", "¡Atención!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El Acudiente NO pudo ser guardado.", "¡Problemas al Guardar!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddEstudiantes.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnSaveAcudientesActionPerformed

    private void btnDeleteEstAcargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEstAcargoActionPerformed
        try {
            if (tblEstudiantesACargo.getRowCount() != 0) {
                if (tblEstudiantesACargo.getSelectedRow() > -1) {
                    long docEst = (long) tblEstudiantesACargo.getValueAt(tblEstudiantesACargo.getSelectedRow(), 2);
                    for (AcuEst acuest : this.listAcuEst) {
                        if (acuest.getAcuId().equals(this.acudiente) && acuest.getEstId().getDocEst() == docEst) {
                            acuestController.destroy(acuest.getIdAcuest());
                            DefaultTableModel modelo = (DefaultTableModel) tblEstudiantesACargo.getModel();
                            modelo.removeRow(tblEstudiantesACargo.getSelectedRow());
                            JOptionPane.showMessageDialog(this, "Estudiante eliminado Correctamente");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un Estudiante de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No es posible eliminar el registro. " + e, "¡Problemas al Eliminar!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteEstAcargoActionPerformed

    private void btnAddEstAcargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEstAcargoActionPerformed
        this.listEstudiantes = estController.findEstudiantesEntities();
        this.newAcuEst = new AcuEst();
        boolean agregar = false;
        for (Estudiantes est : listEstudiantes) {
            for (AcuEst acuest : listAcuEst) {
                if (!acuest.getEstId().equals(est)) {
                    agregar = true;
                }
            }
            if (agregar) {
                this.jComboEstudiantes.addItem(est.getNomEst() + " " + est.getApeEst());
            }
        }
        this.dlgAddEstAcargo.pack();
        this.dlgAddEstAcargo.setModal(true);
        this.dlgAddEstAcargo.setLocationRelativeTo(null);
        this.dlgAddEstAcargo.setVisible(true);
    }//GEN-LAST:event_btnAddEstAcargoActionPerformed

    private void btnAddEstudianteCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEstudianteCargoActionPerformed
        String nomEst = this.jComboEstudiantes.getSelectedItem().toString();
        for (Estudiantes estu : listEstudiantes) {
            if (estu.getNomEst().equals(nomEst)) {
                newAcuEst.setEstId(estu);
                newAcuEst.setAcuId(this.acudiente);
                if (this.acuestController.create(newAcuEst)) {
                    this.listAcuEst = acuestController.findEstudiantes(this.acudiente);
                    this.tblEstudiantesACargo.setModel(setTableModelAcuEst());
                } else {
                    JOptionPane.showMessageDialog(this, "No es posible Añadir Nuevo Estudiante", "¡Problemas al Añadir!", JOptionPane.ERROR_MESSAGE);
                }
                this.dlgAddEstAcargo.setVisible(false);
                break;
            }
        }
    }//GEN-LAST:event_btnAddEstudianteCargoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAcudiente;
    private javax.swing.JButton btnAddEstAcargo;
    private javax.swing.JButton btnAddEstudianteCargo;
    private javax.swing.JButton btnDeleteEstAcargo;
    private javax.swing.JButton btnEditAcudiente;
    private javax.swing.JButton btnEliminarAcudiente;
    private javax.swing.JButton btnSaveAcudientes;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JDialog dlgAddEstAcargo;
    private javax.swing.JDialog dlgNewAcudiente;
    private javax.swing.JComboBox<String> jComboEstudiantes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JRadioButton radioButtonF;
    private javax.swing.JRadioButton radioButtonM;
    private javax.swing.JTable tblAcudientes;
    private javax.swing.JTable tblEstudiantesACargo;
    private javax.swing.JTextField txtApellidoAcudiente;
    private javax.swing.JTextField txtDocumentoAcudiente;
    private javax.swing.JTextField txtEmailAcudiente;
    private javax.swing.JTextField txtLoginAcudiente;
    private javax.swing.JTextField txtNombreAcudiente;
    private javax.swing.JTextField txtPassAcudiente;
    private javax.swing.JTextField txtTelefonoAcudiente;
    // End of variables declaration//GEN-END:variables
}
