package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }

    public void guardarProfesor(Profesor profesor){
        delegateProfesor.saveProfesor(profesor);
    }

    public void actualizarProfesor(Profesor profesor){
        delegateProfesor.updateProfesor(profesor);
    }

    public void eliminarProfesor(Profesor profesor){
        delegateProfesor.deleteProfesor(profesor);
    }

    public List<Profesor> findAll(){
        return delegateProfesor.findAll();
    }

}
