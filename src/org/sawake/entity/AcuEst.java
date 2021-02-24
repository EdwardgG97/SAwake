package org.sawake.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "acu_est")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcuEst.findAll", query = "SELECT a FROM AcuEst a")
    , @NamedQuery(name = "AcuEst.findByIdAcuest", query = "SELECT a FROM AcuEst a WHERE a.idAcuest = :idAcuest")})
public class AcuEst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acuest")
    private Integer idAcuest;
    @JoinColumn(name = "acu_id", referencedColumnName = "id_acu")
    @ManyToOne
    private Acudientes acuId;
    @JoinColumn(name = "est_id", referencedColumnName = "id_est")
    @ManyToOne
    private Estudiantes estId;

    public AcuEst() {
    }

    public AcuEst(Integer idAcuest) {
        this.idAcuest = idAcuest;
    }

    public Integer getIdAcuest() {
        return idAcuest;
    }

    public void setIdAcuest(Integer idAcuest) {
        this.idAcuest = idAcuest;
    }

    public Acudientes getAcuId() {
        return acuId;
    }

    public void setAcuId(Acudientes acuId) {
        this.acuId = acuId;
    }

    public Estudiantes getEstId() {
        return estId;
    }

    public void setEstId(Estudiantes estId) {
        this.estId = estId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcuest != null ? idAcuest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcuEst)) {
            return false;
        }
        AcuEst other = (AcuEst) object;
        if ((this.idAcuest == null && other.idAcuest != null) || (this.idAcuest != null && !this.idAcuest.equals(other.idAcuest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.AcuEst[ idAcuest=" + idAcuest + " ]";
    }
    
}
