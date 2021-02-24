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
@Table(name = "grados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grados.findAll", query = "SELECT g FROM Grados g")
    , @NamedQuery(name = "Grados.findByIdGrados", query = "SELECT g FROM Grados g WHERE g.idGrados = :idGrados")
    , @NamedQuery(name = "Grados.findByGradoGrados", query = "SELECT g FROM Grados g WHERE g.gradoGrados = :gradoGrados")})
public class Grados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grados")
    private Integer idGrados;
    @Basic(optional = false)
    @Column(name = "grado_grados")
    private String gradoGrados;
    @OneToMany(mappedBy = "gradoId")
    private List<Grupo> grupoList;

    public Grados() {
    }

    public Grados(Integer idGrados) {
        this.idGrados = idGrados;
    }

    public Grados(Integer idGrados, String gradoGrados) {
        this.idGrados = idGrados;
        this.gradoGrados = gradoGrados;
    }

    public Integer getIdGrados() {
        return idGrados;
    }

    public void setIdGrados(Integer idGrados) {
        this.idGrados = idGrados;
    }

    public String getGradoGrados() {
        return gradoGrados;
    }

    public void setGradoGrados(String gradoGrados) {
        this.gradoGrados = gradoGrados;
    }

    @XmlTransient
    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrados != null ? idGrados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grados)) {
            return false;
        }
        Grados other = (Grados) object;
        if ((this.idGrados == null && other.idGrados != null) || (this.idGrados != null && !this.idGrados.equals(other.idGrados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Grados[ idGrados=" + idGrados + " ]";
    }
    
}
