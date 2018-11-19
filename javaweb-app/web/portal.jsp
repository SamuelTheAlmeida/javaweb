<%@ page errorPage="erro.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>


<c:if test="${empty loginBean}">
    <jsp:forward page="/index.jsp"> 
        <jsp:param name="msg" value="Usuário precisa se autenticar para acessar o sistema" /> 
    </jsp:forward> 
</c:if>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Portal</title>
        <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">
        <style>
            #center {
              width: 60%;
              margin: 150px auto;
            }
        </style>
    </head>
    <body>
        <div class="container text-center" id="center">
        <h1> Bem vindo, ${loginBean.nome}</h1>
        <nav class="navbar navbar-default">
            <a class="btn btn-default" href="ClientesServlet"><strong class="text-info">Cadastro de Clientes</strong></a>
            <a class="btn btn-default" href="LogoutServlet"><strong class="text-warning">Logout</strong></a>         
        </nav>

        <br>
        <footer>
            <p> Em caso de problemas contactar o administrador: ${$email} </p>
        </footer>
        </div>
    </body>
</html>

