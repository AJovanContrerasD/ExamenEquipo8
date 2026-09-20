package mx.desarrollo.delegate;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class DelegateUnidadAprendizaje {

    public List<UnidadAprendizaje> getTodasUnidades(){
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();

    }
    public void saveUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){

            ServiceLocator.getInstanceUnidadAprendizajeDAO().save(unidadAprendizaje);

    }
}
