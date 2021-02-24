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
@Table(name = "dias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dias.findAll", query = "SELECT d FROM Dias d")
    , @NamedQuery(name = "Dias.findByIdDias", query = "SELECT d FROM Dias d WHERE d.idDias = :idDias")
    , @NamedQuery(name = "Dias.findByNomDias", query = "SELECT d FROM Dias d WHERE d.nomDias = :nomDias")})
public class Dias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dias")
    private Integer idDias;
    @Basic(optional = false)
    @Column(name = "nom_dias")
    private String nomDias;
    @OneToMany(mappedBy = "diasId")
    private List<Asistencia> asistenciaList;

    public Dias() {
    }

    public Dias(Integer idDias) {
        this.idDias = idDias;
    }

    public Dias(Integer idDias, String nomDias) {
        this.idDias = idDias;
        this.nomDias = nomDias;
    }

    public Integer getIdDias() {
        return idDias;
    }

    public void setIdDias(Integer idDias) {
        this.idDias = idDias;
    }

    public String getNomDias() {
        return nomDias;
    }

    public void setNomDias(String nomDias) {
        this.nomDias = nomDias;
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
        hash += (idDias != null ? idDias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dias)) {
            return false;
        }
        Dias other = (Dias) object;
        if ((this.idDias == null && other.idDias != null) || (this.idDias != null && !this.idDias.equals(other.idDias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Dias[ idDias=" + idDias + " ]";
    }
    
}
