package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateProfesor;
import mx.desarrollo.entity.Profesor;

import java.util.List;

public class FacadeProfesor {

    private final DelegateProfesor delegateProfesor;

    public FacadeProfesor() {
        this.delegateProfesor= new DelegateProfesor();
    }

    public List<Profesor> obtenerTodosProfesores() {
        return delegateProfesor.getTodosProfesores();
    }
}
