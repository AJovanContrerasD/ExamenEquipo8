package ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Profesor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("profesorBean")
@ViewScoped
public class ProfesorBean implements Serializable {

    private List<Profesor> profesores;
    private String filtroBusqueda;
    private Profesor profesorSeleccionado;

    @PostConstruct
    public void init(){
        profesores = ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerTodos();
    }

    public void nuevoProfesor(){

    }

    public void buscar(){

    }

    public void guardarEdicion(){
        ServiceFacadeLocator.getInstanceFacadeProfesor().actualizar(profesorSeleccionado);
        profesorSeleccionado = null;
        profesores = ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerTodos();
    }

    public void cancelarEdicion(){
        profesorSeleccionado = null;
    }

    public void elim(Profesor profesor){
        ServiceFacadeLocator.getInstanceFacadeProfesor().eliminar(profesor);
        profesores = ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerTodos();
    }

    public List<Profesor> getListaProfesores(){
        if(filtroBusqueda == null || filtroBusqueda.trim().isEmpty()){
            return profesores;
        }
        List<Profesor> filtrados = new ArrayList<>();
        String criterio = filtroBusqueda.toLowerCase();
        for(Profesor prof : profesores){
            if(prof.getNombre().toLowerCase().contains(criterio)
                    || prof.getAppaterno().toLowerCase().contains(criterio)
                    || prof.getApmaterno().toLowerCase().contains(criterio)
                    || prof.getRfc().toLowerCase().contains(criterio)){
                filtrados.add(prof);
            }
        }
        return filtrados;
    }

    public String getFiltroBusqueda() {
        return filtroBusqueda;
    }

    public void setFiltroBusqueda(String filtroBusqueda) {
        this.filtroBusqueda = filtroBusqueda;
    }

    public Profesor getProfesorSeleccionado() {
        return profesorSeleccionado;
    }

    public void setProfesorSeleccionado(Profesor profesorSeleccionado) {
        this.profesorSeleccionado = profesorSeleccionado;
    }

}
