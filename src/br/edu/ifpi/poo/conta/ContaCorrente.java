package br.edu.ifpi.poo.conta;
import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.notificacoes.Notificacoes;
import br.edu.ifpi.poo.transacao.Transacao;

public class ContaCorrente extends Conta{
    private Client cliente;
    private double taxa = 0.1;
    private double saldo;
    private Transacao transacao;
    private Notificacoes notificacoes;
   
   
    public ContaCorrente(int numberAgency, Client client, Client cliente, double taxa, double saldo,
            Transacao transacao, Notificacoes notificacoes) {
        super(numberAgency, client);
        this.taxa = taxa;
        this.saldo = saldo;
        this.transacao = transacao;
        this.notificacoes = notificacoes;
    }


    public Client getCliente() {
        return cliente;
    }


    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }


    public double getTaxa() {
        return taxa;
    }


    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }


    public double getSaldo() {
        return saldo;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public Transacao getTransacao() {
        return transacao;
    }


    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }


    public Notificacoes getNotificacoes() {
        return notificacoes;
    }


    public void setNotificacoes(Notificacoes notificacoes) {
        this.notificacoes = notificacoes;
    }

}