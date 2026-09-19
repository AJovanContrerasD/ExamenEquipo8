package helper;

import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Profesor;

import java.io.Serializable;

public class regProfesorHelper implements Serializable{
    public void registrar(Profesor profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesor().guardarProfesor(profesor);
    }
}
