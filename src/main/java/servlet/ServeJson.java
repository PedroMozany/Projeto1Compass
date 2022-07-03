package servlet;

import DAO.CarrinhoDAO;
import DAO.ClienteDAO;
import com.google.gson.Gson;
import connection.ConnectionFactory;
import controller.Frete;
import model.Cliente;
import model.Produto;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/freteJson")
public class ServeJson extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cliente cliente;
        Frete frete;

        String cpf = request.getParameter("cpf");
        String idProduto = request.getParameter("id");

        try {
            addUltimoItem(cpf, idProduto);
            cliente = informacao(cpf);
            frete = calcFrete(cliente.getEndereco().getCep());
            limparCarrinho(cpf);
        } catch (SQLException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        Gson gson = new Gson();
        String json1 = gson.toJson(frete);
        String json2 = gson.toJson(cliente);

        response.setContentType("application/json");
        response.getWriter().print(json2 + json1);

    }


    public void limparCarrinho(String cpf) throws SQLException {
        try (Connection connection = new ConnectionFactory().initConexao()) {
            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(connection);
            carrinhoDAO.deletar(cpf);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUltimoItem(String cpf, String idProduto) throws SQLException, ClassNotFoundException {
        try (Connection connection = new ConnectionFactory().initConexao()) {
            CarrinhoDAO carrinhoDAO = new CarrinhoDAO(connection);
            try {
                carrinhoDAO.salvar(cpf, Integer.parseInt(idProduto));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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
        return list.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
    }

    public Cliente informacao(String cpf) throws SQLException, ClassNotFoundException {
        Cliente cliente;
        try (Connection connection = new ConnectionFactory().initConexao()) {
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
