package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAsignacion;
import mx.desarrollo.entity.Asignacion;

import java.util.List;

public class FacadeAsignacion {

    private final DelegateAsignacion delegateAsignacion;

    public FacadeAsignacion() {
        this.delegateAsignacion = new DelegateAsignacion();
    }

    public void guardarAsignacion(Asignacion asignacion){
        delegateAsignacion.saveAsignacion(asignacion);
    }

    public void actualizarAsignacion(Asignacion asignacion){
        delegateAsignacion.updateAsignacion(asignacion);
    }

    public void eliminarAsignacion(Asignacion asignacion){
        delegateAsignacion.deleteAsignacion(asignacion);
    }

    public List<Asignacion> findAll(){
        return delegateAsignacion.findAll();
    }

}
