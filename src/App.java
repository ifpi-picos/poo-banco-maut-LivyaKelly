import java.time.LocalDate;
import java.util.Scanner;
import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.conta.Conta;
import br.edu.ifpi.poo.endereco.Endereco;
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
        while (true) {
            System.out.println("\n1 - Cadastrar cliente");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Depósito");
            System.out.println("4 - Saque");
            System.out.println("5 - Transferência");
            System.out.println("6 - Ver extrato");
            System.out.println("7 - Ver dados de cliente");
            System.out.println("0 - Sair\n");
            System.out.print("Digite a opção escolhida: ");
        
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
        
            if (opcao == 1) {
                cadastroCliente(enderecos, clientes, scanner);
            } else if (opcao == 2) {
                novaConta(scanner, contas, clientes);
            } else if (opcao == 3) {
                realizarDeposito(scanner, contas);
            } else if (opcao == 4) {
                realizarSaque(scanner, contas, clientes);
            } else if (opcao == 5) {
                realizarTransferencia(scanner, contas);
            } else if (opcao == 6) {
                verExtrato(scanner, contas);
            } else if (opcao == 7) {
                verDadosDoCliente(scanner, clientes, contas);
            } else if (opcao == 0) {
                System.out.print("Você saiu do programa!");
                break;
            }
            
        }
        
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
    }

    public static void novaConta(Scanner scanner, List<Conta> contas, List<Client> clients) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
    
        Client cliente = null;
        for (Client c : clients) {
            if (c.getCpf().equals(cpf)) {
                cliente = c;
                break;
            }
        }
    
        if (cliente != null) {
            System.out.print("Digite o número da agência: ");
            int numberAgency = scanner.nextInt();
            Conta conta = new Conta(numberAgency, cliente);
            contas.add(conta);

            cliente.adicionarConta(conta);
    
            System.out.println("Conta criada com sucesso. Saldo inicial: " + conta.getSaldo() + " reais." + "\n" + "Número da conta: " + conta.getNumber() + "\n" + "Número da agência: " + conta.getNumberAgency());
        } else {
            System.out.println("Cliente com o CPF fornecido não encontrado. Não é possível criar uma conta.");
        }
    }

    public static void verDadosDoCliente(Scanner scanner, List<Client> clientes, List<Conta> contas) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
    
        Client clienteEncontrado = null;
    
        for (Client cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                clienteEncontrado = cliente;
                break; // Encerra o loop assim que encontrar um cliente com o CPF correspondente
            }
        }
    
        if (clienteEncontrado != null) {
            System.out.println("Dados do cliente:");
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("Data de Nascimento: " + clienteEncontrado.getDn());
    
            // Exibir dados de endereço, se necessário
            Endereco endereco = clienteEncontrado.getEndereco();
            if (endereco != null) {
                System.out.println("Endereço:");
                System.out.println("Rua: " + endereco.getRua());
                System.out.println("Bairro: " + endereco.getBairro());
                System.out.println("CEP: " + endereco.getCep());
                System.out.println("Número: " + endereco.getNumero());
            }
    
            // Exibir as contas associadas a este cliente
            if (clienteEncontrado.getConta().size() > 0) {
                System.out.println("Contas:");
                for (Conta conta : clienteEncontrado.getConta()) {
                    System.out.println("Número da conta: " + conta.getNumber());
                    System.out.println("Número da agência: " + conta.getNumberAgency());
                    System.out.println("Saldo: " + conta.getSaldo());
                }
            } else {
                System.out.println("Este cliente não possui contas.");
            }
        } else {
            System.out.println("Cliente com CPF " + cpf + " não encontrado.");
        }
    }

    public static void realizarDeposito(Scanner scanner, List<Conta> contas) {
        System.out.print("Digite o número da conta de destino: ");
        int numeroContaDestino = scanner.nextInt();
        
        // Encontre a conta de destino
        Conta contaDestino = null;
        for (Conta conta : contas) {
            if (conta.getNumber() == numeroContaDestino) {
                contaDestino = conta;
                break;
            }
        }
        
        if (contaDestino != null) {
            System.out.print("Digite o valor a ser depositado: ");
            double valorDeposito = scanner.nextDouble();
            
            // Realize o depósito
            contaDestino.depositar(valorDeposito);
            
            System.out.println("Depósito de " + valorDeposito + " realizado na conta " + contaDestino.getNumber());
            System.out.println("Novo saldo da conta: " + contaDestino.getSaldo());
        } else {
            System.out.println("Conta de destino não encontrada.");
        }
    }
    
    public static void realizarSaque(Scanner scanner, List<Conta> contas, List<Client> clientes) {
        System.out.print("Digite o número da conta origem: ");
        int numeroContaOrigem = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
    
        System.out.print("Digite o valor do saque: ");
        double valorSaque = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
    
        Conta contaOrigem = null;
        Client clienteDono = null;
    
        // Encontre a conta origem e seu cliente associado
        for (Client cliente : clientes) {
            for (Conta conta : cliente.getConta()) {
                if (conta.getNumber() == numeroContaOrigem) {
                    contaOrigem = conta;
                    clienteDono = cliente;
                    break;
                }
            }
            if (contaOrigem != null) {
                break;
            }
        }
    
        if (contaOrigem != null) {
            if (contaOrigem.saque(valorSaque)) {
                System.out.println("Saque de " + valorSaque + " reais realizado com sucesso na conta número " + numeroContaOrigem);
                System.out.println("Saldo atual da conta: " + contaOrigem.getSaldo() + " reais.");
            } else {
                System.out.println("Saldo insuficiente para o saque na conta número " + numeroContaOrigem);
            }
        } else {
            System.out.println("Conta com número " + numeroContaOrigem + " não encontrada.");
        }
    }

    public static void realizarTransferencia(Scanner scanner, List<Conta> contas) {
        System.out.print("Digite o número da conta de origem: ");
        int numeroContaOrigem = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
    
        // Encontrar a conta de origem com base no número da conta
        Conta contaOrigem = null;
        for (Conta conta : contas) {
            if (conta.getNumber() == numeroContaOrigem) {
                contaOrigem = conta;
                break;
            }
        }
    
        if (contaOrigem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }
    
        System.out.print("Digite o CPF associado à conta de origem para confirmar: ");
        String cpfOrigem = scanner.nextLine();
    
        // Verificar se o CPF fornecido corresponde ao cliente associado à conta de origem
        if (!contaOrigem.getClient().getCpf().equals(cpfOrigem)) {
            System.out.println("CPF incorreto. A transferência foi cancelada.");
            return;
        }
    
        System.out.print("Digite o número da conta de destino: ");
        int numeroContaDestino = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
    
        // Encontrar a conta de destino com base no número da conta
        Conta contaDestino = null;
        for (Conta conta : contas) {
            if (conta.getNumber() == numeroContaDestino) {
                contaDestino = conta;
                break;
            }
        }
    
        if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada.");
            return;
        }
    
        System.out.print("Digite o valor a ser transferido: ");
        double valorTransferencia = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
    
        if (contaOrigem.saque(valorTransferencia)) {
            contaDestino.depositar(valorTransferencia);
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente na conta de origem para a transferência.");
        }
    }

    public static void verExtrato(Scanner scanner, List<Conta> contas) {
        System.out.print("Digite o número da conta: ");
        int numeroConta = scanner.nextInt();
    
        Conta contaEncontrada = null;
    
        for (Conta conta : contas) {
            if (conta.getNumber() == numeroConta) {
                contaEncontrada = conta;
                break;
            }
        }
    
        if (contaEncontrada != null) {
            contaEncontrada.extrato();
        } else {
            System.out.println("Conta com número " + numeroConta + " não encontrada.");
        }
    }
    


    
    
}
