package org.sawake.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.sawake.entity.Grados;
import org.sawake.entity.Salones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.sawake.controller.exceptions.NonexistentEntityException;
import org.sawake.entity.Estudiantes;
import org.sawake.entity.Grupo;

public class GrupoJpaController implements Serializable {

    public GrupoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAwakePU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) {
        if (grupo.getSalonesList() == null) {
            grupo.setSalonesList(new ArrayList<Salones>());
        }
        if (grupo.getEstudiantesList() == null) {
            grupo.setEstudiantesList(new ArrayList<Estudiantes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grados gradoId = grupo.getGradoId();
            if (gradoId != null) {
                gradoId = em.getReference(gradoId.getClass(), gradoId.getIdGrados());
                grupo.setGradoId(gradoId);
            }
            List<Salones> attachedSalonesList = new ArrayList<Salones>();
            for (Salones salonesListSalonesToAttach : grupo.getSalonesList()) {
                salonesListSalonesToAttach = em.getReference(salonesListSalonesToAttach.getClass(), salonesListSalonesToAttach.getIdSalon());
                attachedSalonesList.add(salonesListSalonesToAttach);
            }
            grupo.setSalonesList(attachedSalonesList);
            List<Estudiantes> attachedEstudiantesList = new ArrayList<Estudiantes>();
            for (Estudiantes estudiantesListEstudiantesToAttach : grupo.getEstudiantesList()) {
                estudiantesListEstudiantesToAttach = em.getReference(estudiantesListEstudiantesToAttach.getClass(), estudiantesListEstudiantesToAttach.getIdEst());
                attachedEstudiantesList.add(estudiantesListEstudiantesToAttach);
            }
            grupo.setEstudiantesList(attachedEstudiantesList);
            em.persist(grupo);
            if (gradoId != null) {
                gradoId.getGrupoList().add(grupo);
                gradoId = em.merge(gradoId);
            }
            for (Salones salonesListSalones : grupo.getSalonesList()) {
                Grupo oldGrupoIdOfSalonesListSalones = salonesListSalones.getGrupoId();
                salonesListSalones.setGrupoId(grupo);
                salonesListSalones = em.merge(salonesListSalones);
                if (oldGrupoIdOfSalonesListSalones != null) {
                    oldGrupoIdOfSalonesListSalones.getSalonesList().remove(salonesListSalones);
                    oldGrupoIdOfSalonesListSalones = em.merge(oldGrupoIdOfSalonesListSalones);
                }
            }
            for (Estudiantes estudiantesListEstudiantes : grupo.getEstudiantesList()) {
                Grupo oldGrupoIdOfEstudiantesListEstudiantes = estudiantesListEstudiantes.getGrupoId();
                estudiantesListEstudiantes.setGrupoId(grupo);
                estudiantesListEstudiantes = em.merge(estudiantesListEstudiantes);
                if (oldGrupoIdOfEstudiantesListEstudiantes != null) {
                    oldGrupoIdOfEstudiantesListEstudiantes.getEstudiantesList().remove(estudiantesListEstudiantes);
                    oldGrupoIdOfEstudiantesListEstudiantes = em.merge(oldGrupoIdOfEstudiantesListEstudiantes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getIdGrupo());
            Grados gradoIdOld = persistentGrupo.getGradoId();
            Grados gradoIdNew = grupo.getGradoId();
            List<Salones> salonesListOld = persistentGrupo.getSalonesList();
            List<Salones> salonesListNew = grupo.getSalonesList();
            List<Estudiantes> estudiantesListOld = persistentGrupo.getEstudiantesList();
            List<Estudiantes> estudiantesListNew = grupo.getEstudiantesList();
            if (gradoIdNew != null) {
                gradoIdNew = em.getReference(gradoIdNew.getClass(), gradoIdNew.getIdGrados());
                grupo.setGradoId(gradoIdNew);
            }
            List<Salones> attachedSalonesListNew = new ArrayList<Salones>();
            for (Salones salonesListNewSalonesToAttach : salonesListNew) {
                salonesListNewSalonesToAttach = em.getReference(salonesListNewSalonesToAttach.getClass(), salonesListNewSalonesToAttach.getIdSalon());
                attachedSalonesListNew.add(salonesListNewSalonesToAttach);
            }
            salonesListNew = attachedSalonesListNew;
            grupo.setSalonesList(salonesListNew);
            List<Estudiantes> attachedEstudiantesListNew = new ArrayList<Estudiantes>();
            for (Estudiantes estudiantesListNewEstudiantesToAttach : estudiantesListNew) {
                estudiantesListNewEstudiantesToAttach = em.getReference(estudiantesListNewEstudiantesToAttach.getClass(), estudiantesListNewEstudiantesToAttach.getIdEst());
                attachedEstudiantesListNew.add(estudiantesListNewEstudiantesToAttach);
            }
            estudiantesListNew = attachedEstudiantesListNew;
            grupo.setEstudiantesList(estudiantesListNew);
            grupo = em.merge(grupo);
            if (gradoIdOld != null && !gradoIdOld.equals(gradoIdNew)) {
                gradoIdOld.getGrupoList().remove(grupo);
                gradoIdOld = em.merge(gradoIdOld);
            }
            if (gradoIdNew != null && !gradoIdNew.equals(gradoIdOld)) {
                gradoIdNew.getGrupoList().add(grupo);
                gradoIdNew = em.merge(gradoIdNew);
            }
            for (Salones salonesListOldSalones : salonesListOld) {
                if (!salonesListNew.contains(salonesListOldSalones)) {
                    salonesListOldSalones.setGrupoId(null);
                    salonesListOldSalones = em.merge(salonesListOldSalones);
                }
            }
            for (Salones salonesListNewSalones : salonesListNew) {
                if (!salonesListOld.contains(salonesListNewSalones)) {
                    Grupo oldGrupoIdOfSalonesListNewSalones = salonesListNewSalones.getGrupoId();
                    salonesListNewSalones.setGrupoId(grupo);
                    salonesListNewSalones = em.merge(salonesListNewSalones);
                    if (oldGrupoIdOfSalonesListNewSalones != null && !oldGrupoIdOfSalonesListNewSalones.equals(grupo)) {
                        oldGrupoIdOfSalonesListNewSalones.getSalonesList().remove(salonesListNewSalones);
                        oldGrupoIdOfSalonesListNewSalones = em.merge(oldGrupoIdOfSalonesListNewSalones);
                    }
                }
            }
            for (Estudiantes estudiantesListOldEstudiantes : estudiantesListOld) {
                if (!estudiantesListNew.contains(estudiantesListOldEstudiantes)) {
                    estudiantesListOldEstudiantes.setGrupoId(null);
                    estudiantesListOldEstudiantes = em.merge(estudiantesListOldEstudiantes);
                }
            }
            for (Estudiantes estudiantesListNewEstudiantes : estudiantesListNew) {
                if (!estudiantesListOld.contains(estudiantesListNewEstudiantes)) {
                    Grupo oldGrupoIdOfEstudiantesListNewEstudiantes = estudiantesListNewEstudiantes.getGrupoId();
                    estudiantesListNewEstudiantes.setGrupoId(grupo);
                    estudiantesListNewEstudiantes = em.merge(estudiantesListNewEstudiantes);
                    if (oldGrupoIdOfEstudiantesListNewEstudiantes != null && !oldGrupoIdOfEstudiantesListNewEstudiantes.equals(grupo)) {
                        oldGrupoIdOfEstudiantesListNewEstudiantes.getEstudiantesList().remove(estudiantesListNewEstudiantes);
                        oldGrupoIdOfEstudiantesListNewEstudiantes = em.merge(oldGrupoIdOfEstudiantesListNewEstudiantes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupo.getIdGrupo();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
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
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getIdGrupo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            Grados gradoId = grupo.getGradoId();
            if (gradoId != null) {
                gradoId.getGrupoList().remove(grupo);
                gradoId = em.merge(gradoId);
            }
            List<Salones> salonesList = grupo.getSalonesList();
            for (Salones salonesListSalones : salonesList) {
                salonesListSalones.setGrupoId(null);
                salonesListSalones = em.merge(salonesListSalones);
            }
            List<Estudiantes> estudiantesList = grupo.getEstudiantesList();
            for (Estudiantes estudiantesListEstudiantes : estudiantesList) {
                estudiantesListEstudiantes.setGrupoId(null);
                estudiantesListEstudiantes = em.merge(estudiantesListEstudiantes);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
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

    public Grupo findGrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
