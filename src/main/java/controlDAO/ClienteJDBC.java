package controlDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empresa;
import modelo.Individual;
import servicios.Conexion;

public class ClienteJDBC {

    private final String SQL_INSERT_I = "INSERT INTO CLIENTE(nombre, telefono, direccion, dpi, tipo_cliente) VALUES(?,?,?,?,?);";
    public String insertIndi(Individual individual) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_I);
            int index = 1;

            stmt.setString(index++, individual.getNombre());
            stmt.setString(index++, individual.getTelefono());
            stmt.setString(index++, individual.getDireccion());
            stmt.setString(index++, individual.getDpi());
            stmt.setString(index++, individual.getTipoCliente());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
 
    private final String SQL_INSERT_E = "INSERT INTO cliente(nombre, telefono, direccion, contacto, descuento, tipo_cliente) VALUES (?, ?, ?, ?, ?, ?);";
    public String insertEmp(Empresa empresa) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_E);
            int index = 1;
            stmt.setString(index++, empresa.getNombre());
            stmt.setString(index++, empresa.getTelefono());
            stmt.setString(index++, empresa.getDireccion());
            stmt.setString(index++, empresa.getContacto());
            stmt.setInt(index++, empresa.getDescuento());
            stmt.setString(index++, empresa.getTipoCliente());
            row = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(mensaje = "Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
        }
        return mensaje;
    }
    
    private final String SQL_SELECT_I = "SELECT id, nombre, telefono, direccion, dpi FROM CLIENTE WHERE tipo_cliente='individual'";
    public List<Individual> selectIndividual() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Individual individual = null;
        List<Individual> individuales = new ArrayList();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_I);
            rs = stmt.executeQuery();
            while (rs.next()) {
                individual = new Individual();
                individual.setId(rs.getInt(1));
                individual.setNombre(rs.getString(2));
                individual.setTelefono(rs.getString(3));
                individual.setDireccion(rs.getString(4));
                individual.setDpi(rs.getString(5));
                individuales.add(individual);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return individuales;
    }
       
    private final String SQL_SELECT_E = "SELECT id, nombre, telefono, direccion, contacto, descuento, tipo_cliente FROM cliente WHERE tipo_cliente='empresa'";

    public List<Empresa> selectEmpresa() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empresa empresa = null;
        List<Empresa> empresas = new ArrayList();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_E);
            rs = stmt.executeQuery();
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt(1));
                empresa.setNombre(rs.getString(2));
                empresa.setTelefono(rs.getString(3));
                empresa.setDireccion(rs.getString(4));
                empresa.setContacto(rs.getString(5));
                empresa.setDescuento(Integer.parseInt(rs.getString(6)));
                empresa.setTipoCliente(rs.getString(7));
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return empresas;
    }


    private final String SQL_UPDATE_I = "UPDATE cliente SET  nombre=?, telefono=?, direccion=?, dpi=? WHERE id=?;";
    public String updateIndividual(Individual individual) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_I);
            int index = 1;
            stmt.setString(index++, individual.getNombre());
            stmt.setString(index++, individual.getTelefono());
            stmt.setString(index++, individual.getDireccion());
            stmt.setString(index++, individual.getDpi());
            stmt.setInt(index++, individual.getId());
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
    
   
    private final String SQL_UPDATE_E = "UPDATE cliente SET nombre=?, telefono=?, direccion=?, contacto=?, descuento=? WHERE id=?;";
    public String updateEmpresa(Empresa empresa) {
        String mensaje = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_E);
            int index = 1;
            
            stmt.setString(index++, empresa.getNombre());
            stmt.setString(index++, empresa.getTelefono());
            stmt.setString(index++, empresa.getDireccion());
            stmt.setString(index++, empresa.getContacto());
            stmt.setInt(index++, empresa.getDescuento());
            stmt.setInt(index++, empresa.getId());
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

    //_______________________-
    
    private final String SQL_TIPO_C = "SELECT tipo_cliente FROM cliente WHERE id=?";
    public String validarTipoCliente(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String tipoCliente=null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_TIPO_C);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tipoCliente = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.print("Error tipo cliente>> " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return tipoCliente;
    }
    
    
    
    private final String SQL_SELECT2_I = "SELECT id, nombre, telefono, direccion, dpi, tipo_cliente FROM cliente WHERE id=?;";
    public Individual selectIndividual(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Individual individual = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT2_I);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                individual = new Individual();
                individual.setId(rs.getInt(1));
                individual.setNombre(rs.getString(2));
                individual.setTelefono(rs.getString(3));
                individual.setDireccion(rs.getString(4));
                individual.setDpi(rs.getString(5));
                individual.setTipoCliente(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.print("Error >> " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }

        return individual;
    }
   
    private final String SQL_SELECT2_E = "SELECT id, nombre, telefono, direccion, contacto, descuento, tipo_cliente FROM cliente WHERE id=?;";
    public Empresa selectEmpresa(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Empresa empresa = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT2_E);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt(1));
                empresa.setNombre(rs.getString(2));
                empresa.setTelefono(rs.getString(3));
                empresa.setDireccion(rs.getString(4));
                empresa.setContacto(rs.getString(5));
                empresa.setDescuento(rs.getInt(6));
                empresa.setTipoCliente(rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.print("Error Empresa b id>> " + e.getMessage());
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }
        return empresa;
    }
    
    
    private final String SQL_DELETE = "DELETE FROM CLIENTE WHERE id=?";
    public String delete(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String mensaje = "";
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
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

    private static ClienteJDBC clienteJDBC;
    public static ClienteJDBC instance() {
        if (clienteJDBC == null) {
            clienteJDBC = new ClienteJDBC();
        }
        return clienteJDBC;
    }

}
