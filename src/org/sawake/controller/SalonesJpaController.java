package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.Grupo;
import org.sawake.entity.Asistencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Salones;

public class SalonesJpaController implements Serializable {

    public SalonesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salones salones) {
        if (salones.getAsistenciaList() == null) {
            salones.setAsistenciaList(new ArrayList<Asistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupoId = salones.getGrupoId();
            if (grupoId != null) {
                grupoId = em.getReference(grupoId.getClass(), grupoId.getIdGrupo());
                salones.setGrupoId(grupoId);
            }
            List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListAsistenciaToAttach : salones.getAsistenciaList()) {
                asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsist());
                attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
            }
            salones.setAsistenciaList(attachedAsistenciaList);
            em.persist(salones);
            if (grupoId != null) {
                grupoId.getSalonesList().add(salones);
                grupoId = em.merge(grupoId);
            }
            for (Asistencia asistenciaListAsistencia : salones.getAsistenciaList()) {
                Salones oldSalonIdOfAsistenciaListAsistencia = asistenciaListAsistencia.getSalonId();
                asistenciaListAsistencia.setSalonId(salones);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
                if (oldSalonIdOfAsistenciaListAsistencia != null) {
                    oldSalonIdOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
                    oldSalonIdOfAsistenciaListAsistencia = em.merge(oldSalonIdOfAsistenciaListAsistencia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salones salones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salones persistentSalones = em.find(Salones.class, salones.getIdSalon());
            Grupo grupoIdOld = persistentSalones.getGrupoId();
            Grupo grupoIdNew = salones.getGrupoId();
            List<Asistencia> asistenciaListOld = persistentSalones.getAsistenciaList();
            List<Asistencia> asistenciaListNew = salones.getAsistenciaList();
            if (grupoIdNew != null) {
                grupoIdNew = em.getReference(grupoIdNew.getClass(), grupoIdNew.getIdGrupo());
                salones.setGrupoId(grupoIdNew);
            }
            List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
                asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsist());
                attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
            }
            asistenciaListNew = attachedAsistenciaListNew;
            salones.setAsistenciaList(asistenciaListNew);
            salones = em.merge(salones);
            if (grupoIdOld != null && !grupoIdOld.equals(grupoIdNew)) {
                grupoIdOld.getSalonesList().remove(salones);
                grupoIdOld = em.merge(grupoIdOld);
            }
            if (grupoIdNew != null && !grupoIdNew.equals(grupoIdOld)) {
                grupoIdNew.getSalonesList().add(salones);
                grupoIdNew = em.merge(grupoIdNew);
            }
            for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
                if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
                    asistenciaListOldAsistencia.setSalonId(null);
                    asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
                }
            }
            for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
                if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
                    Salones oldSalonIdOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getSalonId();
                    asistenciaListNewAsistencia.setSalonId(salones);
                    asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
                    if (oldSalonIdOfAsistenciaListNewAsistencia != null && !oldSalonIdOfAsistenciaListNewAsistencia.equals(salones)) {
                        oldSalonIdOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
                        oldSalonIdOfAsistenciaListNewAsistencia = em.merge(oldSalonIdOfAsistenciaListNewAsistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salones.getIdSalon();
                if (findSalones(id) == null) {
                    throw new NonexistentEntityException("The salones with id " + id + " no longer exists.");
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
            Salones salones;
            try {
                salones = em.getReference(Salones.class, id);
                salones.getIdSalon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salones with id " + id + " no longer exists.", enfe);
            }
            Grupo grupoId = salones.getGrupoId();
            if (grupoId != null) {
                grupoId.getSalonesList().remove(salones);
                grupoId = em.merge(grupoId);
            }
            List<Asistencia> asistenciaList = salones.getAsistenciaList();
            for (Asistencia asistenciaListAsistencia : asistenciaList) {
                asistenciaListAsistencia.setSalonId(null);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
            }
            em.remove(salones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salones> findSalonesEntities() {
        return findSalonesEntities(true, -1, -1);
    }

    public List<Salones> findSalonesEntities(int maxResults, int firstResult) {
        return findSalonesEntities(false, maxResults, firstResult);
    }

    private List<Salones> findSalonesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salones.class));
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

    public Salones findSalones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salones.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalonesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salones> rt = cq.from(Salones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
