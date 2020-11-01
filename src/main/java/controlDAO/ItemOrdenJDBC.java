package controlDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ItemOrden;
import servicios.Conexion;

public class ItemOrdenJDBC {
    
    private final String SQL_INSERT = "INSERT INTO itemorden(id, cantidad, id_producto) VALUES (?, ?, ?);";
    
    public String insert(ItemOrden item){
        String mensaje ="";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try{
            conn= Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, item.getId());
            stmt.setInt(index++, item.getCantidad());
            stmt.setInt(index++, item.getProducto().getId());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
           System.out.println(mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
    
     private final String SQL_SELECT = "select id, cantidad, id_producto from itemorden where id=?";
     public ItemOrden select(String id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        ItemOrden item = null;
        try{
             conn= Conexion.getConnection();
             stmt = conn.prepareStatement(SQL_SELECT);
             stmt.setString(1, id);
             rs = stmt.executeQuery();
             while(rs.next()){
                item = new ItemOrden(); 
                item.setId(rs.getString(1));
                item.setCantidad(rs.getInt(2));
                item.setProducto(ProductoJDBC.instance().selectProducto(rs.getInt(3)));
               }
         }catch(SQLException e){
             System.out.print("Error >> " +e.getMessage());
         }
        finally{
          Conexion.closed(stmt);
          Conexion.closed(conn);
          Conexion.closed(rs);
         }
       
         return item;
    }
    
    private final String SQL_UPDATE = "UPDATE itemorden SET cantidad=?, id_producto=? WHERE id=?;";
    public String update(ItemOrden item) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1; 
            stmt.setInt(index++, item.getCantidad());
            stmt.setInt(index++, item.getProducto().getId());
            stmt.setString(index++, item.getId());
            row = stmt.executeUpdate();
            mensaje = "Se actualizo" + row + "registro(s), satisfactoriamente";
        } catch (SQLException e) {
            System.out.println(  mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
     private final String SQL_DELETE = "DELETE FROM itemorden WHERE id=?";
        public String delete(String id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        String mensaje="";
            try{
                conn= Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setString(1, id);
                rs = stmt.executeQuery();
                }catch(SQLException e){
             mensaje = e.getMessage();
         }finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return mensaje;        
    }
        
        
        
    private static ItemOrdenJDBC itemOrdenJDBC;
    
    public static ItemOrdenJDBC instance() {
        if (itemOrdenJDBC == null) {
            itemOrdenJDBC = new ItemOrdenJDBC();
        }
        return itemOrdenJDBC;
    }
    
}
