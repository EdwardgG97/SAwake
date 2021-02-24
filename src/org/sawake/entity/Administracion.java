package org.sawake.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "administracion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administracion.findAll", query = "SELECT a FROM Administracion a")
    , @NamedQuery(name = "Administracion.findByIdAdmi", query = "SELECT a FROM Administracion a WHERE a.idAdmi = :idAdmi")
    , @NamedQuery(name = "Administracion.findByNomAdmi", query = "SELECT a FROM Administracion a WHERE a.nomAdmi = :nomAdmi")
    , @NamedQuery(name = "Administracion.findByApeAdmi", query = "SELECT a FROM Administracion a WHERE a.apeAdmi = :apeAdmi")
    , @NamedQuery(name = "Administracion.findByEmailAdmi", query = "SELECT a FROM Administracion a WHERE a.emailAdmi = :emailAdmi")
    , @NamedQuery(name = "Administracion.findByLogAdmi", query = "SELECT a FROM Administracion a WHERE a.logAdmi = :logAdmi")
    , @NamedQuery(name = "Administracion.findByPassAdmi", query = "SELECT a FROM Administracion a WHERE a.passAdmi = :passAdmi")})
public class Administracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_admi")
    private Integer idAdmi;
    @Basic(optional = false)
    @Column(name = "nom_admi")
    private String nomAdmi;
    @Basic(optional = false)
    @Column(name = "ape_admi")
    private String apeAdmi;
    @Basic(optional = false)
    @Column(name = "email_admi")
    private String emailAdmi;
    @Basic(optional = false)
    @Column(name = "log_admi")
    private String logAdmi;
    @Basic(optional = false)
    @Column(name = "pass_admi")
    private String passAdmi;

    public Administracion() {
    }

    public Administracion(Integer idAdmi) {
        this.idAdmi = idAdmi;
    }

    public Administracion(Integer idAdmi, String nomAdmi, String apeAdmi, String emailAdmi, String logAdmi, String passAdmi) {
        this.idAdmi = idAdmi;
        this.nomAdmi = nomAdmi;
        this.apeAdmi = apeAdmi;
        this.emailAdmi = emailAdmi;
        this.logAdmi = logAdmi;
        this.passAdmi = passAdmi;
    }

    public Integer getIdAdmi() {
        return idAdmi;
    }

    public void setIdAdmi(Integer idAdmi) {
        this.idAdmi = idAdmi;
    }

    public String getNomAdmi() {
        return nomAdmi;
    }

    public void setNomAdmi(String nomAdmi) {
        this.nomAdmi = nomAdmi;
    }

    public String getApeAdmi() {
        return apeAdmi;
    }

    public void setApeAdmi(String apeAdmi) {
        this.apeAdmi = apeAdmi;
    }

    public String getEmailAdmi() {
        return emailAdmi;
    }

    public void setEmailAdmi(String emailAdmi) {
        this.emailAdmi = emailAdmi;
    }

    public String getLogAdmi() {
        return logAdmi;
    }

    public void setLogAdmi(String logAdmi) {
        this.logAdmi = logAdmi;
    }

    public String getPassAdmi() {
        return passAdmi;
    }

    public void setPassAdmi(String passAdmi) {
        this.passAdmi = passAdmi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdmi != null ? idAdmi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administracion)) {
            return false;
        }
        Administracion other = (Administracion) object;
        if ((this.idAdmi == null && other.idAdmi != null) || (this.idAdmi != null && !this.idAdmi.equals(other.idAdmi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Administracion[ idAdmi=" + idAdmi + " ]";
    }
    
}
