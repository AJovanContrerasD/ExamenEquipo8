package mx.desarrollo.delegate;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateUnidadAprendizaje {
    public void saveUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().save(unidadAprendizaje);
    }

    public void updateUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().update(unidadAprendizaje);
    }

    public void deleteUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        ServiceLocator.getInstanceUnidadAprendizajeDAO().delete(unidadAprendizaje);
    }

    public List<UnidadAprendizaje> findAll(){
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();
    }

}
