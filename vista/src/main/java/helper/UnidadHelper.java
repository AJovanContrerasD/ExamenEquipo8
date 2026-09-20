package helper;

import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;

public class UnidadHelper implements Serializable {
    public void registrar(UnidadAprendizaje unidad){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().guardarUnidadAprendizaje(unidad);
    }
}
