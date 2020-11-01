package controlDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;
import modelo.Orden;
import servicios.Conexion;

public class OrdenJDBC {

    private final String SQL_INSERT = "INSERT INTO orden(id_cliente, fecha, precio_envio, tipo_envio, estado, itemuno, itemdos, total, dias_envio) 	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public String insertOrden(Orden orden) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, orden.getCliente().getId());
            java.sql.Date fechaSQL = new java.sql.Date(orden.getFechaOrden().getTime());
            stmt.setDate(index++, fechaSQL);
            stmt.setDouble(index++, orden.getPrecioEnvio());
            stmt.setString(index++, orden.getTipoEnvio());
            stmt.setString(index++, orden.getEstado());
            stmt.setString(index++, orden.getItem1().getId());
            stmt.setString(index++, orden.getItem2().getId());
            stmt.setDouble(index++, orden.getTotal());
            stmt.setInt(index++, orden.getDiasEnvio());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
    private final String SQL_SELECT = "SELECT id, id_cliente, fecha, precio_envio, tipo_envio, estado, itemuno, itemdos, total, dias_envio FROM orden;";
    public List<Orden> selectOrden() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Orden orden = null;
        List<Orden> ordenes = new ArrayList();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                orden = new Orden();
                orden.setId(rs.getInt(1));
                orden.setCliente(validarCliente(rs.getInt(2)));
                orden.setFechaOrden(rs.getDate(3));
                orden.setPrecioEnvio(rs.getDouble(4));
                orden.setTipoEnvio(rs.getString(5));
                orden.setEstado(rs.getString(6));
                orden.setItem1(ItemOrdenJDBC.instance().select(rs.getString(7)));
                orden.setItem2(ItemOrdenJDBC.instance().select(rs.getString(8)));
                orden.setTotal(rs.getDouble(9));
                orden.setDiasEnvio(rs.getInt(10));
                ordenes.add(orden);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return ordenes;
    }
       
    private final String SQL_UPDATE = "UPDATE orden SET id_cliente=?, fecha=?, precio_envio=?, tipo_envio=?, estado=?, itemuno=?, itemdos=?, total=?, dias_envio=? WHERE id=?;";
    public String update(Orden orden) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setInt(index++, orden.getCliente().getId());
            java.sql.Date fechaSQL = new java.sql.Date(orden.getFechaOrden().getTime());
            stmt.setDate(index++, fechaSQL);
            stmt.setDouble(index++, orden.getPrecioEnvio());
            stmt.setString(index++, orden.getTipoEnvio());
            stmt.setString(index++, orden.getEstado());
            stmt.setString(index++, orden.getItem1().getId());
            stmt.setString(index++, orden.getItem2().getId());
            stmt.setDouble(index++, orden.getTotal());
            stmt.setInt(index++, orden.getDiasEnvio());
            stmt.setInt(index++, orden.getId());
            row = stmt.executeUpdate();
            mensaje = "Se actualizo" + row + "registro(s), satisfactoriamente";
        } catch (SQLException e) {
            System.out.println(mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
    private final String SQL_SELECT2 = "SELECT id, id_cliente, fecha, precio_envio, tipo_envio, estado, itemuno, itemdos, total, dias_envio FROM orden WHERE id=?;";
    public Orden selectOrden(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Orden orden = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT2);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                orden = new Orden();
                orden.setId(rs.getInt(1));
                orden.setCliente(validarCliente(rs.getInt(2)));
                orden.setFechaOrden(rs.getDate(3));
                orden.setPrecioEnvio(rs.getDouble(4));
                orden.setTipoEnvio(rs.getString(5));
                orden.setEstado(rs.getString(6));
                orden.setItem1(ItemOrdenJDBC.instance().select(rs.getString(7)));
                orden.setItem2(ItemOrdenJDBC.instance().select(rs.getString(8)));
                orden.setTotal(rs.getDouble(9));
                orden.setDiasEnvio(rs.getInt(10));
            }
        } catch (SQLException e) {
            System.out.print("Error >> " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }

        return orden;
    }
   
    private final String SQL_DELETE = "DELETE FROM orden WHERE id=?";
    public String delete(Orden orden) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String mensaje = "";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, orden.getId());
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            mensaje = "Error : " + e.getMessage();
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return mensaje;
    }

    public Cliente validarCliente(int id) {
        String tipoCliente = ClienteJDBC.instance().validarTipoCliente(id);
        if(tipoCliente.equals("empresa")){
            return ClienteJDBC.instance().selectEmpresa(id);
        }else{
            if(tipoCliente.equals("individual")){
                return ClienteJDBC.instance().selectIndividual(id);
            }else{
                return null;
            }
        }
    }
    
        private static OrdenJDBC ordenJDBC;
    public static OrdenJDBC instance() {
        if (ordenJDBC == null) {
            ordenJDBC = new OrdenJDBC();
        }
        return ordenJDBC;
    }


}
