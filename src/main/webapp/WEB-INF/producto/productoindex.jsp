<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <body background="C:\Users\PEDRO\Documents\NetBeansProjects\Prueba1.0\target\Prueba1.0-1.0\WEB-INF\img\carro.jpeg" bgcolor="white" >
    
</body>
    <head >
    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/st.css">
    
    </head>
    
    
        <body style="color:#fff;" >

        
        <div  class="container" style="margin-top: 15px; ">  
        <jsp:include page="../includes/header.jsp"></jsp:include>
        </div>
        
       <div class="cuerpo col-sm-9 " style="margin-left: 100px" >
            <form action="ProductoCtrl" method="GET">
               <div class="col-sm-9"> 
               <h3 class="titulo" style="margin-left: 25px" style="color:#fff;">Lista de Productos</h3>  
               
               <input type="submit" name="accion" value="nuevo" class="btn btn-success nuevo" style="margin-left: 25px">
               </div>
            </form>
           
             <div style="margin-left:30px;">
                   <c:forEach items="${productos}" var="producto">
                        <div class="col-sm-4 item" style="padding-top:25px;">
                            <div class="card ">
                                <div class="card-body">
                                    <h4 class="card-title" style="text-transform: uppercase; font-weight: 700;">${producto.nombre}</h4>
                                    <h5 class="card-text" style="">Descipcion: ${producto.descripcion}</h5>
                                    <h5 class="card-text" style="">Precio unidad: ${producto.precio}</h5>
                                    <h5 class="card-text" style="">Cantidad: ${producto.cantidad}</h5>
                                    
                                    <form action="ProductoCtrl" method="GET" style="display:inline-block">
                                        <input type="hidden" name="id" value="${producto.id}">
                                        <input type="submit" name="accion" value="editar" class="btn btn-warning btn-sm">       
                                    </form>
                                    <form  action="ProductoCtrl" method="POST" style="display:inline-block">
                                        <input type="hidden" name="id" value="${producto.id}">
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
