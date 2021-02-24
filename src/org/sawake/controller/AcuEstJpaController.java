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
import org.sawake.entity.AcuEst;
import org.sawake.entity.Acudientes;
import org.sawake.entity.Estudiantes;

public class AcuEstJpaController implements Serializable {

    public AcuEstJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(AcuEst acuEst) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acudientes acuId = acuEst.getAcuId();
            if (acuId != null) {
                acuId = em.getReference(acuId.getClass(), acuId.getIdAcu());
                acuEst.setAcuId(acuId);
            }
            Estudiantes estId = acuEst.getEstId();
            if (estId != null) {
                estId = em.getReference(estId.getClass(), estId.getIdEst());
                acuEst.setEstId(estId);
            }
            em.persist(acuEst);
            if (acuId != null) {
                acuId.getAcuEstList().add(acuEst);
                acuId = em.merge(acuId);
            }
            if (estId != null) {
                estId.getAcuEstList().add(acuEst);
                estId = em.merge(estId);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AcuEst acuEst) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AcuEst persistentAcuEst = em.find(AcuEst.class, acuEst.getIdAcuest());
            Acudientes acuIdOld = persistentAcuEst.getAcuId();
            Acudientes acuIdNew = acuEst.getAcuId();
            Estudiantes estIdOld = persistentAcuEst.getEstId();
            Estudiantes estIdNew = acuEst.getEstId();
            if (acuIdNew != null) {
                acuIdNew = em.getReference(acuIdNew.getClass(), acuIdNew.getIdAcu());
                acuEst.setAcuId(acuIdNew);
            }
            if (estIdNew != null) {
                estIdNew = em.getReference(estIdNew.getClass(), estIdNew.getIdEst());
                acuEst.setEstId(estIdNew);
            }
            acuEst = em.merge(acuEst);
            if (acuIdOld != null && !acuIdOld.equals(acuIdNew)) {
                acuIdOld.getAcuEstList().remove(acuEst);
                acuIdOld = em.merge(acuIdOld);
            }
            if (acuIdNew != null && !acuIdNew.equals(acuIdOld)) {
                acuIdNew.getAcuEstList().add(acuEst);
                acuIdNew = em.merge(acuIdNew);
            }
            if (estIdOld != null && !estIdOld.equals(estIdNew)) {
                estIdOld.getAcuEstList().remove(acuEst);
                estIdOld = em.merge(estIdOld);
            }
            if (estIdNew != null && !estIdNew.equals(estIdOld)) {
                estIdNew.getAcuEstList().add(acuEst);
                estIdNew = em.merge(estIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acuEst.getIdAcuest();
                if (findAcuEst(id) == null) {
                    throw new NonexistentEntityException("The acuEst with id " + id + " no longer exists.");
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
            AcuEst acuEst;
            try {
                acuEst = em.getReference(AcuEst.class, id);
                acuEst.getIdAcuest();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acuEst with id " + id + " no longer exists.", enfe);
            }
            Acudientes acuId = acuEst.getAcuId();
            if (acuId != null) {
                acuId.getAcuEstList().remove(acuEst);
                acuId = em.merge(acuId);
            }
            Estudiantes estId = acuEst.getEstId();
            if (estId != null) {
                estId.getAcuEstList().remove(acuEst);
                estId = em.merge(estId);
            }
            em.remove(acuEst);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AcuEst> findEstudiantes(Acudientes acudientes) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT ae FROM AcuEst ae WHERE ae.acuId =:acuId ORDER BY ae.idAcuest ASC", AcuEst.class);
            q.setParameter("acuId", acudientes);
            List<AcuEst> acuest = q.getResultList();
            em.getTransaction().commit();
            return acuest;
        } finally {
            em.close();
        }
    }

    public List<AcuEst> findAcuEstEntities() {
        return findAcuEstEntities(true, -1, -1);
    }

    public List<AcuEst> findAcuEstEntities(int maxResults, int firstResult) {
        return findAcuEstEntities(false, maxResults, firstResult);
    }

    private List<AcuEst> findAcuEstEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AcuEst.class));
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

    public AcuEst findAcuEst(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AcuEst.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcuEstCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AcuEst> rt = cq.from(AcuEst.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
