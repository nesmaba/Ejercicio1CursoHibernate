/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ud1hibernate;

import java.sql.Date;

/**
 *
 * @author nestor
 */
public class UD1Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Seguro s = new Seguro(1,"22222222D","Néstor","Martínez","Ballester",30, 0, new Date(System.currentTimeMillis()));
        PersistenciaSeguro ps = new PersistenciaSeguro();
        
        //ps.insertSeguro(s); //Insertamos un seguro
        Seguro s1 = ps.getSeguro(1); //Recuperamos un seguro
        System.out.println(s1); 
        Seguro s2 = new Seguro(1,"33333333D","Néstor","Martínez","Ballester",30, 0, new Date(System.currentTimeMillis()));
        ps.updateSeguro(s2); //Actualizamos un seguro
        
        ps.deleteSeguro(1); //Eliminamos un seguro
    }
}
