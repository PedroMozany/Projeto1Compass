package controller;

import DAO.CarrinhoDAO;
import connection.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class FinalizaCompra implements IAcao {

    @Override
    public String acao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        System.out.println("entrei");
        String cpf = request.getParameter("cpf");
        System.out.println(cpf);

        try(Connection connection = new ConnectionFactory().initConexao()){
            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(connection);
            carrinhoDAO.deletar(cpf);
        }

        return "forward:Finalizar.jsp";
    }
}
