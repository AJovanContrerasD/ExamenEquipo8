package mx.desarrollo.delegate;

import mx.desarrollo.entity.Asignacion;
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

    public List<Asignacion> findAll(){
        return ServiceLocator.getInstanceAsignacionDAO().findAll();
    }

}
