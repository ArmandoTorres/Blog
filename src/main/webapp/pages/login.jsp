<%-- 
    Document   : login
    Created on : May 22, 2014, 9:56:51 AM
    Author     : Armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Login page for Programmer Blog">
    <meta name="author" content="ATORRES">
    <link rel="shortcut icon" href="images/icons/java.png">
    <title>Login</title>
    <!-- Bootstrap core CSS -->
    <link type="text/css" href="css/bootstrapMain.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link type="text/css" href="css/login.css" rel="stylesheet">
  </head>

  <body>
    <div class="container">
      <form action="<c:url value='Login.htm'/>" method="POST" class="form-signin" role="form">
        <h2 class="form-signin-heading">Login</h2>
        <input type="text" name="userName" class="form-control" placeholder="User Name" required autofocus>
        <input type="password" name="pass" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
    </div> <!-- /container -->
  </body>
</html>
