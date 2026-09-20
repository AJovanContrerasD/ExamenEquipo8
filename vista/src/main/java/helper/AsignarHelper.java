package helper;
import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.delegate.DelegateProfesor;

import java.io.Serializable;
import java.util.List;

public class AsignarHelper {

    public List<Profesor> obtenerProfesores(){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerTodosProfesores();
    }

    public void registrarAsignacion(Asignacion asignacion){
        ServiceFacadeLocator.getInstanceFacadeAsignacion().guardarAsignacion(asignacion);
    }

    public List<UnidadAprendizaje> obtenerUnidades (){

        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().obtenerTodasUnidades();

    }
}
