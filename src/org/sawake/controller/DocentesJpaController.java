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
import org.sawake.entity.Asistencia;
import org.sawake.entity.Docentes;
import org.sawake.entity.FaltasAlertas;

public class DocentesJpaController implements Serializable {

    public DocentesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docentes docentes) {
        if (docentes.getObsList() == null) {
            docentes.setObsList(new ArrayList<Observador>());
        }
        if (docentes.getAsistenciaList() == null) {
            docentes.setAsistenciaList(new ArrayList<Asistencia>());
        }
        if (docentes.getObservadorList() == null) {
            docentes.setObservadorList(new ArrayList<Observador>());
        }
        if (docentes.getFaltasAlertasList() == null) {
            docentes.setFaltasAlertasList(new ArrayList<FaltasAlertas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Observador> attachedObsList = new ArrayList<Observador>();
            for (Observador obsListObservadorToAttach : docentes.getObsList()) {
                obsListObservadorToAttach = em.getReference(obsListObservadorToAttach.getClass(), obsListObservadorToAttach.getIdObs());
                attachedObsList.add(obsListObservadorToAttach);
            }
            docentes.setObsList(attachedObsList);
            List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListAsistenciaToAttach : docentes.getAsistenciaList()) {
                asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsist());
                attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
            }
            docentes.setAsistenciaList(attachedAsistenciaList);
            List<Observador> attachedObservadorList = new ArrayList<Observador>();
            for (Observador observadorListObservadorToAttach : docentes.getObservadorList()) {
                observadorListObservadorToAttach = em.getReference(observadorListObservadorToAttach.getClass(), observadorListObservadorToAttach.getIdObs());
                attachedObservadorList.add(observadorListObservadorToAttach);
            }
            docentes.setObservadorList(attachedObservadorList);
            List<FaltasAlertas> attachedFaltasAlertasList = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListFaltasAlertasToAttach : docentes.getFaltasAlertasList()) {
                faltasAlertasListFaltasAlertasToAttach = em.getReference(faltasAlertasListFaltasAlertasToAttach.getClass(), faltasAlertasListFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasList.add(faltasAlertasListFaltasAlertasToAttach);
            }
            docentes.setFaltasAlertasList(attachedFaltasAlertasList);
            em.persist(docentes);
            for (Observador obsListObservador : docentes.getObsList()) {
                Docentes oldDocIdOfObsListObservador = obsListObservador.getDocId();
                obsListObservador.setDocId(docentes);
                obsListObservador = em.merge(obsListObservador);
                if (oldDocIdOfObsListObservador != null) {
                    oldDocIdOfObsListObservador.getObsList().remove(obsListObservador);
                    oldDocIdOfObsListObservador = em.merge(oldDocIdOfObsListObservador);
                }
            }
            for (Asistencia asistenciaListAsistencia : docentes.getAsistenciaList()) {
                Docentes oldDocIdOfAsistenciaListAsistencia = asistenciaListAsistencia.getDocId();
                asistenciaListAsistencia.setDocId(docentes);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
                if (oldDocIdOfAsistenciaListAsistencia != null) {
                    oldDocIdOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
                    oldDocIdOfAsistenciaListAsistencia = em.merge(oldDocIdOfAsistenciaListAsistencia);
                }
            }
            for (Observador observadorListObservador : docentes.getObservadorList()) {
                Docentes oldDocIdOfObservadorListObservador = observadorListObservador.getDocId();
                observadorListObservador.setDocId(docentes);
                observadorListObservador = em.merge(observadorListObservador);
                if (oldDocIdOfObservadorListObservador != null) {
                    oldDocIdOfObservadorListObservador.getObservadorList().remove(observadorListObservador);
                    oldDocIdOfObservadorListObservador = em.merge(oldDocIdOfObservadorListObservador);
                }
            }
            for (FaltasAlertas faltasAlertasListFaltasAlertas : docentes.getFaltasAlertasList()) {
                Docentes oldDocIdOfFaltasAlertasListFaltasAlertas = faltasAlertasListFaltasAlertas.getDocId();
                faltasAlertasListFaltasAlertas.setDocId(docentes);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
                if (oldDocIdOfFaltasAlertasListFaltasAlertas != null) {
                    oldDocIdOfFaltasAlertasListFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListFaltasAlertas);
                    oldDocIdOfFaltasAlertasListFaltasAlertas = em.merge(oldDocIdOfFaltasAlertasListFaltasAlertas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docentes docentes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docentes persistentDocentes = em.find(Docentes.class, docentes.getIdDoc());
            List<Observador> obsListOld = persistentDocentes.getObsList();
            List<Observador> obsListNew = docentes.getObsList();
            List<Asistencia> asistenciaListOld = persistentDocentes.getAsistenciaList();
            List<Asistencia> asistenciaListNew = docentes.getAsistenciaList();
            List<Observador> observadorListOld = persistentDocentes.getObservadorList();
            List<Observador> observadorListNew = docentes.getObservadorList();
            List<FaltasAlertas> faltasAlertasListOld = persistentDocentes.getFaltasAlertasList();
            List<FaltasAlertas> faltasAlertasListNew = docentes.getFaltasAlertasList();
            List<Observador> attachedObsListNew = new ArrayList<Observador>();
            for (Observador obsListNewObservadorToAttach : obsListNew) {
                obsListNewObservadorToAttach = em.getReference(obsListNewObservadorToAttach.getClass(), obsListNewObservadorToAttach.getIdObs());
                attachedObsListNew.add(obsListNewObservadorToAttach);
            }
            obsListNew = attachedObsListNew;
            docentes.setObsList(obsListNew);
            List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
                asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsist());
                attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
            }
            asistenciaListNew = attachedAsistenciaListNew;
            docentes.setAsistenciaList(asistenciaListNew);
            List<Observador> attachedObservadorListNew = new ArrayList<Observador>();
            for (Observador observadorListNewObservadorToAttach : observadorListNew) {
                observadorListNewObservadorToAttach = em.getReference(observadorListNewObservadorToAttach.getClass(), observadorListNewObservadorToAttach.getIdObs());
                attachedObservadorListNew.add(observadorListNewObservadorToAttach);
            }
            observadorListNew = attachedObservadorListNew;
            docentes.setObservadorList(observadorListNew);
            List<FaltasAlertas> attachedFaltasAlertasListNew = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListNewFaltasAlertasToAttach : faltasAlertasListNew) {
                faltasAlertasListNewFaltasAlertasToAttach = em.getReference(faltasAlertasListNewFaltasAlertasToAttach.getClass(), faltasAlertasListNewFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasListNew.add(faltasAlertasListNewFaltasAlertasToAttach);
            }
            faltasAlertasListNew = attachedFaltasAlertasListNew;
            docentes.setFaltasAlertasList(faltasAlertasListNew);
            docentes = em.merge(docentes);
            for (Observador obsListOldObservador : obsListOld) {
                if (!obsListNew.contains(obsListOldObservador)) {
                    obsListOldObservador.setDocId(null);
                    obsListOldObservador = em.merge(obsListOldObservador);
                }
            }
            for (Observador obsListNewObservador : obsListNew) {
                if (!obsListOld.contains(obsListNewObservador)) {
                    Docentes oldDocIdOfObsListNewObservador = obsListNewObservador.getDocId();
                    obsListNewObservador.setDocId(docentes);
                    obsListNewObservador = em.merge(obsListNewObservador);
                    if (oldDocIdOfObsListNewObservador != null && !oldDocIdOfObsListNewObservador.equals(docentes)) {
                        oldDocIdOfObsListNewObservador.getObsList().remove(obsListNewObservador);
                        oldDocIdOfObsListNewObservador = em.merge(oldDocIdOfObsListNewObservador);
                    }
                }
            }
            for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
                if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
                    asistenciaListOldAsistencia.setDocId(null);
                    asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
                }
            }
            for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
                if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
                    Docentes oldDocIdOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getDocId();
                    asistenciaListNewAsistencia.setDocId(docentes);
                    asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
                    if (oldDocIdOfAsistenciaListNewAsistencia != null && !oldDocIdOfAsistenciaListNewAsistencia.equals(docentes)) {
                        oldDocIdOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
                        oldDocIdOfAsistenciaListNewAsistencia = em.merge(oldDocIdOfAsistenciaListNewAsistencia);
                    }
                }
            }
            for (Observador observadorListOldObservador : observadorListOld) {
                if (!observadorListNew.contains(observadorListOldObservador)) {
                    observadorListOldObservador.setDocId(null);
                    observadorListOldObservador = em.merge(observadorListOldObservador);
                }
            }
            for (Observador observadorListNewObservador : observadorListNew) {
                if (!observadorListOld.contains(observadorListNewObservador)) {
                    Docentes oldDocIdOfObservadorListNewObservador = observadorListNewObservador.getDocId();
                    observadorListNewObservador.setDocId(docentes);
                    observadorListNewObservador = em.merge(observadorListNewObservador);
                    if (oldDocIdOfObservadorListNewObservador != null && !oldDocIdOfObservadorListNewObservador.equals(docentes)) {
                        oldDocIdOfObservadorListNewObservador.getObservadorList().remove(observadorListNewObservador);
                        oldDocIdOfObservadorListNewObservador = em.merge(oldDocIdOfObservadorListNewObservador);
                    }
                }
            }
            for (FaltasAlertas faltasAlertasListOldFaltasAlertas : faltasAlertasListOld) {
                if (!faltasAlertasListNew.contains(faltasAlertasListOldFaltasAlertas)) {
                    faltasAlertasListOldFaltasAlertas.setDocId(null);
                    faltasAlertasListOldFaltasAlertas = em.merge(faltasAlertasListOldFaltasAlertas);
                }
            }
            for (FaltasAlertas faltasAlertasListNewFaltasAlertas : faltasAlertasListNew) {
                if (!faltasAlertasListOld.contains(faltasAlertasListNewFaltasAlertas)) {
                    Docentes oldDocIdOfFaltasAlertasListNewFaltasAlertas = faltasAlertasListNewFaltasAlertas.getDocId();
                    faltasAlertasListNewFaltasAlertas.setDocId(docentes);
                    faltasAlertasListNewFaltasAlertas = em.merge(faltasAlertasListNewFaltasAlertas);
                    if (oldDocIdOfFaltasAlertasListNewFaltasAlertas != null && !oldDocIdOfFaltasAlertasListNewFaltasAlertas.equals(docentes)) {
                        oldDocIdOfFaltasAlertasListNewFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListNewFaltasAlertas);
                        oldDocIdOfFaltasAlertasListNewFaltasAlertas = em.merge(oldDocIdOfFaltasAlertasListNewFaltasAlertas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docentes.getIdDoc();
                if (findDocentes(id) == null) {
                    throw new NonexistentEntityException("The docentes with id " + id + " no longer exists.");
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
            Docentes docentes;
            try {
                docentes = em.getReference(Docentes.class, id);
                docentes.getIdDoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docentes with id " + id + " no longer exists.", enfe);
            }
            List<Observador> obsList = docentes.getObsList();
            for (Observador obsListObservador : obsList) {
                obsListObservador.setDocId(null);
                obsListObservador = em.merge(obsListObservador);
            }
            List<Asistencia> asistenciaList = docentes.getAsistenciaList();
            for (Asistencia asistenciaListAsistencia : asistenciaList) {
                asistenciaListAsistencia.setDocId(null);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
            }
            List<Observador> observadorList = docentes.getObservadorList();
            for (Observador observadorListObservador : observadorList) {
                observadorListObservador.setDocId(null);
                observadorListObservador = em.merge(observadorListObservador);
            }
            List<FaltasAlertas> faltasAlertasList = docentes.getFaltasAlertasList();
            for (FaltasAlertas faltasAlertasListFaltasAlertas : faltasAlertasList) {
                faltasAlertasListFaltasAlertas.setDocId(null);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
            }
            em.remove(docentes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docentes> findDocentesEntities() {
        return findDocentesEntities(true, -1, -1);
    }

    public List<Docentes> findDocentesEntities(int maxResults, int firstResult) {
        return findDocentesEntities(false, maxResults, firstResult);
    }

    private List<Docentes> findDocentesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docentes.class));
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

    public Docentes findDocentes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docentes.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocentesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docentes> rt = cq.from(Docentes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
