package org.sawake.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.sawake.controller.EstudiantesJpaController;
import org.sawake.controller.RegistroJpaController;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Registro;

public class Notificaciones_Observaciones extends javax.swing.JFrame {

    private Estudiantes estudiante;
    private EstudiantesJpaController estController;
    private Registro registro;
    private RegistroJpaController registroController;

    public Notificaciones_Observaciones() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.btnRegistrar.setVisible(false);
        this.estController = new EstudiantesJpaController();
        this.registroController = new RegistroJpaController();
    }

    public void Leer_Codigo() {
        try {
            Scanner leer = new Scanner(System.in);
            long codigo;
            codigo = leer.nextLong();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El codigo de estudiante NO es reconocido.", "¡Codigo No Registrado!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        checkManual = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Código Estudiantil:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(86, 138, 215));
        btnRegistrar.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(231, 231, 231));
        btnRegistrar.setText("Registro");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        checkManual.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        checkManual.setForeground(new java.awt.Color(102, 102, 102));
        checkManual.setText("Manual");
        checkManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkManualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkManual)
                .addGap(208, 208, 208))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(checkManual)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(86, 138, 215));

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("REGISTROS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(190, 190, 190))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(23, 23, 23))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = new Date();
            String fechaS = "DATE('" + fecha.toString().substring(0, 11) + "')";

            if (this.txtCodigo.getText() != null && !this.txtCodigo.getText().equals("")) {
                long codigo = Long.parseLong(this.txtCodigo.getText());
                this.estudiante = this.estController.findEstudiantesDoc(codigo);
                if (estudiante != null) {
                    this.registro = this.registroController.buscarRegistroPorFecha(estudiante, fechaS);
                    if (registro == null) {
                        registro = new Registro();
                        registro.setEstId(estudiante);
                        this.registro.setFEntradareg(fecha);
                        this.registroController.create(registro);
                    } else if (registro.getFEntradareg() != null && registro.getFSalidareg() == null) {
                        this.registro.setFSalidareg(fecha);
                        this.registroController.create(registro);
                    } else {
                        JOptionPane.showMessageDialog(this, "El estudiante no puede ser registrado mas de 2 veces al día", "No es Aceptable", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor pasar el Carnet Nuevamente o Digitar el código correcto", "Campo Vacio", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.err.println("Error " + e);
            JOptionPane.showMessageDialog(this, "El codigo de estudiante NO es reconocido.", "¡Codigo No Registrado!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void checkManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkManualActionPerformed
        if (this.checkManual.isSelected()) {
            this.btnRegistrar.setVisible(true);
        } else {
            this.btnRegistrar.setVisible(false);
        }
    }//GEN-LAST:event_checkManualActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        try {
            Date fecha = new Date();
            
            Locale currentLocale = new Locale("en","US");
            
            DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, currentLocale);
            
            String fechaOut =  dateFormatter.format(fecha); //"DATE('" + fecha.toString().substring(0, 11) + "')";
            
            System.out.println(fechaOut);
            
            if (this.txtCodigo.getText() != null && !this.txtCodigo.getText().equals("")) {
                int maxCod = this.txtCodigo.getText().length();
                if (maxCod >= 9) {
                    long codigo = Long.parseLong(this.txtCodigo.getText());
                    this.estudiante = this.estController.findEstudiantesDoc(codigo);
                    if (estudiante != null) {
                        this.registro = this.registroController.buscarRegistroPorFecha(estudiante, fechaOut);
                        if (registro == null) {
                            registro = new Registro();
                            registro.setEstId(estudiante);
                            this.registro.setFEntradareg(fecha);
                            this.registroController.create(registro);
                            this.txtCodigo.setText("");
                        } else if (registro.getFEntradareg() != null && registro.getFSalidareg() == null) {
                            this.registro.setFSalidareg(fecha);
                            this.registroController.create(registro);
                            this.txtCodigo.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this, "El estudiante no puede ser registrado mas de 2 veces al día", "No es Aceptable", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "El codigo de estudiante no se encuentra registrado.", "¡Codigo No valido!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error " + e);
            JOptionPane.showMessageDialog(this, "El codigo de estudiante NO es reconocido.", "¡Codigo No Registrado!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JCheckBox checkManual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
