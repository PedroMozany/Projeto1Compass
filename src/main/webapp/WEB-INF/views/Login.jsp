<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align: center">Login</h1>
<form style="padding-left: 320px; padding-top: 100px" class="row g-3" action="Entrada?acao=Login" method="post">
    <div class="col-auto">
        <label  class="visually-hidden">CPF</label>
        <input type="txt" class="form-control" placeholder="CPF" name="cpf">
    </div>
    <div class="col-auto">
        <label  class="visually-hidden">Password</label>
        <input type="password" class="form-control" name="senha" placeholder="Password">
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary mb-3">ENTRAR</button>
    </div>
    <a href="Entrada?acao=MostrarFormulario">CASDATRA</a>
</form>




</body>
</html>
