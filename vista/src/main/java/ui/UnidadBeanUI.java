/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import mx.desarrollo.entity.Asignacion;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("unidadUI")
@ViewScoped
public class UnidadBeanUI implements Serializable{
    private List<UnidadAprendizaje> unidades;
    private String filtroBusqueda;
    private UnidadAprendizaje unidadSeleccionada;
    /**
     * Metodo postconstructor todo lo que este dentro de este metodo
     * sera la primero que haga cuando cargue la pagina
     */

    @PostConstruct
    public void init(){
        unidades = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findAll();
    }

    public void nuevaUnidad(){

    }

    public void buscar(){

    }

    public void guardarEdicion(){
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().actualizarUnidadAprendizaje(unidadSeleccionada);
        unidadSeleccionada = null;
        unidades = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findAll();
    }

    public void cancelarEdicion(){
        unidadSeleccionada = null;
    }

    public void eliminar(UnidadAprendizaje unidad){
        List<Asignacion> asignacionesDeLaUnidad =
                ServiceFacadeLocator.getInstanceFacadeAsignacion().obtenerAsignacionesPorUnidad(unidad.getId());

        if(asignacionesDeLaUnidad != null && !asignacionesDeLaUnidad.isEmpty()){
            mensajeError("No se puede eliminar la unidad '" + unidad.getNombre() + "' porque tiene "
                    + asignacionesDeLaUnidad.size() + " asignacion(es) registrada(s). Elimine o reasigne primero esas asignaciones.");
            return;
        }

        try{
            ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().eliminarUnidadAprendizaje(unidad);
            unidades = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().findAll();
        } catch (Exception ex){
            mensajeError("No se pudo eliminar la unidad. Verifique que no tenga registros relacionados.");
        }
    }

    private void mensajeError(String textoMensaje){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", textoMensaje));
    }

    public List<UnidadAprendizaje> getListaUnidades(){
        if(filtroBusqueda == null || filtroBusqueda.trim().isEmpty()){
            return unidades;
        }
        List<UnidadAprendizaje> filtradas = new ArrayList<>();
        String criterio = filtroBusqueda.toLowerCase();
        for(UnidadAprendizaje u : unidades){
            if(u.getNombre().toLowerCase().contains(criterio)){
                filtradas.add(u);
            }
        }
        return filtradas;
    }

    public String getFiltroBusqueda() {
        return filtroBusqueda;
    }

    public void setFiltroBusqueda(String filtroBusqueda) {
        this.filtroBusqueda = filtroBusqueda;
    }

    /* getters y setters*/
    public UnidadAprendizaje getUnidadSeleccionada() {
        return unidadSeleccionada;
    }

    public void setUnidadSeleccionada(UnidadAprendizaje unidad) {
        this.unidadSeleccionada = unidad;
    }

}