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
import modelo.Individual;

@WebServlet(name = "IndividualCtrl", urlPatterns = {"/IndividualCtrl"})

public class IndividualCtrl extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            if (accion.equals("nuevo")) {
                crearIndividual(request, response);
            } else {
                if (accion.equals("editar")) {
                    editarIndividual(request, response);
                }
            }
        } else {
            List<Individual> listaIndividuales = ClienteJDBC.instance().selectIndividual();
            request.setAttribute("individuales", listaIndividuales);
            request.getRequestDispatcher("WEB-INF/individual/individualindex.jsp").forward(request, response);
        }
    }
  
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("Crear")) {
            try {
                insertarIndividual(request, response);
            } catch (InterruptedException ex) {
                Logger.getLogger(IndividualCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (accion.equals("actualizar")) {
                try {
                   modificarIndividual(request, response);
                } catch (InterruptedException ex) {
                    Logger.getLogger(IndividualCtrl.class.getName()).log(Level.SEVERE, null, ex);
               }
            } else {
                if (accion.equals("eliminar")) {
                    eliminarIndividual(request, response);
                }
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void crearIndividual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("tipoFormulario", "Crear");
        request.getRequestDispatcher("WEB-INF/individual/formulario.jsp").forward(request, response);
    }

    private void insertarIndividual(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        String nombre=request.getParameter("nombre");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        String dpi=request.getParameter("dpi");
        Individual indi = new Individual(dpi, nombre, direccion, telefono, "individual");
        ClienteJDBC.instance().insertIndi(indi);
        response.sendRedirect("IndividualCtrl");
    }
    
    private void editarIndividual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Individual indi = ClienteJDBC.instance().selectIndividual(id);
        request.setAttribute("individual", indi);
        request.setAttribute("tipoFormulario", "actualizar");
        request.getRequestDispatcher("WEB-INF/individual/formulario.jsp").forward(request, response);
    }

    private void modificarIndividual(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre=request.getParameter("nombre");
        String telefono=request.getParameter("telefono");
        String direccion=request.getParameter("direccion");
        String dpi=request.getParameter("dpi");

        Individual indi = new Individual(dpi, nombre, direccion, telefono, "individual");
        indi.setId(id);
        ClienteJDBC.instance().updateIndividual(indi);
        response.sendRedirect("IndividualCtrl");
    }

    private void eliminarIndividual(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(" >>>>>> " + id);
        String mensaje = ClienteJDBC.instance().delete(id);
        request.setAttribute("mensaje", mensaje);
        response.sendRedirect("IndividualCtrl");
    }

      
    
      
          
      

}
