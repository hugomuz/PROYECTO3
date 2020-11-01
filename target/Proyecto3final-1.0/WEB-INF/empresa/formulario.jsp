<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="modelo.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <link rel="stylesheet" type="text/css" href="styles/doctor.css">
          <%
        String tipoForm= (String) request.getAttribute("tipoFormulario");
        Empresa empresa=null;
        if(tipoForm.equals("actualizar")){
        empresa = (Empresa) request.getAttribute("empresa");
        }
    %>  
    </head>
    <body  style="color:#fff"> </body>
         <body background="C:\Users\PEDRO\Desktop\proyectotres\target\Prueba1.0-1.0\fondos\gi1.gif">
        <div class="container" style="margin-top: 5px; ">
        <jsp:include page="../includes/header.jsp"></jsp:include>
        </div>
       <div  class="cuerpo" style="margin:15px 200px 150px 200px;
                  border: activeborder 0px solid; padding: 20px 20px 20px">
           
            <h3 class="titulo" style="padding-bottom:10px"><%=tipoForm%> Empresa</h3>
            <form action="EmpresaCtrl" method="POST">
                    <div class="form-group item">
                        <label>Nombre:</label>
                        <input type="text" class="form-control" name="nombre" value="${empresa.nombre}">
                    </div>    
                    <div class="form-group item">
                        <label>Telefono: </label>
                        <input type="text" class="form-control" name="id" value="${empresa.id}" style="display:none">
                        <input type="text" class="form-control" name="telefono" value="${empresa.telefono}">
                    </div>
                    <div class="form-group item">
                        <label>Direccion: </label>
                        <input type="text" class="form-control" name="direccion" value="${empresa.direccion}">
                    </div>
                    <div class="form-group item">
                        <label>Contacto: </label>
                        <input type="text" class="form-control" name="contacto" value="${empresa.contacto}">
                    </div>
                    <div class="form-group item">
                        <label>Descuento: </label>
                        <input type="text" class="form-control" name="descuento" value="${empresa.descuento}">
                    </div>
                    <input type="submit" class="btn btn-success  item" value="<%=tipoForm%>" name="accion" >
            </form>
        </div>
    </body>
</html>
