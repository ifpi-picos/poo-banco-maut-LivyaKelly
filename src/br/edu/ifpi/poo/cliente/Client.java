package br.edu.ifpi.poo.cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifpi.poo.conta.Conta;
import br.edu.ifpi.poo.endereco.Endereco;

// Exibição dos atributos da classe Cliente
public class Client {
    private String nome;
    private String cpf;
    private LocalDate dn;
    private Endereco endereco;

    private List<Conta> contas;

    // Construtor da classe cliente
    public Client(String nome, String cpf, LocalDate dn, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dn = dn;
        this.endereco = endereco;
        this.contas = new ArrayList<Conta>(); 
    }

    // Retorna o valor atual do nome do cliente
    public String getNome() {
        return nome;
    }

    // Permite definir ou atualizar o valor do nome
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // Retorna o valor do atributo cpf
    public String getCpf() {
        return cpf;
    }

    // Retorna o valor do atributo dn, que se refere à data de nascimento do cliente
    public LocalDate getDn() {
        return dn;
    }
    
    // Permite definir ou atualizar a data de nascimento
    public void setDn(LocalDate dn) {
        this.dn = dn;
    }

    // Método que retorna o objeto Endereco, contém informações sobre o endereço do cliente, como rua, número, cidade...
    // Usado para acessar os detalhes do endereço do cliente
    public Endereco getEndereco(){
        return endereco;
    }

    // Usado para obter uma lista de objetos do tipo "Conta"
    public List<Conta> getConta() {
        return contas;
    }

    // Método para adicionar uma conta à lista de contas do cliente
    public void adicionarConta(Conta conta){
        contas.add(conta);
    }

    // Método para exibir os dados do cliente
    public void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Data de nascimento: " + this.dn);
        System.out.println("Endereço: " + this.endereco.getRua() + ", " + this.endereco.getNumero() + ", " + this.endereco.getBairro() + ", " + this.endereco.getCep());
    }

    // Método para exibir as contas do cliente

    public void exibirContas() {
        System.out.println("Contas do cliente: ");
        this.contas.forEach(c -> System.out.println(c.getNumberAgency() + " - " + c.getNumber()));
    }

    // Método para exibir o extrato de todas as contas do cliente
    public void extrato() {
        System.out.println("Extrato do cliente: ");
        this.contas.forEach(c -> c.extrato());
    }
}
