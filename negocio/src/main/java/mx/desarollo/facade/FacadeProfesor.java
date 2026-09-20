package mx.desarollo.facade;

import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor = new DelegateProfesor();
    }

    public List<Profesor> obtenerTodos(){
        return delegateProfesor.obtenerTodos();
    }

    public void actualizar(Profesor profesor){
        delegateProfesor.actualizar(profesor);
    }

    public void eliminar(Profesor profesor){
        delegateProfesor.eliminar(profesor);
    }

}
