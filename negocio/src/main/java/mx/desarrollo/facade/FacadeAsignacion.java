package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacion;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class FacadeAsignacion {

    private final DelegateAsignacion delegateAsignacion;

    public FacadeAsignacion() {
        this.delegateAsignacion = new DelegateAsignacion();
    }

    public void guardarAsignacion(Asignacion asignacion){
        delegateAsignacion.saveAsignacion(asignacion);
    }

    public void borrarAsignacion (Integer IdAsignacion) {
        delegateAsignacion.borrarAsignacion(IdAsignacion);
    }

    public List<Asignacion> obtenerTodasAsignaciones() {
        return delegateAsignacion.getTodasAsignaciones();
    }

    public List<Asignacion> obtenerAsignacionesProf(Integer idProfesor){ return delegateAsignacion.getAsignacionesPorProf(idProfesor);
    }


}