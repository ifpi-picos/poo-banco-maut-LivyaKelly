package br.edu.ifpi.poo.conta;
import br.edu.ifpi.poo.conta.Conta;

import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.notificacao.Notificacao;
import br.edu.ifpi.poo.transacao.Transacao;

public class ContaPoupanca extends Conta{
    private double rendimento = 0.1;

    public ContaPoupanca(int numberAgency, int numeroConta, int number, double saldo, Client client, Notificacao notificacao) {
        super(numberAgency, number, saldo, client, notificacao);
        this.rendimento = 0.1;
    }

    @Override
    public boolean sacar (double valor) {
        if (valor <= saldo) {
            double taxa = valor * 0.05;
            super.sacar(valor + taxa);
            return true;
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }   
    }

    @Override
    public void transferir(Conta destino, double valor, String tipo) {
        double taxa = valor * 0.10; // 10% de taxa
        if (this.saldo >= valor + taxa) {
            this.sacar(valor + taxa);
            destino.depositar(valor);
            notificacao.enviarNotificacao("Transferência realizada no valor de " + valor + " com taxa de " + taxa);
        } else {
            notificacao.enviarNotificacao("Saldo insuficiente para transferência de " + valor + " com taxa de " + taxa);
        }
    }

    @Override
    public void depositar(double valor) {
        double rendimentoGanho = valor * rendimento; // Calcular o rendimento
        super.depositar(valor + rendimentoGanho); // Adicionar o rendimento ao saldo
        notificacao.enviarNotificacao("Depósito realizado no valor de " + valor + " com rendimento de " + rendimentoGanho);
    }
    
}
