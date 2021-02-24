package org.sawake.app;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.sawake.controller.EstudiantesJpaController;
import org.sawake.controller.GrupoJpaController;
import org.sawake.controller.ObservadorJpaController;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Grupo;
import org.sawake.entity.Observador;

public class ListObs extends javax.swing.JFrame {

    private boolean filtroEst;
    private String numGrupo;
    private DefaultTableModel tableModel;
    private List<Estudiantes> listEstudiantes;
    private EstudiantesJpaController estController;
    private List<Grupo> listGrupo;
    private GrupoJpaController grupoController;
    private List<Observador> listObs;
    private ObservadorJpaController observadorController;

    public ListObs() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        //Estudiantes
        this.filtroEst = false;
        this.estController = new EstudiantesJpaController();
        this.listEstudiantes = estController.findEstudiantesEntities();
        //Grupos
        this.grupoController = new GrupoJpaController();
        this.listGrupo = grupoController.findGrupoEntities();

        jComboBoxGrupos.addItem("Todos");
        for (Grupo grupo : listGrupo) {
            jComboBoxGrupos.addItem(grupo.getNumGrupo());
        }
        //Observador
        observadorController = new ObservadorJpaController();
        //Llenar Tablas
        if (listEstudiantes.isEmpty()) {
            tableModel = (DefaultTableModel) tblEstudiantes.getModel();
            tblEstudiantes.setModel(tableModel);
        } else {
            this.tblEstudiantes.setModel(setTableModelEstudiantes());
        }

    }

    public DefaultTableModel setTableModelEstudiantes() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Documento", "Nombre", "Apellido"}) {
            Class[] types = new Class[]{Object.class, String.class, String.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        if (!filtroEst) {
            for (Estudiantes est : listEstudiantes) {
                modelo.addRow(new Object[]{est.getDocEst(), est.getNomEst(), est.getApeEst()});
            }
        } else if (filtroEst && numGrupo != null) {
            for (Estudiantes est : listEstudiantes) {
                if (est.getGrupoId().getNumGrupo().equals(numGrupo)) {
                    modelo.addRow(new Object[]{est.getDocEst(), est.getNomEst(), est.getApeEst()});
                }
            }
        }
        return modelo;
    }

    public DefaultTableModel setTableModelObservador() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Detalle", "Fecha"}) {
            Class[] types = new Class[]{String.class, String.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        for (Observador obs : listObs) {
            modelo.addRow(new Object[]{obs.getDetalleObs(), obs.getFechaObs().toString()});
        }
        return modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObservador = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxGrupos = new javax.swing.JComboBox<>();
        btnBuscarEstudiantesPorGrupo = new javax.swing.JButton();
        btnVerObservadorEstudiante = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AS Observador Y Alertas");
        setSize(new java.awt.Dimension(658, 540));

        jPanel1.setBackground(new java.awt.Color(86, 138, 215));

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("OBSERVADOR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(242, 242, 242))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblObservador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Detalle", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblObservador);
        if (tblObservador.getColumnModel().getColumnCount() > 0) {
            tblObservador.getColumnModel().getColumn(0).setResizable(false);
            tblObservador.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Filtro por Grupo:");

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Documento", "Nombres", "Apellidos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEstudiantes);
        if (tblEstudiantes.getColumnModel().getColumnCount() > 0) {
            tblEstudiantes.getColumnModel().getColumn(0).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(1).setResizable(false);
            tblEstudiantes.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Detalles del Observador");

        btnBuscarEstudiantesPorGrupo.setBackground(new java.awt.Color(86, 138, 215));
        btnBuscarEstudiantesPorGrupo.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnBuscarEstudiantesPorGrupo.setForeground(new java.awt.Color(231, 231, 231));
        btnBuscarEstudiantesPorGrupo.setText("Buscar Estudiantes por Grupo");
        btnBuscarEstudiantesPorGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstudiantesPorGrupoActionPerformed(evt);
            }
        });

        btnVerObservadorEstudiante.setBackground(new java.awt.Color(86, 138, 215));
        btnVerObservadorEstudiante.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnVerObservadorEstudiante.setForeground(new java.awt.Color(231, 231, 231));
        btnVerObservadorEstudiante.setText("Ver Observador del Estudiante");
        btnVerObservadorEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerObservadorEstudianteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnVerObservadorEstudiante)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarEstudiantesPorGrupo))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEstudiantesPorGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVerObservadorEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
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

    private void btnBuscarEstudiantesPorGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstudiantesPorGrupoActionPerformed
        numGrupo = jComboBoxGrupos.getSelectedItem().toString();
        if (!numGrupo.equals("Todos")) {
            for (Grupo grupo : listGrupo) {
                if (grupo.getNumGrupo().equals(numGrupo)) {
                    filtroEst = true;
                    this.tblEstudiantes.setModel(setTableModelEstudiantes());
                }
            }
        } else {
            filtroEst = false;
            this.tblEstudiantes.setModel(setTableModelEstudiantes());
        }
    }//GEN-LAST:event_btnBuscarEstudiantesPorGrupoActionPerformed

    private void btnVerObservadorEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerObservadorEstudianteActionPerformed
        try {
            if (tblEstudiantes.getRowCount() != 0) {
                if (tblEstudiantes.getSelectedRow() > -1) {
                    long docEstAux = (long) this.tblEstudiantes.getValueAt(tblEstudiantes.getSelectedRow(), 0);
                    for (Estudiantes est : listEstudiantes) {
                        long docEst = est.getDocEst();
                        if (docEst == docEstAux) {
                            listObs = observadorController.buscarObservadorPorEstudiante(est);
                            if (listObs == null) {
                                tableModel = (DefaultTableModel) tblObservador.getModel();
                                tblObservador.setModel(tableModel);
                            } else {
                                this.tblObservador.setModel(setTableModelObservador());
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un estudiante de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Existen problemas al consultar las observasiones.", "¡Problemas al Eliminar!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerObservadorEstudianteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarEstudiantesPorGrupo;
    private javax.swing.JButton btnVerObservadorEstudiante;
    private javax.swing.JComboBox<String> jComboBoxGrupos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEstudiantes;
    private javax.swing.JTable tblObservador;
    // End of variables declaration//GEN-END:variables
}
