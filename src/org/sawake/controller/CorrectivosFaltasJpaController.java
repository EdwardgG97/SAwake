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
import org.sawake.entity.CorrectivosFaltas;
import org.sawake.entity.TiposFaltas;

public class CorrectivosFaltasJpaController implements Serializable {

    public CorrectivosFaltasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CorrectivosFaltas correctivosFaltas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposFaltas tipoId = correctivosFaltas.getTipoId();
            if (tipoId != null) {
                tipoId = em.getReference(tipoId.getClass(), tipoId.getIdTipo());
                correctivosFaltas.setTipoId(tipoId);
            }
            em.persist(correctivosFaltas);
            if (tipoId != null) {
                tipoId.getCorrectivosFaltasList().add(correctivosFaltas);
                tipoId = em.merge(tipoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CorrectivosFaltas correctivosFaltas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CorrectivosFaltas persistentCorrectivosFaltas = em.find(CorrectivosFaltas.class, correctivosFaltas.getIdCorrectivo());
            TiposFaltas tipoIdOld = persistentCorrectivosFaltas.getTipoId();
            TiposFaltas tipoIdNew = correctivosFaltas.getTipoId();
            if (tipoIdNew != null) {
                tipoIdNew = em.getReference(tipoIdNew.getClass(), tipoIdNew.getIdTipo());
                correctivosFaltas.setTipoId(tipoIdNew);
            }
            correctivosFaltas = em.merge(correctivosFaltas);
            if (tipoIdOld != null && !tipoIdOld.equals(tipoIdNew)) {
                tipoIdOld.getCorrectivosFaltasList().remove(correctivosFaltas);
                tipoIdOld = em.merge(tipoIdOld);
            }
            if (tipoIdNew != null && !tipoIdNew.equals(tipoIdOld)) {
                tipoIdNew.getCorrectivosFaltasList().add(correctivosFaltas);
                tipoIdNew = em.merge(tipoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = correctivosFaltas.getIdCorrectivo();
                if (findCorrectivosFaltas(id) == null) {
                    throw new NonexistentEntityException("The correctivosFaltas with id " + id + " no longer exists.");
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
            CorrectivosFaltas correctivosFaltas;
            try {
                correctivosFaltas = em.getReference(CorrectivosFaltas.class, id);
                correctivosFaltas.getIdCorrectivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The correctivosFaltas with id " + id + " no longer exists.", enfe);
            }
            TiposFaltas tipoId = correctivosFaltas.getTipoId();
            if (tipoId != null) {
                tipoId.getCorrectivosFaltasList().remove(correctivosFaltas);
                tipoId = em.merge(tipoId);
            }
            em.remove(correctivosFaltas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CorrectivosFaltas> findCorrectivosFaltasEntities() {
        return findCorrectivosFaltasEntities(true, -1, -1);
    }

    public List<CorrectivosFaltas> findCorrectivosFaltasEntities(int maxResults, int firstResult) {
        return findCorrectivosFaltasEntities(false, maxResults, firstResult);
    }

    private List<CorrectivosFaltas> findCorrectivosFaltasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CorrectivosFaltas.class));
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

    public CorrectivosFaltas findCorrectivosFaltas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CorrectivosFaltas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorrectivosFaltasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CorrectivosFaltas> rt = cq.from(CorrectivosFaltas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
