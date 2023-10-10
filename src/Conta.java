import java.util.List;

public class Conta {
    private int numberAgency;
    private int numeroConta = 1;
    private double saldo = 0.0;
    private Client client;
    List<Transacao> transacoes;

    public Conta(int numberAgency, Client client, double saldo) {
        this.numberAgency = numberAgency;
        this.numeroConta += 1;
        this.saldo = saldo;
        this.client = client;
    }

    public int getNumberAgency() {
        return numberAgency;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Client getClient() {
        return client;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public boolean saque(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }
}
