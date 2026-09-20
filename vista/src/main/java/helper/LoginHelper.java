package helper;


import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.desarrollo.entity.Administrador;

import java.io.Serializable;

public class LoginHelper implements Serializable {


    /**
     * Metodo para hacer login llamara a la instancia de administradorFacade
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    public Administrador Login(String usuario, String contrasena){
        return ServiceFacadeLocator.getInstanceFacadeAdministrador().login(contrasena, usuario);
    }



}