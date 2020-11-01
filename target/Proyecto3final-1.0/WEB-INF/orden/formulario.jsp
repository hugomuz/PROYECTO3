<%@page import="modelo.Cliente"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="modelo.Orden"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <link rel="stylesheet" type="text/css" href="styles/orden.css">
          <%
        String tipoForm= (String) request.getAttribute("tipoFormulario");
        List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
        Orden orden=null;
        if(tipoForm.equals("Actualizar")){
        orden = (Orden) request.getAttribute("orden");
        }
    %>  
    </head>
    <body  style="color:#fff;" >
    </body>
    <body background="C:\Users\PEDRO\Desktop\proyectotres\target\Prueba1.0-1.0\fondos\WhatsApp Image 2020-10-31 at 15.02.15.jpeg" >
           
         <div class="container" style="margin-top: 5px; ">
        <jsp:include page="../includes/header.jsp"></jsp:include>
         </div>
            <div  class="cuerpo" style="margin:25px 100px 100px 100px;
                  border: activeborder 0px solid; padding: 20px 20px 20px">
              
                <h3 class="titulo" style="padding-bottom:30px"><%=tipoForm%> Orden ${orden.id}</h3>
            <form action="OrdenCtrl" method="POST">
               
                <div style="padding: 20px 25px 15px 25px">
                    
                    <label>Cliente:</label>
                     <input type="text" class="form-control" name="id" value="${orden.id}" style="display:none">
                    <select class="form-control" name="idCliente" >
                        <option 
                            <% if (tipoForm.equals("Actualizar")) {%>
                            value="${orden.cliente.id}" 
                            selected>${orden.cliente.nombre}
                            <%} else {%>
                            selected>Choose...
                            <%}%>
                        </option>
                        <% for (Cliente cliente : clientes) {%>
                        <option value="<%=cliente.getId()%>"><%=cliente.getNombre()%></option>
                        <%}%>
                    </select>
                </div>
                    
                    
               <div style="padding: 10px 15px 10px 15px">
                    <label>Precio Envio:</label>
                    <input type="text" class="form-control" name="precioEnvio" value="${orden.precioEnvio}">
                </div>    
                <div style="padding: 40px 15px 10px 15px">
                    <label>Tipo Envio</label>
                    <select  class="form-control" name="tipoEnvio">
                        <option 
                            <% if (tipoForm.equals("Actualizar")) {%>
                            value="${orden.tipoEnvio}" 
                            selected>${orden.tipoEnvio}
                            <%} else {%>
                            selected>Choose...
                            <%}%>
                        </option>
                        <option value="Urgente">Urgente</option>
                        <option value="Estandar">Estandar</option>
                    </select>
                </div>
              <div style="padding: 10px 15px 10px 15px">
                    <label>Estado</label>
                    <select  class="form-control" name="estado">
                        <option 
                            <% if (tipoForm.equals("Actualizar")) {%>
                            value="${orden.estado}" 
                            selected>${orden.estado}
                            <%} else {%>
                            selected>Choose...
                            <%}%>
                        </option>
                        <option value="Pendiente">Pendiente</option>
                        <option value="Completada">Completada</option>
                        <option value="Cancelada">Cancelada</option>
                    </select>
                </div>
                <div style="padding: 40px 15px 10px 15px">
                    <label>Producto1</label>
                    <select class="form-control" name="producto1">
                        <option 
                            <% if (tipoForm.equals("Actualizar")) {%>
                            value="${orden.item1.producto.id}"
                            selected>${orden.item1.producto.nombre}
                            <%} else {%>
                            selected>Choose...
                            <%}%>
                        </option>
                        <% for (Producto producto : productos) {%>
                        <option value="<%=producto.getId()%>"><%=producto.getNombre()%></option>
                        <%}%>
                    </select>
                </div>
               <div style="padding: 10px 15px 10px 15px">
                    <label>Cantidad Producto 1:</label>
                    <input type="text" class="form-control" name="cantidad1" value="${orden.item1.cantidad}">
                </div> 
                <div style="padding: 10px 15px 10px 15px">
                    <label>Producto 2:</label>
                    <select class="form-control" name="producto2">
                        <option 
                            <% if (tipoForm.equals("Actualizar")) {%>
                            value="${orden.item2.producto.id}"
                            selected>${orden.item2.producto.nombre}
                            <%} else {%>
                            selected>Choose...
                            <%}%>
                        </option>
                        <% for (Producto producto : productos) {%>
                        <option value="<%=producto.getId()%>"><%=producto.getNombre()%></option>
                        <%}%>
                    </select>
                </div>
               <div style="padding: 10px 15px 10px 15px">
                    <label>Cantidad Producto 2:</label>
                    <input type="text" class="form-control" name="cantidad2" value="${orden.item2.cantidad}">
                </div> 
               <div style="padding: 5px 10px 15px 10px">
                    <label>Dias Envio:</label>
                    <select  class="form-control" name="diasEnvio">
                        <option 
                            <% if (tipoForm.equals("Actualizar")) {%>
                            value="${orden.diasEnvio}" 
                            selected>${orden.diasEnvio}
                            <%} else {%>
                            selected>Choose...
                            <%}%>
                        </option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                         <option value="4">4</option>
                          <option value="5">5</option>
                    </select>
                </div>
                <input type="submit" class="btn btn-success" style="margin-left:20px;" value="<%=tipoForm%>" name="accion" >
            </form>
        </div>
    </body>
</html>
