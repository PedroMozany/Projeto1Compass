<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Cliente" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
Ususario:${logado.nome}<br>
<body>
<a href="Entrada?acao=MostrarLogin">Login</a><br>
<a href="Entrada?acao=MostrarFormulario">Cadastro</a><br>
<a href="Entrada?acao=Logout">Sair</a>
</body>
</html>
