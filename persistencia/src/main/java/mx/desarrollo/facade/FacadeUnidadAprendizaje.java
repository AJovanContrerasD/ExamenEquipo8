package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateUnidadAprendizaje;
import mx.desarrollo.entity.UnidadAprendizaje;

import java.util.List;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegateUnidadAprendizaje;

    public FacadeUnidadAprendizaje() {
        this.delegateUnidadAprendizaje = new DelegateUnidadAprendizaje();
    }

    public void guardarUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        delegateUnidadAprendizaje.saveUnidadAprendizaje(unidadAprendizaje);
    }

    public void actualizarUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        delegateUnidadAprendizaje.updateUnidadAprendizaje(unidadAprendizaje);
    }

    public void eliminarUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje){
        delegateUnidadAprendizaje.deleteUnidadAprendizaje(unidadAprendizaje);
    }

    public List<UnidadAprendizaje> findAll(){
        return delegateUnidadAprendizaje.findAll();
    }

}
