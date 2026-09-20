package mx.desarollo.delegate;

import mx.desarollo.persistence.integration.ServiceLocator;
import mx.desarrollo.entity.Alumno;

public class DelegateAlumno {
    public void saveAlumno(Alumno alumno){
        ServiceLocator.getInstanceAlumnoDAO().save(alumno);
    }

}