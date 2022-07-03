package model;

public class Produto {
    private int idProduto;
    private String nome;
    private double preco;


    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto(String nome, double preco, int idProduto) {
        this.nome = nome;
        this.preco = preco;
        this.idProduto = idProduto;
    }

    public int getIdProduto() {
        return this.idProduto;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "ID:" + idProduto + "," +
                "nome: " + nome + "," +
                "Preço " + preco;
    }
}
