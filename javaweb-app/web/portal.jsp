<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<% 
   int idUsuario;
   ServletContext ctx = request.getServletContext();
   String email = (String)ctx.getAttribute("configuracao");
   try {
       idUsuario = ((LoginBean)session.getAttribute("loginBean")).getId();
   } catch (Exception e) {
       idUsuario = 0;
   }
   
   if (idUsuario == 0) { 
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
        rd.forward(request, response);
   } else {
  %>
<jsp:useBean id="loginBean" class="com.ufpr.tads.web2.beans.LoginBean" scope="session"/>

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
        <h1>Bem vindo, <jsp:getProperty name="loginBean" property="nome"/></h1>
        <nav class="navbar navbar-default">
            <a class="btn btn-default" href="ClientesServlet"><strong class="text-info">Cadastro de Clientes</strong></a>
            <a class="btn btn-default" href="LogoutServlet"><strong class="text-warning">Logout</strong></a>         
        </nav>

        <br>
        <footer>
            <p> Em caso de problemas contactar o administrador: <% out.print(email); %></p>
        </footer>
        </div>
    </body>
</html>
<% } %>

