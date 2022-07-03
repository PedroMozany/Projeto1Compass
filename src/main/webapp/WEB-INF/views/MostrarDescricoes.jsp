<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List, model.Produto,model.Cliente,controller.Frete " %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CARRINHO</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align: center">CARRINHO</h1>
<div>
  <samp style="font-size: 16px">Usuario:</samp>${cliente.nome}<br>
    <samp style="font-size: 16px">CPF:</samp>${cliente.cpf}
</div>

<div>
    <h4>Local da Entrega</h4>
    <samp style="font-size: 16px">CEP:</samp>${cliente.endereco.cep}<br>
    <samp style="font-size: 16px">ENDEREÇO:</samp>${cliente.endereco.logradouro}<br>
    <samp style="font-size: 16px">BAIRRO:</samp>${cliente.endereco.bairro}<br>
    <samp style="font-size: 16px">CIDADE:</samp>${cliente.endereco.localidade}<br>
</div>


<h4 style="text-align: center">Produtos</h4>
<div style="padding-left: 500px">
        <c:forEach items="${produtos}" var="produto">
            <li>
                    ${produto.nome} R$:${produto.preco}<br>
            </li>
            <br>
        </c:forEach>
</div>
<div>
    <h4 style="text-align: center">Frete</h4>
    Valor:${frete.valor} | Prazo:${frete.prazo} | Entrega Domiciliar:${frete.entregaDomiciliar} | Entraga finais de semana:${frete.entregaFinais}<br>
</div>
Valor do total sem frete:${valorProduto}<br>
valor total:${total}<br>
<div style="padding-left: 600px">
    <form action="Entrada?acao=FinalizaCompra" method="post">
        <input type="hidden" name="cpf" value="${cliente.cpf}">
        <button type="submit" class="btn btn-outline-primary">FINALIZAR</button>
    </form>
</div>
</body>
</html>
