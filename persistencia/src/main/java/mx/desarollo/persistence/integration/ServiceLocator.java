package mx.desarrollo.persistence.integration;

import jakarta.persistence.EntityManager;

import mx.desarrollo.persistence.dao.*;
import mx.desarrollo.persistence.persistence.HibernateUtil;


public class ServiceLocator {

    private static AdministradorDAO administradorDAO;
    private static ProfesorDAO profesorDAO;
    private static UnidadAprendizajeDAO unidadAprendizajeDAO;
    private static AsignacionDAO asignacionDAO;
    private static AlumnoDAO alumnoDAO;
    private static UsuarioDAO usuarioDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    public static AdministradorDAO getInstanceAdministradorDAO(){
        if(administradorDAO == null){
            administradorDAO = new AdministradorDAO(getEntityManager());
            return administradorDAO;
        } else{
            return administradorDAO;
        }
    }

    public static ProfesorDAO getInstanceProfesorDAO(){
        if(profesorDAO == null){
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;
        } else{
            return profesorDAO;
        }
    }

    public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO(){
        if(unidadAprendizajeDAO == null){
            unidadAprendizajeDAO = new UnidadAprendizajeDAO(getEntityManager());
            return unidadAprendizajeDAO;
        } else{
            return unidadAprendizajeDAO;
        }
    }

    public static AsignacionDAO getInstanceAsignacionDAO(){
        if(asignacionDAO == null){
            asignacionDAO = new AsignacionDAO(getEntityManager());
            return asignacionDAO;
        } else{
            return asignacionDAO;
        }
    }

    public static AlumnoDAO getInstanceAlumnoDAO() {
        if(alumnoDAO == null){
            alumnoDAO = new AlumnoDAO(getEntityManager());
            return alumnoDAO;
        } else{
            return alumnoDAO;
        }

    }

    public static UsuarioDAO getInstanceUsuarioDAO() {
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else{
            return usuarioDAO;
        }
    }
}
