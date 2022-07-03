<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<H1 style="text-align: center">CASDATRO</H1>
<form class="row g-3" action="Entrada?acao=Cadastro" method="post">
    <div class="col-md-4">
        <label for="validationServer01" class="form-label">NOME</label>
        <input type="text" class="form-control" id="validationServer01" name="nome" required>
    </div>
    <div class="col-md-4">
        <label for="validationServer02" class="form-label">CPF</label>
        <input type="text" class="form-control" id="validationServer02" name="cpf" required>
    </div>
    <div class="col-md-4">
        <label for="validationServer03" class="form-label">CEP</label>
        <input type="text" class="form-control" id="validationServer03" name="cep" required>
    </div>
    <div class="col-md-4">
        <label for="validationServer04" class="form-label">ENDEREÇO</label>
        <input type="text" class="form-control" id="validationServer04" name="logradouro" required>
    </div>
    </div>
    <div class="col-md-4">
        <label for="validationServer05" class="form-label">BAIRRO</label>
        <input type="text" class="form-control" id="validationServer05" name="bairro" required>
    </div>
    <div class="col-md-4">
        <label for="validationServer06" class="form-label">CIDADE</label>
        <input type="text" class="form-control" id="validationServer06" name="localidade" required>
    </div>
    <div class="col-md-4">
        <label for="validationServer07" class="form-label">SENHA</label>
        <input type="text" class="form-control" id="validationServer07" name="senha" required>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">FINALIZAR</button>
    </div>
</form>
</body>
</html>
