package org.sawake.app;

import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.sawake.entity.Administracion;
import org.sawake.controller.AdministracionJpaController;

public class LoginSA extends javax.swing.JFrame {

    private boolean logeado;
    private Administracion admin;
    private List<Administracion> listaAdmin;
    private AdministracionJpaController adminControl;
    private Timer tiempo;
    private ActionListener acL;
    private int cont;
    private Home home;
    public final static int SEGUNDOS = 5;

    public LoginSA() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.logeado = false;
        this.txtUsuario.setText("");
        this.txtContraseña.setText("");
        lblValidando.setVisible(false);
        pgValidando.setVisible(false);
        adminControl = new AdministracionJpaController();
        listaAdmin = adminControl.findAdministracionEntities();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        pgValidando = new javax.swing.JProgressBar();
        lblValidando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("School Awake");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(692, 236));

        jPanel1.setBackground(new java.awt.Color(86, 138, 215));

        jLabel3.setBackground(new java.awt.Color(0, 0, 255));
        jLabel3.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(231, 231, 231));
        jLabel3.setText("SAwake");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sawake/icons/icon2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblUsuario.setFont(new java.awt.Font("Britannic Bold", 1, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblUsuario.setText("Usuario:");

        lblContraseña.setFont(new java.awt.Font("Britannic Bold", 1, 18)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(102, 102, 102));
        lblContraseña.setText("Contraseña:");

        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyPressed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(86, 138, 215));
        btnLogin.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(231, 231, 231));
        btnLogin.setText("Iniciar Sesion");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        pgValidando.setForeground(new java.awt.Color(86, 138, 215));

        lblValidando.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblValidando.setForeground(new java.awt.Color(51, 51, 51));
        lblValidando.setText("Validando...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblValidando)
                        .addGap(36, 36, 36)
                        .addComponent(pgValidando, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUsuario)
                            .addComponent(lblContraseña))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pgValidando, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValidando))
                .addGap(23, 23, 23))
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

    class timerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            cont++;
            pgValidando.setValue(cont);
            if (cont == 100) {
                tiempo.stop();
                if (logeado) {
                    esconder();
                    home = new Home(admin);
                    home.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "El USUARIO o CONTRASEÑA son incorrectos", "¡Intente Nuevamente!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    public void esconder() {
        this.txtUsuario.setText("");
        this.txtContraseña.setText("");
        lblValidando.setVisible(false);
        pgValidando.setVisible(false);
        this.dispose();
    }


    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            String usuario = this.txtUsuario.getText();
            char[] arrayC = this.txtContraseña.getPassword();
            String password = new String(arrayC);
            for (Administracion admin : listaAdmin) {
                if (admin.getLogAdmi().equals(usuario) && admin.getPassAdmi().equals(password)) {
                    logeado = true;
                    this.admin = admin;
                    lblValidando.setVisible(true);
                    pgValidando.setVisible(true);
                    cont = -1;
                    pgValidando.setValue(0);
                    pgValidando.setStringPainted(true);
                    tiempo = new Timer(SEGUNDOS, new timerListener());
                    tiempo.start();
                    break;
                }
            }
            if (!logeado) {
                lblValidando.setVisible(true);
                pgValidando.setVisible(true);
                cont = -1;
                pgValidando.setValue(0);
                pgValidando.setStringPainted(true);
                tiempo = new Timer(SEGUNDOS, new timerListener());
                tiempo.start();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            JOptionPane.showMessageDialog(this, "Existen Problemas al Iniciar Sesion " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String usuario = this.txtUsuario.getText();
                char[] arrayC = this.txtContraseña.getPassword();
                String password = new String(arrayC);
                for (Administracion admin : listaAdmin) {
                    if (admin.getLogAdmi().equals(usuario) && admin.getPassAdmi().equals(password)) {
                        logeado = true;
                        this.admin = admin;
                        lblValidando.setVisible(true);
                        pgValidando.setVisible(true);
                        cont = -1;
                        pgValidando.setValue(0);
                        pgValidando.setStringPainted(true);
                        tiempo = new Timer(SEGUNDOS, new timerListener());
                        tiempo.start();
                        break;
                    }
                }
                if (!logeado) {
                    lblValidando.setVisible(true);
                    pgValidando.setVisible(true);
                    cont = -1;
                    pgValidando.setValue(0);
                    pgValidando.setStringPainted(true);
                    tiempo = new Timer(SEGUNDOS, new timerListener());
                    tiempo.start();
                }
            } catch (Exception e) {
                System.err.println("Error: " + e);
                JOptionPane.showMessageDialog(this, "Existen Problemas al Iniciar Sesion " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtContraseñaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblValidando;
    private javax.swing.JProgressBar pgValidando;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
