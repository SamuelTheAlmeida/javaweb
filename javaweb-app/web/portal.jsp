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
        RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
        request.setAttribute("msg", "Usuario não logado");
        request.setAttribute("page", "index.html");
        rd.forward(request, response);
   } else {
  %>
<jsp:useBean id="loginBean" class="com.ufpr.tads.web2.beans.LoginBean" scope="session"/>

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
        <h1>Bem vindo, <jsp:getProperty name="loginBean" property="nome"/></h1>
        <a class="btn btn-success" href="inserir.jsp">Inserir novo</a>
        <a class="btn btn-success" href="LogoutServlet">Logout</a>
        <br>
        <footer>
            <p> Em caso de problemas contactar o administrador: <% out.print(email); %></p>
        </footer>
        </div>
    </body>
</html>
<% } %>

