<%@page import="modelo.Cliente"%>
<%@page import="modelo.Producto"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles Ordenes</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="styles/st.css">         
    </head>
    <%

        String tipo = (String) request.getAttribute("tipo");
    %>
    <body  style="color: #fff"></body>
    <body   background="C:\Users\PEDRO\Desktop\proyectotres\target\Prueba1.0-1.0\fondos\WhatsApp Image 2020-10-31 at 14.46.05.jpeg">   
        <div class="container" style="margin-top: 5px; ">
            <jsp:include page="../includes/header.jsp"></jsp:include>
            </div>
            <div class="cuerpo col-sm-9 " style="margin-left: 100px;  padding-bottom:50px;" >
                <h3 class="titulo" style="margin-left: 45%">Detalles Orden ${orden.id}</h3>  
            <div style="margin-left:45%;" >
                <div class="col-sm-12 item" style="padding-top:0px;" >
                    <div class="card ">
                        <div class="card-body">
                            <h3 class="card-title" style="text-transform: uppercase; font-weight: 700;">Orden ${orden.id}</h3>
                            <h5 class="card-text" style="">Fecha: ${orden.fechaOrden}</h5>
                            <h5 class="card-text" style="">Tipo Envio: ${orden.tipoEnvio}</h5>
                            <h5 class="card-text" style="">Estado Orden: ${orden.estado}</h5>
                            <h5 class="card-text" style="">Dias Envio: ${orden.diasEnvio}</h5>
                            <h5 class="card-text" style="">Precio Envio: ${orden.precioEnvio}</h5>
                            <div>
                                <h4 class="card-text"  style="text-transform: uppercase; font-weight: 700;">Datos Del Cliente</h4>
                                <h5 class="card-text" style="">Tipo Cliente: ${orden.cliente.tipoCliente}</h5>
                                <h5 class="card-text" style="">Nombre: ${orden.cliente.nombre}</h5>
                                <h5 class="card-text" style="">Telefono: ${orden.cliente.telefono}</h5>
                                <h5 class="card-text" style="">Direccion: ${orden.cliente.direccion}</h5>
                                <% if (tipo.equals("empresa")) {%>
                                <h5 class="card-text" style="">Contacto: ${orden.cliente.contacto}</h5>
                                <h5 class="card-text" style="">Descuento: ${orden.cliente.descuento}</h5>
                                <%} else {%>
                                <h5 class="card-text" style="">Dpi: ${orden.cliente.dpi}</h5>
                                <%}%>
                            </div>
                            <div>
                                <h4 class="card-text"  style="text-transform: uppercase; font-weight: 700;">Datos Del Producto 1</h4>
                                <h5 class="card-text" style="">Nombre: ${orden.item1.producto.nombre}</h5>
                                <h5 class="card-text" style="">Precio Unidad: ${orden.item1.producto.precio}</h5>
                                <h5 class="card-text" style="">Cantidad : ${orden.item1.cantidad}</h5>
                                <h5 class="card-text" style="">Costo : ${orden.item1.getTotalItem()}</h5>
                            </div>
                            <div>
                                <h4 class="card-text"  style="text-transform: uppercase; font-weight: 700;">Datos Del Producto 2</h4>
                                <h5 class="card-text" style="">Nombre: ${orden.item2.producto.nombre}</h5>
                                <h5 class="card-text" style="">Precio Unidad: ${orden.item2.producto.precio}</h5>
                                <h5 class="card-text" style="">Cantidad : ${orden.item2.cantidad}</h5>
                                <h5 class="card-text" style="">Costo : ${orden.item2.getTotalItem()}</h5>
                            </div>
                            <div>
                                <h5 class="card-text" style="text-transform: uppercase; font-weight: 700;" >Total : ${orden.total}</h5>
                            </div>                               
                        </div>           
                    </div>  
                </div>
            </div>
        </div>
    </body>
</html>
