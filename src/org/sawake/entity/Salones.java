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
@Table(name = "salones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salones.findAll", query = "SELECT s FROM Salones s")
    , @NamedQuery(name = "Salones.findByIdSalon", query = "SELECT s FROM Salones s WHERE s.idSalon = :idSalon")
    , @NamedQuery(name = "Salones.findByNomSalon", query = "SELECT s FROM Salones s WHERE s.nomSalon = :nomSalon")})
public class Salones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_salon")
    private Integer idSalon;
    @Basic(optional = false)
    @Column(name = "nom_salon")
    private String nomSalon;
    @JoinColumn(name = "grupo_id", referencedColumnName = "id_grupo")
    @ManyToOne
    private Grupo grupoId;
    @OneToMany(mappedBy = "salonId")
    private List<Asistencia> asistenciaList;

    public Salones() {
    }

    public Salones(Integer idSalon) {
        this.idSalon = idSalon;
    }

    public Salones(Integer idSalon, String nomSalon) {
        this.idSalon = idSalon;
        this.nomSalon = nomSalon;
    }

    public Integer getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(Integer idSalon) {
        this.idSalon = idSalon;
    }

    public String getNomSalon() {
        return nomSalon;
    }

    public void setNomSalon(String nomSalon) {
        this.nomSalon = nomSalon;
    }

    public Grupo getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Grupo grupoId) {
        this.grupoId = grupoId;
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
        hash += (idSalon != null ? idSalon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salones)) {
            return false;
        }
        Salones other = (Salones) object;
        if ((this.idSalon == null && other.idSalon != null) || (this.idSalon != null && !this.idSalon.equals(other.idSalon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Salones[ idSalon=" + idSalon + " ]";
    }
    
}
