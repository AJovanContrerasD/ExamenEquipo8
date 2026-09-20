package helper;
import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.entity.UnidadAprendizaje;

import java.util.List;

public class AsignarHelper {

    public List<Profesor> obtenerProfesores(){
        return ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerTodosProfesores();
    }

    public void registrarAsignacion(Asignacion asignacion){
        ServiceFacadeLocator.getInstanceFacadeAsignacion().guardarAsignacion(asignacion);
    }

    public List<Asignacion> obtenerAsignacionesProf(Integer idProfesor){
    return ServiceFacadeLocator.getInstanceFacadeAsignacion().obtenerAsignacionesProf(idProfesor);

    }

    public List<UnidadAprendizaje> obtenerUnidades (){

        return ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().obtenerTodasUnidades();

    }
}
