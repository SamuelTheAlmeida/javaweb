<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<% 
    int id = 0;
    Cliente cliente = (Cliente)request.getAttribute("cliente");
    ServletContext ctx = request.getServletContext();
    RequestDispatcher rd;
    try {
        id = ((LoginBean)session.getAttribute("loginBean")).getId();
    } catch (Exception e) {
        id = 0;
    }
    
    if (id == 0) {
        rd = ctx.getRequestDispatcher("/index.jsp");
        request.setAttribute("msg", "Usuário precisa estar autenticado para acessar ao sistema");
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
        <form class="" method="POST" action="ClientesServlet">
          <input type="hidden" name="action" value="update" />

            <input hidden type="text" name="idCliente" id="idCliente" value="<% out.print(cliente.getId()); %>" >
        <div class="form-group">
          <label for="inputCPF">CPF</label>
          <input type="text" class="form-control" id="inputCPF" name="cpf" value=" ${cliente.cpf}">
        </div>
        <div class="form-group">
          <label for="inputNome">Nome</label>
          <input type="text" class="form-control" id="inputNome" name="nome" value="${cliente.nome}">
        </div>
        <div class="form-group">
          <label for="inputEmail">Email</label>
          <input type="email" class="form-control" id="inputEmail" name="email" value="${cliente.email}">
        </div>
       <div class="form-group">
          <label for="inputRua">Rua</label>
          <input type="text" class="form-control" id="inputRua" name="rua" value="${cliente.rua}">
        </div>
        <div class="form-group">
          <label for="inputNúmero">Número</label>
          <input type="number" class="form-control" id="inputNumero" name="numero" value="${cliente.numero}">
        </div>
       <div class="form-group">
          <label for="inputCep">CEP</label>
          <input type="number" class="form-control" id="inputCEP" name="cep" value="${cliente.cep}">
        </div>
       <div class="form-group">
          <label for="inputCidade">Cidade</label>
          <input type="text" class="form-control" id="inputCidade" name="cidade" value="${cliente.cidade}">
        </div>
       <div class="form-group">
          <label for="inputCidade">UF</label>
          <input type="text" class="form-control" id="inputUF" name="uf" value="${cliente.uf}">
        </div>
        
       
        <button type="submit" class="btn btn-default">Salvar</button>
        <a href="ClientesServlet"><button type="button" class="btn btn-default">Cancelar</button></a>
      </form>

        <br>
        </div>
    </body>
</html>
