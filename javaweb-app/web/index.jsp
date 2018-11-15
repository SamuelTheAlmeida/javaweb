<%@ page errorPage="erro.jsp" %>
<% 
    String erro = (String)request.getAttribute("msg");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>App</title>

    <!-- Bootstrap -->
    <link href="https://bootswatch.com/3/superhero/bootstrap.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style type="text/css">
    	#center {
    		width: 30%;
    		margin: 150px auto;
    	}
    </style>
  </head>
  <body>
	<div class="container text-center" id="center">
            <%  if (erro != null) {  %>
            <div class="alert">
                <span class="text-danger"><strong><% out.print(erro); %> </strong></span>
            </div>
            <% } %>
		<h1>Login</h1>
		<form method="POST" action="LoginServlet">
		  <div class="form-group">
		    <label for="inputUsuario">Usuario</label>
		    <input name="usuario" type="text" class="form-control" id="inputUsuario" placeholder="Usuario">
		  </div>
		  <div class="form-group">
		    <label for="inputSenha">Senha</label>
		    <input name="senha" type="password" class="form-control" id="inputSenha" placeholder="Senha">
		  </div>
		  <button type="submit" class="btn btn-default">Login</button>
		</form>
	</div>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
