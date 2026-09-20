package mx.desarrollo.delegate;


import mx.desarollo.persistence.integration.ServiceLocator;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class DelegateProfesor {
    public List<Profesor> obtenerTodos(){
        return ServiceLocator.getInstanceProfesorDAO().obtenerTodos();
    }

    public void actualizar(Profesor profesor){
        ServiceLocator.getInstanceProfesorDAO().actualizar(profesor);
    }

    public void eliminar(Profesor profesor){
        ServiceLocator.getInstanceProfesorDAO().eliminar(profesor);
    }

}
