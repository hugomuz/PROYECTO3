package controlador;

import controlDAO.ProductoJDBC;
import controlDAO.UsuarioJDBC;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Usuario;

@WebServlet(name = "LoginCtrl", urlPatterns = {"/LoginCtrl"})

public class LoginCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            validarAcceso(request, response);
        } 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void validarAcceso(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        Usuario usu = new Usuario(usuario, clave);
        if (UsuarioJDBC.instance().validarUsuario(usu)==true) {
            List<Producto> listaProductos = ProductoJDBC.instance().select();
            request.setAttribute("productos", listaProductos);
            request.getRequestDispatcher("WEB-INF/producto/productoindex.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        }catch(Exception e){
             request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    
    

}
