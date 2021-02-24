package org.sawake.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "registro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registro.findAll", query = "SELECT r FROM Registro r")
    , @NamedQuery(name = "Registro.findByIdReg", query = "SELECT r FROM Registro r WHERE r.idReg = :idReg")
    , @NamedQuery(name = "Registro.findByFEntradareg", query = "SELECT r FROM Registro r WHERE r.fEntradareg = :fEntradareg")
    , @NamedQuery(name = "Registro.findByFSalidareg", query = "SELECT r FROM Registro r WHERE r.fSalidareg = :fSalidareg")})
public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reg")
    private Integer idReg;
    @Column(name = "fEntrada_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fEntradareg;
    @Column(name = "fSalida_reg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fSalidareg;
    @JoinColumn(name = "est_id", referencedColumnName = "id_est")
    @ManyToOne
    private Estudiantes estId;

    public Registro() {
    }

    public Registro(Integer idReg) {
        this.idReg = idReg;
    }

    public Integer getIdReg() {
        return idReg;
    }

    public void setIdReg(Integer idReg) {
        this.idReg = idReg;
    }

    public Date getFEntradareg() {
        return fEntradareg;
    }

    public void setFEntradareg(Date fEntradareg) {
        this.fEntradareg = fEntradareg;
    }

    public Date getFSalidareg() {
        return fSalidareg;
    }

    public void setFSalidareg(Date fSalidareg) {
        this.fSalidareg = fSalidareg;
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
        hash += (idReg != null ? idReg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registro)) {
            return false;
        }
        Registro other = (Registro) object;
        if ((this.idReg == null && other.idReg != null) || (this.idReg != null && !this.idReg.equals(other.idReg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Registro[ idReg=" + idReg + " ]";
    }
    
}
