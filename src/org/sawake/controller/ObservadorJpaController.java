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
import org.sawake.entity.Docentes;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Acudientes;
import org.sawake.entity.Observador;

public class ObservadorJpaController implements Serializable {

    public ObservadorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Observador observador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes docId = observador.getDocId();
            if (docId != null) {
                docId = em.getReference(docId.getClass(), docId.getIdDoc());
                observador.setDocId(docId);
            }
            Estudiantes estId = observador.getEstId();
            if (estId != null) {
                estId = em.getReference(estId.getClass(), estId.getIdEst());
                observador.setEstId(estId);
            }
            Acudientes acuId = observador.getAcuId();
            if (acuId != null) {
                acuId = em.getReference(acuId.getClass(), acuId.getIdAcu());
                observador.setAcuId(acuId);
            }
            em.persist(observador);
            if (docId != null) {
                docId.getObsList().add(observador);
                docId = em.merge(docId);
            }
            if (estId != null) {
                estId.getObsList().add(observador);
                estId = em.merge(estId);
            }
            if (acuId != null) {
                acuId.getObsList().add(observador);
                acuId = em.merge(acuId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Observador observador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Observador persistentObservador = em.find(Observador.class, observador.getIdObs());
            Docentes docIdOld = persistentObservador.getDocId();
            Docentes docIdNew = observador.getDocId();
            Estudiantes estIdOld = persistentObservador.getEstId();
            Estudiantes estIdNew = observador.getEstId();
            Acudientes acuIdOld = persistentObservador.getAcuId();
            Acudientes acuIdNew = observador.getAcuId();
            if (docIdNew != null) {
                docIdNew = em.getReference(docIdNew.getClass(), docIdNew.getIdDoc());
                observador.setDocId(docIdNew);
            }
            if (estIdNew != null) {
                estIdNew = em.getReference(estIdNew.getClass(), estIdNew.getIdEst());
                observador.setEstId(estIdNew);
            }
            if (acuIdNew != null) {
                acuIdNew = em.getReference(acuIdNew.getClass(), acuIdNew.getIdAcu());
                observador.setAcuId(acuIdNew);
            }
            observador = em.merge(observador);
            if (docIdOld != null && !docIdOld.equals(docIdNew)) {
                docIdOld.getObsList().remove(observador);
                docIdOld = em.merge(docIdOld);
            }
            if (docIdNew != null && !docIdNew.equals(docIdOld)) {
                docIdNew.getObsList().add(observador);
                docIdNew = em.merge(docIdNew);
            }
            if (estIdOld != null && !estIdOld.equals(estIdNew)) {
                estIdOld.getObsList().remove(observador);
                estIdOld = em.merge(estIdOld);
            }
            if (estIdNew != null && !estIdNew.equals(estIdOld)) {
                estIdNew.getObsList().add(observador);
                estIdNew = em.merge(estIdNew);
            }
            if (acuIdOld != null && !acuIdOld.equals(acuIdNew)) {
                acuIdOld.getObsList().remove(observador);
                acuIdOld = em.merge(acuIdOld);
            }
            if (acuIdNew != null && !acuIdNew.equals(acuIdOld)) {
                acuIdNew.getObsList().add(observador);
                acuIdNew = em.merge(acuIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = observador.getIdObs();
                if (findObservador(id) == null) {
                    throw new NonexistentEntityException("The observador with id " + id + " no longer exists.");
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
            Observador observador;
            try {
                observador = em.getReference(Observador.class, id);
                observador.getIdObs();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The observador with id " + id + " no longer exists.", enfe);
            }
            Docentes docId = observador.getDocId();
            if (docId != null) {
                docId.getObsList().remove(observador);
                docId = em.merge(docId);
            }
            Estudiantes estId = observador.getEstId();
            if (estId != null) {
                estId.getObsList().remove(observador);
                estId = em.merge(estId);
            }
            Acudientes acuId = observador.getAcuId();
            if (acuId != null) {
                acuId.getObsList().remove(observador);
                acuId = em.merge(acuId);
            }
            em.remove(observador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Observador> findObservadorEntities() {
        return findObservadorEntities(true, -1, -1);
    }

    public List<Observador> findObservadorEntities(int maxResults, int firstResult) {
        return findObservadorEntities(false, maxResults, firstResult);
    }

    private List<Observador> findObservadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Observador.class));
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

    public Observador findObservador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Observador.class, id);
        } finally {
            em.close();
        }
    }

    public int getObservadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Observador> rt = cq.from(Observador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Observador> buscarObservadorPorEstudiante(Estudiantes estId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT a FROM Observador a WHERE a.estId=:estId", Observador.class);
            q.setParameter("estId", estId);
            List<Observador> listObservador = (List<Observador>) q.getResultList();
            em.getTransaction().commit();
            return listObservador;
        } catch (Exception e) {
            System.err.println("AsistenciaJpaController:buscarAsistenciasPorSalon:Error: " + e);
            return null;
        } finally {
            em.close();
        }
    }

}
