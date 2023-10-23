package br.edu.ifpi.poo.endereco;
public class Endereco {
    private String rua;
    private String bairro;
    private String cep;
    private int numero;

    public Endereco (String rua, String bairro, String cep, int numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }
}
