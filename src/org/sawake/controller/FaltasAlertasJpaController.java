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
import org.sawake.entity.TiposFaltas;
import org.sawake.entity.Docentes;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Acudientes;
import org.sawake.entity.FaltasAlertas;

public class FaltasAlertasJpaController implements Serializable {

    public FaltasAlertasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FaltasAlertas faltasAlertas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposFaltas tipoId = faltasAlertas.getTipoId();
            if (tipoId != null) {
                tipoId = em.getReference(tipoId.getClass(), tipoId.getIdTipo());
                faltasAlertas.setTipoId(tipoId);
            }
            Docentes docId = faltasAlertas.getDocId();
            if (docId != null) {
                docId = em.getReference(docId.getClass(), docId.getIdDoc());
                faltasAlertas.setDocId(docId);
            }
            Estudiantes estId = faltasAlertas.getEstId();
            if (estId != null) {
                estId = em.getReference(estId.getClass(), estId.getIdEst());
                faltasAlertas.setEstId(estId);
            }
            Acudientes acuId = faltasAlertas.getAcuId();
            if (acuId != null) {
                acuId = em.getReference(acuId.getClass(), acuId.getIdAcu());
                faltasAlertas.setAcuId(acuId);
            }
            em.persist(faltasAlertas);
            if (tipoId != null) {
                tipoId.getFaltasAlertasList().add(faltasAlertas);
                tipoId = em.merge(tipoId);
            }
            if (docId != null) {
                docId.getFaltasAlertasList().add(faltasAlertas);
                docId = em.merge(docId);
            }
            if (estId != null) {
                estId.getFaltasAlertasList().add(faltasAlertas);
                estId = em.merge(estId);
            }
            if (acuId != null) {
                acuId.getFaltasAlertasList().add(faltasAlertas);
                acuId = em.merge(acuId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FaltasAlertas faltasAlertas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FaltasAlertas persistentFaltasAlertas = em.find(FaltasAlertas.class, faltasAlertas.getIdAlertas());
            TiposFaltas tipoIdOld = persistentFaltasAlertas.getTipoId();
            TiposFaltas tipoIdNew = faltasAlertas.getTipoId();
            Docentes docIdOld = persistentFaltasAlertas.getDocId();
            Docentes docIdNew = faltasAlertas.getDocId();
            Estudiantes estIdOld = persistentFaltasAlertas.getEstId();
            Estudiantes estIdNew = faltasAlertas.getEstId();
            Acudientes acuIdOld = persistentFaltasAlertas.getAcuId();
            Acudientes acuIdNew = faltasAlertas.getAcuId();
            if (tipoIdNew != null) {
                tipoIdNew = em.getReference(tipoIdNew.getClass(), tipoIdNew.getIdTipo());
                faltasAlertas.setTipoId(tipoIdNew);
            }
            if (docIdNew != null) {
                docIdNew = em.getReference(docIdNew.getClass(), docIdNew.getIdDoc());
                faltasAlertas.setDocId(docIdNew);
            }
            if (estIdNew != null) {
                estIdNew = em.getReference(estIdNew.getClass(), estIdNew.getIdEst());
                faltasAlertas.setEstId(estIdNew);
            }
            if (acuIdNew != null) {
                acuIdNew = em.getReference(acuIdNew.getClass(), acuIdNew.getIdAcu());
                faltasAlertas.setAcuId(acuIdNew);
            }
            faltasAlertas = em.merge(faltasAlertas);
            if (tipoIdOld != null && !tipoIdOld.equals(tipoIdNew)) {
                tipoIdOld.getFaltasAlertasList().remove(faltasAlertas);
                tipoIdOld = em.merge(tipoIdOld);
            }
            if (tipoIdNew != null && !tipoIdNew.equals(tipoIdOld)) {
                tipoIdNew.getFaltasAlertasList().add(faltasAlertas);
                tipoIdNew = em.merge(tipoIdNew);
            }
            if (docIdOld != null && !docIdOld.equals(docIdNew)) {
                docIdOld.getFaltasAlertasList().remove(faltasAlertas);
                docIdOld = em.merge(docIdOld);
            }
            if (docIdNew != null && !docIdNew.equals(docIdOld)) {
                docIdNew.getFaltasAlertasList().add(faltasAlertas);
                docIdNew = em.merge(docIdNew);
            }
            if (estIdOld != null && !estIdOld.equals(estIdNew)) {
                estIdOld.getFaltasAlertasList().remove(faltasAlertas);
                estIdOld = em.merge(estIdOld);
            }
            if (estIdNew != null && !estIdNew.equals(estIdOld)) {
                estIdNew.getFaltasAlertasList().add(faltasAlertas);
                estIdNew = em.merge(estIdNew);
            }
            if (acuIdOld != null && !acuIdOld.equals(acuIdNew)) {
                acuIdOld.getFaltasAlertasList().remove(faltasAlertas);
                acuIdOld = em.merge(acuIdOld);
            }
            if (acuIdNew != null && !acuIdNew.equals(acuIdOld)) {
                acuIdNew.getFaltasAlertasList().add(faltasAlertas);
                acuIdNew = em.merge(acuIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = faltasAlertas.getIdAlertas();
                if (findFaltasAlertas(id) == null) {
                    throw new NonexistentEntityException("The faltasAlertas with id " + id + " no longer exists.");
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
            FaltasAlertas faltasAlertas;
            try {
                faltasAlertas = em.getReference(FaltasAlertas.class, id);
                faltasAlertas.getIdAlertas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The faltasAlertas with id " + id + " no longer exists.", enfe);
            }
            TiposFaltas tipoId = faltasAlertas.getTipoId();
            if (tipoId != null) {
                tipoId.getFaltasAlertasList().remove(faltasAlertas);
                tipoId = em.merge(tipoId);
            }
            Docentes docId = faltasAlertas.getDocId();
            if (docId != null) {
                docId.getFaltasAlertasList().remove(faltasAlertas);
                docId = em.merge(docId);
            }
            Estudiantes estId = faltasAlertas.getEstId();
            if (estId != null) {
                estId.getFaltasAlertasList().remove(faltasAlertas);
                estId = em.merge(estId);
            }
            Acudientes acuId = faltasAlertas.getAcuId();
            if (acuId != null) {
                acuId.getFaltasAlertasList().remove(faltasAlertas);
                acuId = em.merge(acuId);
            }
            em.remove(faltasAlertas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FaltasAlertas> findFaltasAlertasEntities() {
        return findFaltasAlertasEntities(true, -1, -1);
    }

    public List<FaltasAlertas> findFaltasAlertasEntities(int maxResults, int firstResult) {
        return findFaltasAlertasEntities(false, maxResults, firstResult);
    }

    private List<FaltasAlertas> findFaltasAlertasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FaltasAlertas.class));
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

    public FaltasAlertas findFaltasAlertas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FaltasAlertas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFaltasAlertasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FaltasAlertas> rt = cq.from(FaltasAlertas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
