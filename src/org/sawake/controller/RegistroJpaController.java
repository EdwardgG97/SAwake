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
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Registro;

public class RegistroJpaController implements Serializable {

    public RegistroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registro registro) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiantes estId = registro.getEstId();
            if (estId != null) {
                estId = em.getReference(estId.getClass(), estId.getIdEst());
                registro.setEstId(estId);
            }
            em.persist(registro);
            if (estId != null) {
                estId.getRegistroList().add(registro);
                estId = em.merge(estId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registro registro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registro persistentRegistro = em.find(Registro.class, registro.getIdReg());
            Estudiantes estIdOld = persistentRegistro.getEstId();
            Estudiantes estIdNew = registro.getEstId();
            if (estIdNew != null) {
                estIdNew = em.getReference(estIdNew.getClass(), estIdNew.getIdEst());
                registro.setEstId(estIdNew);
            }
            registro = em.merge(registro);
            if (estIdOld != null && !estIdOld.equals(estIdNew)) {
                estIdOld.getRegistroList().remove(registro);
                estIdOld = em.merge(estIdOld);
            }
            if (estIdNew != null && !estIdNew.equals(estIdOld)) {
                estIdNew.getRegistroList().add(registro);
                estIdNew = em.merge(estIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registro.getIdReg();
                if (findRegistro(id) == null) {
                    throw new NonexistentEntityException("The registro with id " + id + " no longer exists.");
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
            Registro registro;
            try {
                registro = em.getReference(Registro.class, id);
                registro.getIdReg();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registro with id " + id + " no longer exists.", enfe);
            }
            Estudiantes estId = registro.getEstId();
            if (estId != null) {
                estId.getRegistroList().remove(registro);
                estId = em.merge(estId);
            }
            em.remove(registro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registro> findRegistroEntities() {
        return findRegistroEntities(true, -1, -1);
    }

    public List<Registro> findRegistroEntities(int maxResults, int firstResult) {
        return findRegistroEntities(false, maxResults, firstResult);
    }

    private List<Registro> findRegistroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registro.class));
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

    public Registro findRegistro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registro.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registro> rt = cq.from(Registro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Registro> buscarRegistroPorEstudiante(Estudiantes est) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT r FROM Registro r WHERE r.estId=:estId");
            q.setParameter("estId", est);
            List<Registro> listRegistroEstudiante = q.getResultList();
            em.getTransaction().commit();
            return listRegistroEstudiante;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Registro buscarRegistroPorFecha(Estudiantes est, String fechaActual) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT r FROM Registro r WHERE r.estId = :estId AND r.fEntradareg = :fEntrada_reg");
            q.setParameter("estId", est);
            q.setParameter("fEntrada_reg", fechaActual);
            Registro reg = (Registro) q.getSingleResult();
            return reg;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
