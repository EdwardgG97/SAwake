package org.sawake.app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.sawake.entity.Administracion;
import org.sawake.controller.AdministracionJpaController;

public class Home extends javax.swing.JFrame {
    
    private String passOld, passNew;
    private List<Administracion> listaAdmin;
    private Administracion admin;
    private AdministracionJpaController adminControl;
    
    public Home(Administracion admin) {
        this.admin = admin;
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.adminControl = new AdministracionJpaController();
        this.txtNombre.setText(admin.getNomAdmi());
        this.txtApellido.setText(admin.getApeAdmi());
        this.txtEmail.setText(admin.getEmailAdmi());
        this.txtLogin.setText(admin.getLogAdmi());
        this.passOld = admin.getPassAdmi();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dlgPass = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtPassNew = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtPassOld = new javax.swing.JPasswordField();
        btnSavePass = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        dlgNewAdmin = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNombreNewAdmin = new javax.swing.JTextField();
        txtApellidoNewAdmin = new javax.swing.JTextField();
        txtEmailNewAdmin = new javax.swing.JTextField();
        txtLoginNewAdmin = new javax.swing.JTextField();
        txtPasswordNewAdmin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnSaveNewAdmin = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnFrmEstudiantes = new javax.swing.JButton();
        btnFrmAcudientes = new javax.swing.JButton();
        btnFrmDocentes = new javax.swing.JButton();
        btnFrmCitas = new javax.swing.JButton();
        SchoolAwake = new javax.swing.JLabel();
        lblconfig = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        btnCambioPass = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        checkEditar = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();

        dlgPass.setTitle("Cambiar Contraseña");
        dlgPass.setResizable(false);
        dlgPass.setSize(new java.awt.Dimension(363, 187));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Cambio de Contraseña");

        jLabel8.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Antigua Contraseña:");

        btnSavePass.setBackground(new java.awt.Color(86, 138, 215));
        btnSavePass.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        btnSavePass.setForeground(new java.awt.Color(231, 231, 231));
        btnSavePass.setText("Aceptar");
        btnSavePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSavePassActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nueva Contraseña:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassOld, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(txtPassNew))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnSavePass)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassOld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(btnSavePass, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dlgPassLayout = new javax.swing.GroupLayout(dlgPass.getContentPane());
        dlgPass.getContentPane().setLayout(dlgPassLayout);
        dlgPassLayout.setHorizontalGroup(
            dlgPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dlgPassLayout.setVerticalGroup(
            dlgPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Nombre Admin:");

        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Apellido Admin:");

        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Email Admin:");

        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Login Admin:");

        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Pass Admin:");

        jLabel15.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("NUEVO ADMIN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreNewAdmin)
                    .addComponent(txtApellidoNewAdmin)
                    .addComponent(txtEmailNewAdmin)
                    .addComponent(txtLoginNewAdmin)
                    .addComponent(txtPasswordNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(110, 110, 110))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtApellidoNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtEmailNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtLoginNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPasswordNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel5.setBackground(new java.awt.Color(86, 138, 215));

        btnSaveNewAdmin.setBackground(new java.awt.Color(255, 255, 255));
        btnSaveNewAdmin.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnSaveNewAdmin.setForeground(new java.awt.Color(102, 102, 102));
        btnSaveNewAdmin.setText("Guardar");
        btnSaveNewAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNewAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSaveNewAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btnSaveNewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dlgNewAdminLayout = new javax.swing.GroupLayout(dlgNewAdmin.getContentPane());
        dlgNewAdmin.getContentPane().setLayout(dlgNewAdminLayout);
        dlgNewAdminLayout.setHorizontalGroup(
            dlgNewAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dlgNewAdminLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dlgNewAdminLayout.setVerticalGroup(
            dlgNewAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SA Home");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(86, 138, 215));

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("SAwake Home");

        btnFrmEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        btnFrmEstudiantes.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnFrmEstudiantes.setForeground(new java.awt.Color(102, 102, 102));
        btnFrmEstudiantes.setText("ESTUDIANTES");
        btnFrmEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrmEstudiantesActionPerformed(evt);
            }
        });

        btnFrmAcudientes.setBackground(new java.awt.Color(255, 255, 255));
        btnFrmAcudientes.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnFrmAcudientes.setForeground(new java.awt.Color(102, 102, 102));
        btnFrmAcudientes.setText("ACUDIENTES");
        btnFrmAcudientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrmAcudientesActionPerformed(evt);
            }
        });

        btnFrmDocentes.setBackground(new java.awt.Color(255, 255, 255));
        btnFrmDocentes.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnFrmDocentes.setForeground(new java.awt.Color(102, 102, 102));
        btnFrmDocentes.setText("DOCENTES");
        btnFrmDocentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrmDocentesActionPerformed(evt);
            }
        });

        btnFrmCitas.setBackground(new java.awt.Color(255, 255, 255));
        btnFrmCitas.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnFrmCitas.setForeground(new java.awt.Color(102, 102, 102));
        btnFrmCitas.setText("OBS");
        btnFrmCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrmCitasActionPerformed(evt);
            }
        });

        SchoolAwake.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sawake/icons/ic_launcher.png"))); // NOI18N
        SchoolAwake.setToolTipText("¡SCHOOL AWAKE!");
        SchoolAwake.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SchoolAwakeMouseClicked(evt);
            }
        });

        lblconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sawake/icons/config.png"))); // NOI18N
        lblconfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblconfigMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnFrmEstudiantes)
                .addGap(53, 53, 53)
                .addComponent(btnFrmAcudientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(btnFrmDocentes)
                .addGap(52, 52, 52)
                .addComponent(btnFrmCitas)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SchoolAwake)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(150, 150, 150)
                .addComponent(lblconfig)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblconfig)
                    .addComponent(jLabel1)
                    .addComponent(SchoolAwake))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFrmEstudiantes)
                    .addComponent(btnFrmAcudientes)
                    .addComponent(btnFrmDocentes)
                    .addComponent(btnFrmCitas))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Apellido:");

        jLabel4.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("email:");

        jLabel5.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Login:");

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Contraseña:");

        txtNombre.setEditable(false);

        txtApellido.setEditable(false);

        txtEmail.setEditable(false);

        txtLogin.setEditable(false);

        btnCambioPass.setBackground(new java.awt.Color(86, 138, 215));
        btnCambioPass.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnCambioPass.setForeground(new java.awt.Color(231, 231, 231));
        btnCambioPass.setText("Cambiar Contraseña");
        btnCambioPass.setEnabled(false);
        btnCambioPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioPassActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(86, 138, 215));
        btnGuardar.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(231, 231, 231));
        btnGuardar.setText("GUARDAR");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(86, 138, 215));
        btnCerrarSesion.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(231, 231, 231));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        checkEditar.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        checkEditar.setForeground(new java.awt.Color(102, 102, 102));
        checkEditar.setText("Editar Datos Perfil:");
        checkEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEditarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(86, 138, 215));
        jButton1.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(231, 231, 231));
        jButton1.setText("+Admin");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(txtApellido)
                    .addComponent(txtEmail)
                    .addComponent(txtLogin)
                    .addComponent(btnCambioPass, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(checkEditar)
                            .addComponent(btnCerrarSesion))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkEditar)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnCambioPass))
                .addGap(39, 39, 39)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrarSesion)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            int res = JOptionPane.showConfirmDialog(this, "¿Seguro que desea guardar Cambios?", "¡Atención!", JOptionPane.YES_NO_OPTION);
            switch (res) {
                case JOptionPane.YES_OPTION:
                    admin.setNomAdmi(this.txtNombre.getText());
                    admin.setApeAdmi(this.txtApellido.getText());
                    admin.setEmailAdmi(this.txtEmail.getText());
                    admin.setLogAdmi(this.txtLogin.getText());
                    if (passNew != null) {
                        admin.setPassAdmi(this.passNew);
                    }
                    adminControl.edit(admin);
                    JOptionPane.showMessageDialog(null, "Cambios Exitosos", "¡Guardado!", JOptionPane.INFORMATION_MESSAGE);
                    this.txtNombre.setEditable(false);
                    this.txtApellido.setEditable(false);
                    this.txtEmail.setEditable(false);
                    this.txtLogin.setEditable(false);
                    this.btnCambioPass.setEnabled(false);
                    this.btnGuardar.setEnabled(false);
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnFrmEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrmEstudiantesActionPerformed
        AddEstudiantes frmEst = new AddEstudiantes();
        frmEst.setVisible(true);
    }//GEN-LAST:event_btnFrmEstudiantesActionPerformed

    private void btnCambioPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioPassActionPerformed
        dlgPass.pack();
        dlgPass.setLocationRelativeTo(null);
        dlgPass.setVisible(true);
    }//GEN-LAST:event_btnCambioPassActionPerformed

    private void btnSavePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSavePassActionPerformed
        char[] passOld = txtPassOld.getPassword();
        String passwordOld = new String(passOld);
        if (this.passOld.equals(passwordOld)) {
            int res = JOptionPane.showConfirmDialog(this, "¿Seguro que desea guardar los Cambios?", "¡Atención!", JOptionPane.YES_NO_OPTION);
            switch (res) {
                case JOptionPane.YES_OPTION:
                    char[] PassNew = txtPassNew.getPassword();
                    String passwordNew = new String(PassNew);
                    passNew = passwordNew;
                    dlgPass.dispose();
                    break;
                case JOptionPane.NO_OPTION:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "La Contraseña es incorrecta", "¡Intente Nuevamente!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSavePassActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        LoginSA login = new LoginSA();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void btnFrmAcudientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrmAcudientesActionPerformed
        AddAcudientes frmAcudiente = new AddAcudientes();
        frmAcudiente.setVisible(true);
    }//GEN-LAST:event_btnFrmAcudientesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int res = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir de su Sesión?", "¡Se cerrará la Sesión!", JOptionPane.YES_NO_OPTION);
        switch (res) {
            case JOptionPane.YES_OPTION:
                LoginSA login = new LoginSA();
                this.dispose();
                login.setVisible(true);
                break;
            case JOptionPane.NO_OPTION:
                break;
        }
    }//GEN-LAST:event_formWindowClosing

    private void checkEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEditarActionPerformed
        if (this.checkEditar.isSelected()) {
            this.txtNombre.setEditable(true);
            this.txtApellido.setEditable(true);
            this.txtEmail.setEditable(true);
            this.txtLogin.setEditable(true);
            this.btnCambioPass.setEnabled(true);
            this.btnGuardar.setEnabled(true);
        } else {
            this.txtNombre.setEditable(false);
            this.txtApellido.setEditable(false);
            this.txtEmail.setEditable(false);
            this.txtLogin.setEditable(false);
            this.btnCambioPass.setEnabled(false);
            this.btnGuardar.setEnabled(false);
        }

    }//GEN-LAST:event_checkEditarActionPerformed

    private void btnFrmDocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrmDocentesActionPerformed
        AddDocentes frmDocentes = new AddDocentes();
        frmDocentes.setVisible(true);
    }//GEN-LAST:event_btnFrmDocentesActionPerformed

    private void btnFrmCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFrmCitasActionPerformed
        ListObs observador = new ListObs();
        observador.setVisible(true);
    }//GEN-LAST:event_btnFrmCitasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dlgNewAdmin.pack();
        this.dlgNewAdmin.setModal(true);
        this.dlgNewAdmin.setLocationRelativeTo(null);
        this.dlgNewAdmin.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSaveNewAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNewAdminActionPerformed
        try {
            Administracion admin = new Administracion();
            admin.setNomAdmi(txtNombreNewAdmin.getText());
            admin.setApeAdmi(txtApellidoNewAdmin.getText());
            admin.setEmailAdmi(txtEmailNewAdmin.getText());
            admin.setLogAdmi(txtLoginNewAdmin.getText());
            admin.setPassAdmi(txtPasswordNewAdmin.getText());
            adminControl.create(admin);
            JOptionPane.showMessageDialog(this.dlgNewAdmin, "Admin Creado Correctamente");
            this.dlgNewAdmin.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.dlgNewAdmin, "Problemas al Crear nuevo Admin", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveNewAdminActionPerformed

    private void SchoolAwakeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SchoolAwakeMouseClicked
        Notificaciones_Observaciones notiObs = new Notificaciones_Observaciones();
        notiObs.setVisible(true);
    }//GEN-LAST:event_SchoolAwakeMouseClicked

    private void lblconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblconfigMouseClicked
        AddOtros frmOtros = new AddOtros();
        frmOtros.setVisible(true);
    }//GEN-LAST:event_lblconfigMouseClicked
    
    public Administracion getAdmin() {
        return admin;
    }
    
    public void setAdmin(Administracion admin) {
        this.admin = admin;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SchoolAwake;
    private javax.swing.JButton btnCambioPass;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnFrmAcudientes;
    private javax.swing.JButton btnFrmCitas;
    private javax.swing.JButton btnFrmDocentes;
    private javax.swing.JButton btnFrmEstudiantes;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSaveNewAdmin;
    private javax.swing.JButton btnSavePass;
    private javax.swing.JCheckBox checkEditar;
    private javax.swing.JDialog dlgNewAdmin;
    private javax.swing.JDialog dlgPass;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel lblconfig;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellidoNewAdmin;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailNewAdmin;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtLoginNewAdmin;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreNewAdmin;
    private javax.swing.JPasswordField txtPassNew;
    private javax.swing.JPasswordField txtPassOld;
    private javax.swing.JTextField txtPasswordNewAdmin;
    // End of variables declaration//GEN-END:variables
}
