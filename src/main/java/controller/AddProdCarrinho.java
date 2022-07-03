package controller;

import DAO.CarrinhoDAO;
import DAO.ClienteDAO;
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

public class AddProdCarrinho implements IAcao {


    @Override
    public String acao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        System.out.println("AddProdCarrinho");
        String idProduto = request.getParameter("id");
        String cpf = request.getParameter("cpf");


        try (Connection connection = new ConnectionFactory().initConexao()) {
            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(connection);
            carrinhoDAO.salvar(cpf, Integer.parseInt(idProduto));
        }

        return "redirect:Entrada?acao=MostrarProdutos";

    }
}
