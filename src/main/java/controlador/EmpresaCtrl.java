package controlador;

import controlDAO.ClienteJDBC;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empresa;

@WebServlet(name = "EmpresaCtrl", urlPatterns = {"/EmpresaCtrl"})

public class EmpresaCtrl extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            if (accion.equals("nuevo")) {
                crearEmpresa(request, response);
            } else {
                if (accion.equals("editar")) {
                    editarEmpresa(request, response);
                }
            }
        } else {
            List<Empresa> listaEmpresas = ClienteJDBC.instance().selectEmpresa();
            request.setAttribute("empresas", listaEmpresas);
            request.getRequestDispatcher("WEB-INF/empresa/empresaindex.jsp").forward(request, response);
        }
    }
  
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("Crear")) {
            try {
                insertarEmpresa(request, response);
            } catch (InterruptedException ex) {
                Logger.getLogger(EmpresaCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (accion.equals("actualizar")) {
                try {
                   modificarEmpresa(request, response);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EmpresaCtrl.class.getName()).log(Level.SEVERE, null, ex);
               }
            } else {
                if (accion.equals("eliminar")) {
                    eliminarEmpresa(request, response);
                }
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
    private void crearEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoFormulario", "Crear");
        request.getRequestDispatcher("WEB-INF/empresa/formulario.jsp").forward(request, response);
    }

    private void insertarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        String nombre=request.getParameter("nombre");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        String contacto=request.getParameter("contacto");
        int descuento = Integer.parseInt(request.getParameter("descuento"));
        Empresa empresa = new Empresa(contacto, descuento, nombre, direccion, telefono, "empresa");
        ClienteJDBC.instance().insertEmp(empresa);
        response.sendRedirect("EmpresaCtrl");
    }
    
    private void editarEmpresa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empresa empresa = ClienteJDBC.instance().selectEmpresa(id);
        request.setAttribute("empresa", empresa);
        request.setAttribute("tipoFormulario", "actualizar");
        request.getRequestDispatcher("WEB-INF/empresa/formulario.jsp").forward(request, response);
    }

    private void modificarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre=request.getParameter("nombre");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        String contacto=request.getParameter("contacto");
        int descuento = Integer.parseInt(request.getParameter("descuento"));
        Empresa empresa = new Empresa(contacto, descuento, nombre, direccion, telefono, "empresa");
        empresa.setId(id);
        ClienteJDBC.instance().updateEmpresa(empresa);
        response.sendRedirect("EmpresaCtrl");
    }

    private void eliminarEmpresa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String mensaje = ClienteJDBC.instance().delete(id);
        request.setAttribute("mensaje", mensaje);
        response.sendRedirect("EmpresaCtrl");
    }

      
    
      
          
      

}
