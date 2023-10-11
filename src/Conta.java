import java.util.List;
import java.time.LocalDate;

public class Conta {
    private int numberAgency;
    private int numeroConta = 1;
    private String number;
    private double saldo;
    private Client client;
    List<Transacao> transacoes;

    public Conta(int numberAgency, Client client) {
        this.numberAgency = numberAgency;
        this.number = visualizarNumero();
        this.saldo = 0.0;
        this.client = client;
    }

    public String visualizarNumero(){
        return String.format("%04d", numeroConta++);
    }

    public int getNumberAgency() {
        return numberAgency;
    }

    public String getNumber(){
        return number;
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
        addTransacao(valor, "deposito");
    }

    public boolean saque(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            addTransacao(valor*-1, "saque");
            return true;
        } else {
            return false;
        }
    }

    public void extrato(){
        this.transacoes.forEach(t -> System.out.println(t));
        System.out.println("saldo atual: " + this.saldo);
        System.out.println("------------------------");
    }

    private void addTransacao(double valor, String tipo){
        Transacao t = new Transacao(LocalDate.now(), valor, tipo);
        this.transacoes.add(t);
    }
}
