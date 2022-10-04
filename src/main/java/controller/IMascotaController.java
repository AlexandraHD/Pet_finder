package controller;

import java.sql.Date;

public interface IMascotaController {
    public String registerMascota(int id_mascota, String tipo, 
            String nombre_mascota, String raza, int edad, String tamano, String peso, String color, String ciudad, String ruta_foto, Date fecha_perdido, String otros);
    
    public String listarMascota(boolean ordenar, String orden);
}
