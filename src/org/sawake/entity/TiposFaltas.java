/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author EdwardAlejandro
 */
@Entity
@Table(name = "tipos_faltas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposFaltas.findAll", query = "SELECT t FROM TiposFaltas t")
    , @NamedQuery(name = "TiposFaltas.findByIdTipo", query = "SELECT t FROM TiposFaltas t WHERE t.idTipo = :idTipo")
    , @NamedQuery(name = "TiposFaltas.findByNombreTipo", query = "SELECT t FROM TiposFaltas t WHERE t.nombreTipo = :nombreTipo")
    , @NamedQuery(name = "TiposFaltas.findByDetalleTipo", query = "SELECT t FROM TiposFaltas t WHERE t.detalleTipo = :detalleTipo")})
public class TiposFaltas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo")
    private Integer idTipo;
    @Basic(optional = false)
    @Column(name = "nombre_tipo")
    private String nombreTipo;
    @Column(name = "detalle_tipo")
    private String detalleTipo;
    @OneToMany(mappedBy = "tipoId")
    private List<CorrectivosFaltas> correctivosFaltasList;
    @OneToMany(mappedBy = "tipoId")
    private List<FaltasAlertas> faltasAlertasList;

    public TiposFaltas() {
    }

    public TiposFaltas(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public TiposFaltas(Integer idTipo, String nombreTipo) {
        this.idTipo = idTipo;
        this.nombreTipo = nombreTipo;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getDetalleTipo() {
        return detalleTipo;
    }

    public void setDetalleTipo(String detalleTipo) {
        this.detalleTipo = detalleTipo;
    }

    @XmlTransient
    public List<CorrectivosFaltas> getCorrectivosFaltasList() {
        return correctivosFaltasList;
    }

    public void setCorrectivosFaltasList(List<CorrectivosFaltas> correctivosFaltasList) {
        this.correctivosFaltasList = correctivosFaltasList;
    }

    @XmlTransient
    public List<FaltasAlertas> getFaltasAlertasList() {
        return faltasAlertasList;
    }

    public void setFaltasAlertasList(List<FaltasAlertas> faltasAlertasList) {
        this.faltasAlertasList = faltasAlertasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposFaltas)) {
            return false;
        }
        TiposFaltas other = (TiposFaltas) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.TiposFaltas[ idTipo=" + idTipo + " ]";
    }
    
}
