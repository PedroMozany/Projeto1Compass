package controller;

import DAO.ClienteDAO;
import DAO.EnderecoDAO;
import connection.ConnectionFactory;
import model.Cliente;
import model.Endereco;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Cadastro implements IAcao{


    @Override
    public String acao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        System.out.println("Cadastro");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String cep = request.getParameter("cep");
        String logradouro = request.getParameter("logradouro");
        String bairro = request.getParameter("bairro");
        String localidade = request.getParameter("localidade");
        String senha = request.getParameter("senha");

        System.out.println(nome + cpf + cep + logradouro + bairro + localidade + senha);


        try(Connection connection = new ConnectionFactory().initConexao()){
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

            Endereco endereco = new Endereco(cep,logradouro,bairro,localidade);
            Cliente cliente = new Cliente(nome,cpf,senha,endereco);
            clienteDAO.salvar(cliente);
            enderecoDAO.salvar(cliente,endereco);

        }

        return "forward:Login.jsp";
    }
}
