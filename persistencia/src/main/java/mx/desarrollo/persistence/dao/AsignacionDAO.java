package mx.desarrollo.persistence.dao;

import jakarta.persistence.EntityManager;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.persistence.persistence.AbstractDAO;


import java.util.List;


public class AsignacionDAO extends AbstractDAO<Asignacion> {
    private final EntityManager entityManager;

    public AsignacionDAO(EntityManager em) {
        super(Asignacion.class);
        this.entityManager = em;
    }




    public List<Asignacion> obtenerTodos(){
        return entityManager
                .createQuery("SELECT s FROM Asignacion s", Asignacion.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
