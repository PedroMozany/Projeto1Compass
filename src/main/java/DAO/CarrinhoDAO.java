package DAO;

import model.Cliente;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CarrinhoDAO {


    private Connection connection;

    public CarrinhoDAO(Connection connection) {
        this.connection = connection;
    }


    public void salvar(String cpf, int idProduto) throws SQLException {
        String query = "INSERT INTO NF(FK_CPF_CLIENTE,FK_ID_PRODUTO) VALUE (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cpf);
            ps.setInt(2, idProduto);
            ps.execute();
        }
    };

    public List<Produto> listCompra(String cpf) throws SQLException {
        String query =  "SELECT FK_ID_PRODUTO, NOME, PREÇO FROM( SELECT FK_CPF_CLIENTE, FK_ID_PRODUTO, NOME, PREÇO FROM NF LEFT JOIN( SELECT ID, NOME, PREÇO FROM PRODUTO )PRODUTO ON FK_ID_PRODUTO = PRODUTO.ID WHERE FK_CPF_CLIENTE = ? )CONSULTA";
       List<Produto> list = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cpf);
            ps.execute();
            try (ResultSet rst = ps.getResultSet()) {
               while (rst.next()){
                   list.add(new Produto(rst.getString(2), rst.getDouble(3),rst.getInt(1)));
               }
            }
        }
        return list;
    };

    public void deletar(String cpf) throws SQLException {
        String query = "DELETE FROM NF WHERE FK_CPF_CLIENTE = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cpf);
            ps.execute();
        }
    }

}
