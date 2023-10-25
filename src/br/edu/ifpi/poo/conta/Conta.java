package br.edu.ifpi.poo.conta;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.notificacao.Notificacao;
import br.edu.ifpi.poo.transacao.Transacao;

public class Conta {
    private int numberAgency;
    private static int numeroConta = 1;
    private int number;
    protected double saldo;
    private Client client;
    protected Notificacao notificacao;
    private List<Transacao> transacoes;

    // Construtor da classe 
    public Conta(int numberAgency, int number2, double saldo2, Client client, Notificacao notificacao2) {
        this.numberAgency = numberAgency;
        this.number = numeroConta++;
        this.saldo = 0.0;
        this.client = client;
        this.transacoes = new ArrayList<>();
        this.notificacao = notificacao;
    }

    // Getter para obter o número da agência
    public int getNumberAgency() {
        return numberAgency;
    }

    // Getter para obter o número da conta
    public int getNumber(){
        return number;
    }

    // Getter para obter o saldo da conta
    public double getSaldo() {
        return saldo;
    }

    // Setter para definir o saldo da conta
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Getter para obter o cliente associado à conta
    public Client getClient() {
        return client;
    }

    // Getter para obter o número da próxima conta a ser criada
    public int getNumeroConta() {
        return numeroConta;
    }

    
    public Notificacao getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    // Método para depositar um valor na conta
    public void depositar(double valor) {
        this.saldo += valor;
        addTransacao(valor, "deposito");
    }

    // Método para realizar um saque da conta
    public boolean sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            addTransacao(valor * -1, "saque"); // Registra a transação de saque
            return true;
        } else {
            return false; // Retorna falso se não houver saldo suficiente
        }
    }

    public void transferir(Conta destino, double valor, String tipo){
        if (this.saldo >= valor){
            this.sacar(valor);
            destino.depositar(valor);
            addTransacao(valor * -1, "Transferência para " + destino.getNumber());
        }
    }

     // Método para exibir o extrato da conta, incluindo transações e saldo atual
    public void extrato(){
        this.transacoes.forEach(t -> System.out.println(t)); // Exibe todas as transações
        System.out.println("saldo atual: " + this.saldo); // Exibe o saldo atual
        System.out.println("------------------------");
    }

    // Método privado para adicionar uma transação à lista de transações
    protected void addTransacao(double valor, String tipo){
        Transacao t = new Transacao(LocalDate.now(), valor, tipo); // Cria uma nova transação
        this.transacoes.add(t); // Adiciona a transação à lista de transações da conta
    }
}
