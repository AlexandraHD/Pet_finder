package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Mascota;
import beans.Usuario;
import connection.DBConnection;

public class MascotaController implements IMascotaController {

    @Override
    public String registerMascota(int id_mascota, String tipo, 
            String nombre_mascota, String raza, int edad, String tamano, String peso, String color, String ciudad, String ruta_foto, Date fecha_perdido, String otros) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Insert into pet_finder values('" + id_mascota + "','" + tipo + "', '" + nombre_mascota + "', '" + raza
                + "', '" + edad + "', '" + tamano + "', " + peso + ", " + color + ", " + ciudad + ", " + ruta_foto + ", " + fecha_perdido+ ", , " + otros + ")";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            Mascota mascota = new Mascota(id_mascota, tipo, nombre_mascota, raza, edad, tamano, peso, color, ciudad, ruta_foto, fecha_perdido, otros);

            st.close();

            return gson.toJson(mascota);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String listarMascota(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from pet_finder";

        if (ordenar == true) {
            sql += " order by fecha_perdido " + orden;
        }

        List<String> mascotas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id_mascota = rs.getInt("id_mascota");
                String tipo = rs.getString("tipo");
                String nombre_mascota = rs.getString("nombre_mascota");
                String raza = rs.getString("raza");
                int edad = rs.getInt("edad");
                String tamano = rs.getString("tamano");
                String peso = rs.getString("peso");
                String color = rs.getString("color");
                String ciudad = rs.getString("ciudad");
                String ruta_foto = rs.getString("ruta_foto");
                Date fecha_perdido = rs.getDate("fecha_perdido");
                String otros = rs.getString("otros");

                Mascota mascota = new Mascota(id_mascota, tipo, nombre_mascota, raza, edad, tamano, peso, color, ciudad, ruta_foto, fecha_perdido, otros);

                mascotas.add(gson.toJson(mascota));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(mascotas);

    }
}