package controlDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;
import servicios.Conexion;

public class ProductoJDBC {
    
    private final String SQL_INSERT = "INSERT INTO PRODUCTO(nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?);";
    
    public String insert(Producto producto){
        String mensaje ="";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try{
            conn= Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setString(index++, producto.getNombre());
            stmt.setString(index++, producto.getDescripcion());
            stmt.setInt(index++, producto.getPrecio());
            stmt.setInt(index++, producto.getCantidad());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
           System.out.println(mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
    private final String SQL_SELECT = "SELECT * FROM PRODUCTO";
    public List<Producto> select() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList();
            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_SELECT);
                rs = stmt.executeQuery();
                while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setPrecio(rs.getInt(4));
                producto.setCantidad(rs.getInt(5));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return productos;
    }
    
    private final String SQL_UPDATE = "UPDATE producto SET nombre=?, descripcion=?, precio=?, cantidad=? WHERE id=?;";
    public String update(Producto producto) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1; 
            stmt.setString(index++, producto.getNombre());
            stmt.setString(index++, producto.getDescripcion());
            stmt.setInt(index++, producto.getPrecio());
            stmt.setInt(index++, producto.getCantidad());
            stmt.setInt(index++, producto.getId());
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
    
    private final String SQL_SELECT2 = "SELECT id, nombre, descripcion, precio, cantidad FROM producto WHERE id=?;";
     public Producto selectProducto(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Producto producto = null;
        try{
             conn= Conexion.getConnection();
             stmt = conn.prepareStatement(SQL_SELECT2);
             stmt.setInt(1, id);
             rs = stmt.executeQuery();
             while(rs.next()){
                producto = new Producto(); 
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setDescripcion(rs.getString(3));
                producto.setPrecio(rs.getInt(4));
                producto.setCantidad(rs.getInt(5));
               }
         }catch(SQLException e){
             System.out.print("Error >> " +e.getMessage());
         }
        finally{
          Conexion.closed(stmt);
          Conexion.closed(conn);
          Conexion.closed(rs);
         }
       
         return producto;
    }
    
    
     private final String SQL_DELETE = "DELETE FROM PRODUCTO WHERE id=?";
        public String delete(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        String mensaje="";
            try{
                conn= Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                }catch(SQLException e){
              mensaje = "Error : " + e.getMessage();
         }finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return mensaje;        
    }
        
    private static ProductoJDBC productoJDBC;
    
    public static ProductoJDBC instance() {
        if (productoJDBC == null) {
            productoJDBC = new ProductoJDBC();
        }
        return productoJDBC;
    }
    
}
