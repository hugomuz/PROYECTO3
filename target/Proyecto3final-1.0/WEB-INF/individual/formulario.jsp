

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="modelo.Individual"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Individual</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <link rel="stylesheet" type="text/css" href="styles/st.css">
        
          <%
        String tipoForm= (String) request.getAttribute("tipoFormulario");
        Individual individual=null;
        if(tipoForm.equals("actualizar")){
        individual = (Individual) request.getAttribute("individual");
        }
    %>  
    </head>
    
    <body style="background-color:#07FEFE">
         <div class="container" style="margin-top: 10px; ">
        <jsp:include page="../includes/header.jsp"></jsp:include>
         </div>
        <div  class="cuerpo" style="margin:5px 75px 75px 75px;
                  border: activeborder 0px solid; padding: 5px 5px 5px">
            <h3 class="titulo" style="padding-bottom:10px"><%=tipoForm%> Individual</h3>
            <form action="IndividualCtrl" method="POST">
                    <div class="form-group item">
                        <label>Nombre:</label>
                        <input type="text" class="form-control" name="nombre" value="${individual.nombre}">
                    </div>    
                    <div class="form-group item">
                        <label>Telefono: </label>
                        <input type="text" class="form-control" name="id" value="${individual.id}" style="display:none">
                        <input type="text" class="form-control" name="telefono" value="${individual.telefono}">
                    </div>
                    <div class="form-group item">
                        <label>Direccion: </label>
                        <input type="text" class="form-control" name="direccion" value="${individual.direccion}">
                    </div>
                    <div class="form-group item">
                        <label>Dpi</label>
                        <input type="text" class="form-control" name="dpi" value="${individual.dpi}">
                    </div>
                    <input type="submit" class="btn btn-success  item" value="<%=tipoForm%>" name="accion" >
            </form>
        </div>
    </body>
</html>
