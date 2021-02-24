package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.Observador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.AcuEst;
import org.sawake.entity.Acudientes;
import org.sawake.entity.FaltasAlertas;

public class AcudientesJpaController implements Serializable {

    public AcudientesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acudientes acudientes) {
        if (acudientes.getObsList() == null) {
            acudientes.setObsList(new ArrayList<Observador>());
        }
        if (acudientes.getAcuEstList() == null) {
            acudientes.setAcuEstList(new ArrayList<AcuEst>());
        }
        if (acudientes.getObservadorList() == null) {
            acudientes.setObservadorList(new ArrayList<Observador>());
        }
        if (acudientes.getFaltasAlertasList() == null) {
            acudientes.setFaltasAlertasList(new ArrayList<FaltasAlertas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Observador> attachedObsList = new ArrayList<Observador>();
            for (Observador obsListObservadorToAttach : acudientes.getObsList()) {
                obsListObservadorToAttach = em.getReference(obsListObservadorToAttach.getClass(), obsListObservadorToAttach.getIdObs());
                attachedObsList.add(obsListObservadorToAttach);
            }
            acudientes.setObsList(attachedObsList);
            List<AcuEst> attachedAcuEstList = new ArrayList<AcuEst>();
            for (AcuEst acuEstListAcuEstToAttach : acudientes.getAcuEstList()) {
                acuEstListAcuEstToAttach = em.getReference(acuEstListAcuEstToAttach.getClass(), acuEstListAcuEstToAttach.getIdAcuest());
                attachedAcuEstList.add(acuEstListAcuEstToAttach);
            }
            acudientes.setAcuEstList(attachedAcuEstList);
            List<Observador> attachedObservadorList = new ArrayList<Observador>();
            for (Observador observadorListObservadorToAttach : acudientes.getObservadorList()) {
                observadorListObservadorToAttach = em.getReference(observadorListObservadorToAttach.getClass(), observadorListObservadorToAttach.getIdObs());
                attachedObservadorList.add(observadorListObservadorToAttach);
            }
            acudientes.setObservadorList(attachedObservadorList);
            List<FaltasAlertas> attachedFaltasAlertasList = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListFaltasAlertasToAttach : acudientes.getFaltasAlertasList()) {
                faltasAlertasListFaltasAlertasToAttach = em.getReference(faltasAlertasListFaltasAlertasToAttach.getClass(), faltasAlertasListFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasList.add(faltasAlertasListFaltasAlertasToAttach);
            }
            acudientes.setFaltasAlertasList(attachedFaltasAlertasList);
            em.persist(acudientes);
            for (Observador obsListObservador : acudientes.getObsList()) {
                Acudientes oldAcuIdOfObsListObservador = obsListObservador.getAcuId();
                obsListObservador.setAcuId(acudientes);
                obsListObservador = em.merge(obsListObservador);
                if (oldAcuIdOfObsListObservador != null) {
                    oldAcuIdOfObsListObservador.getObsList().remove(obsListObservador);
                    oldAcuIdOfObsListObservador = em.merge(oldAcuIdOfObsListObservador);
                }
            }
            for (AcuEst acuEstListAcuEst : acudientes.getAcuEstList()) {
                Acudientes oldAcuIdOfAcuEstListAcuEst = acuEstListAcuEst.getAcuId();
                acuEstListAcuEst.setAcuId(acudientes);
                acuEstListAcuEst = em.merge(acuEstListAcuEst);
                if (oldAcuIdOfAcuEstListAcuEst != null) {
                    oldAcuIdOfAcuEstListAcuEst.getAcuEstList().remove(acuEstListAcuEst);
                    oldAcuIdOfAcuEstListAcuEst = em.merge(oldAcuIdOfAcuEstListAcuEst);
                }
            }
            for (Observador observadorListObservador : acudientes.getObservadorList()) {
                Acudientes oldAcuIdOfObservadorListObservador = observadorListObservador.getAcuId();
                observadorListObservador.setAcuId(acudientes);
                observadorListObservador = em.merge(observadorListObservador);
                if (oldAcuIdOfObservadorListObservador != null) {
                    oldAcuIdOfObservadorListObservador.getObservadorList().remove(observadorListObservador);
                    oldAcuIdOfObservadorListObservador = em.merge(oldAcuIdOfObservadorListObservador);
                }
            }
            for (FaltasAlertas faltasAlertasListFaltasAlertas : acudientes.getFaltasAlertasList()) {
                Acudientes oldAcuIdOfFaltasAlertasListFaltasAlertas = faltasAlertasListFaltasAlertas.getAcuId();
                faltasAlertasListFaltasAlertas.setAcuId(acudientes);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
                if (oldAcuIdOfFaltasAlertasListFaltasAlertas != null) {
                    oldAcuIdOfFaltasAlertasListFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListFaltasAlertas);
                    oldAcuIdOfFaltasAlertasListFaltasAlertas = em.merge(oldAcuIdOfFaltasAlertasListFaltasAlertas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acudientes acudientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acudientes persistentAcudientes = em.find(Acudientes.class, acudientes.getIdAcu());
            List<Observador> obsListOld = persistentAcudientes.getObsList();
            List<Observador> obsListNew = acudientes.getObsList();
            List<AcuEst> acuEstListOld = persistentAcudientes.getAcuEstList();
            List<AcuEst> acuEstListNew = acudientes.getAcuEstList();
            List<Observador> observadorListOld = persistentAcudientes.getObservadorList();
            List<Observador> observadorListNew = acudientes.getObservadorList();
            List<FaltasAlertas> faltasAlertasListOld = persistentAcudientes.getFaltasAlertasList();
            List<FaltasAlertas> faltasAlertasListNew = acudientes.getFaltasAlertasList();
            List<Observador> attachedObsListNew = new ArrayList<Observador>();
            for (Observador obsListNewObservadorToAttach : obsListNew) {
                obsListNewObservadorToAttach = em.getReference(obsListNewObservadorToAttach.getClass(), obsListNewObservadorToAttach.getIdObs());
                attachedObsListNew.add(obsListNewObservadorToAttach);
            }
            obsListNew = attachedObsListNew;
            acudientes.setObsList(obsListNew);
            List<AcuEst> attachedAcuEstListNew = new ArrayList<AcuEst>();
            for (AcuEst acuEstListNewAcuEstToAttach : acuEstListNew) {
                acuEstListNewAcuEstToAttach = em.getReference(acuEstListNewAcuEstToAttach.getClass(), acuEstListNewAcuEstToAttach.getIdAcuest());
                attachedAcuEstListNew.add(acuEstListNewAcuEstToAttach);
            }
            acuEstListNew = attachedAcuEstListNew;
            acudientes.setAcuEstList(acuEstListNew);
            List<Observador> attachedObservadorListNew = new ArrayList<Observador>();
            for (Observador observadorListNewObservadorToAttach : observadorListNew) {
                observadorListNewObservadorToAttach = em.getReference(observadorListNewObservadorToAttach.getClass(), observadorListNewObservadorToAttach.getIdObs());
                attachedObservadorListNew.add(observadorListNewObservadorToAttach);
            }
            observadorListNew = attachedObservadorListNew;
            acudientes.setObservadorList(observadorListNew);
            List<FaltasAlertas> attachedFaltasAlertasListNew = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListNewFaltasAlertasToAttach : faltasAlertasListNew) {
                faltasAlertasListNewFaltasAlertasToAttach = em.getReference(faltasAlertasListNewFaltasAlertasToAttach.getClass(), faltasAlertasListNewFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasListNew.add(faltasAlertasListNewFaltasAlertasToAttach);
            }
            faltasAlertasListNew = attachedFaltasAlertasListNew;
            acudientes.setFaltasAlertasList(faltasAlertasListNew);
            acudientes = em.merge(acudientes);
            for (Observador obsListOldObservador : obsListOld) {
                if (!obsListNew.contains(obsListOldObservador)) {
                    obsListOldObservador.setAcuId(null);
                    obsListOldObservador = em.merge(obsListOldObservador);
                }
            }
            for (Observador obsListNewObservador : obsListNew) {
                if (!obsListOld.contains(obsListNewObservador)) {
                    Acudientes oldAcuIdOfObsListNewObservador = obsListNewObservador.getAcuId();
                    obsListNewObservador.setAcuId(acudientes);
                    obsListNewObservador = em.merge(obsListNewObservador);
                    if (oldAcuIdOfObsListNewObservador != null && !oldAcuIdOfObsListNewObservador.equals(acudientes)) {
                        oldAcuIdOfObsListNewObservador.getObsList().remove(obsListNewObservador);
                        oldAcuIdOfObsListNewObservador = em.merge(oldAcuIdOfObsListNewObservador);
                    }
                }
            }
            for (AcuEst acuEstListOldAcuEst : acuEstListOld) {
                if (!acuEstListNew.contains(acuEstListOldAcuEst)) {
                    acuEstListOldAcuEst.setAcuId(null);
                    acuEstListOldAcuEst = em.merge(acuEstListOldAcuEst);
                }
            }
            for (AcuEst acuEstListNewAcuEst : acuEstListNew) {
                if (!acuEstListOld.contains(acuEstListNewAcuEst)) {
                    Acudientes oldAcuIdOfAcuEstListNewAcuEst = acuEstListNewAcuEst.getAcuId();
                    acuEstListNewAcuEst.setAcuId(acudientes);
                    acuEstListNewAcuEst = em.merge(acuEstListNewAcuEst);
                    if (oldAcuIdOfAcuEstListNewAcuEst != null && !oldAcuIdOfAcuEstListNewAcuEst.equals(acudientes)) {
                        oldAcuIdOfAcuEstListNewAcuEst.getAcuEstList().remove(acuEstListNewAcuEst);
                        oldAcuIdOfAcuEstListNewAcuEst = em.merge(oldAcuIdOfAcuEstListNewAcuEst);
                    }
                }
            }
            for (Observador observadorListOldObservador : observadorListOld) {
                if (!observadorListNew.contains(observadorListOldObservador)) {
                    observadorListOldObservador.setAcuId(null);
                    observadorListOldObservador = em.merge(observadorListOldObservador);
                }
            }
            for (Observador observadorListNewObservador : observadorListNew) {
                if (!observadorListOld.contains(observadorListNewObservador)) {
                    Acudientes oldAcuIdOfObservadorListNewObservador = observadorListNewObservador.getAcuId();
                    observadorListNewObservador.setAcuId(acudientes);
                    observadorListNewObservador = em.merge(observadorListNewObservador);
                    if (oldAcuIdOfObservadorListNewObservador != null && !oldAcuIdOfObservadorListNewObservador.equals(acudientes)) {
                        oldAcuIdOfObservadorListNewObservador.getObservadorList().remove(observadorListNewObservador);
                        oldAcuIdOfObservadorListNewObservador = em.merge(oldAcuIdOfObservadorListNewObservador);
                    }
                }
            }
            for (FaltasAlertas faltasAlertasListOldFaltasAlertas : faltasAlertasListOld) {
                if (!faltasAlertasListNew.contains(faltasAlertasListOldFaltasAlertas)) {
                    faltasAlertasListOldFaltasAlertas.setAcuId(null);
                    faltasAlertasListOldFaltasAlertas = em.merge(faltasAlertasListOldFaltasAlertas);
                }
            }
            for (FaltasAlertas faltasAlertasListNewFaltasAlertas : faltasAlertasListNew) {
                if (!faltasAlertasListOld.contains(faltasAlertasListNewFaltasAlertas)) {
                    Acudientes oldAcuIdOfFaltasAlertasListNewFaltasAlertas = faltasAlertasListNewFaltasAlertas.getAcuId();
                    faltasAlertasListNewFaltasAlertas.setAcuId(acudientes);
                    faltasAlertasListNewFaltasAlertas = em.merge(faltasAlertasListNewFaltasAlertas);
                    if (oldAcuIdOfFaltasAlertasListNewFaltasAlertas != null && !oldAcuIdOfFaltasAlertasListNewFaltasAlertas.equals(acudientes)) {
                        oldAcuIdOfFaltasAlertasListNewFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListNewFaltasAlertas);
                        oldAcuIdOfFaltasAlertasListNewFaltasAlertas = em.merge(oldAcuIdOfFaltasAlertasListNewFaltasAlertas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acudientes.getIdAcu();
                if (findAcudientes(id) == null) {
                    throw new NonexistentEntityException("The acudientes with id " + id + " no longer exists.");
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
            Acudientes acudientes;
            try {
                acudientes = em.getReference(Acudientes.class, id);
                acudientes.getIdAcu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acudientes with id " + id + " no longer exists.", enfe);
            }
            List<Observador> obsList = acudientes.getObsList();
            for (Observador obsListObservador : obsList) {
                obsListObservador.setAcuId(null);
                obsListObservador = em.merge(obsListObservador);
            }
            List<AcuEst> acuEstList = acudientes.getAcuEstList();
            for (AcuEst acuEstListAcuEst : acuEstList) {
                acuEstListAcuEst.setAcuId(null);
                acuEstListAcuEst = em.merge(acuEstListAcuEst);
            }
            List<Observador> observadorList = acudientes.getObservadorList();
            for (Observador observadorListObservador : observadorList) {
                observadorListObservador.setAcuId(null);
                observadorListObservador = em.merge(observadorListObservador);
            }
            List<FaltasAlertas> faltasAlertasList = acudientes.getFaltasAlertasList();
            for (FaltasAlertas faltasAlertasListFaltasAlertas : faltasAlertasList) {
                faltasAlertasListFaltasAlertas.setAcuId(null);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
            }
            em.remove(acudientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acudientes> findAcudientesEntities() {
        return findAcudientesEntities(true, -1, -1);
    }

    public List<Acudientes> findAcudientesEntities(int maxResults, int firstResult) {
        return findAcudientesEntities(false, maxResults, firstResult);
    }

    private List<Acudientes> findAcudientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acudientes.class));
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

    public Acudientes findAcudientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acudientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcudientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acudientes> rt = cq.from(Acudientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
