package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.Grupo;
import org.sawake.entity.Observador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.AcuEst;
import org.sawake.entity.Asistencia;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Registro;
import org.sawake.entity.FaltasAlertas;

public class EstudiantesJpaController implements Serializable {

    public EstudiantesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiantes estudiantes) {
        if (estudiantes.getObsList() == null) {
            estudiantes.setObsList(new ArrayList<Observador>());
        }
        if (estudiantes.getAcuEstList() == null) {
            estudiantes.setAcuEstList(new ArrayList<AcuEst>());
        }
        if (estudiantes.getAsistenciaList() == null) {
            estudiantes.setAsistenciaList(new ArrayList<Asistencia>());
        }
        if (estudiantes.getRegistroList() == null) {
            estudiantes.setRegistroList(new ArrayList<Registro>());
        }
        if (estudiantes.getObservadorList() == null) {
            estudiantes.setObservadorList(new ArrayList<Observador>());
        }
        if (estudiantes.getFaltasAlertasList() == null) {
            estudiantes.setFaltasAlertasList(new ArrayList<FaltasAlertas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupoId = estudiantes.getGrupoId();
            if (grupoId != null) {
                grupoId = em.getReference(grupoId.getClass(), grupoId.getIdGrupo());
                estudiantes.setGrupoId(grupoId);
            }
            List<Observador> attachedObsList = new ArrayList<Observador>();
            for (Observador obsListObservadorToAttach : estudiantes.getObsList()) {
                obsListObservadorToAttach = em.getReference(obsListObservadorToAttach.getClass(), obsListObservadorToAttach.getIdObs());
                attachedObsList.add(obsListObservadorToAttach);
            }
            estudiantes.setObsList(attachedObsList);
            List<AcuEst> attachedAcuEstList = new ArrayList<AcuEst>();
            for (AcuEst acuEstListAcuEstToAttach : estudiantes.getAcuEstList()) {
                acuEstListAcuEstToAttach = em.getReference(acuEstListAcuEstToAttach.getClass(), acuEstListAcuEstToAttach.getIdAcuest());
                attachedAcuEstList.add(acuEstListAcuEstToAttach);
            }
            estudiantes.setAcuEstList(attachedAcuEstList);
            List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListAsistenciaToAttach : estudiantes.getAsistenciaList()) {
                asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsist());
                attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
            }
            estudiantes.setAsistenciaList(attachedAsistenciaList);
            List<Registro> attachedRegistroList = new ArrayList<Registro>();
            for (Registro registroListRegistroToAttach : estudiantes.getRegistroList()) {
                registroListRegistroToAttach = em.getReference(registroListRegistroToAttach.getClass(), registroListRegistroToAttach.getIdReg());
                attachedRegistroList.add(registroListRegistroToAttach);
            }
            estudiantes.setRegistroList(attachedRegistroList);
            List<Observador> attachedObservadorList = new ArrayList<Observador>();
            for (Observador observadorListObservadorToAttach : estudiantes.getObservadorList()) {
                observadorListObservadorToAttach = em.getReference(observadorListObservadorToAttach.getClass(), observadorListObservadorToAttach.getIdObs());
                attachedObservadorList.add(observadorListObservadorToAttach);
            }
            estudiantes.setObservadorList(attachedObservadorList);
            List<FaltasAlertas> attachedFaltasAlertasList = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListFaltasAlertasToAttach : estudiantes.getFaltasAlertasList()) {
                faltasAlertasListFaltasAlertasToAttach = em.getReference(faltasAlertasListFaltasAlertasToAttach.getClass(), faltasAlertasListFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasList.add(faltasAlertasListFaltasAlertasToAttach);
            }
            estudiantes.setFaltasAlertasList(attachedFaltasAlertasList);
            em.persist(estudiantes);
            if (grupoId != null) {
                grupoId.getEstudiantesList().add(estudiantes);
                grupoId = em.merge(grupoId);
            }
            for (Observador obsListObservador : estudiantes.getObsList()) {
                Estudiantes oldEstIdOfObsListObservador = obsListObservador.getEstId();
                obsListObservador.setEstId(estudiantes);
                obsListObservador = em.merge(obsListObservador);
                if (oldEstIdOfObsListObservador != null) {
                    oldEstIdOfObsListObservador.getObsList().remove(obsListObservador);
                    oldEstIdOfObsListObservador = em.merge(oldEstIdOfObsListObservador);
                }
            }
            for (AcuEst acuEstListAcuEst : estudiantes.getAcuEstList()) {
                Estudiantes oldEstIdOfAcuEstListAcuEst = acuEstListAcuEst.getEstId();
                acuEstListAcuEst.setEstId(estudiantes);
                acuEstListAcuEst = em.merge(acuEstListAcuEst);
                if (oldEstIdOfAcuEstListAcuEst != null) {
                    oldEstIdOfAcuEstListAcuEst.getAcuEstList().remove(acuEstListAcuEst);
                    oldEstIdOfAcuEstListAcuEst = em.merge(oldEstIdOfAcuEstListAcuEst);
                }
            }
            for (Asistencia asistenciaListAsistencia : estudiantes.getAsistenciaList()) {
                Estudiantes oldEstIdOfAsistenciaListAsistencia = asistenciaListAsistencia.getEstId();
                asistenciaListAsistencia.setEstId(estudiantes);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
                if (oldEstIdOfAsistenciaListAsistencia != null) {
                    oldEstIdOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
                    oldEstIdOfAsistenciaListAsistencia = em.merge(oldEstIdOfAsistenciaListAsistencia);
                }
            }
            for (Registro registroListRegistro : estudiantes.getRegistroList()) {
                Estudiantes oldEstIdOfRegistroListRegistro = registroListRegistro.getEstId();
                registroListRegistro.setEstId(estudiantes);
                registroListRegistro = em.merge(registroListRegistro);
                if (oldEstIdOfRegistroListRegistro != null) {
                    oldEstIdOfRegistroListRegistro.getRegistroList().remove(registroListRegistro);
                    oldEstIdOfRegistroListRegistro = em.merge(oldEstIdOfRegistroListRegistro);
                }
            }
            for (Observador observadorListObservador : estudiantes.getObservadorList()) {
                Estudiantes oldEstIdOfObservadorListObservador = observadorListObservador.getEstId();
                observadorListObservador.setEstId(estudiantes);
                observadorListObservador = em.merge(observadorListObservador);
                if (oldEstIdOfObservadorListObservador != null) {
                    oldEstIdOfObservadorListObservador.getObservadorList().remove(observadorListObservador);
                    oldEstIdOfObservadorListObservador = em.merge(oldEstIdOfObservadorListObservador);
                }
            }
            for (FaltasAlertas faltasAlertasListFaltasAlertas : estudiantes.getFaltasAlertasList()) {
                Estudiantes oldEstIdOfFaltasAlertasListFaltasAlertas = faltasAlertasListFaltasAlertas.getEstId();
                faltasAlertasListFaltasAlertas.setEstId(estudiantes);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
                if (oldEstIdOfFaltasAlertasListFaltasAlertas != null) {
                    oldEstIdOfFaltasAlertasListFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListFaltasAlertas);
                    oldEstIdOfFaltasAlertasListFaltasAlertas = em.merge(oldEstIdOfFaltasAlertasListFaltasAlertas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiantes estudiantes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiantes persistentEstudiantes = em.find(Estudiantes.class, estudiantes.getIdEst());
            Grupo grupoIdOld = persistentEstudiantes.getGrupoId();
            Grupo grupoIdNew = estudiantes.getGrupoId();
            List<Observador> obsListOld = persistentEstudiantes.getObsList();
            List<Observador> obsListNew = estudiantes.getObsList();
            List<AcuEst> acuEstListOld = persistentEstudiantes.getAcuEstList();
            List<AcuEst> acuEstListNew = estudiantes.getAcuEstList();
            List<Asistencia> asistenciaListOld = persistentEstudiantes.getAsistenciaList();
            List<Asistencia> asistenciaListNew = estudiantes.getAsistenciaList();
            List<Registro> registroListOld = persistentEstudiantes.getRegistroList();
            List<Registro> registroListNew = estudiantes.getRegistroList();
            List<Observador> observadorListOld = persistentEstudiantes.getObservadorList();
            List<Observador> observadorListNew = estudiantes.getObservadorList();
            List<FaltasAlertas> faltasAlertasListOld = persistentEstudiantes.getFaltasAlertasList();
            List<FaltasAlertas> faltasAlertasListNew = estudiantes.getFaltasAlertasList();
            if (grupoIdNew != null) {
                grupoIdNew = em.getReference(grupoIdNew.getClass(), grupoIdNew.getIdGrupo());
                estudiantes.setGrupoId(grupoIdNew);
            }
            List<Observador> attachedObsListNew = new ArrayList<Observador>();
            for (Observador obsListNewObservadorToAttach : obsListNew) {
                obsListNewObservadorToAttach = em.getReference(obsListNewObservadorToAttach.getClass(), obsListNewObservadorToAttach.getIdObs());
                attachedObsListNew.add(obsListNewObservadorToAttach);
            }
            obsListNew = attachedObsListNew;
            estudiantes.setObsList(obsListNew);
            List<AcuEst> attachedAcuEstListNew = new ArrayList<AcuEst>();
            for (AcuEst acuEstListNewAcuEstToAttach : acuEstListNew) {
                acuEstListNewAcuEstToAttach = em.getReference(acuEstListNewAcuEstToAttach.getClass(), acuEstListNewAcuEstToAttach.getIdAcuest());
                attachedAcuEstListNew.add(acuEstListNewAcuEstToAttach);
            }
            acuEstListNew = attachedAcuEstListNew;
            estudiantes.setAcuEstList(acuEstListNew);
            List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
            for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
                asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsist());
                attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
            }
            asistenciaListNew = attachedAsistenciaListNew;
            estudiantes.setAsistenciaList(asistenciaListNew);
            List<Registro> attachedRegistroListNew = new ArrayList<Registro>();
            for (Registro registroListNewRegistroToAttach : registroListNew) {
                registroListNewRegistroToAttach = em.getReference(registroListNewRegistroToAttach.getClass(), registroListNewRegistroToAttach.getIdReg());
                attachedRegistroListNew.add(registroListNewRegistroToAttach);
            }
            registroListNew = attachedRegistroListNew;
            estudiantes.setRegistroList(registroListNew);
            List<Observador> attachedObservadorListNew = new ArrayList<Observador>();
            for (Observador observadorListNewObservadorToAttach : observadorListNew) {
                observadorListNewObservadorToAttach = em.getReference(observadorListNewObservadorToAttach.getClass(), observadorListNewObservadorToAttach.getIdObs());
                attachedObservadorListNew.add(observadorListNewObservadorToAttach);
            }
            observadorListNew = attachedObservadorListNew;
            estudiantes.setObservadorList(observadorListNew);
            List<FaltasAlertas> attachedFaltasAlertasListNew = new ArrayList<FaltasAlertas>();
            for (FaltasAlertas faltasAlertasListNewFaltasAlertasToAttach : faltasAlertasListNew) {
                faltasAlertasListNewFaltasAlertasToAttach = em.getReference(faltasAlertasListNewFaltasAlertasToAttach.getClass(), faltasAlertasListNewFaltasAlertasToAttach.getIdAlertas());
                attachedFaltasAlertasListNew.add(faltasAlertasListNewFaltasAlertasToAttach);
            }
            faltasAlertasListNew = attachedFaltasAlertasListNew;
            estudiantes.setFaltasAlertasList(faltasAlertasListNew);
            estudiantes = em.merge(estudiantes);
            if (grupoIdOld != null && !grupoIdOld.equals(grupoIdNew)) {
                grupoIdOld.getEstudiantesList().remove(estudiantes);
                grupoIdOld = em.merge(grupoIdOld);
            }
            if (grupoIdNew != null && !grupoIdNew.equals(grupoIdOld)) {
                grupoIdNew.getEstudiantesList().add(estudiantes);
                grupoIdNew = em.merge(grupoIdNew);
            }
            for (Observador obsListOldObservador : obsListOld) {
                if (!obsListNew.contains(obsListOldObservador)) {
                    obsListOldObservador.setEstId(null);
                    obsListOldObservador = em.merge(obsListOldObservador);
                }
            }
            for (Observador obsListNewObservador : obsListNew) {
                if (!obsListOld.contains(obsListNewObservador)) {
                    Estudiantes oldEstIdOfObsListNewObservador = obsListNewObservador.getEstId();
                    obsListNewObservador.setEstId(estudiantes);
                    obsListNewObservador = em.merge(obsListNewObservador);
                    if (oldEstIdOfObsListNewObservador != null && !oldEstIdOfObsListNewObservador.equals(estudiantes)) {
                        oldEstIdOfObsListNewObservador.getObsList().remove(obsListNewObservador);
                        oldEstIdOfObsListNewObservador = em.merge(oldEstIdOfObsListNewObservador);
                    }
                }
            }
            for (AcuEst acuEstListOldAcuEst : acuEstListOld) {
                if (!acuEstListNew.contains(acuEstListOldAcuEst)) {
                    acuEstListOldAcuEst.setEstId(null);
                    acuEstListOldAcuEst = em.merge(acuEstListOldAcuEst);
                }
            }
            for (AcuEst acuEstListNewAcuEst : acuEstListNew) {
                if (!acuEstListOld.contains(acuEstListNewAcuEst)) {
                    Estudiantes oldEstIdOfAcuEstListNewAcuEst = acuEstListNewAcuEst.getEstId();
                    acuEstListNewAcuEst.setEstId(estudiantes);
                    acuEstListNewAcuEst = em.merge(acuEstListNewAcuEst);
                    if (oldEstIdOfAcuEstListNewAcuEst != null && !oldEstIdOfAcuEstListNewAcuEst.equals(estudiantes)) {
                        oldEstIdOfAcuEstListNewAcuEst.getAcuEstList().remove(acuEstListNewAcuEst);
                        oldEstIdOfAcuEstListNewAcuEst = em.merge(oldEstIdOfAcuEstListNewAcuEst);
                    }
                }
            }
            for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
                if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
                    asistenciaListOldAsistencia.setEstId(null);
                    asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
                }
            }
            for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
                if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
                    Estudiantes oldEstIdOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getEstId();
                    asistenciaListNewAsistencia.setEstId(estudiantes);
                    asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
                    if (oldEstIdOfAsistenciaListNewAsistencia != null && !oldEstIdOfAsistenciaListNewAsistencia.equals(estudiantes)) {
                        oldEstIdOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
                        oldEstIdOfAsistenciaListNewAsistencia = em.merge(oldEstIdOfAsistenciaListNewAsistencia);
                    }
                }
            }
            for (Registro registroListOldRegistro : registroListOld) {
                if (!registroListNew.contains(registroListOldRegistro)) {
                    registroListOldRegistro.setEstId(null);
                    registroListOldRegistro = em.merge(registroListOldRegistro);
                }
            }
            for (Registro registroListNewRegistro : registroListNew) {
                if (!registroListOld.contains(registroListNewRegistro)) {
                    Estudiantes oldEstIdOfRegistroListNewRegistro = registroListNewRegistro.getEstId();
                    registroListNewRegistro.setEstId(estudiantes);
                    registroListNewRegistro = em.merge(registroListNewRegistro);
                    if (oldEstIdOfRegistroListNewRegistro != null && !oldEstIdOfRegistroListNewRegistro.equals(estudiantes)) {
                        oldEstIdOfRegistroListNewRegistro.getRegistroList().remove(registroListNewRegistro);
                        oldEstIdOfRegistroListNewRegistro = em.merge(oldEstIdOfRegistroListNewRegistro);
                    }
                }
            }
            for (Observador observadorListOldObservador : observadorListOld) {
                if (!observadorListNew.contains(observadorListOldObservador)) {
                    observadorListOldObservador.setEstId(null);
                    observadorListOldObservador = em.merge(observadorListOldObservador);
                }
            }
            for (Observador observadorListNewObservador : observadorListNew) {
                if (!observadorListOld.contains(observadorListNewObservador)) {
                    Estudiantes oldEstIdOfObservadorListNewObservador = observadorListNewObservador.getEstId();
                    observadorListNewObservador.setEstId(estudiantes);
                    observadorListNewObservador = em.merge(observadorListNewObservador);
                    if (oldEstIdOfObservadorListNewObservador != null && !oldEstIdOfObservadorListNewObservador.equals(estudiantes)) {
                        oldEstIdOfObservadorListNewObservador.getObservadorList().remove(observadorListNewObservador);
                        oldEstIdOfObservadorListNewObservador = em.merge(oldEstIdOfObservadorListNewObservador);
                    }
                }
            }
            for (FaltasAlertas faltasAlertasListOldFaltasAlertas : faltasAlertasListOld) {
                if (!faltasAlertasListNew.contains(faltasAlertasListOldFaltasAlertas)) {
                    faltasAlertasListOldFaltasAlertas.setEstId(null);
                    faltasAlertasListOldFaltasAlertas = em.merge(faltasAlertasListOldFaltasAlertas);
                }
            }
            for (FaltasAlertas faltasAlertasListNewFaltasAlertas : faltasAlertasListNew) {
                if (!faltasAlertasListOld.contains(faltasAlertasListNewFaltasAlertas)) {
                    Estudiantes oldEstIdOfFaltasAlertasListNewFaltasAlertas = faltasAlertasListNewFaltasAlertas.getEstId();
                    faltasAlertasListNewFaltasAlertas.setEstId(estudiantes);
                    faltasAlertasListNewFaltasAlertas = em.merge(faltasAlertasListNewFaltasAlertas);
                    if (oldEstIdOfFaltasAlertasListNewFaltasAlertas != null && !oldEstIdOfFaltasAlertasListNewFaltasAlertas.equals(estudiantes)) {
                        oldEstIdOfFaltasAlertasListNewFaltasAlertas.getFaltasAlertasList().remove(faltasAlertasListNewFaltasAlertas);
                        oldEstIdOfFaltasAlertasListNewFaltasAlertas = em.merge(oldEstIdOfFaltasAlertasListNewFaltasAlertas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiantes.getIdEst();
                if (findEstudiantes(id) == null) {
                    throw new NonexistentEntityException("The estudiantes with id " + id + " no longer exists.");
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
            Estudiantes estudiantes;
            try {
                estudiantes = em.getReference(Estudiantes.class, id);
                estudiantes.getIdEst();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiantes with id " + id + " no longer exists.", enfe);
            }
            Grupo grupoId = estudiantes.getGrupoId();
            if (grupoId != null) {
                grupoId.getEstudiantesList().remove(estudiantes);
                grupoId = em.merge(grupoId);
            }
            List<Observador> obsList = estudiantes.getObsList();
            for (Observador obsListObservador : obsList) {
                obsListObservador.setEstId(null);
                obsListObservador = em.merge(obsListObservador);
            }
            List<AcuEst> acuEstList = estudiantes.getAcuEstList();
            for (AcuEst acuEstListAcuEst : acuEstList) {
                acuEstListAcuEst.setEstId(null);
                acuEstListAcuEst = em.merge(acuEstListAcuEst);
            }
            List<Asistencia> asistenciaList = estudiantes.getAsistenciaList();
            for (Asistencia asistenciaListAsistencia : asistenciaList) {
                asistenciaListAsistencia.setEstId(null);
                asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
            }
            List<Registro> registroList = estudiantes.getRegistroList();
            for (Registro registroListRegistro : registroList) {
                registroListRegistro.setEstId(null);
                registroListRegistro = em.merge(registroListRegistro);
            }
            List<Observador> observadorList = estudiantes.getObservadorList();
            for (Observador observadorListObservador : observadorList) {
                observadorListObservador.setEstId(null);
                observadorListObservador = em.merge(observadorListObservador);
            }
            List<FaltasAlertas> faltasAlertasList = estudiantes.getFaltasAlertasList();
            for (FaltasAlertas faltasAlertasListFaltasAlertas : faltasAlertasList) {
                faltasAlertasListFaltasAlertas.setEstId(null);
                faltasAlertasListFaltasAlertas = em.merge(faltasAlertasListFaltasAlertas);
            }
            em.remove(estudiantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiantes> findEstudiantesEntities() {
        return findEstudiantesEntities(true, -1, -1);
    }

    public List<Estudiantes> findEstudiantesEntities(int maxResults, int firstResult) {
        return findEstudiantesEntities(false, maxResults, firstResult);
    }

    private List<Estudiantes> findEstudiantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiantes.class));
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

    public Estudiantes findEstudiantes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudiantesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiantes> rt = cq.from(Estudiantes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Estudiantes findEstudiantesDoc(Long documento) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT e FROM Estudiantes e WHERE e.docEst =:documento", Estudiantes.class);
            q.setParameter("documento", documento);
            Estudiantes estudiante = (Estudiantes) q.getSingleResult();
            em.getTransaction().commit();
            return estudiante;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

}
