package org.sawake.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "acudientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acudientes.findAll", query = "SELECT a FROM Acudientes a")
    , @NamedQuery(name = "Acudientes.findByIdAcu", query = "SELECT a FROM Acudientes a WHERE a.idAcu = :idAcu")
    , @NamedQuery(name = "Acudientes.findByNomAcu", query = "SELECT a FROM Acudientes a WHERE a.nomAcu = :nomAcu")
    , @NamedQuery(name = "Acudientes.findByApeAcu", query = "SELECT a FROM Acudientes a WHERE a.apeAcu = :apeAcu")
    , @NamedQuery(name = "Acudientes.findByDocAcu", query = "SELECT a FROM Acudientes a WHERE a.docAcu = :docAcu")
    , @NamedQuery(name = "Acudientes.findBySexAcu", query = "SELECT a FROM Acudientes a WHERE a.sexAcu = :sexAcu")
    , @NamedQuery(name = "Acudientes.findByTelAcu", query = "SELECT a FROM Acudientes a WHERE a.telAcu = :telAcu")
    , @NamedQuery(name = "Acudientes.findByEmailAcu", query = "SELECT a FROM Acudientes a WHERE a.emailAcu = :emailAcu")
    , @NamedQuery(name = "Acudientes.findByLogAcu", query = "SELECT a FROM Acudientes a WHERE a.logAcu = :logAcu")
    , @NamedQuery(name = "Acudientes.findByPassAcu", query = "SELECT a FROM Acudientes a WHERE a.passAcu = :passAcu")})
public class Acudientes implements Serializable {

    @OneToMany(mappedBy = "acuId")
    private List<FaltasAlertas> faltasAlertasList;

    @OneToMany(mappedBy = "acuId")
    private List<Observador> observadorList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acu")
    private Integer idAcu;
    @Basic(optional = false)
    @Column(name = "nom_acu")
    private String nomAcu;
    @Basic(optional = false)
    @Column(name = "ape_acu")
    private String apeAcu;
    @Basic(optional = false)
    @Column(name = "doc_acu")
    private long docAcu;
    @Column(name = "sex_acu")
    private String sexAcu;
    @Basic(optional = false)
    @Column(name = "tel_acu")
    private long telAcu;
    @Basic(optional = false)
    @Column(name = "email_acu")
    private String emailAcu;
    @Basic(optional = false)
    @Column(name = "log_acu")
    private String logAcu;
    @Basic(optional = false)
    @Column(name = "pass_acu")
    private String passAcu;
    @OneToMany(mappedBy = "acuId")
    private List<Observador> obsList;
    @OneToMany(mappedBy = "acuId")
    private List<AcuEst> acuEstList;

    public Acudientes() {
    }

    public Acudientes(Integer idAcu) {
        this.idAcu = idAcu;
    }

    public Acudientes(Integer idAcu, String nomAcu, String apeAcu, long docAcu, long telAcu, String emailAcu, String logAcu, String passAcu) {
        this.idAcu = idAcu;
        this.nomAcu = nomAcu;
        this.apeAcu = apeAcu;
        this.docAcu = docAcu;
        this.telAcu = telAcu;
        this.emailAcu = emailAcu;
        this.logAcu = logAcu;
        this.passAcu = passAcu;
    }

    public Integer getIdAcu() {
        return idAcu;
    }

    public void setIdAcu(Integer idAcu) {
        this.idAcu = idAcu;
    }

    public String getNomAcu() {
        return nomAcu;
    }

    public void setNomAcu(String nomAcu) {
        this.nomAcu = nomAcu;
    }

    public String getApeAcu() {
        return apeAcu;
    }

    public void setApeAcu(String apeAcu) {
        this.apeAcu = apeAcu;
    }

    public long getDocAcu() {
        return docAcu;
    }

    public void setDocAcu(long docAcu) {
        this.docAcu = docAcu;
    }

    public String getSexAcu() {
        return sexAcu;
    }

    public void setSexAcu(String sexAcu) {
        this.sexAcu = sexAcu;
    }

    public long getTelAcu() {
        return telAcu;
    }

    public void setTelAcu(long telAcu) {
        this.telAcu = telAcu;
    }

    public String getEmailAcu() {
        return emailAcu;
    }

    public void setEmailAcu(String emailAcu) {
        this.emailAcu = emailAcu;
    }

    public String getLogAcu() {
        return logAcu;
    }

    public void setLogAcu(String logAcu) {
        this.logAcu = logAcu;
    }

    public String getPassAcu() {
        return passAcu;
    }

    public void setPassAcu(String passAcu) {
        this.passAcu = passAcu;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcu != null ? idAcu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acudientes)) {
            return false;
        }
        Acudientes other = (Acudientes) object;
        if ((this.idAcu == null && other.idAcu != null) || (this.idAcu != null && !this.idAcu.equals(other.idAcu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Acudientes[ idAcu=" + idAcu + " ]";
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
