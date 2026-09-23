package ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Asignacion;
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
        profesores = ServiceFacadeLocator.getInstanceFacadeProfesor().findAll();
    }

    public void nuevoProfesor(){

    }

    public void buscar(){

    }

    public void guardarEdicion(){
        ServiceFacadeLocator.getInstanceFacadeProfesor().actualizarProfesor(profesorSeleccionado);
        profesorSeleccionado = null;
        profesores = ServiceFacadeLocator.getInstanceFacadeProfesor().findAll();
    }

    public void cancelarEdicion(){
        profesorSeleccionado = null;
    }

    public void elim(Profesor profesor){
        List<Asignacion> asignacionesDelProfesor =
                ServiceFacadeLocator.getInstanceFacadeAsignacion().obtenerAsignacionesProf(profesor.getId());

        if(asignacionesDelProfesor != null && !asignacionesDelProfesor.isEmpty()){
            mensajeError("No se puede eliminar al profesor " + profesor.getNombre() + " " + profesor.getAppaterno()
                    + " porque tiene " + asignacionesDelProfesor.size()
                    + " asignacion(es) registrada(s). Elimine o reasigne primero esas asignaciones.");
            return;
        }

        try{
            ServiceFacadeLocator.getInstanceFacadeProfesor().eliminarProfesor(profesor);
            profesores = ServiceFacadeLocator.getInstanceFacadeProfesor().findAll();
        } catch (Exception ex){
            mensajeError("No se pudo eliminar al profesor. Verifique que no tenga registros relacionados.");
        }
    }

    private void mensajeError(String textoMensaje){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", textoMensaje));
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
