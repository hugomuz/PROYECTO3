package servicios;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static final String JDBC_DRIVER = "org.postgresql.Driver";  
    private static final String DB = "Proyecto3";
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/" + DB; 
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASS = "diosesamor";
    private static Driver driver;

    public static synchronized Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
                System.out.println("oook");
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException e) {
                System.out.println("Fallo en cargar el Driver");
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void closed(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {

        }
    }

    public static void closed(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {

        }
    }

    public static void closed(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {

        }
    }

}
