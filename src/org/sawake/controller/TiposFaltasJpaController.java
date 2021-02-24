package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.CorrectivosFaltas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.FaltasAlertas;
import org.sawake.entity.TiposFaltas;

public class TiposFaltasJpaController implements Serializable {

    public TiposFaltasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TiposFaltas tiposFaltas) {
        if (tiposFaltas.getCorrectivosFaltasList() == null) {
            tiposFaltas.setCorrectivosFaltasList(new ArrayList<CorrectivosFaltas>());
        }
        if (tiposFaltas.getFaltasAlertasList() == null) {
            tiposFaltas.setFaltasAlertasList(new ArrayList<FaltasAlertas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CorrectivosFaltas> attachedCorrectivosFaltasList = new ArrayList<CorrectivosFaltas>();
            for (CorrectivosFaltas correctivosFaltasListCorrectivosFaltasToAttach : tiposFaltas.getCorrectivosFaltasList()) {
                correctivosFaltasListCorrectivosFaltasToAttach = em.getReference(correctivosFaltasListCorrectivosFaltasToAttach.getClass(), correctivosFaltasListCorrectivosFaltasToAttach.getIdCorrectivo());
                attachedCorrectivosFaltasList.add(correctivosFaltasListCorrectivosFaltasToAttach);
            }
            tiposFaltas.setCorrectivosFaltasList(attachedCorrectivosFaltasList);
            List<FaltasAlertas> attachedFaltasAlertasList = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListFaltasAlertasToAttach : tiposFaltas.getFaltasAlertasList()) {
                faltasAlertasListFaltasAlertasToAttach = em.getReference(faltasAlertasListFaltasAlertasToAttach.getClass(), faltasAlertasListFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasList.add(faltasAlertasListFaltasAlertasToAttach);
            }
            tiposFaltas.setFaltasAlertasList(attachedFaltasAlertasList);
            em.persist(tiposFaltas);
            for (CorrectivosFaltas correctivosFaltasListCorrectivosFaltas : tiposFaltas.getCorrectivosFaltasList()) {
                TiposFaltas oldTipoIdOfCorrectivosFaltasListCorrectivosFaltas = correctivosFaltasListCorrectivosFaltas.getTipoId();
                correctivosFaltasListCorrectivosFaltas.setTipoId(tiposFaltas);
                correctivosFaltasListCorrectivosFaltas = em.merge(correctivosFaltasListCorrectivosFaltas);
                if (oldTipoIdOfCorrectivosFaltasListCorrectivosFaltas != null) {
                    oldTipoIdOfCorrectivosFaltasListCorrectivosFaltas.getCorrectivosFaltasList().remove(correctivosFaltasListCorrectivosFaltas);
                    oldTipoIdOfCorrectivosFaltasListCorrectivosFaltas = em.merge(oldTipoIdOfCorrectivosFaltasListCorrectivosFaltas);
                }
            }
            for (FaltasAlertas faltasAlertasListFaltasAlertas : tiposFaltas.getFaltasAlertasList()) {
                TiposFaltas oldTipoIdOfFaltasAlertasListFaltasAlertas = faltasAlertasListFaltasAlertas.getTipoId();
                faltasAlertasListFaltasAlertas.setTipoId(tiposFaltas);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
                if (oldTipoIdOfFaltasAlertasListFaltasAlertas != null) {
                    oldTipoIdOfFaltasAlertasListFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListFaltasAlertas);
                    oldTipoIdOfFaltasAlertasListFaltasAlertas = em.merge(oldTipoIdOfFaltasAlertasListFaltasAlertas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TiposFaltas tiposFaltas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TiposFaltas persistentTiposFaltas = em.find(TiposFaltas.class, tiposFaltas.getIdTipo());
            List<CorrectivosFaltas> correctivosFaltasListOld = persistentTiposFaltas.getCorrectivosFaltasList();
            List<CorrectivosFaltas> correctivosFaltasListNew = tiposFaltas.getCorrectivosFaltasList();
            List<FaltasAlertas> faltasAlertasListOld = persistentTiposFaltas.getFaltasAlertasList();
            List<FaltasAlertas> faltasAlertasListNew = tiposFaltas.getFaltasAlertasList();
            List<CorrectivosFaltas> attachedCorrectivosFaltasListNew = new ArrayList<CorrectivosFaltas>();
            for (CorrectivosFaltas correctivosFaltasListNewCorrectivosFaltasToAttach : correctivosFaltasListNew) {
                correctivosFaltasListNewCorrectivosFaltasToAttach = em.getReference(correctivosFaltasListNewCorrectivosFaltasToAttach.getClass(), correctivosFaltasListNewCorrectivosFaltasToAttach.getIdCorrectivo());
                attachedCorrectivosFaltasListNew.add(correctivosFaltasListNewCorrectivosFaltasToAttach);
            }
            correctivosFaltasListNew = attachedCorrectivosFaltasListNew;
            tiposFaltas.setCorrectivosFaltasList(correctivosFaltasListNew);
            List<FaltasAlertas> attachedFaltasAlertasListNew = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListNewFaltasAlertasToAttach : faltasAlertasListNew) {
                faltasAlertasListNewFaltasAlertasToAttach = em.getReference(faltasAlertasListNewFaltasAlertasToAttach.getClass(), faltasAlertasListNewFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasListNew.add(faltasAlertasListNewFaltasAlertasToAttach);
            }
            faltasAlertasListNew = attachedFaltasAlertasListNew;
            tiposFaltas.setFaltasAlertasList(faltasAlertasListNew);
            tiposFaltas = em.merge(tiposFaltas);
            for (CorrectivosFaltas correctivosFaltasListOldCorrectivosFaltas : correctivosFaltasListOld) {
                if (!correctivosFaltasListNew.contains(correctivosFaltasListOldCorrectivosFaltas)) {
                    correctivosFaltasListOldCorrectivosFaltas.setTipoId(null);
                    correctivosFaltasListOldCorrectivosFaltas = em.merge(correctivosFaltasListOldCorrectivosFaltas);
                }
            }
            for (CorrectivosFaltas correctivosFaltasListNewCorrectivosFaltas : correctivosFaltasListNew) {
                if (!correctivosFaltasListOld.contains(correctivosFaltasListNewCorrectivosFaltas)) {
                    TiposFaltas oldTipoIdOfCorrectivosFaltasListNewCorrectivosFaltas = correctivosFaltasListNewCorrectivosFaltas.getTipoId();
                    correctivosFaltasListNewCorrectivosFaltas.setTipoId(tiposFaltas);
                    correctivosFaltasListNewCorrectivosFaltas = em.merge(correctivosFaltasListNewCorrectivosFaltas);
                    if (oldTipoIdOfCorrectivosFaltasListNewCorrectivosFaltas != null && !oldTipoIdOfCorrectivosFaltasListNewCorrectivosFaltas.equals(tiposFaltas)) {
                        oldTipoIdOfCorrectivosFaltasListNewCorrectivosFaltas.getCorrectivosFaltasList().remove(correctivosFaltasListNewCorrectivosFaltas);
                        oldTipoIdOfCorrectivosFaltasListNewCorrectivosFaltas = em.merge(oldTipoIdOfCorrectivosFaltasListNewCorrectivosFaltas);
                    }
                }
            }
            for (FaltasAlertas faltasAlertasListOldFaltasAlertas : faltasAlertasListOld) {
                if (!faltasAlertasListNew.contains(faltasAlertasListOldFaltasAlertas)) {
                    faltasAlertasListOldFaltasAlertas.setTipoId(null);
                    faltasAlertasListOldFaltasAlertas = em.merge(faltasAlertasListOldFaltasAlertas);
                }
            }
            for (FaltasAlertas faltasAlertasListNewFaltasAlertas : faltasAlertasListNew) {
                if (!faltasAlertasListOld.contains(faltasAlertasListNewFaltasAlertas)) {
                    TiposFaltas oldTipoIdOfFaltasAlertasListNewFaltasAlertas = faltasAlertasListNewFaltasAlertas.getTipoId();
                    faltasAlertasListNewFaltasAlertas.setTipoId(tiposFaltas);
                    faltasAlertasListNewFaltasAlertas = em.merge(faltasAlertasListNewFaltasAlertas);
                    if (oldTipoIdOfFaltasAlertasListNewFaltasAlertas != null && !oldTipoIdOfFaltasAlertasListNewFaltasAlertas.equals(tiposFaltas)) {
                        oldTipoIdOfFaltasAlertasListNewFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListNewFaltasAlertas);
                        oldTipoIdOfFaltasAlertasListNewFaltasAlertas = em.merge(oldTipoIdOfFaltasAlertasListNewFaltasAlertas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiposFaltas.getIdTipo();
                if (findTiposFaltas(id) == null) {
                    throw new NonexistentEntityException("The tiposFaltas with id " + id + " no longer exists.");
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
            TiposFaltas tiposFaltas;
            try {
                tiposFaltas = em.getReference(TiposFaltas.class, id);
                tiposFaltas.getIdTipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposFaltas with id " + id + " no longer exists.", enfe);
            }
            List<CorrectivosFaltas> correctivosFaltasList = tiposFaltas.getCorrectivosFaltasList();
            for (CorrectivosFaltas correctivosFaltasListCorrectivosFaltas : correctivosFaltasList) {
                correctivosFaltasListCorrectivosFaltas.setTipoId(null);
                correctivosFaltasListCorrectivosFaltas = em.merge(correctivosFaltasListCorrectivosFaltas);
            }
            List<FaltasAlertas> faltasAlertasList = tiposFaltas.getFaltasAlertasList();
            for (FaltasAlertas faltasAlertasListFaltasAlertas : faltasAlertasList) {
                faltasAlertasListFaltasAlertas.setTipoId(null);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
            }
            em.remove(tiposFaltas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TiposFaltas> findTiposFaltasEntities() {
        return findTiposFaltasEntities(true, -1, -1);
    }

    public List<TiposFaltas> findTiposFaltasEntities(int maxResults, int firstResult) {
        return findTiposFaltasEntities(false, maxResults, firstResult);
    }

    private List<TiposFaltas> findTiposFaltasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TiposFaltas.class));
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

    public TiposFaltas findTiposFaltas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TiposFaltas.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposFaltasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TiposFaltas> rt = cq.from(TiposFaltas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
