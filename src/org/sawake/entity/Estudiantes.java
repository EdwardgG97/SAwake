package org.sawake.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "estudiantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudiantes.findAll", query = "SELECT e FROM Estudiantes e")
    , @NamedQuery(name = "Estudiantes.findByIdEst", query = "SELECT e FROM Estudiantes e WHERE e.idEst = :idEst")
    , @NamedQuery(name = "Estudiantes.findByNomEst", query = "SELECT e FROM Estudiantes e WHERE e.nomEst = :nomEst")
    , @NamedQuery(name = "Estudiantes.findByApeEst", query = "SELECT e FROM Estudiantes e WHERE e.apeEst = :apeEst")
    , @NamedQuery(name = "Estudiantes.findByDocEst", query = "SELECT e FROM Estudiantes e WHERE e.docEst = :docEst")
    , @NamedQuery(name = "Estudiantes.findByEdadEst", query = "SELECT e FROM Estudiantes e WHERE e.edadEst = :edadEst")
    , @NamedQuery(name = "Estudiantes.findBySexEst", query = "SELECT e FROM Estudiantes e WHERE e.sexEst = :sexEst")
    , @NamedQuery(name = "Estudiantes.findByTelEst", query = "SELECT e FROM Estudiantes e WHERE e.telEst = :telEst")})
public class Estudiantes implements Serializable {

    @OneToMany(mappedBy = "estId")
    private List<FaltasAlertas> faltasAlertasList;

    @OneToMany(mappedBy = "estId")
    private List<Observador> observadorList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_est")
    private Integer idEst;
    @Basic(optional = false)
    @Column(name = "nom_est")
    private String nomEst;
    @Basic(optional = false)
    @Column(name = "ape_est")
    private String apeEst;
    @Basic(optional = false)
    @Column(name = "doc_est")
    private long docEst;
    @Basic(optional = false)
    @Column(name = "edad_est")
    private short edadEst;
    @Basic(optional = false)
    @Column(name = "sex_est")
    private String sexEst;
    @Column(name = "tel_est")
    private Long telEst;
    @OneToMany(mappedBy = "estId")
    private List<Observador> obsList;
    @OneToMany(mappedBy = "estId")
    private List<AcuEst> acuEstList;
    @OneToMany(mappedBy = "estId")
    private List<Asistencia> asistenciaList;
    @JoinColumn(name = "grupo_id", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo grupoId;
    @OneToMany(mappedBy = "estId")
    private List<Registro> registroList;

    public Estudiantes() {
    }

    public Estudiantes(Integer idEst) {
        this.idEst = idEst;
    }

    public Estudiantes(Integer idEst, String nomEst, String apeEst, long docEst, short edadEst, String sexEst) {
        this.idEst = idEst;
        this.nomEst = nomEst;
        this.apeEst = apeEst;
        this.docEst = docEst;
        this.edadEst = edadEst;
        this.sexEst = sexEst;
    }

    public Integer getIdEst() {
        return idEst;
    }

    public void setIdEst(Integer idEst) {
        this.idEst = idEst;
    }

    public String getNomEst() {
        return nomEst;
    }

    public void setNomEst(String nomEst) {
        this.nomEst = nomEst;
    }

    public String getApeEst() {
        return apeEst;
    }

    public void setApeEst(String apeEst) {
        this.apeEst = apeEst;
    }

    public long getDocEst() {
        return docEst;
    }

    public void setDocEst(long docEst) {
        this.docEst = docEst;
    }

    public short getEdadEst() {
        return edadEst;
    }

    public void setEdadEst(short edadEst) {
        this.edadEst = edadEst;
    }

    public String getSexEst() {
        return sexEst;
    }

    public void setSexEst(String sexEst) {
        this.sexEst = sexEst;
    }

    public Long getTelEst() {
        return telEst;
    }

    public void setTelEst(Long telEst) {
        this.telEst = telEst;
    }

    @XmlTransient
    public List<Observador> getObsList() {
        return obsList;
    }

    public void setObsList(List<Observador> obsList) {
        this.obsList = obsList;
    }

    @XmlTransient
    public List<AcuEst> getAcuEstList() {
        return acuEstList;
    }

    public void setAcuEstList(List<AcuEst> acuEstList) {
        this.acuEstList = acuEstList;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    public Grupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Grupo grupoId) {
        this.grupoId = grupoId;
    }

    @XmlTransient
    public List<Registro> getRegistroList() {
        return registroList;
    }

    public void setRegistroList(List<Registro> registroList) {
        this.registroList = registroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEst != null ? idEst.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiantes)) {
            return false;
        }
        Estudiantes other = (Estudiantes) object;
        if ((this.idEst == null && other.idEst != null) || (this.idEst != null && !this.idEst.equals(other.idEst))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Estudiantes[ idEst=" + idEst + " ]";
    }

    @XmlTransient
    public List<Observador> getObservadorList() {
        return observadorList;
    }

    public void setObservadorList(List<Observador> observadorList) {
        this.observadorList = observadorList;
    }

    @XmlTransient
    public List<FaltasAlertas> getFaltasAlertasList() {
        return faltasAlertasList;
    }

    public void setFaltasAlertasList(List<FaltasAlertas> faltasAlertasList) {
        this.faltasAlertasList = faltasAlertasList;
    }
    
}
