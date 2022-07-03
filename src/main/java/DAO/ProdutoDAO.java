package DAO;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String query = "INSERT INTO PRODUTO(NOME,PREÇO) VALUE (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.execute();
        }
    }

    public  Produto buscar(int id) throws SQLException {
        String query = "SELECT * FROM PRODUTO WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {
                    return new Produto(rs.getString(2), rs.getDouble(3));
                } else {
                    return null;
                }
            }
        }
    }


    public List<Produto> produtosEstoque() throws SQLException {
        List<Produto> list = new LinkedList<>();
        String query = "SELECT * FROM PRODUTO";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
            try (ResultSet rs = ps.getResultSet()) {
                while (rs.next()) {
                    list.add(new Produto(rs.getString(2), rs.getDouble(3), rs.getInt(1)));
                }

            }
        }
        return list;
    }

}
