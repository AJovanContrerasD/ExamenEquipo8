package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.dao.AsignacionDAO;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;
import jakarta.persistence.EntityManager;

public class DelegateAsignacion {

    public void saveAsignacion(Asignacion asignacion) {

        ServiceLocator.getInstanceAsignacionDAO().save(asignacion);
    }

    public List<Asignacion> getTodasAsignaciones(){
        return ServiceLocator.getInstanceAsignacionDAO().findAll();
    }


    public List<Asignacion> getAsignacionesPorProf(Integer idProfesor){
        return ServiceLocator.getInstanceAsignacionDAO().findFromWhere("idprofesor", "id",String.valueOf(idProfesor));
    }

    public void borrarAsignacion (Integer idAsignacion){
        AsignacionDAO dao = ServiceLocator.getInstanceAsignacionDAO();

        Asignacion asignacion = dao.find(idAsignacion).orElse(null);
        if(asignacion!=null){
            dao.delete(asignacion);
        }

    }
}