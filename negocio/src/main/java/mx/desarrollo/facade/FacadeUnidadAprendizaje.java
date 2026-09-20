package mx.desarrollo.facade;



import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarrollo.entity.UnidadAprendizaje;

import java.util.List;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegateUnidad;

    public FacadeUnidadAprendizaje() {
        this.delegateUnidad= new DelegateUnidadAprendizaje();
    }

    public List<UnidadAprendizaje> obtenerTodasUnidades() {
        return delegateUnidad.getTodasUnidades();
    }
}
