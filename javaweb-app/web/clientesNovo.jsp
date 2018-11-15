<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<%@ page errorPage="erro.jsp" %>

<%
   int id;
        try {
            id = ((LoginBean)session.getAttribute("loginBean")).getId();
        } catch (Exception e) {
            id = 0;
        }
    
    if (id == 0) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        request.setAttribute("msg", "Usuario deve se autenticar para acessar o sistema");
        rd.forward(request, response);
    }
%>

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
        <form class="" method="POST" action="ClientesServlet">
            <input type="hidden" name="action" value="new" />
        <div class="form-group">
          <label for="inputCPF">CPF</label>
          <input type="text" class="form-control" id="inputCPF" name="cpf" required="required">
        </div>
        <div class="form-group">
          <label for="inputNome">Nome</label>
          <input type="text" class="form-control" id="inputNome" name="nome" required="required">
        </div>
        <div class="form-group">
          <label for="inputEmail">Email</label>
          <input type="email" class="form-control" id="inputEmail" name="email" required="required">
        </div>
       <div class="form-group">
          <label for="inputRua">Rua</label>
          <input type="text" class="form-control" id="inputRua" name="rua" required="required">
        </div>
        <div class="form-group">
          <label for="inputNúmero">Número</label>
          <input type="number" class="form-control" id="inputNumero" name="numero" required="required">
        </div>
       <div class="form-group">
          <label for="inputNúmero">CEP</label>
          <input type="number" class="form-control" id="inputCEP" name="cep" required="required">
        </div>
       <div class="form-group">
          <label for="inputCidade">Cidade</label>
          <input type="text" class="form-control" id="inputCidade" name="cidade" required="required">
        </div>
       <div class="form-group">
          <label for="inputCidade">UF</label>
          <input type="text" class="form-control" id="inputUF" name="uf" required="required">
        </div>
        
       
        <button type="submit" class="btn btn-default">Salvar</button>
        <a href="ClientesServlet"><button type="button" class="btn btn-default">Cancelar</button></a>
      </form>

        <br>
        </div>
    </body>
</html>
