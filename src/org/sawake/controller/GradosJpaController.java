package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.Grupo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Grados;

public class GradosJpaController implements Serializable {

    public GradosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grados grados) {
        if (grados.getGrupoList() == null) {
            grados.setGrupoList(new ArrayList<Grupo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : grados.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getIdGrupo());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            grados.setGrupoList(attachedGrupoList);
            em.persist(grados);
            for (Grupo grupoListGrupo : grados.getGrupoList()) {
                Grados oldGradoIdOfGrupoListGrupo = grupoListGrupo.getGradoId();
                grupoListGrupo.setGradoId(grados);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldGradoIdOfGrupoListGrupo != null) {
                    oldGradoIdOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldGradoIdOfGrupoListGrupo = em.merge(oldGradoIdOfGrupoListGrupo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grados grados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grados persistentGrados = em.find(Grados.class, grados.getIdGrados());
            List<Grupo> grupoListOld = persistentGrados.getGrupoList();
            List<Grupo> grupoListNew = grados.getGrupoList();
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getIdGrupo());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            grados.setGrupoList(grupoListNew);
            grados = em.merge(grados);
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    grupoListOldGrupo.setGradoId(null);
                    grupoListOldGrupo = em.merge(grupoListOldGrupo);
                }
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Grados oldGradoIdOfGrupoListNewGrupo = grupoListNewGrupo.getGradoId();
                    grupoListNewGrupo.setGradoId(grados);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldGradoIdOfGrupoListNewGrupo != null && !oldGradoIdOfGrupoListNewGrupo.equals(grados)) {
                        oldGradoIdOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldGradoIdOfGrupoListNewGrupo = em.merge(oldGradoIdOfGrupoListNewGrupo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grados.getIdGrados();
                if (findGrados(id) == null) {
                    throw new NonexistentEntityException("The grados with id " + id + " no longer exists.");
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
            Grados grados;
            try {
                grados = em.getReference(Grados.class, id);
                grados.getIdGrados();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grados with id " + id + " no longer exists.", enfe);
            }
            List<Grupo> grupoList = grados.getGrupoList();
            for (Grupo grupoListGrupo : grupoList) {
                grupoListGrupo.setGradoId(null);
                grupoListGrupo = em.merge(grupoListGrupo);
            }
            em.remove(grados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grados> findGradosEntities() {
        return findGradosEntities(true, -1, -1);
    }

    public List<Grados> findGradosEntities(int maxResults, int firstResult) {
        return findGradosEntities(false, maxResults, firstResult);
    }

    private List<Grados> findGradosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grados.class));
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

    public Grados findGrados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grados.class, id);
        } finally {
            em.close();
        }
    }

    public int getGradosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grados> rt = cq.from(Grados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
