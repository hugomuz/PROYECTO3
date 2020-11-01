<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empresa</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/st.css">
    </head>
    <body  style="color:#1935E5;">   
         <body background="C:\Users\PEDRO\Desktop\proyectotres\target\Prueba1.0-1.0\fondos\nieve.gif">
        </body>
        <div class="container" style="margin-top: 5px; ">  
        <jsp:include page="../includes/header.jsp"></jsp:include>
        </div>
        
        
       <div class="cuerpo col-sm-9 " style="margin-left: 100px" >
        
           <form action="EmpresaCtrl" method="GET">
               <div class="col-sm-9"> 
               <h3  class="titulo" style="margin-left: 10px">Lista de Empresas</h3>  
               <input type="submit" name="accion" value="nuevo" class="btn btn-success nuevo" style="margin-left: 10px">
               </div>
            </form>
           
           <div style="margin-left:20px;" >
                   <c:forEach items="${empresas}" var="empresa">
                        <div class="col-sm-4 item" style="padding-top:15px;">
                            <div class="card ">
                                <div class="card-body">
                                    <h4 class="card-title" style="text-transform: uppercase; font-weight: 600;">${empresa.nombre}</h4>
                                    <h5 class="card-text" style="">Telefono ${empresa.telefono}</h5>
                                    <h5 class="card-text" style="">Direccion  ${empresa.direccion}</h5>
                                    <h5 class="card-text" style="">Contacto ${empresa.contacto}</h5>
                                    <h5 class="card-text" style="">Descuento ${empresa.descuento}</h5>
                                    
                                    <form action="EmpresaCtrl" method="GET" style="display:inline-block">
                                        <input type="hidden" name="id" value="${empresa.id}">
                                        <input type="submit" name="accion" value="editar" class="btn btn-warning btn-sm">       
                                    </form>
                                    <form  action="EmpresaCtrl" method="POST" style="display:inline-block">
                                        <input type="hidden" name="id" value="${empresa.id}">
                                        <input type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">
                                    </form>
                                </div>           
                            </div>  
                        </div>
                   </c:forEach>
               </div>
        </div>
    </body>
</html>
