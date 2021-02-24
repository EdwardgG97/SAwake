package org.sawake.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.sawake.controller.AsistenciaJpaController;
import org.sawake.controller.EstudiantesJpaController;
import org.sawake.controller.GradosJpaController;
import org.sawake.controller.GrupoJpaController;
import org.sawake.controller.RegistroJpaController;
import org.sawake.controller.SalonesJpaController;
import org.sawake.controller.TiposFaltasJpaController;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Grados;
import org.sawake.entity.Grupo;
import org.sawake.entity.Registro;
import org.sawake.entity.Salones;
import org.sawake.entity.TiposFaltas;

public class AddOtros extends javax.swing.JFrame {

    //Registros
    private boolean filtroRegistros;
    private boolean filtroAsistencias;
    private List<Registro> listRegistroEstudiante;
    private List<Registro> listRegistro;
    private RegistroJpaController registroController;
    private DefaultTableModel tableModel;
    private List<Estudiantes> listEstudiantes;
    private EstudiantesJpaController estudianteController;
    //Asistencia
    private List<Asistencia> listAsistencia;
    private List<Asistencia> listAsistenciaSalon;
    private AsistenciaJpaController asistenciaController;
    //Grados/Grupos/Salones    
    private List<Grados> listGrados;
    private List<Grupo> listGrupos;
    private List<Salones> listSalones;
    private GradosJpaController gradosController;
    private GrupoJpaController grupoController;
    private SalonesJpaController salonesController;
    //Tipo Faltas
    private List<TiposFaltas> listTipoFaltas;
    private TiposFaltasJpaController tipoFaltasController;

