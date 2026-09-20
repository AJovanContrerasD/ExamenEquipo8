package mx.desarollo.delegate;

import mx.desarollo.persistence.integration.ServiceLocator;
import mx.desarrollo.entity.Usuario;

import java.util.List;

public class DelegateUsuario {
    public Usuario login(String password, String correo){
        Usuario usuario = new Usuario();
        List<Usuario> usuarios = ServiceLocator.getInstanceUsuarioDAO().findAll();

        for(Usuario us:usuarios){
            if(us.getContrasena().equalsIgnoreCase(password) && us.getCorreo().equalsIgnoreCase(correo)){
                usuario = us;
            }
        }
        return usuario;
    }

    public void saveUsario(Usuario usuario){
        ServiceLocator.getInstanceUsuarioDAO().save(usuario);
    }

}