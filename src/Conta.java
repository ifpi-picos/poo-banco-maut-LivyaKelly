import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Conta {
    private int numberAgency;
    private int numeroConta = 1;
    private int number;
    private double saldo;
    private Client client;
    List<Transacao> transacoes;

    // Construtor da classe 
    public Conta(int numberAgency, Client client) {
        this.numberAgency = numberAgency;
        this.number = numeroConta++;
        this.saldo = 0.0;
        this.client = client;
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
    
    // Método para depositar um valor na conta
    public void depositar(double valor) {
        this.saldo += valor;
        addTransacao(valor, "deposito");
    }

    // Método para realizar um saque da conta
    public boolean saque(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            addTransacao(valor * -1, "saque"); // Registra a transação de saque
            return true;
        } else {
            return false; // Retorna falso se não houver saldo suficiente
        }
    }

     // Método para exibir o extrato da conta, incluindo transações e saldo atual
    public void extrato(){
        this.transacoes.forEach(t -> System.out.println(t)); // Exibe todas as transações
        System.out.println("saldo atual: " + this.saldo); // Exibe o saldo atual
        System.out.println("------------------------");
    }

    // Método privado para adicionar uma transação à lista de transações
    private void addTransacao(double valor, String tipo){
        Transacao t = new Transacao(LocalDate.now(), valor, tipo); // Cria uma nova transação
        this.transacoes.add(t); // Adiciona a transação à lista de transações da conta
    }
}

// 
