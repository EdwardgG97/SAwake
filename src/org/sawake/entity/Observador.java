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
@Table(name = "observador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Observador.findAll", query = "SELECT o FROM Observador o")
    , @NamedQuery(name = "Observador.findByIdObs", query = "SELECT o FROM Observador o WHERE o.idObs = :idObs")
    , @NamedQuery(name = "Observador.findByDetalleObs", query = "SELECT o FROM Observador o WHERE o.detalleObs = :detalleObs")
    , @NamedQuery(name = "Observador.findByFechaObs", query = "SELECT o FROM Observador o WHERE o.fechaObs = :fechaObs")
    , @NamedQuery(name = "Observador.findByFechaCitaobs", query = "SELECT o FROM Observador o WHERE o.fechaCitaobs = :fechaCitaobs")})
public class Observador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_obs")
    private Integer idObs;
    @Basic(optional = false)
    @Column(name = "detalle_obs")
    private String detalleObs;
    @Basic(optional = false)
    @Column(name = "fecha_obs")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaObs;
    @Column(name = "fechaCita_obs")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCitaobs;
    @JoinColumn(name = "doc_id", referencedColumnName = "id_doc")
    @ManyToOne
    private Docentes docId;
    @JoinColumn(name = "est_id", referencedColumnName = "id_est")
    @ManyToOne
    private Estudiantes estId;
    @JoinColumn(name = "acu_id", referencedColumnName = "id_acu")
    @ManyToOne
    private Acudientes acuId;

    public Observador() {
    }

    public Observador(Integer idObs) {
        this.idObs = idObs;
    }

    public Observador(Integer idObs, String detalleObs, Date fechaObs) {
        this.idObs = idObs;
        this.detalleObs = detalleObs;
        this.fechaObs = fechaObs;
    }

    public Integer getIdObs() {
        return idObs;
    }

    public void setIdObs(Integer idObs) {
        this.idObs = idObs;
    }

    public String getDetalleObs() {
        return detalleObs;
    }

    public void setDetalleObs(String detalleObs) {
        this.detalleObs = detalleObs;
    }

    public Date getFechaObs() {
        return fechaObs;
    }

    public void setFechaObs(Date fechaObs) {
        this.fechaObs = fechaObs;
    }

    public Date getFechaCitaobs() {
        return fechaCitaobs;
    }

    public void setFechaCitaobs(Date fechaCitaobs) {
        this.fechaCitaobs = fechaCitaobs;
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
        hash += (idObs != null ? idObs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Observador)) {
            return false;
        }
        Observador other = (Observador) object;
        if ((this.idObs == null && other.idObs != null) || (this.idObs != null && !this.idObs.equals(other.idObs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Observador[ idObs=" + idObs + " ]";
    }
    
}
