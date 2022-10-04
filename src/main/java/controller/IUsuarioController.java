package controller;

import java.util.Map;

public interface IUsuarioController {

    public String login(String username, String contrasena);
    
    public String register(String username, String contrasena, 
            String nombre, String apellidos, String email, String telefono, String ciudad);
    
    public String pedir(String username);

    public String modificar(String username, String nuevaContrasena,
            String nuevoNombre, String nuevoApellido, String nuevoEmail,
            String nuevoTelefono, String nuevoCiudad);

    public String eliminar(String username);
}
