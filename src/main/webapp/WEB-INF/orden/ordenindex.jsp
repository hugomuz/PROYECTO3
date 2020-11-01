<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ordenes</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
         <link rel="stylesheet" type="text/css" href="styles/st.css">
    </head>
    <body  background="C:\Users\PEDRO\Documents\NetBeansProjects\Prueba1.0\target\Prueba1.0-1.0\WEB-INF\img\nieve.gif"  >   
    </body>
    <body  style="color:#1935E5;" >
        <div class="container" style="margin-top: 10px; ">
        <jsp:include page="../includes/header.jsp"></jsp:include>
        </div>
        
        <div class="cuerpo col-sm-9 " style="margin-left: 100px" >
            <form action="OrdenCtrl" method="GET">
               <div class="col-sm-9"> 
               <h3 class="titulo" style="margin-left: 15px">Lista de Ordenes</h3>  
               <input type="submit" name="accion" value="nuevo" class="btn btn-success nuevo" style="margin-left: 10px" >
               </div>
            </form>
            
           <div style="margin-left:25px;" >
                   <c:forEach items="${ordenes}" var="orden">
                        <div class="col-sm-4 item" style="padding-top:20px;" >
                            <div class="card ">
                                <div class="card-body">
                                    <h5 class="card-title" style="text-transform: uppercase; font-weight: 700;">Orden: ${orden.id}</h5>
                                    <h5 class="card-text" style="">Cliente: ${orden.cliente.nombre}</h5>
                                    <h5 class="card-text" style="">Fecha: ${orden.fechaOrden}</h5>
                                    <h5 class="card-text" style="">Precio Envio: ${orden.precioEnvio}</h5>
                                    <h5 class="card-text" style="">Tipo Envio: ${orden.tipoEnvio}</h5>
                                    <h5 class="card-text" style="">Estado Orden: ${orden.estado}</h5>
                                    <h5 class="card-text" style="">Producto 1: ${orden.item1.producto.nombre}</h5>
                                    <h5 class="card-text" style="">Producto 2: ${orden.item2.producto.nombre}</h5>
                                    <h5 class="card-text" style="">Dias Envio: ${orden.diasEnvio}</h5>
                                    <h5 class="card-text" style="">Total: ${orden.total}</h5>
                                    
                                    
                                    <form action="OrdenCtrl" method="GET" style="display:inline-block">
                                        <input type="hidden" name="id" value="${orden.id}">
                                        <input type="submit" name="accion" value="editar" class="btn btn-warning btn-sm">       
                                    </form>
                                         <form action="OrdenCtrl" method="GET" style="display:inline-block">
                                        <input type="hidden" name="id" value="${orden.id}">
                                        <input type="submit" name="accion" value="detalles" class="btn btn-warning btn-sm">       
                                    </form>
                                    <form  action="OrdenCtrl" method="POST" style="display:inline-block">
                                        <input type="hidden" name="id" value="${orden.id}">
                                        <input type="submit" name="accion" value="eliminar" class="btn btn-danger btn-sm">
                                    </form>
                                </div>           
                            </div>  
                        </div>
                   </c:forEach>
               </div>
        </div>
        
    </body>
</body>
</html>
