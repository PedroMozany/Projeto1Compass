package model;

public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;

    public Endereco(String cep,String logradouro,String bairro,String localidade){
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }


    @Override
    public String toString() {
        return "cep: " + cep + '\n' +
                "logradouro: " + logradouro + '\n' +
                "bairro: " + bairro + '\n' +
                "localidade: " + localidade;
    }
}
