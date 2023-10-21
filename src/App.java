import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//Definição da classe app e formação de listas vazias para armazenar algumas informações do cliente
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Endereco> enderecos = new ArrayList<>();
        List<Client> clientes = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();

        // Usado para criar uma interface de menu interativa que possibilita ao usuário realizar diversas ações ou encerrar o programa
        while(true) {
            System.out.println("\n1 - Cadastro de Cliente"); // Opção para cadastrar um novo cliente
            System.out.println("2 - Criar conta"); // Opção para criar uma nova conta
            System.out.println("0 - Sair\n"); // Opção para sair do programa
            System.out.print("Digite a opção escolhida: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                cadastroCliente(enderecos, clientes, scanner); // Chama a função para cadastrar um cliente
            } else if (opcao == 0) {
                System.out.print("Você saiu do programa!");
                break; // Encerra o programa
            } else if (opcao == 2) {
                novaConta(scanner, contas, clientes); // Chama a função para criar uma nova conta
            }
        }
    }

    // public static void criarConta(List<Conta> conta, List<Client> clientes, Scanner scanner) {
    //     System.out.print("Digite o número da agencia: ");
    //     int numeroAgencia = scanner.nextInt();

    //     Conta conta = new Conta(numeroAgencia);
    // }
        
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

    public static void novaConta(Scanner scanner, List<Conta> contas, List<Client> clients) {
        System.out.print("Digite o número da agência: ");
        int numberAgency = scanner.nextInt(); // Lê o número da agência fornecido pelo usuário
        Conta conta = new Conta(numberAgency, clients.get(0));
        contas.add(conta); // Adiciona a conta à lista de contas

        System.out.println(conta.getSaldo() + " " + conta.getNumeroConta()); // Exibe o saldo inicial e o número da conta recém-criada

        System.out.println("\nConta criada com sucesso. Saldo inicial: " + conta.getSaldo());
    
        while (true) {
            System.out.println("\n1 - Realizar Depósito");
            System.out.println("2 - Realizar Saque");
            System.out.println("0 - Sair");
    
            System.out.print("Digite a opção escolhida: ");
            int opcao = scanner.nextInt();
    
            if (opcao == 1) {
                System.out.print("Digite o valor do depósito: ");
                double valorDeposito = scanner.nextDouble();
                conta.depositar(valorDeposito);
                System.out.println("Depósito realizado. Novo saldo: " + conta.getSaldo());
            } else if (opcao == 2) {
                System.out.print("Digite o valor do saque: ");
                double valorSaque = scanner.nextDouble();
                boolean saqueEfetuado = conta.saque(valorSaque);
                if (saqueEfetuado) {
                    System.out.println("Saque realizado. Novo saldo: " + conta.getSaldo());
                } else {
                    System.out.println("Saldo insuficiente para o saque.");
                }
            } else if (opcao == 0) {
                System.out.println("Saindo do menu de operações.");
                break;
            }
        }
    }
    
}

// Comitado