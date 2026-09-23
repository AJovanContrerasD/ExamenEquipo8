package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.persistence.dao.AsignacionDAO;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateAsignacion {
    public void saveAsignacion(Asignacion asignacion){

        ServiceLocator.getInstanceAsignacionDAO().save(asignacion);
    }

    public void updateAsignacion(Asignacion asignacion){
        ServiceLocator.getInstanceAsignacionDAO().update(asignacion);
    }

    public void deleteAsignacion(Asignacion asignacion){
        ServiceLocator.getInstanceAsignacionDAO().delete(asignacion);
    }

    public List<Asignacion> getTodasAsignaciones(){
        return ServiceLocator.getInstanceAsignacionDAO().findAll();
    }

    public void borrarAsignacion (Integer idAsignacion){
        AsignacionDAO dao = ServiceLocator.getInstanceAsignacionDAO();

        Asignacion asignacion = dao.find(idAsignacion).orElse(null);
        if(asignacion!=null){
            dao.delete(asignacion);
        }

    }

    public List<Asignacion> getAsignacionesPorProf(Integer idProfesor){
        return ServiceLocator.getInstanceAsignacionDAO().findFromWhere("idProfesor", "id",String.valueOf(idProfesor));
    }

    public List<Asignacion> getAsignacionesPorUnidad(Integer idUnidad){
        return ServiceLocator.getInstanceAsignacionDAO().findFromWhere("idUnidad", "id",String.valueOf(idUnidad));
    }



    public List<Asignacion> findAll(){
        return ServiceLocator.getInstanceAsignacionDAO().findAll();
    }

}
