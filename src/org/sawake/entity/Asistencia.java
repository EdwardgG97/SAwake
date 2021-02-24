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
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a")
    , @NamedQuery(name = "Asistencia.findByIdAsist", query = "SELECT a FROM Asistencia a WHERE a.idAsist = :idAsist")
    , @NamedQuery(name = "Asistencia.findByAsistAsist", query = "SELECT a FROM Asistencia a WHERE a.asistAsist = :asistAsist")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asist")
    private Integer idAsist;
    @Basic(optional = false)
    @Column(name = "asist_asist")
    private String asistAsist;
    @Basic(optional = false)
    @Column(name = "fecha_asist")
    @Temporal(TemporalType.DATE)
    private Date fechaAsist;
    @JoinColumn(name = "doc_id", referencedColumnName = "id_doc")
    @ManyToOne
    private Docentes docId;
    @JoinColumn(name = "dias_id", referencedColumnName = "id_dias")
    @ManyToOne
    private Dias diasId;
    @JoinColumn(name = "est_id", referencedColumnName = "id_est")
    @ManyToOne
    private Estudiantes estId;
    @JoinColumn(name = "salon_id", referencedColumnName = "id_salon")
    @ManyToOne
    private Salones salonId;

    public Asistencia() {
    }

    public Asistencia(Integer idAsist) {
        this.idAsist = idAsist;
    }

    public Asistencia(Integer idAsist, String asistAsist) {
        this.idAsist = idAsist;
        this.asistAsist = asistAsist;
    }

    public Integer getIdAsist() {
        return idAsist;
    }

    public void setIdAsist(Integer idAsist) {
        this.idAsist = idAsist;
    }

    public String getAsistAsist() {
        return asistAsist;
    }

    public void setAsistAsist(String asistAsist) {
        this.asistAsist = asistAsist;
    }

    public Date getFechaAsist() {
        return fechaAsist;
    }

    public void setFechaAsist(Date fechaAsist) {
        this.fechaAsist = fechaAsist;
    }

    public Docentes getDocId() {
        return docId;
    }

    public void setDocId(Docentes docId) {
        this.docId = docId;
    }

    public Dias getDiasId() {
        return diasId;
    }

    public void setDiasId(Dias diasId) {
        this.diasId = diasId;
    }

    public Estudiantes getEstId() {
        return estId;
    }

    public void setEstId(Estudiantes estId) {
        this.estId = estId;
    }

    public Salones getSalonId() {
        return salonId;
    }

    public void setSalonId(Salones salonId) {
        this.salonId = salonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsist != null ? idAsist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsist == null && other.idAsist != null) || (this.idAsist != null && !this.idAsist.equals(other.idAsist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.sawake.entity.Asistencia[ idAsist=" + idAsist + " ]";
    }

}
