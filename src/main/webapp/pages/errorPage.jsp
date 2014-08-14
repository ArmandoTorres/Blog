<%-- 
    Document   : errorPage
    Created on : May 22, 2014, 2:56:32 AM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="images/icons/java.png">
        <link type="text/css" href="css/bootstrapMain.css" rel="stylesheet">
        <link type="text/css" href="css/login.css" rel="stylesheet">
        <title>Error page</title>
    </head>
    <body>
        <div class="container">    
            <div class="panel panel-info">
               <div class="panel-heading">
                 <h3 class="panel-title">Error!!!</h3>
               </div>
               <div class="panel-body">
                   ${pageContext.exception}
               </div>
            </div>
        </div>       
    </body>
</html>