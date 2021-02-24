/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author EdwardAlejandro
 */
@Entity
@Table(name = "faltas_alertas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaltasAlertas.findAll", query = "SELECT f FROM FaltasAlertas f")
    , @NamedQuery(name = "FaltasAlertas.findByIdAlertas", query = "SELECT f FROM FaltasAlertas f WHERE f.idAlertas = :idAlertas")
    , @NamedQuery(name = "FaltasAlertas.findByDetalleAlertas", query = "SELECT f FROM FaltasAlertas f WHERE f.detalleAlertas = :detalleAlertas")
    , @NamedQuery(name = "FaltasAlertas.findByFechaAlertas", query = "SELECT f FROM FaltasAlertas f WHERE f.fechaAlertas = :fechaAlertas")})
public class FaltasAlertas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_alertas")
    private Integer idAlertas;
    @Basic(optional = false)
    @Column(name = "detalle_alertas")
    private String detalleAlertas;
    @Basic(optional = false)
    @Column(name = "fecha_alertas")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlertas;
    @JoinColumn(name = "tipo_id", referencedColumnName = "id_tipo")
    @ManyToOne
    private TiposFaltas tipoId;
    @JoinColumn(name = "doc_id", referencedColumnName = "id_doc")
    @ManyToOne
    private Docentes docId;
    @JoinColumn(name = "est_id", referencedColumnName = "id_est")
    @ManyToOne
    private Estudiantes estId;
    @JoinColumn(name = "acu_id", referencedColumnName = "id_acu")
    @ManyToOne
    private Acudientes acuId;

    public FaltasAlertas() {
    }

    public FaltasAlertas(Integer idAlertas) {
        this.idAlertas = idAlertas;
    }

    public FaltasAlertas(Integer idAlertas, String detalleAlertas, Date fechaAlertas) {
        this.idAlertas = idAlertas;
        this.detalleAlertas = detalleAlertas;
        this.fechaAlertas = fechaAlertas;
    }

    public Integer getIdAlertas() {
        return idAlertas;
    }

    public void setIdAlertas(Integer idAlertas) {
        this.idAlertas = idAlertas;
    }

    public String getDetalleAlertas() {
        return detalleAlertas;
    }

    public void setDetalleAlertas(String detalleAlertas) {
        this.detalleAlertas = detalleAlertas;
    }

    public Date getFechaAlertas() {
        return fechaAlertas;
    }

    public void setFechaAlertas(Date fechaAlertas) {
        this.fechaAlertas = fechaAlertas;
    }

    public TiposFaltas getTipoId() {
        return tipoId;
    }

    public void setTipoId(TiposFaltas tipoId) {
        this.tipoId = tipoId;
    }

    public Docentes getDocId() {
        return docId;
    }

    public void setDocId(Docentes docId) {
        this.docId = docId;
    }

    public Estudiantes getEstId() {
        return estId;
    }

    public void setEstId(Estudiantes estId) {
        this.estId = estId;
    }

    public Acudientes getAcuId() {
        return acuId;
    }

    public void setAcuId(Acudientes acuId) {
        this.acuId = acuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlertas != null ? idAlertas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaltasAlertas)) {
            return false;
        }
        FaltasAlertas other = (FaltasAlertas) object;
        if ((this.idAlertas == null && other.idAlertas != null) || (this.idAlertas != null && !this.idAlertas.equals(other.idAlertas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.FaltasAlertas[ idAlertas=" + idAlertas + " ]";
    }
    
}
