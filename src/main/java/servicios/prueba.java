/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import controlDAO.ItemOrdenJDBC;
import java.sql.SQLException;

/**
 *
 * @author Juan Pablo Castiblan
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        ItemOrdenJDBC.instance().delete("0e371fb9-81e9-46d6-a271-0541b7f1a1e0");
        ItemOrdenJDBC.instance().delete("b4427747-bc43-4c60-b8f3-9492953b7a3a");
        
    }
    
}
