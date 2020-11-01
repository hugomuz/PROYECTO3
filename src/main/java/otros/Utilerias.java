
package otros;

import controlDAO.ClienteJDBC;
import controlDAO.ProductoJDBC;
import controlador.EmpresaCtrl;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import modelo.Cliente;
import modelo.Empresa;
import modelo.Individual;
import modelo.Producto;


public class Utilerias {
    
    
    public  static String getNombreClase(Class clase) {
        return clase.getSimpleName();
    }
    
    public static String formatoMoneda(double num){
       return (DecimalFormat.getCurrencyInstance(Locale.US).format(num)) ;
    }
    public static String formatoMoneda(int num){
       return (DecimalFormat.getCurrencyInstance(Locale.US).format(num)) ;
    }
    
    public static List<Cliente> listaClintes() throws IOException{
        List<Cliente> clientes = new ArrayList();
        for(Empresa e: ClienteJDBC.instance().selectEmpresa()){
            clientes.add(e);
        }
        for(Individual i: ClienteJDBC.instance().selectIndividual()){
            clientes.add(i);
        }
        return clientes;
    }
    
    public static List<Producto> listaProductos() throws IOException{
        List<Producto> productos = new ArrayList();
        for(Producto p: ProductoJDBC.instance().select()){
            if(p.getCantidad()>0){
                productos.add(p);
            }
        }
        return productos; 
    }
    
}
