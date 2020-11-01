
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema De Ventas</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="login.css"><link>
       
    </head>
    <body >
        
  
        
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <!-- Tabs Titles -->
                <!-- Icon -->
                <div class="fadeIn first">
                   
                    <img src="C:\Users\PEDRO\Desktop\proyectotres\target\Prueba1.0-1.0\fondos\logoo.gif" id="icon" alt="User Icon" />
                    
                </div>

                <!-- Login Form -->
                 <form action="LoginCtrl" method="GET">
                    <input type="text"  class="text-Primary" name="usuario" placeholder="USUARIO">
        
                    <input type="password" class="fadeIn third" name="clave" placeholder="CONTRASEÑA">
                   
                    <input type="submit" class="fadeIn fourth" value="INGRESAR" name="accion">
                </form>

                <!-- Remind Passowrd -->
                <div id="formFooter">
                    <label class="p-3 mb-2 bg-transparent text-dark" >AutoPartes</label>
                   
                </div>

            </div>
        </div>

    </div>


</body>
</html>
