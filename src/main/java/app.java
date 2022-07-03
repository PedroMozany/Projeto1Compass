import DAO.ProdutoDAO;
import connection.ConnectionFactory;
import model.Produto;

import java.sql.Connection;
import java.sql.SQLException;


public class app {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Produto iphone = new Produto("IPHONE X 16GB",3500);
        Produto xbox = new Produto("xboxONE",2500);
        Produto notebook = new Produto("DELL 16GB CORE9°",5000);
        Produto ps4 = new Produto("PS4",2000);
        Produto notebook2 = new Produto("SAMSUNG 8GB CORE3°",2300);
        Produto smartphone = new Produto("GALAX21 32GB ",3750);
        Produto smartTv = new Produto("SMARTV LG",2000);

        try(Connection connection = new ConnectionFactory().initConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(iphone);
            produtoDAO.salvar(xbox);
            produtoDAO.salvar(notebook);
            produtoDAO.salvar(ps4);
            produtoDAO.salvar(notebook2);
            produtoDAO.salvar(smartphone);
            produtoDAO.salvar(smartTv);
        }
    }
}
