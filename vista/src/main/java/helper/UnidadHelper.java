package helper;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.io.Serializable;
import java.util.List;

public class UnidadHelper implements Serializable {
    public List<UnidadAprendizaje> obtenerTodas(){
        return  ServiceLocator.getInstanceUnidadAprendizajeDAO().findAll();
    }

    public void registrar(UnidadAprendizaje unidad){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().guardarUnidadAprendizaje(unidad);
    }

    public void actualizar(UnidadAprendizaje unidad){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().actualizarUnidadAprendizaje(unidad);
    }

    public void eliminar(UnidadAprendizaje unidad){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminarUnidadAprendizaje(unidad);
    }
}
