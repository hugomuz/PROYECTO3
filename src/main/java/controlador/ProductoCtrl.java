package controlador;



import controlDAO.ProductoJDBC;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;



@WebServlet(name = "ProductoCtrl", urlPatterns = {"/ProductoCtrl"})


public class ProductoCtrl extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            if (accion.equals("nuevo")) {
                crearProducto(request, response);
            } else {
                if (accion.equals("editar")) {
                    editarProducto(request, response);
                }
            }
        } else {
            List<Producto> listaProductos = ProductoJDBC.instance().select();
            for(Producto p: listaProductos){
                System.out.println(" -- "+p.toString());
            }
            request.setAttribute("productos", listaProductos);
            request.getRequestDispatcher("WEB-INF/producto/productoindex.jsp").forward(request, response);
        }
    }
  
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("Crear")) {
            try {
                insertarProducto(request, response);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProductoCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (accion.equals("actualizar")) {
                try {
                   modificarProducto(request, response);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ProductoCtrl.class.getName()).log(Level.SEVERE, null, ex);
               }
            } else {
                if (accion.equals("eliminar")) {
                    eliminarProducto(request, response);
                }
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
    private void crearProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoFormulario", "Crear");
        request.getRequestDispatcher("WEB-INF/producto/formulario.jsp").forward(request, response);
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        String nombre=request.getParameter("nombre");
        String descripcion=request.getParameter("descripcion");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        Producto producto = new Producto(nombre,descripcion,precio,cantidad);
        ProductoJDBC.instance().insert(producto);
        response.sendRedirect("ProductoCtrl");
    }

    
    
    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Producto producto = ProductoJDBC.instance().selectProducto(id);
        request.setAttribute("producto", producto);
        request.setAttribute("tipoFormulario", "actualizar");
        request.getRequestDispatcher("WEB-INF/producto/formulario.jsp").forward(request, response);
    }

    private void modificarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        String nombre=request.getParameter("nombre");
        String descripcion=request.getParameter("descripcion");
        int precio = Integer.parseInt(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        Producto producto = new Producto(nombre,descripcion,precio,cantidad);
        producto.setId(id);
        ProductoJDBC.instance().update(producto);
        response.sendRedirect("ProductoCtrl");
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String mensaje = ProductoJDBC.instance().delete(id);
        request.setAttribute("mensaje", mensaje);
        response.sendRedirect("ProductoCtrl");
    }

      
    
      
          
      

}
