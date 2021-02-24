package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.Asistencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Dias;

public class DiasJpaController implements Serializable {

    public DiasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dias dias) {
        if (dias.getAsistenciaList() == null) {
            dias.setAsistenciaList(new ArrayList<Asistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListAsistenciaToAttach : dias.getAsistenciaList()) {
                asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsist());
                attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
            }
            dias.setAsistenciaList(attachedAsistenciaList);
            em.persist(dias);
            for (Asistencia asistenciaListAsistencia : dias.getAsistenciaList()) {
                Dias oldDiasIdOfAsistenciaListAsistencia = asistenciaListAsistencia.getDiasId();
                asistenciaListAsistencia.setDiasId(dias);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
                if (oldDiasIdOfAsistenciaListAsistencia != null) {
                    oldDiasIdOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
                    oldDiasIdOfAsistenciaListAsistencia = em.merge(oldDiasIdOfAsistenciaListAsistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dias dias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dias persistentDias = em.find(Dias.class, dias.getIdDias());
            List<Asistencia> asistenciaListOld = persistentDias.getAsistenciaList();
            List<Asistencia> asistenciaListNew = dias.getAsistenciaList();
            List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
                asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsist());
                attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
            }
            asistenciaListNew = attachedAsistenciaListNew;
            dias.setAsistenciaList(asistenciaListNew);
            dias = em.merge(dias);
            for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
                if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
                    asistenciaListOldAsistencia.setDiasId(null);
                    asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
                }
            }
            for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
                if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
                    Dias oldDiasIdOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getDiasId();
                    asistenciaListNewAsistencia.setDiasId(dias);
                    asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
                    if (oldDiasIdOfAsistenciaListNewAsistencia != null && !oldDiasIdOfAsistenciaListNewAsistencia.equals(dias)) {
                        oldDiasIdOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
                        oldDiasIdOfAsistenciaListNewAsistencia = em.merge(oldDiasIdOfAsistenciaListNewAsistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dias.getIdDias();
                if (findDias(id) == null) {
                    throw new NonexistentEntityException("The dias with id " + id + " no longer exists.");
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
            Dias dias;
            try {
                dias = em.getReference(Dias.class, id);
                dias.getIdDias();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dias with id " + id + " no longer exists.", enfe);
            }
            List<Asistencia> asistenciaList = dias.getAsistenciaList();
            for (Asistencia asistenciaListAsistencia : asistenciaList) {
                asistenciaListAsistencia.setDiasId(null);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
            }
            em.remove(dias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dias> findDiasEntities() {
        return findDiasEntities(true, -1, -1);
    }

    public List<Dias> findDiasEntities(int maxResults, int firstResult) {
        return findDiasEntities(false, maxResults, firstResult);
    }

    private List<Dias> findDiasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dias.class));
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

    public Dias findDias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dias.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dias> rt = cq.from(Dias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
