package mx.desarrollo.delegate;

import mx.desarrollo.entity.Profesor;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateProfesor {
    public void saveProfesor(Profesor profesor){
        ServiceLocator.getInstanceProfesorDAO().save(profesor);
    }

    public void updateProfesor(Profesor profesor){
        ServiceLocator.getInstanceProfesorDAO().update(profesor);
    }

    public void deleteProfesor(Profesor profesor){
        ServiceLocator.getInstanceProfesorDAO().delete(profesor);
    }

    public List<Profesor> findAll(){
        return ServiceLocator.getInstanceProfesorDAO().findAll();
    }

}