    public AddOtros() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/org/sawake/icons/icon.png"));
        this.setIconImage(img.getImage());
        this.setLocationRelativeTo(null);
        this.filtroRegistros = false;
        this.estudianteController = new EstudiantesJpaController();
        this.listEstudiantes = estudianteController.findEstudiantesEntities();
        //Registros
        this.tblRegistros.getTableHeader().setReorderingAllowed(false);
        this.registroController = new RegistroJpaController();
        this.listRegistro = registroController.findRegistroEntities();
        if (listRegistro.isEmpty()) {
            tableModel = (DefaultTableModel) tblRegistros.getModel();
            tblRegistros.setModel(tableModel);
        } else {
            this.tblRegistros.setModel(setTableModelRegistros());
        }
        //Asistencias
        this.filtroAsistencias = false;
        this.tblAsistencias.getTableHeader().setReorderingAllowed(false);
        this.asistenciaController = new AsistenciaJpaController();
        this.salonesController = new SalonesJpaController();
        this.listAsistencia = asistenciaController.findAsistenciaEntities();
        this.listSalones = salonesController.findSalonesEntities();
        this.jComboSalones.addItem("Todo");
        for (Salones salon : listSalones) {
            this.jComboSalones.addItem(salon.getNomSalon());
        }
        if (listAsistencia.isEmpty()) {
            tableModel = (DefaultTableModel) tblAsistencias.getModel();
            tblAsistencias.setModel(tableModel);
        } else {
            this.tblAsistencias.setModel(setTableModelAsistencias());
        }
        //Grados/Grupos/Salones
        this.tblGrados.getTableHeader().setReorderingAllowed(false);
        this.tblGrupos.getTableHeader().setReorderingAllowed(false);
        this.tblSalones.getTableHeader().setReorderingAllowed(false);
        this.gradosController = new GradosJpaController();
        this.grupoController = new GrupoJpaController();
        this.salonesController = new SalonesJpaController();
        this.listGrados = gradosController.findGradosEntities();
        this.listGrupos = grupoController.findGrupoEntities();
        this.listSalones = salonesController.findSalonesEntities();
        if (listGrados.isEmpty()) {
            tableModel = (DefaultTableModel) tblGrados.getModel();
            tblGrados.setModel(tableModel);
        } else {
            for (Grados grad : listGrados) {
                this.jCGradoGrupos.addItem(grad.getGradoGrados());
            }
            this.tblGrados.setModel(setTableModelGrados());
        }
        if (listGrupos.isEmpty()) {
            tableModel = (DefaultTableModel) tblGrupos.getModel();
            tblGrupos.setModel(tableModel);
        } else {
            for (Grupo grup : listGrupos) {
                this.jCGrupoSalon.addItem(grup.getNumGrupo());
            }
            this.tblGrupos.setModel(setTableModelGrupos());
        }
        if (listSalones.isEmpty()) {
            tableModel = (DefaultTableModel) tblSalones.getModel();
            tblSalones.setModel(tableModel);
        } else {
            this.tblSalones.setModel(setTableModelSalon());
        }
        //TIPO FALTAS
        this.tipoFaltasController = new TiposFaltasJpaController();
        this.listTipoFaltas = tipoFaltasController.findTiposFaltasEntities();
        if (listTipoFaltas.isEmpty()) {
            tableModel = (DefaultTableModel) tblTipoFaltas.getModel();
            tblTipoFaltas.setModel(tableModel);
        } else {
            this.tblTipoFaltas.setModel(setTableModelTipoFaltas());
        }
    }

    public DefaultTableModel setTableModelRegistros() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Fecha Ingreso", "Fecha Salida", "Estudiante", "Documento"}) {
            Class[] types = new Class[]{String.class, String.class, String.class, Long.class};

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
        if (!filtroRegistros) {
            for (Registro regis : listRegistro) {
                if (regis.getFSalidareg() != null) {
                    modelo.addRow(new Object[]{this.converDateString(regis.getFEntradareg()), this.converDateString(regis.getFSalidareg()), regis.getEstId().getNomEst() + " " + regis.getEstId().getApeEst(), regis.getEstId().getDocEst()});
                } else {
                    modelo.addRow(new Object[]{this.converDateString(regis.getFEntradareg()), "", regis.getEstId().getNomEst() + " " + regis.getEstId().getApeEst(), regis.getEstId().getDocEst()});
                }
            }
        } else {
            for (Registro regis : listRegistroEstudiante) {
                modelo.addRow(new Object[]{this.converDateString(regis.getFEntradareg()), this.converDateString(regis.getFSalidareg()), regis.getEstId().getNomEst() + " " + regis.getEstId().getApeEst(), regis.getEstId().getDocEst()});
            }
        }
        return modelo;
    }

    public String converDateString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public DefaultTableModel setTableModelAsistencias() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Fecha", "Asistencia", "Docente", "Dia", "Estudiante", "Salon"}) {
            Class[] types = new Class[]{String.class, String.class, String.class, String.class, String.class, String.class};

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
        if (!filtroAsistencias) {
            for (Asistencia asis : listAsistencia) {
                modelo.addRow(new Object[]{this.converDateString(asis.getFechaAsist()), asis.getAsistAsist(), asis.getDocId().getNomDoc(), asis.getDiasId().getNomDias(), asis.getEstId().getNomEst(), asis.getSalonId().getNomSalon()});
            }
        } else {
            for (Asistencia asis : listAsistenciaSalon) {
                modelo.addRow(new Object[]{this.converDateString(asis.getFechaAsist()), asis.getAsistAsist(), asis.getDocId().getNomDoc(), asis.getDiasId().getNomDias(), asis.getEstId().getNomEst(), asis.getSalonId().getNomSalon()});
            }
        }
        return modelo;
    }

    public DefaultTableModel setTableModelGrados() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Grados"}) {
            Class[] types = new Class[]{String.class};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        for (Grados grado : listGrados) {
            modelo.addRow(new Object[]{grado.getGradoGrados()});
        }
        return modelo;
    }

    public DefaultTableModel setTableModelGrupos() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Grado", "Grupo"}) {
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
        for (Grupo grupo : listGrupos) {
            modelo.addRow(new Object[]{grupo.getGradoId().getGradoGrados(), grupo.getNumGrupo()});
        }
        return modelo;
    }

    public DefaultTableModel setTableModelSalon() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Grupo", "Salon"}) {
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
        for (Salones salon : listSalones) {
            modelo.addRow(new Object[]{salon.getGrupoId().getNumGrupo(), salon.getNomSalon()});
        }
        return modelo;
    }

    public DefaultTableModel setTableModelTipoFaltas() {
        DefaultTableModel modelo = new DefaultTableModel(new Object[][]{}, new String[]{"Id", "Nombre", "Detalle"}) {
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
        for (TiposFaltas falta : listTipoFaltas) {
            modelo.addRow(new Object[]{falta.getIdTipo().toString(), falta.getNombreTipo(), falta.getDetalleTipo()});
        }
        return modelo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        txtFiltroDocumento = new javax.swing.JTextField();
        btnBuscarEstudiante = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAsistencias = new javax.swing.JTable();
        btnBuscarAsistencias = new javax.swing.JButton();
        jComboSalones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtFiltrarFecha = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGrados = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtGrado = new javax.swing.JTextField();
        btnSaveGrado = new javax.swing.JButton();
        btnDeleteGrado = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSalones = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtGrupo = new javax.swing.JTextField();
        btnSaveGrupo = new javax.swing.JButton();
        btnDeleteGrupo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtSalon = new javax.swing.JTextField();
        btnSaveSalon = new javax.swing.JButton();
        btnDeleteSalon = new javax.swing.JButton();
        jCGradoGrupos = new javax.swing.JComboBox<>();
        jCGrupoSalon = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTipoFaltas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombreNewFalta = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtDetalleNewFalta = new javax.swing.JTextArea();
        btnSaveNewFaltas = new javax.swing.JButton();
        btnDeleteFaltas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SA Otros");
        setResizable(false);
        setSize(new java.awt.Dimension(658, 540));

        jPanel2.setBackground(new java.awt.Color(86, 138, 215));
        jPanel2.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(231, 231, 231));
        jLabel1.setText("Otras Configuraciones");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(227, 227, 227))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha Ingreso", "Fecha Salida", "Estudiante", "Documento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class
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
        tblRegistros.setMaximumSize(new java.awt.Dimension(590, 380));
        tblRegistros.setMinimumSize(new java.awt.Dimension(590, 380));
        jScrollPane2.setViewportView(tblRegistros);
        if (tblRegistros.getColumnModel().getColumnCount() > 0) {
            tblRegistros.getColumnModel().getColumn(0).setResizable(false);
            tblRegistros.getColumnModel().getColumn(1).setResizable(false);
            tblRegistros.getColumnModel().getColumn(2).setResizable(false);
            tblRegistros.getColumnModel().getColumn(3).setResizable(false);
        }

        txtFiltroDocumento.setToolTipText("");

        btnBuscarEstudiante.setBackground(new java.awt.Color(86, 138, 215));
        btnBuscarEstudiante.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnBuscarEstudiante.setForeground(new java.awt.Color(231, 231, 231));
        btnBuscarEstudiante.setText("Buscar");
        btnBuscarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEstudianteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Ingrese Documento del estudiante:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtFiltroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Registros", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblAsistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Asistencia", "Docente", "Dia", "Estudiante", "Salon"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblAsistencias.setMaximumSize(new java.awt.Dimension(590, 380));
        tblAsistencias.setMinimumSize(new java.awt.Dimension(590, 380));
        tblAsistencias.setPreferredSize(new java.awt.Dimension(300, 64));
        jScrollPane1.setViewportView(tblAsistencias);
        if (tblAsistencias.getColumnModel().getColumnCount() > 0) {
            tblAsistencias.getColumnModel().getColumn(0).setResizable(false);
            tblAsistencias.getColumnModel().getColumn(1).setResizable(false);
            tblAsistencias.getColumnModel().getColumn(2).setResizable(false);
            tblAsistencias.getColumnModel().getColumn(3).setResizable(false);
            tblAsistencias.getColumnModel().getColumn(4).setResizable(false);
            tblAsistencias.getColumnModel().getColumn(5).setResizable(false);
        }

        btnBuscarAsistencias.setBackground(new java.awt.Color(86, 138, 215));
        btnBuscarAsistencias.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnBuscarAsistencias.setForeground(new java.awt.Color(231, 231, 231));
        btnBuscarAsistencias.setText("Buscar");
        btnBuscarAsistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAsistenciasActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Escriba fecha y/o Seleccione un salon para filtrar:");

        txtFiltrarFecha.setText("YYYY-MM-DD");
        txtFiltrarFecha.setToolTipText("YYYY-MM-DD");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtFiltrarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboSalones, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarAsistencias, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(275, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboSalones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarAsistencias)
                    .addComponent(txtFiltrarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Asistencias", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblGrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Grados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGrados.setMaximumSize(new java.awt.Dimension(205, 304));
        tblGrados.setMinimumSize(new java.awt.Dimension(205, 304));
        jScrollPane3.setViewportView(tblGrados);
        if (tblGrados.getColumnModel().getColumnCount() > 0) {
            tblGrados.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Ingrese o Elimine Grado:");

        btnSaveGrado.setBackground(new java.awt.Color(86, 138, 215));
        btnSaveGrado.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        btnSaveGrado.setForeground(new java.awt.Color(231, 231, 231));
        btnSaveGrado.setText("Guardar");
        btnSaveGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGradoActionPerformed(evt);
            }
        });

        btnDeleteGrado.setBackground(new java.awt.Color(86, 138, 215));
        btnDeleteGrado.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        btnDeleteGrado.setForeground(new java.awt.Color(231, 231, 231));
        btnDeleteGrado.setText("Eliminar Grado");
        btnDeleteGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteGradoActionPerformed(evt);
            }
        });

        tblSalones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Grupo", "Salon"
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
        tblSalones.setMaximumSize(new java.awt.Dimension(205, 304));
        tblSalones.setMinimumSize(new java.awt.Dimension(205, 304));
        jScrollPane4.setViewportView(tblSalones);
        if (tblSalones.getColumnModel().getColumnCount() > 0) {
            tblSalones.getColumnModel().getColumn(0).setResizable(false);
            tblSalones.getColumnModel().getColumn(1).setResizable(false);
        }

        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Grado", "Grupo"
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
        tblGrupos.setMaximumSize(new java.awt.Dimension(205, 304));
        tblGrupos.setMinimumSize(new java.awt.Dimension(205, 304));
        jScrollPane5.setViewportView(tblGrupos);
        if (tblGrupos.getColumnModel().getColumnCount() > 0) {
            tblGrupos.getColumnModel().getColumn(0).setResizable(false);
            tblGrupos.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Ingrese o Elimine Grupo:");

        btnSaveGrupo.setBackground(new java.awt.Color(86, 138, 215));
        btnSaveGrupo.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        btnSaveGrupo.setForeground(new java.awt.Color(231, 231, 231));
        btnSaveGrupo.setText("Guardar");
        btnSaveGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveGrupoActionPerformed(evt);
            }
        });

        btnDeleteGrupo.setBackground(new java.awt.Color(86, 138, 215));
        btnDeleteGrupo.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        btnDeleteGrupo.setForeground(new java.awt.Color(231, 231, 231));
        btnDeleteGrupo.setText("Eliminar Grupo");
        btnDeleteGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteGrupoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Ingrese o Elimine Salon:");

        btnSaveSalon.setBackground(new java.awt.Color(86, 138, 215));
        btnSaveSalon.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        btnSaveSalon.setForeground(new java.awt.Color(231, 231, 231));
        btnSaveSalon.setText("Guardar");
        btnSaveSalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveSalonActionPerformed(evt);
            }
        });

        btnDeleteSalon.setBackground(new java.awt.Color(86, 138, 215));
        btnDeleteSalon.setFont(new java.awt.Font("Britannic Bold", 0, 11)); // NOI18N
        btnDeleteSalon.setForeground(new java.awt.Color(231, 231, 231));
        btnDeleteSalon.setText("Eliminar Salon");
        btnDeleteSalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSalonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btnDeleteGrado)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSaveGrado))
                        .addComponent(txtGrado))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGrupo)
                                    .addComponent(btnDeleteGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSaveGrupo)
                                    .addComponent(jCGradoGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnDeleteSalon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSalon, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCGrupoSalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(btnSaveSalon)))))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCGradoGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCGrupoSalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteGrado)
                    .addComponent(btnDeleteGrupo)
                    .addComponent(btnDeleteSalon)
                    .addComponent(btnSaveGrupo)
                    .addComponent(btnSaveGrado)
                    .addComponent(btnSaveSalon))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Grados/Grupos/Salones", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tblTipoFaltas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Detalle"
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
        jScrollPane6.setViewportView(tblTipoFaltas);
        if (tblTipoFaltas.getColumnModel().getColumnCount() > 0) {
            tblTipoFaltas.getColumnModel().getColumn(0).setResizable(false);
            tblTipoFaltas.getColumnModel().getColumn(1).setResizable(false);
            tblTipoFaltas.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Crear Nuevo Tipo De Falta");

        jLabel8.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Nombre:");

        jLabel9.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Detalle:");

        txtDetalleNewFalta.setColumns(20);
        txtDetalleNewFalta.setRows(5);
        jScrollPane7.setViewportView(txtDetalleNewFalta);

        btnSaveNewFaltas.setBackground(new java.awt.Color(86, 138, 215));
        btnSaveNewFaltas.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnSaveNewFaltas.setForeground(new java.awt.Color(231, 231, 231));
        btnSaveNewFaltas.setText("Guardar");
        btnSaveNewFaltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveNewFaltasActionPerformed(evt);
            }
        });

        btnDeleteFaltas.setBackground(new java.awt.Color(86, 138, 215));
        btnDeleteFaltas.setFont(new java.awt.Font("Britannic Bold", 0, 12)); // NOI18N
        btnDeleteFaltas.setForeground(new java.awt.Color(231, 231, 231));
        btnDeleteFaltas.setText("Eliminar");
        btnDeleteFaltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFaltasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnDeleteFaltas)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreNewFalta, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSaveNewFaltas)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(218, 218, 218))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnDeleteFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNombreNewFalta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(btnSaveNewFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel9)))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tipos de Faltas", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEstudianteActionPerformed
        boolean noExiste = true;
        for (Estudiantes est : listEstudiantes) {
            String docu = String.valueOf(est.getDocEst());
            if (docu.equals(this.txtFiltroDocumento.getText())) {
                this.listRegistroEstudiante = registroController.buscarRegistroPorEstudiante(est);
                if (listRegistroEstudiante != null) {
                    noExiste = false;
                    this.filtroRegistros = true;
                    this.tblRegistros.setModel(setTableModelRegistros());
                } else {
                    JOptionPane.showMessageDialog(this, "El Estudiante no tiene Registros");
                }
            }
        }
        if (noExiste) {
            JOptionPane.showMessageDialog(this, "Estudiante no existente", "Verifique!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarEstudianteActionPerformed

    private void btnBuscarAsistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarAsistenciasActionPerformed
        for (Salones salones : listSalones) {
            String salon = this.jComboSalones.getSelectedItem().toString();
            if (salon.equals(salones.getNomSalon())) {
                this.listAsistenciaSalon = new ArrayList<>();
                List<Asistencia> listPrueba = asistenciaController.buscarAsistenciasPorSalon(salones);
                if (listPrueba != null) {
                    if (!this.txtFiltrarFecha.getText().equals("YYYY-MM-DD") && !this.txtFiltrarFecha.getText().equals("")) {
                        boolean auxValidar = false;
                        for (Asistencia asistencia : listPrueba) {
                            if (this.converDateString(asistencia.getFechaAsist()).equals(this.txtFiltrarFecha.getText())) {
                                this.listAsistenciaSalon.add(asistencia);
                                auxValidar = true;
                            }
                        }
                        if (!auxValidar) {
                            this.listAsistenciaSalon = listPrueba;
                            this.filtroAsistencias = true;
                            this.tblAsistencias.setModel(setTableModelAsistencias());
                            JOptionPane.showMessageDialog(this, "No existe Asistencias en esta Fecha: " + this.txtFiltrarFecha.getText());
                        } else {
                            this.filtroAsistencias = true;
                            this.tblAsistencias.setModel(setTableModelAsistencias());
                        }
                    } else {
                        this.listAsistenciaSalon = listPrueba;
                        this.filtroAsistencias = true;
                        this.tblAsistencias.setModel(setTableModelAsistencias());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El salón no tiene asistencias");
                }
            }
        }
    }//GEN-LAST:event_btnBuscarAsistenciasActionPerformed

    private void btnSaveGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGradoActionPerformed
        try {
            boolean existeGrado = false;
            if (!"".equals(this.txtGrado.getText())) {
                for (Grados grado : listGrados) {
                    if (grado.getGradoGrados().equals(txtGrado.getText())) {
                        existeGrado = true;
                        JOptionPane.showMessageDialog(this, "¡Ya existe un grado con el mismo nombre!");
                    }
                }
                if (!existeGrado) {
                    Grados grados = new Grados();
                    grados.setGradoGrados(txtGrado.getText());
                    this.gradosController.create(grados);
                    this.listGrados = gradosController.findGradosEntities();
                    this.jCGradoGrupos.removeAllItems();
                    for (Grados grad : listGrados) {
                        this.jCGradoGrupos.addItem(grad.getGradoGrados());
                    }
                    this.tblGrados.setModel(setTableModelGrados());
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Insertar Grados " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveGradoActionPerformed

    private void btnDeleteGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteGradoActionPerformed
        try {
            if (!"".equals(txtGrado.getText())) {
                for (Grados grado : listGrados) {
                    if (grado.getGradoGrados().equals(txtGrado.getText())) {
                        this.gradosController.destroy(grado.getIdGrados());
                        this.listGrados = gradosController.findGradosEntities();
                        this.jCGradoGrupos.removeAllItems();
                        for (Grados grad : listGrados) {
                            this.jCGradoGrupos.addItem(grad.getGradoGrados());
                        }
                        this.tblGrados.setModel(setTableModelGrados());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Eliminar Grados " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteGradoActionPerformed

    private void btnSaveGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveGrupoActionPerformed
        try {
            boolean existeGrupo = false;
            if (!"".equals(this.txtGrupo.getText())) {
                for (Grupo grupos : listGrupos) {
                    if (grupos.getNumGrupo().equals(txtGrupo.getText())) {
                        existeGrupo = true;
                        JOptionPane.showMessageDialog(this, "¡Ya existe un grupo con el mismo nombre!");
                    }
                }
                if (!existeGrupo) {
                    Grupo grupo = new Grupo();
                    grupo.setNumGrupo(txtGrupo.getText());
                    for (Grados grado : listGrados) {
                        if (grado.getGradoGrados().equals(this.jCGradoGrupos.getSelectedItem().toString())) {
                            grupo.setGradoId(grado);
                        }
                    }
                    this.grupoController.create(grupo);
                    this.listGrupos = grupoController.findGrupoEntities();
                    this.jCGrupoSalon.removeAllItems();
                    for (Grupo grup : listGrupos) {
                        this.jCGrupoSalon.addItem(grup.getNumGrupo());
                    }
                    this.tblGrupos.setModel(setTableModelGrupos());
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Insertar el Grupo " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveGrupoActionPerformed

    private void btnDeleteGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteGrupoActionPerformed
        try {
            if (!"".equals(txtGrupo.getText())) {
                for (Grupo group : listGrupos) {
                    if (group.getNumGrupo().equals(txtGrupo.getText())) {
                        this.grupoController.destroy(group.getIdGrupo());
                        this.listGrupos = grupoController.findGrupoEntities();
                        this.jCGrupoSalon.removeAllItems();
                        for (Grupo grup : listGrupos) {
                            this.jCGrupoSalon.addItem(grup.getNumGrupo());
                        }
                        this.tblGrupos.setModel(setTableModelGrupos());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Eliminar el Grupo " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteGrupoActionPerformed

    private void btnSaveSalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveSalonActionPerformed
        try {
            boolean existeSalon = false;
            if (!"".equals(txtSalon.getText())) {
                for (Salones salones : listSalones) {
                    if (salones.getNomSalon().equals(txtSalon.getText())) {
                        existeSalon = true;
                        JOptionPane.showMessageDialog(this, "¡Ya existe un grupo con el mismo nombre!");
                    }
                }
                if (!existeSalon) {
                    Salones salon = new Salones();
                    salon.setNomSalon(txtSalon.getText());
                    for (Grupo grupo : listGrupos) {
                        if (grupo.getNumGrupo().equals(jCGrupoSalon.getSelectedItem().toString())) {
                            salon.setGrupoId(grupo);
                        }
                    }
                    this.salonesController.create(salon);
                    this.listSalones = salonesController.findSalonesEntities();
                    this.tblSalones.setModel(setTableModelSalon());
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Insertar el Salón " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveSalonActionPerformed

    private void btnDeleteSalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSalonActionPerformed
        try {
            if (!"".equals(txtSalon.getText())) {
                for (Salones salon : listSalones) {
                    if (salon.getNomSalon().equals(txtSalon.getText())) {
                        this.salonesController.destroy(salon.getIdSalon());
                        this.listSalones = salonesController.findSalonesEntities();
                        this.tblSalones.setModel(setTableModelSalon());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Eliminar el Salón " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteSalonActionPerformed

    private void btnSaveNewFaltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveNewFaltasActionPerformed
        try {
            if (!"".equals(txtNombreNewFalta.getText())) {
                if (!"".equals(txtDetalleNewFalta.getText())) {
                    TiposFaltas newFalta = new TiposFaltas();
                    newFalta.setNombreTipo(txtNombreNewFalta.getText());
                    newFalta.setDetalleTipo(txtDetalleNewFalta.getText());
                    //Guardar la nueva Falta
                    this.tipoFaltasController.create(newFalta);
                    this.listTipoFaltas = tipoFaltasController.findTiposFaltasEntities();
                    this.tblTipoFaltas.setModel(setTableModelTipoFaltas());
                } else {
                    JOptionPane.showMessageDialog(this, "¡El campo detalle esta vacío!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "¡El campo nombre esta vacío!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problemas al Insertar el Salón " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveNewFaltasActionPerformed

    private void btnDeleteFaltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFaltasActionPerformed
        try {
            if (tblTipoFaltas.getRowCount() != 0) {
                if (tblTipoFaltas.getSelectedRow() > -1) {
                    Integer idFalta = (Integer) this.tblTipoFaltas.getValueAt(tblTipoFaltas.getSelectedRow(), 0);
                    for (TiposFaltas falta : listTipoFaltas) {
                        int idaux = falta.getIdTipo();
                        if (idaux == idFalta) {
                            this.tipoFaltasController.destroy(idaux);
                            JOptionPane.showMessageDialog(this, "Falta eliminada Correctamente");
                            this.listTipoFaltas = tipoFaltasController.findTiposFaltasEntities();
                            this.tblTipoFaltas.setModel(setTableModelTipoFaltas());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione una Falta de la Tabla.", "¡Seleccione!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "El estudiante está ligado a otras tablas y no es posible eliminar, Por favor verificar.", "¡Problemas al Eliminar!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddDocentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteFaltasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarAsistencias;
    private javax.swing.JButton btnBuscarEstudiante;
    private javax.swing.JButton btnDeleteFaltas;
    private javax.swing.JButton btnDeleteGrado;
    private javax.swing.JButton btnDeleteGrupo;
    private javax.swing.JButton btnDeleteSalon;
    private javax.swing.JButton btnSaveGrado;
    private javax.swing.JButton btnSaveGrupo;
    private javax.swing.JButton btnSaveNewFaltas;
    private javax.swing.JButton btnSaveSalon;
    private javax.swing.JComboBox<String> jCGradoGrupos;
    private javax.swing.JComboBox<String> jCGrupoSalon;
    private javax.swing.JComboBox<String> jComboSalones;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblAsistencias;
    private javax.swing.JTable tblGrados;
    private javax.swing.JTable tblGrupos;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTable tblSalones;
    private javax.swing.JTable tblTipoFaltas;
    private javax.swing.JTextArea txtDetalleNewFalta;
    private javax.swing.JTextField txtFiltrarFecha;
    private javax.swing.JTextField txtFiltroDocumento;
    private javax.swing.JTextField txtGrado;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtNombreNewFalta;
    private javax.swing.JTextField txtSalon;
    // End of variables declaration//GEN-END:variables
}
