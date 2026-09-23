/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import helper.UnidadHelper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.desarrollo.entity.Administrador;
import mx.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.IOException;
import java.io.Serializable;

@Named("regUnidadUI")
@SessionScoped
public class regUnidadBeanUI implements Serializable{
    private UnidadHelper regUnidadHelper;
    private UnidadAprendizaje unidad;

    @Inject
    private LoginBeanUI loginUI;

    public regUnidadBeanUI() {
        regUnidadHelper = new UnidadHelper();
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo
     * sera la primero que haga cuando cargue la pagina
     */

    @PostConstruct
    public void init(){
        unidad = new UnidadAprendizaje();
    }

    public void registrar() throws IOException{
        try{
            if(unidad.getNombre() != null){
                unidad.setNombre(unidad.getNombre().trim());
            }

            if(unidad.getNombre() == null || unidad.getNombre().isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "ERROR", "Favor de ingresar el nombre de la unidad de aprendizaje"));
                return;
            }

            UnidadAprendizaje existente = ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().obtenerUnidadPorNombre(unidad.getNombre());
            if(existente != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "ERROR", "Ya existe una unidad de aprendizaje registrada con el nombre '" + unidad.getNombre() + "'"));
                return;
            }

            Administrador administrador = loginUI.getUsuario();
            unidad.setIdAdministrador(administrador);

            regUnidadHelper.registrar(unidad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Unidad de aprendizaje registrada", "La unidad '"+unidad.getNombre()+"' fue registrada correctamente!"));
            unidad = new UnidadAprendizaje();
        }catch(Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "ERROR", "La unidad de aprendizaje no se pudo registrar"));
        }

    }


    /* getters y setters*/
    public UnidadAprendizaje getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadAprendizaje unidad) {
        this.unidad = unidad;
    }

}