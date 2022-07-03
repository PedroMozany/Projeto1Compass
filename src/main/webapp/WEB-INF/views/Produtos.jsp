<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List, model.Produto,model.Cliente " %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<h5>Usuario:${logado.nome}</h5><br>
<a href="Entrada?acao=Logout">Sair</a>
<body>
<h1 style="text-align: center">PRODUTOS</h1>

<section style="padding-left:300px">
    <c:forEach items="${produtos}" var="produto">
    <li>
            ${produto.nome} R$:${produto.preco}<br>
        <form action="Entrada?acao=AddProdCarrinho" method="post">
            <input type="hidden" name="id" value="${produto.idProduto}">
            <input type="hidden" name="cpf" value="${logado.cpf}">
            <input type="hidden" name="cliente" value="${logado.nome}">
            <input type="hidden" name="ops" value="1">
            <button type="submit" class="btn btn-outline-primary">ADD AO CARRINHO</button>
        </form>
        <form action="Entrada?acao=PrepararDescricoes" method="post">
            <input type="hidden" name="id" value="${produto.idProduto}">
            <input type="hidden" name="cpf" value="${logado.cpf}">
            <input type="hidden" name="ops" value="2">
            <button type="submit" class="btn btn-outline-primary"> COMPRAR</button>

        </form>
        <form action="freteJson" method="post">
            <input type="hidden" name="id" value="${produto.idProduto}">
            <input type="hidden" name="cpf" value="${logado.cpf}">
            <input type="hidden" name="ops" value="2">
            <button type="submit" class="btn btn-outline-primary">FINALIZAR COM JSON</button>
        </form>
        </c:forEach>
</section>
</body>
</html>
