package br.edu.ifpi.poo.conta;
import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.notificacao.Notificacao;

public class ContaCorrente extends Conta{
    private int transferenciasGratis;
    private double chequeEspecial;
   
    // Construtor da classe
    public ContaCorrente(int numberAgency, int numeroConta, int number, double saldo, Client client, Notificacao notificacao) {
        super(numberAgency, number, saldo, client, notificacao);
        this.transferenciasGratis = 2;
        this.chequeEspecial = 1.000;
    }

    @Override
    public void transferir(Conta destino, double valor, String tipo){
        if (transferenciasGratis > 0){
            if (this.saldo >= valor) {
                this.sacar(valor);
                destino.depositar(valor);
                addTransacao(valor * -1, "transferência para " + destino.getNumber());
                transferenciasGratis--;
                notificacao.enviarNotificacao("Transferência realizada no valor de " + valor);
            } else {
                notificacao.enviarNotificacao("Saldo insuficiente para transferência de " + valor);
            }
        } else {
            double taxa = valor * 0.10; // 10% de taxa
            if (this.saldo >= valor + taxa) {
                this.sacar(valor + taxa);
                destino.depositar(valor);
                addTransacao((valor + taxa) * -1, "transferência para " + destino.getNumber());
                notificacao.enviarNotificacao("Transferência realizada no valor de " + valor + " com taxa de " + taxa);
            } else {
                notificacao.enviarNotificacao("Saldo insuficiente para transferência de " + valor + " com taxa de " + taxa);
            }
        } 
        }
    }
    