package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAdministrador;
import mx.desarrollo.entity.Administrador;
import mx.desarrollo.persistence.integration.ServiceLocator;

import java.util.List;

public class FacadeAdministrador {

    private final DelegateAdministrador delegateAdministrador;

    public Administrador login(String password, String usuario){
        Administrador administrador = new Administrador();
        List<Administrador> administradores = ServiceLocator.getInstanceAdministradorDAO().findAll();

        for(Administrador ad:administradores){
            if(ad.getContrasena().equals(password) && ad.getUsuario().equalsIgnoreCase(usuario)){
                administrador = ad;
            }
        }
        return administrador;
    }
    public FacadeAdministrador() {
        this.delegateAdministrador = new DelegateAdministrador();
    }

    public void guardarAdministrador(Administrador administrador){
        delegateAdministrador.saveAdministrador(administrador);
    }


}