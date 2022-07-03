package controller;

import DAO.ProdutoDAO;
import connection.ConnectionFactory;
import model.Cliente;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MostrarProdutos implements IAcao {
    @Override
    public String acao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        System.out.println("MostrarProdutos");
        String cliente = request.getParameter("nome");
        String cpf = request.getParameter("cpf");

        System.out.println(cpf);

        List<Produto> list;
        try (Connection connection = new ConnectionFactory().initConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            list = produtoDAO.produtosEstoque();

        }

        request.setAttribute("produtos", list);
        request.setAttribute("cpf", cpf);
        request.setAttribute("nome", cliente);
        return "forward:Produtos.jsp";
    }
}
