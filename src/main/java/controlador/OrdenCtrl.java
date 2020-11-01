package controlador;

import controlDAO.ClienteJDBC;
import controlDAO.ItemOrdenJDBC;
import controlDAO.OrdenJDBC;
import controlDAO.ProductoJDBC;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.ItemOrden;
import modelo.Orden;
import modelo.Producto;
import otros.Utilerias;

@WebServlet(name = "OrdenCtrl", urlPatterns = {"/OrdenCtrl"})

public class OrdenCtrl extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            if (accion.equals("nuevo")) {
                crearOrden(request, response);
            } else {
                if (accion.equals("editar")) {
                    editarOrden(request, response);
                }else{
                    if(accion.equals("detalles")){
                        mostrarDetalles(request, response);
                    }
                }
            }
        } else {
            List<Orden> ordenes = OrdenJDBC.instance().selectOrden();
            request.setAttribute("ordenes", ordenes);
            request.getRequestDispatcher("WEB-INF/orden/ordenindex.jsp").forward(request, response);
        }
    }
  
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("Crear")) {
            try {
                insertarOrden(request, response);
            } catch (InterruptedException ex) {
                Logger.getLogger(OrdenCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (accion.equals("Actualizar")) {
                try {
                   modificarOrden(request, response);
                } catch (InterruptedException ex) {
                    Logger.getLogger(OrdenCtrl.class.getName()).log(Level.SEVERE, null, ex);
               }
            } else {
                if (accion.equals("eliminar")) {
                    eliminarOrden(request, response);
                }
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
    private void crearOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = Utilerias.listaClintes();
        request.setAttribute("clientes", clientes);
        List<Producto> productos = Utilerias.listaProductos();
        request.setAttribute("productos", productos);
        request.setAttribute("tipoFormulario", "Crear");
        request.getRequestDispatcher("WEB-INF/orden/formulario.jsp").forward(request, response);
    }

    private void insertarOrden(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = OrdenJDBC.instance().validarCliente(idCliente);
        Date fecha = new Date();
        double precioEnvio = Double.parseDouble(request.getParameter("precioEnvio"));
        String tipoEnvio = request.getParameter("tipoEnvio");
        String estado = request.getParameter("estado");
        //___________
        Producto producto1 = ProductoJDBC.instance().selectProducto(Integer.parseInt(request.getParameter("producto1")));
        ItemOrden item1 = new ItemOrden(Integer.parseInt(request.getParameter("cantidad1")),producto1);
        ItemOrdenJDBC.instance().insert(item1);
        //_____________
        Producto producto2 = ProductoJDBC.instance().selectProducto(Integer.parseInt(request.getParameter("producto2")));
        ItemOrden item2 = new ItemOrden(Integer.parseInt(request.getParameter("cantidad2")),producto2);
        ItemOrdenJDBC.instance().insert(item2);
        //______________
        int diasEnvio = Integer.parseInt(request.getParameter("diasEnvio"));
        Orden orden =  new Orden(cliente, item1, item2, fecha, precioEnvio, tipoEnvio, estado, diasEnvio);
        OrdenJDBC.instance().insertOrden(orden);
        response.sendRedirect("OrdenCtrl");
    }
    
    private void editarOrden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Orden orden = OrdenJDBC.instance().selectOrden(id);
        request.setAttribute("orden", orden);
        List<Cliente> clientes = Utilerias.listaClintes();
        request.setAttribute("clientes", clientes);
        List<Producto> productos = Utilerias.listaProductos();
        request.setAttribute("productos", productos);
        request.setAttribute("tipoFormulario", "Actualizar");
        request.getRequestDispatcher("WEB-INF/orden/formulario.jsp").forward(request, response);
        
    }

    private void modificarOrden(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("--------- > "+ id);
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = OrdenJDBC.instance().validarCliente(idCliente);
        Date fecha = new Date();
        double precioEnvio = Double.parseDouble(request.getParameter("precioEnvio"));
        String tipoEnvio = request.getParameter("tipoEnvio");
        String estado = request.getParameter("estado");
        //___________
        Producto producto1 = ProductoJDBC.instance().selectProducto(Integer.parseInt(request.getParameter("producto1")));
        ItemOrden item1 = new ItemOrden(Integer.parseInt(request.getParameter("cantidad1")),producto1);
        item1.setId(OrdenJDBC.instance().selectOrden(id).getItem1().getId());
        ItemOrdenJDBC.instance().update(item1);
        //_____________
        Producto producto2 = ProductoJDBC.instance().selectProducto(Integer.parseInt(request.getParameter("producto2")));
        ItemOrden item2 = new ItemOrden(Integer.parseInt(request.getParameter("cantidad2")),producto2);
        item2.setId(OrdenJDBC.instance().selectOrden(id).getItem2().getId());
        ItemOrdenJDBC.instance().update(item2);
        //______________
        int diasEnvio = Integer.parseInt(request.getParameter("diasEnvio"));
        Orden orden =  new Orden(cliente, item1, item2, fecha, precioEnvio, tipoEnvio, estado, diasEnvio);
        orden.setId(id);
        OrdenJDBC.instance().update(orden);
        response.sendRedirect("OrdenCtrl");
    }

    private void eliminarOrden(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Orden orden = OrdenJDBC.instance().selectOrden(id);
        //---------------------
        String mensaje = OrdenJDBC.instance().delete(orden);
        //---------------------
        ItemOrdenJDBC.instance().delete(orden.getItem1().getId());
        ItemOrdenJDBC.instance().delete(orden.getItem2().getId());
        request.setAttribute("mensaje", mensaje);
        response.sendRedirect("OrdenCtrl");
    }

    private void mostrarDetalles(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Orden orden = OrdenJDBC.instance().selectOrden(id);
        request.setAttribute("orden", orden);
        request.setAttribute("tipo", orden.getCliente().getTipoCliente());
        ItemOrden item1 = orden.getItem1();
        request.setAttribute("item1", item1);
        ItemOrden item2 = orden.getItem2();
        request.setAttribute("item2", item2);
        request.getRequestDispatcher("WEB-INF/orden/detalles.jsp").forward(request, response);
    }

      
   
      
          
      

}
