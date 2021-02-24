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
@Table(name = "docentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docentes.findAll", query = "SELECT d FROM Docentes d")
    , @NamedQuery(name = "Docentes.findByIdDoc", query = "SELECT d FROM Docentes d WHERE d.idDoc = :idDoc")
    , @NamedQuery(name = "Docentes.findByNomDoc", query = "SELECT d FROM Docentes d WHERE d.nomDoc = :nomDoc")
    , @NamedQuery(name = "Docentes.findByApeDoc", query = "SELECT d FROM Docentes d WHERE d.apeDoc = :apeDoc")
    , @NamedQuery(name = "Docentes.findByDocDoc", query = "SELECT d FROM Docentes d WHERE d.docDoc = :docDoc")
    , @NamedQuery(name = "Docentes.findBySexDoc", query = "SELECT d FROM Docentes d WHERE d.sexDoc = :sexDoc")
    , @NamedQuery(name = "Docentes.findByTelDoc", query = "SELECT d FROM Docentes d WHERE d.telDoc = :telDoc")
    , @NamedQuery(name = "Docentes.findByEmailDoc", query = "SELECT d FROM Docentes d WHERE d.emailDoc = :emailDoc")
    , @NamedQuery(name = "Docentes.findByLogDoc", query = "SELECT d FROM Docentes d WHERE d.logDoc = :logDoc")
    , @NamedQuery(name = "Docentes.findByPassDoc", query = "SELECT d FROM Docentes d WHERE d.passDoc = :passDoc")})
public class Docentes implements Serializable {

    @OneToMany(mappedBy = "docId")
    private List<FaltasAlertas> faltasAlertasList;

    @OneToMany(mappedBy = "docId")
    private List<Observador> observadorList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_doc")
    private Integer idDoc;
    @Basic(optional = false)
    @Column(name = "nom_doc")
    private String nomDoc;
    @Basic(optional = false)
    @Column(name = "ape_doc")
    private String apeDoc;
    @Basic(optional = false)
    @Column(name = "doc_doc")
    private long docDoc;
    @Basic(optional = false)
    @Column(name = "sex_doc")
    private String sexDoc;
    @Basic(optional = false)
    @Column(name = "tel_doc")
    private long telDoc;
    @Basic(optional = false)
    @Column(name = "email_doc")
    private String emailDoc;
    @Basic(optional = false)
    @Column(name = "log_doc")
    private String logDoc;
    @Basic(optional = false)
    @Column(name = "pass_doc")
    private String passDoc;
    @OneToMany(mappedBy = "docId")
    private List<Observador> obsList;
    @OneToMany(mappedBy = "docId")
    private List<Asistencia> asistenciaList;

    public Docentes() {
    }

    public Docentes(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public Docentes(Integer idDoc, String nomDoc, String apeDoc, long docDoc, String sexDoc, long telDoc, String emailDoc, String logDoc, String passDoc) {
        this.idDoc = idDoc;
        this.nomDoc = nomDoc;
        this.apeDoc = apeDoc;
        this.docDoc = docDoc;
        this.sexDoc = sexDoc;
        this.telDoc = telDoc;
        this.emailDoc = emailDoc;
        this.logDoc = logDoc;
        this.passDoc = passDoc;
    }

    public Integer getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getApeDoc() {
        return apeDoc;
    }

    public void setApeDoc(String apeDoc) {
        this.apeDoc = apeDoc;
    }

    public long getDocDoc() {
        return docDoc;
    }

    public void setDocDoc(long docDoc) {
        this.docDoc = docDoc;
    }

    public String getSexDoc() {
        return sexDoc;
    }

    public void setSexDoc(String sexDoc) {
        this.sexDoc = sexDoc;
    }

    public long getTelDoc() {
        return telDoc;
    }

    public void setTelDoc(long telDoc) {
        this.telDoc = telDoc;
    }

    public String getEmailDoc() {
        return emailDoc;
    }

    public void setEmailDoc(String emailDoc) {
        this.emailDoc = emailDoc;
    }

    public String getLogDoc() {
        return logDoc;
    }

    public void setLogDoc(String logDoc) {
        this.logDoc = logDoc;
    }

    public String getPassDoc() {
        return passDoc;
    }

    public void setPassDoc(String passDoc) {
        this.passDoc = passDoc;
    }

    @XmlTransient
    public List<Observador> getObsList() {
        return obsList;
    }

    public void setObsList(List<Observador> obsList) {
        this.obsList = obsList;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDoc != null ? idDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docentes)) {
            return false;
        }
        Docentes other = (Docentes) object;
        if ((this.idDoc == null && other.idDoc != null) || (this.idDoc != null && !this.idDoc.equals(other.idDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Docentes[ idDoc=" + idDoc + " ]";
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
