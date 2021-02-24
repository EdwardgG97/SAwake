package org.sawake.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Docentes;
import org.sawake.entity.Dias;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Salones;

public class AsistenciaJpaController implements Serializable {

    public AsistenciaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistencia asistencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes docId = asistencia.getDocId();
            if (docId != null) {
                docId = em.getReference(docId.getClass(), docId.getIdDoc());
                asistencia.setDocId(docId);
            }
            Dias diasId = asistencia.getDiasId();
            if (diasId != null) {
                diasId = em.getReference(diasId.getClass(), diasId.getIdDias());
                asistencia.setDiasId(diasId);
            }
            Estudiantes estId = asistencia.getEstId();
            if (estId != null) {
                estId = em.getReference(estId.getClass(), estId.getIdEst());
                asistencia.setEstId(estId);
            }
            Salones salonId = asistencia.getSalonId();
            if (salonId != null) {
                salonId = em.getReference(salonId.getClass(), salonId.getIdSalon());
                asistencia.setSalonId(salonId);
            }
            em.persist(asistencia);
            if (docId != null) {
                docId.getAsistenciaList().add(asistencia);
                docId = em.merge(docId);
            }
            if (diasId != null) {
                diasId.getAsistenciaList().add(asistencia);
                diasId = em.merge(diasId);
            }
            if (estId != null) {
                estId.getAsistenciaList().add(asistencia);
                estId = em.merge(estId);
            }
            if (salonId != null) {
                salonId.getAsistenciaList().add(asistencia);
                salonId = em.merge(salonId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistencia asistencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia persistentAsistencia = em.find(Asistencia.class, asistencia.getIdAsist());
            Docentes docIdOld = persistentAsistencia.getDocId();
            Docentes docIdNew = asistencia.getDocId();
            Dias diasIdOld = persistentAsistencia.getDiasId();
            Dias diasIdNew = asistencia.getDiasId();
            Estudiantes estIdOld = persistentAsistencia.getEstId();
            Estudiantes estIdNew = asistencia.getEstId();
            Salones salonIdOld = persistentAsistencia.getSalonId();
            Salones salonIdNew = asistencia.getSalonId();
            if (docIdNew != null) {
                docIdNew = em.getReference(docIdNew.getClass(), docIdNew.getIdDoc());
                asistencia.setDocId(docIdNew);
            }
            if (diasIdNew != null) {
                diasIdNew = em.getReference(diasIdNew.getClass(), diasIdNew.getIdDias());
                asistencia.setDiasId(diasIdNew);
            }
            if (estIdNew != null) {
                estIdNew = em.getReference(estIdNew.getClass(), estIdNew.getIdEst());
                asistencia.setEstId(estIdNew);
            }
            if (salonIdNew != null) {
                salonIdNew = em.getReference(salonIdNew.getClass(), salonIdNew.getIdSalon());
                asistencia.setSalonId(salonIdNew);
            }
            asistencia = em.merge(asistencia);
            if (docIdOld != null && !docIdOld.equals(docIdNew)) {
                docIdOld.getAsistenciaList().remove(asistencia);
                docIdOld = em.merge(docIdOld);
            }
            if (docIdNew != null && !docIdNew.equals(docIdOld)) {
                docIdNew.getAsistenciaList().add(asistencia);
                docIdNew = em.merge(docIdNew);
            }
            if (diasIdOld != null && !diasIdOld.equals(diasIdNew)) {
                diasIdOld.getAsistenciaList().remove(asistencia);
                diasIdOld = em.merge(diasIdOld);
            }
            if (diasIdNew != null && !diasIdNew.equals(diasIdOld)) {
                diasIdNew.getAsistenciaList().add(asistencia);
                diasIdNew = em.merge(diasIdNew);
            }
            if (estIdOld != null && !estIdOld.equals(estIdNew)) {
                estIdOld.getAsistenciaList().remove(asistencia);
                estIdOld = em.merge(estIdOld);
            }
            if (estIdNew != null && !estIdNew.equals(estIdOld)) {
                estIdNew.getAsistenciaList().add(asistencia);
                estIdNew = em.merge(estIdNew);
            }
            if (salonIdOld != null && !salonIdOld.equals(salonIdNew)) {
                salonIdOld.getAsistenciaList().remove(asistencia);
                salonIdOld = em.merge(salonIdOld);
            }
            if (salonIdNew != null && !salonIdNew.equals(salonIdOld)) {
                salonIdNew.getAsistenciaList().add(asistencia);
                salonIdNew = em.merge(salonIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asistencia.getIdAsist();
                if (findAsistencia(id) == null) {
                    throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia asistencia;
            try {
                asistencia = em.getReference(Asistencia.class, id);
                asistencia.getIdAsist();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.", enfe);
            }
            Docentes docId = asistencia.getDocId();
            if (docId != null) {
                docId.getAsistenciaList().remove(asistencia);
                docId = em.merge(docId);
            }
            Dias diasId = asistencia.getDiasId();
            if (diasId != null) {
                diasId.getAsistenciaList().remove(asistencia);
                diasId = em.merge(diasId);
            }
            Estudiantes estId = asistencia.getEstId();
            if (estId != null) {
                estId.getAsistenciaList().remove(asistencia);
                estId = em.merge(estId);
            }
            Salones salonId = asistencia.getSalonId();
            if (salonId != null) {
                salonId.getAsistenciaList().remove(asistencia);
                salonId = em.merge(salonId);
            }
            em.remove(asistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asistencia> findAsistenciaEntities() {
        return findAsistenciaEntities(true, -1, -1);
    }

    public List<Asistencia> findAsistenciaEntities(int maxResults, int firstResult) {
        return findAsistenciaEntities(false, maxResults, firstResult);
    }

    private List<Asistencia> findAsistenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asistencia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Asistencia findAsistencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asistencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsistenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asistencia> rt = cq.from(Asistencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Asistencia> buscarAsistenciasPorSalon(Salones salon) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT a FROM Asistencia a WHERE a.salonId=:salonId ORDER BY a.idAsist DESC", Asistencia.class);
            q.setParameter("salonId", salon);
            List<Asistencia> listAsistencia = (List<Asistencia>) q.getResultList();
            em.getTransaction().commit();
            return listAsistencia;
        } catch (Exception e) {
            System.err.println("AsistenciaJpaController:buscarAsistenciasPorSalon:Error: " + e);
            return null;
        } finally {
            em.close();
        }
    }

}
