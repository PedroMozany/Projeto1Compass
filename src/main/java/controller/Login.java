package controller;

import DAO.ClienteDAO;
import connection.ConnectionFactory;
import model.Cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class Login implements IAcao {


    @Override
    public String acao(HttpServletRequest request, HttpServletResponse response) {
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");


        try (Connection connection = new ConnectionFactory().initConexao()) {
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            Cliente cliente = clienteDAO.verificar(cpf, senha);

            if (cliente != null) {
                HttpSession session = request.getSession();
                session.setAttribute("logado",cliente);
                return "redirect:Entrada?acao=MostrarProdutos";
            } else {
                return "forward:Login.jsp";
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}


