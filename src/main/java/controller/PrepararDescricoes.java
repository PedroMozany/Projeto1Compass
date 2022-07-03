package controller;

import DAO.CarrinhoDAO;
import DAO.ClienteDAO;
import connection.ConnectionFactory;
import model.Cliente;
import model.Produto;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PrepararDescricoes implements IAcao {
    @Override
    public String acao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, ParserConfigurationException, SAXException {
        String cpf = request.getParameter("cpf");
        String idProduto = request.getParameter("id");


        try (Connection connection = new ConnectionFactory().initConexao()) {
            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(connection);
            carrinhoDAO.salvar(cpf, Integer.parseInt(idProduto));
        }


        Cliente cliente = informacao(cpf);
        List<Produto> list = produtosDoCarrinho(cpf);
        Frete frete = calcFrete(cliente.getEndereco().getCep());
        double valorTotalProdustos = valorTotal(list);
        double valototal = valorTotalProdustos + frete.getValor();



        HttpSession session = request.getSession();
        session.setAttribute("cliente", cliente);
        session.setAttribute("produtos", list);
        session.setAttribute("frete",frete);
        session.setAttribute("valorProduto",valorTotalProdustos);
        session.setAttribute("total",valototal);
        return "redirect:Entrada?acao=MostrarDiscricoes";
    }

    public List<Produto> produtosDoCarrinho(String cpf) throws SQLException, ClassNotFoundException {
        List<Produto> list;
        try (Connection connection = new ConnectionFactory().initConexao()) {
            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(connection);
            list = carrinhoDAO.listCompra(cpf);
        }
        return list;
    }

    public double valorTotal(List<Produto> list) throws SQLException, ClassNotFoundException {
                         return  list.stream()
                                      .mapToDouble(Produto::getPreco)
                                      .sum();
    }

    public Cliente informacao(String cpf) throws SQLException, ClassNotFoundException {
        Cliente cliente;
        try(Connection connection = new ConnectionFactory().initConexao()){
            ClienteDAO clienteDAO = new ClienteDAO(connection);
            cliente = clienteDAO.registro(cpf);
        }
        return cliente;
    }

    public Frete calcFrete(String cep) throws IOException, ParserConfigurationException, SAXException {
        Frete frete = new Frete(cep);
        return frete;
    }
}
