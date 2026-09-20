/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.desarollo.persistence.integration;


import jakarta.persistence.EntityManager;
import mx.desarollo.persistence.dao.AlumnoDAO;
import mx.desarollo.persistence.dao.ProfesorDAO;
import mx.desarollo.persistence.dao.UsuarioDAO;
import mx.desarollo.persistence.persistence.HibernateUtil;

/**
 *
 * @author total
 */
public class ServiceLocator {

    private static AlumnoDAO alumnoDAO;
    private static UsuarioDAO usuarioDAO;
    private static ProfesorDAO profesorDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    /**
     * se crea la instancia para alumno DAO si esta no existe
     */
    public static AlumnoDAO getInstanceAlumnoDAO(){
        if(alumnoDAO == null){
            alumnoDAO = new AlumnoDAO(getEntityManager());
            return alumnoDAO;
        } else{
            return alumnoDAO;
        }
    }
    /**
     * se crea la instancia de usuarioDAO si esta no existe
     */
    public static UsuarioDAO getInstanceUsuarioDAO(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else{
            return usuarioDAO;
        }
    }

    public static ProfesorDAO getInstanceProfesorDAO(){
        if(profesorDAO == null){
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;

        }else {
            return profesorDAO;
        }
    }
    
}
