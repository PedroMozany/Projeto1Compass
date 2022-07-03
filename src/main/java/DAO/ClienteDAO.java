package DAO;

import model.Cliente;
import model.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {


    private Connection connection;


    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Cliente cliente) throws SQLException {
        String query = "INSERT INTO CLIENTE(CPF,NOME,SENHA) VALUE (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getSenha());
            ps.execute();
        }
    };

    public Cliente verificar(String cpf, String senha) throws SQLException {
        String query = "SELECT * FROM CLIENTE WHERE CPF = ? and senha = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cpf);
            ps.setString(2,senha);
            ps.execute();
            try (ResultSet rst = ps.getResultSet()) {
                if (rst.next()) {
                    Cliente cliente = new Cliente(rst.getString(2),rst.getString(1),rst.getString(3));
                    return cliente;
                } else {
                    return null;
                }
            }
        }
    };



    public Cliente buscar(String cpf) throws SQLException {
        String query = "SELECT * FROM CLIENTE WHERE CPF = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, cpf);
            ps.execute();
            try (ResultSet rst = ps.getResultSet()) {
                if (rst.next()) {
                    Cliente cliente = new Cliente(rst.getString(2),rst.getString(1),rst.getString(3));
                    return cliente;
                } else {
                    return null;
                }
            }
        }
    };


    public Cliente registro(String cpf) throws SQLException {
        String query = "SELECT NOME, CPF, SENHA, CEP, LOGRADOURO, BAIRRO, LOCALIDADE FROM( SELECT * FROM CLIENTE RIGHT JOIN(SELECT * FROM ENDERECO)ENDERECO ON CLIENTE.CPF = ENDERECO.FK_CPF_CLIENTE )CONSULTA WHERE CPF = ?";
        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,cpf);
            ps.execute();
            try(ResultSet rs = ps.getResultSet()){
                if(rs.next()){
                   return  new Cliente(rs.getString(1),rs.getString(2),rs.getString(3),new Endereco(rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7)));
                }else {
                    return null;
                }
            }
        }
    }


}
