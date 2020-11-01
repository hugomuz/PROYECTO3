
package controlDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Individual;
import modelo.Usuario;
import servicios.Conexion;


public class UsuarioJDBC {
    
    private final String SQL_SELECT_U = "select * from usuario where usuario=? and clave=?;";
    public  boolean validarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usu=null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_U);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getClave());
            rs = stmt.executeQuery();
            while (rs.next()) {
                usu = new Usuario();
                usu.setUsuario(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.print("Error >> " + e.getMessage());
            return false;
        } finally {
            Conexion.closed(stmt);
            Conexion.closed(conn);
            Conexion.closed(rs);
        }

        if(usu.getUsuario().equals(usuario.getUsuario())){
            return true;
        }else{
            return false;
        }
    }
    
        private static UsuarioJDBC usuarioJDBC;
    public static UsuarioJDBC instance() {
        if (usuarioJDBC == null) {
            usuarioJDBC = new UsuarioJDBC();
        }
        return usuarioJDBC;
    }
    
}
