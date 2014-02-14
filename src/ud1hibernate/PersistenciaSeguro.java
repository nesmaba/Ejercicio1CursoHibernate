/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ud1hibernate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nestor
 */
public class PersistenciaSeguro {
    
    private Seguro seguro;
    private static Connection conexion;

    public PersistenciaSeguro() {
        try {
            Class.forName("com.mysql.jdbc.Driver");      
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate1", "hibernate1", "hibernate1");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersistenciaSeguro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaSeguro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Los índices en los parámetros de las instrucciones SQL empiezan en 1.
    public void insertSeguro(Seguro seguro){ 
        try {
            //Insertará el Seguro en la base de datos.
            String sql = "INSERT INTO seguro VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conexion.prepareStatement(sql);
            //Damos valores a los parámetros
            ps.setInt(1, seguro.getIdSeguro());
            ps.setString(2, seguro.getNif());
            ps.setString(3, seguro.getNombre());
            ps.setString(4, seguro.getApe1());
            ps.setString(5, seguro.getApe2());
            ps.setInt(6, seguro.getEdad());
            ps.setInt(7, seguro.getNumHijos());
            ps.setDate(8, seguro.getFechaCreacion());
            //Ejecutamos la SQL.
            ps.executeUpdate();
            System.out.println("Registro insertado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaSeguro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Seguro getSeguro(int idSeguro){ 
        //Obtendrá el Seguro cuya clave primaria es idSeguro
        Seguro seguro = null;
        try {
            String sql = "SELECT * FROM seguro WHERE idSeguro=?;";
            PreparedStatement ps = conexion.prepareStatement(sql);

            //Damos valores a los parámetros
            ps.setInt(1, idSeguro);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                String nif = result.getString(2);
                String nombre = result.getString(3);
                String ape1 = result.getString(4);
                String ape2 = result.getString(5);
                int edad = result.getInt(6);
                int numHijos = result.getInt(7);
                Date fechaCreacion = result.getDate(8);
                seguro = new Seguro(idSeguro, nif, nombre, ape1, ape2, edad, numHijos, fechaCreacion);
                
                System.out.println("Registro recuperado correctamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaSeguro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seguro;
    }
    
    public void updateSeguro(Seguro seguro){ //Actualizará en la base de datos el Seguro.
        try {
            //Insertará el Seguro en la base de datos.
            String sql = "UPDATE seguro SET idSeguro=?, nif=?, nombre=?, ape1=?, ape2=?, edad=?, numHijos=?, fechaCreacion=? WHERE idSeguro="+seguro.getIdSeguro()+";";
            PreparedStatement ps = conexion.prepareStatement(sql);
            //Damos valores a los parámetros
            ps.setInt(1, seguro.getIdSeguro());
            ps.setString(2, seguro.getNif());
            ps.setString(3, seguro.getNombre());
            ps.setString(4, seguro.getApe1());
            ps.setString(5, seguro.getApe2());
            ps.setInt(6, seguro.getEdad());
            ps.setInt(7, seguro.getNumHijos());
            ps.setDate(8, (Date) seguro.getFechaCreacion());
            //Ejecutamos la SQL.
            ps.executeUpdate();
            System.out.println("Registro actualizado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaSeguro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteSeguro(int idSeguro){ //Borrará de la base de datos el seguro cuya clave primaria sea idSeguro
        try {
            //Insertará el Seguro en la base de datos.
            String sql = "DELETE FROM seguro WHERE idSeguro=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            //Damos valores a los parámetros
            ps.setInt(1, idSeguro);
            //Ejecutamos la SQL.
            ps.executeUpdate();
            System.out.println("Registro borrado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(PersistenciaSeguro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
