/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import helper.regProfesorHelper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mx.desarrollo.entity.Administrador;
import mx.desarrollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.io.IOException;
import java.io.Serializable;

@Named("regProfesorUI")
@SessionScoped
public class regProfesorBeanUI implements Serializable{
    private regProfesorHelper regProfesorHelper;
    private Profesor profesor;

    @Inject
    private LoginBeanUI loginUI;

    public regProfesorBeanUI() {
        regProfesorHelper = new regProfesorHelper();
    }

    /**
     * Metodo postconstructor todo lo que este dentro de este metodo
     * sera la primero que haga cuando cargue la pagina
     */
    @PostConstruct
    public void init(){
        profesor = new Profesor();
    }

    public void registrar() throws IOException{
        try{
            if(profesor.getRfc() != null){
                profesor.setRfc(profesor.getRfc().trim().toUpperCase());
            }

            if(profesor.getRfc() == null || profesor.getRfc().isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "ERROR", "Favor de ingresar el RFC del profesor"));
                return;
            }

            Profesor existente = ServiceFacadeLocator.getInstanceFacadeProfesor().obtenerProfesorPorRfc(profesor.getRfc());
            if(existente != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "ERROR", "Ya existe un profesor registrado con el RFC " + profesor.getRfc()
                        + " (" + existente.getNombre() + " " + existente.getAppaterno() + ")"));
                return;
            }

            Administrador administrador = loginUI.getUsuario();
            profesor.setIdAdministrador(administrador);

            regProfesorHelper.registrar(profesor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Profesor Registrado", "El profesor "+profesor.getNombre()+" "
                    +profesor.getAppaterno()+" "+profesor.getApmaterno()+" fue registrado correctamente!"));
            profesor = new Profesor();
        }catch(Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "ERROR", "El profesor no se pudo registrar"));
        }

    }


    /* getters y setters*/
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }












}
