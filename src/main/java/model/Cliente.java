package model;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
    private String nome;
    private  String cpf;
    private String senha;
    private Endereco endereco;

    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public Cliente(String nome, String cpf, String senha, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.endereco = endereco;

    }



    public String getNome() {
        return nome;
    }

    public String getCpf() {return cpf;}

    public String getSenha() {return senha;}

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario:" + this.nome + "\n");
        sb.append(endereco);
        return sb.toString();
    }
}