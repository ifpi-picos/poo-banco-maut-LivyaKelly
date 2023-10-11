import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Endereco> enderecos = new ArrayList<>();
        List<Client> clientes = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();

        while(true) {
            System.out.println("1 - Cadastro de Cliente");
            System.out.println("2 - Criar conta");
            System.out.println("0 - Sair\n");
            System.out.print("Digite a opção escolhida: ");

            int opcao = scanner.nextInt();

            if (opcao == 1) {
                cadastroCliente(enderecos, clientes, scanner);
            } else if (opcao == 0) {
                System.out.print("Você saiu do programa!");
                break;
            } else if (opcao == 2) {

            }
        }
    }

    public static void criarConta(List<Conta> conta, List<Client> clientes, Scanner scanner) {
        System.out.print("Digite o número da agencia: ");
        int numeroAgencia = scanner.nextInt();

        // Conta conta = new Conta(numeroAgencia);
    }
        
    public static void cadastroCliente(List<Endereco> enderecos, List<Client> clientes, Scanner scanner) {
        System.out.println("Cadastrar cliente:");
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a data de nascimento do cliente (no formato YYYY-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        System.out.print("Digite a rua: ");
        String rua = scanner.nextLine();
        System.out.print("Digite o bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite o cep: ");
        String cep = scanner.nextLine();
        System.out.print("Digite o número: ");
        int numero = scanner.nextInt();

        Endereco endereco = new Endereco(rua, bairro, cep, numero);
        enderecos.add(endereco);
        Client cliente = new Client(nome, cpf, dataNascimento, endereco);
        clientes.add(cliente);




        // System.out.println("\nCadastro de Conta:");
        // System.out.print("Digite o número da agência: ");
        // int numeroAgencia = var.nextInt();
        // var.nextLine();
    
        // Conta conta = new Conta(numeroAgencia, cliente, 0.0);
        // Conta conta1 = new Conta(numeroAgencia, cliente, 0.0);
        // conta.depositar(100);
        // conta.saque(50);
        // //conta.transferencia(100, contaDestino);
    
        // clientes.add(cliente);
        // System.out.println("\nCliente e conta cadastrados com sucesso!");
        // contas.add(conta);
        // System.out.println("\nSegue seus dados de cadastro: ");
        // System.out.println("Nome: " + cliente.getNome());
        // System.out.println("CPF: " + cliente.getCpf());
        // System.out.println("Data de nascimento: " + cliente.getDn());
        // System.out.println("Agência: " + conta.getNumberAgency());
        // System.out.println("Conta: " + conta.getNumeroConta());
        // System.out.println("Saldo: " + conta.getSaldo());
    }
}

// Comitado