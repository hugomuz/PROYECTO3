<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <link rel="stylesheet" type="text/css" href="styles/doctor.css">
        
          <%
        String tipoForm= (String) request.getAttribute("tipoFormulario");
        Producto producto=null;
        if(tipoForm.equals("actualizar")){
        producto = (Producto) request.getAttribute("producto");
        }
    %>  
    </head>
    <body style="color:#1935E5;">
        
    </body>
    <body  background="C:\Users\PEDRO\Desktop\proyectotres\target\Prueba1.0-1.0\img\fondo.jpg"> </style>
        <div class="container" style="margin-top: 25px; ">
        <jsp:include page="../includes/header.jsp"></jsp:include>
        </div>
        <div  class="cuerpo" style="margin:25px 100px 100px 100px;
               border: activeborder 0px solid; padding: 15px 15px 15px">
            <h3 class="titulo"><%=tipoForm%> Producto</h3>
            <form action="ProductoCtrl" method="POST">
                    <div class="form-group item">
                        <label>Nombre:</label>
                        <input type="text" class="form-control" name="nombre" value="${producto.nombre}">
                    </div>    
                    <div class="form-group item">
                        <label>Descripcion:</label>
                        <input type="text" class="form-control" name="id" value="${producto.id}" style="display:none">
                        <input type="text" class="form-control" name="descripcion" value="${producto.descripcion}">
                    </div>
                    <div class="form-group item">
                        <label>Precio:</label>
                        <input type="text" class="form-control" name="precio" value="${producto.precio}">
                    </div>
                    <div class="form-group item">
                        <label>Cantidad:</label>
                        <input type="text" class="form-control" name="cantidad" value="${producto.cantidad}">
                    </div>
                    <input type="submit" class="btn btn-success  item" value="<%=tipoForm%>" name="accion" >
            </form>
        </div>
    </body>
</html>
