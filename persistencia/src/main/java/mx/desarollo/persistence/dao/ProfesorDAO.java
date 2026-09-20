package mx.desarollo.persistence.dao;

import jakarta.persistence.EntityManager;
import mx.desarollo.persistence.persistence.AbstractDAO;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class ProfesorDAO extends AbstractDAO<Profesor> {
    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em) {
        super(Profesor.class);
        this.entityManager = em;
    }

    public List<Profesor> obtenerTodos() {
        return entityManager
                .createQuery("SELECT p FROM Profesor p", Profesor.class)
                .getResultList();
    }

    public void actualizar(Profesor profesor) {
        entityManager.getTransaction().begin();
        entityManager.merge(profesor);
        entityManager.getTransaction().commit();
    }

    public void eliminar(Profesor profesor) {
        entityManager.getTransaction().begin();
        Profesor p = entityManager.find(Profesor.class, profesor.getIdprofesor());
        if (p != null) {
            entityManager.remove(p);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}