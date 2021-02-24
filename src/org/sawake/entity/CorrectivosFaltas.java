/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author EdwardAlejandro
 */
@Entity
@Table(name = "correctivos_faltas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorrectivosFaltas.findAll", query = "SELECT c FROM CorrectivosFaltas c")
    , @NamedQuery(name = "CorrectivosFaltas.findByIdCorrectivo", query = "SELECT c FROM CorrectivosFaltas c WHERE c.idCorrectivo = :idCorrectivo")
    , @NamedQuery(name = "CorrectivosFaltas.findByDetalleCorrectivo", query = "SELECT c FROM CorrectivosFaltas c WHERE c.detalleCorrectivo = :detalleCorrectivo")
    , @NamedQuery(name = "CorrectivosFaltas.findByPasoCorrectivo", query = "SELECT c FROM CorrectivosFaltas c WHERE c.pasoCorrectivo = :pasoCorrectivo")})
public class CorrectivosFaltas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_correctivo")
    private Integer idCorrectivo;
    @Basic(optional = false)
    @Column(name = "detalle_correctivo")
    private String detalleCorrectivo;
    @Basic(optional = false)
    @Column(name = "paso_correctivo")
    private String pasoCorrectivo;
    @JoinColumn(name = "tipo_id", referencedColumnName = "id_tipo")
    @ManyToOne
    private TiposFaltas tipoId;

    public CorrectivosFaltas() {
    }

    public CorrectivosFaltas(Integer idCorrectivo) {
        this.idCorrectivo = idCorrectivo;
    }

    public CorrectivosFaltas(Integer idCorrectivo, String detalleCorrectivo, String pasoCorrectivo) {
        this.idCorrectivo = idCorrectivo;
        this.detalleCorrectivo = detalleCorrectivo;
        this.pasoCorrectivo = pasoCorrectivo;
    }

    public Integer getIdCorrectivo() {
        return idCorrectivo;
    }

    public void setIdCorrectivo(Integer idCorrectivo) {
        this.idCorrectivo = idCorrectivo;
    }

    public String getDetalleCorrectivo() {
        return detalleCorrectivo;
    }

    public void setDetalleCorrectivo(String detalleCorrectivo) {
        this.detalleCorrectivo = detalleCorrectivo;
    }

    public String getPasoCorrectivo() {
        return pasoCorrectivo;
    }

    public void setPasoCorrectivo(String pasoCorrectivo) {
        this.pasoCorrectivo = pasoCorrectivo;
    }

    public TiposFaltas getTipoId() {
        return tipoId;
    }

    public void setTipoId(TiposFaltas tipoId) {
        this.tipoId = tipoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorrectivo != null ? idCorrectivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorrectivosFaltas)) {
            return false;
        }
        CorrectivosFaltas other = (CorrectivosFaltas) object;
        if ((this.idCorrectivo == null && other.idCorrectivo != null) || (this.idCorrectivo != null && !this.idCorrectivo.equals(other.idCorrectivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.CorrectivosFaltas[ idCorrectivo=" + idCorrectivo + " ]";
    }
    
}
