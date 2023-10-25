package br.edu.ifpi.poo.conta;
import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.notificacao.Notificacao;
import br.edu.ifpi.poo.transacao.Transacao;

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
    public void transferir(Conta destino, double valor, String tipo) {
        if (this.saldo + chequeEspecial >= valor) {
            if (this.transferenciasGratis < 2) {
                if (this.saldo >= valor) {
                    this.sacar(valor);
                    destino.depositar(valor);
                    addTransacao(valor * -1, "transferência para " + destino.getNumber());
                    transferenciasGratis++;
                    notificacao.enviarNotificacao("Transferência realizada no valor de " + valor);
                } else {
                    double valorComTaxa = valor + (valor * 0.10); // Valor com taxa de 10%
                    this.sacar(valorComTaxa);
                    destino.depositar(valor);
                    addTransacao(valorComTaxa * -1, "transferência para " + destino.getNumber());
                    notificacao.enviarNotificacao("Transferência realizada no valor de " + valor + " com taxa de " + (valor * 0.10));
                }
            } 

        } else {
            notificacao.enviarNotificacao("Limite de transferências gratuitas excedido.");
        }
    }
}