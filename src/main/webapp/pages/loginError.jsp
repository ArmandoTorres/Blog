<%-- 
    Document   : loginError
    Created on : May 22, 2014, 2:11:16 AM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
        <link rel="shortcut icon" href="images/icons/java.png">
        <!-- Bootstrap core CSS -->
        <link type="text/css" href="css/bootstrapMain.css" rel="stylesheet">
        <link type="text/css" href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">    
            <div class="panel panel-info">
               <div class="panel-heading">
                 <h3 class="panel-title">Login Error</h3>
               </div>
               <div class="panel-body">
                 ${mensaje}
                 &nbsp;
                 <a href="<c:url value="Login.htm"/>"> Retry </a>
               </div>
            </div>
        </div>       
    </body>
</html>