package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

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
}