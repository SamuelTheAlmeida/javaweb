<%-- 
    Document   : erro
    Created on : 01/09/2018, 18:14:18
    Author     : SAMUEL
--%>

<%
  String pageReturn = (String)request.getAttribute("page");
  String msg = (String)request.getAttribute("msg");
  
    String email = (String)request.getServletContext().getAttribute("configuracao");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <h1>Erro: <% out.print(msg); %> </h1> 
            <a href="<%out.print(pageReturn); %>">Retornar</a>
        <footer>
            <p> Em caso de problemas contactar o administrador: <% out.print(email); %></p>
        </footer>
        </div>
    </body>
</html>
