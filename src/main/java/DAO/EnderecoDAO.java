package DAO;

import model.Cliente;
import model.Endereco;

import javax.swing.plaf.PanelUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoDAO {
    private Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Cliente cliente, Endereco endereco) throws SQLException {
        String query = "INSERT INTO ENDERECO(CEP,LOGRADOURO,BAIRRO,LOCALIDADE,FK_CPF_CLIENTE) VALUE (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setString(3, endereco.getBairro());
            ps.setString(4, endereco.getLocalidade());
            ps.setString(5, cliente.getCpf());
            ps.execute();
        }
    }

}
