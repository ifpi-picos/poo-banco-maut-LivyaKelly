import java.time.LocalDate;
import java.util.Scanner;
import br.edu.ifpi.poo.cliente.Client;
import br.edu.ifpi.poo.conta.Conta;
import br.edu.ifpi.poo.conta.ContaCorrente;
import br.edu.ifpi.poo.conta.ContaPoupanca;
import br.edu.ifpi.poo.endereco.Endereco;
import br.edu.ifpi.poo.notificacao.Notificacao;
import br.edu.ifpi.poo.notificacao.NotificacaoSmS;
import br.edu.ifpi.poo.notificacao.NotificacaoEmail;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

//Definição da classe app e formação de listas vazias para armazenar algumas informações do cliente
public class App {
    private static Client cliente;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Endereco> enderecos = new ArrayList<>();
        List<Client> clientes = new ArrayList<>();
        List<Conta> contas = new ArrayList<>();
        List<Notificacao> notificacao = new ArrayList<>();

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
            System.out.print("\nDigite a opção escolhida: ");
        
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
        
            if (opcao == 1) {
                cadastroCliente(enderecos, clientes, scanner);
            } else if (opcao == 2) {
                novaConta(scanner, contas, clientes);
            } else if (opcao == 3) {
                realizarDeposito(scanner, contas, notificacao);
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

    // Função usada para cadastrar um cliente
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
        
        // Cria um objeto Endereco com os dados fornecidos
        Endereco endereco = new Endereco(rua, bairro, cep, numero);
        enderecos.add(endereco);

        // Cria um objeto Cliente com os dados fornecidos e o endereço associado
        Client cliente = new Client(nome, cpf, dataNascimento, endereco);
        clientes.add(cliente);
    }

    // Usada para criar uma nova conta bancária
    public static void novaConta(Scanner scanner, List<Conta> contas, List<Client> clientes ) {
        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();
        
        Client cliente = findClientByCPF(clientes, cpf);
        
        if (cliente == null) {
            System.out.println("Cliente com o CPF fornecido não encontrado. Não é possível criar uma conta.");
            return;
        }
    
        System.out.println("Qual conta você deseja criar?");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
    
        int escolha = scanner.nextInt();
    
        switch (escolha) {
            case 1:
            case 2:
                System.out.print("Digite o número da agência: ");
                int numberAgency = scanner.nextInt();
                Conta novaConta = null;
    
                if (escolha == 1) {
                    novaConta = new ContaCorrente(numberAgency, 0, 0, 250, cliente, new NotificacaoSmS());
                } else if (escolha == 2) {
                    novaConta = new ContaPoupanca(numberAgency, 0, 0, 250, cliente, new NotificacaoSmS());
                }
    
                contas.add(novaConta);
                cliente.adicionarConta(novaConta);
                novaConta.setNotificacao(new NotificacaoEmail());
                novaConta.setNotificacao(new NotificacaoSmS());
    
                System.out.println("\nConta criada com sucesso. Saldo inicial: " + novaConta.getSaldo() + " reais.");
                System.out.println("Número da conta: " + novaConta.getNumber());
                System.out.println("Número da agência: " + novaConta.getNumberAgency());
                break;
    
            default:
                System.out.println("Escolha inválida. Por favor, escolha 1 para Conta Corrente ou 2 para Conta Poupança.");
        }
    }
    

    private static Client findClientByCPF(List<Client> clientes, String cpf) {
        return null;
    }

    //Função que permite visualizar os dados de um cliente
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
            System.out.println("\nDados do cliente:");
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("CPF: " + clienteEncontrado.getCpf());
            System.out.println("Data de Nascimento: " + clienteEncontrado.getDn());
    
            // Exibir dados de endereço
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

    // Função para realizar depósito em uma conta
    public static void realizarDeposito(Scanner scanner, List<Conta> contas, List<Notificacao> notificacao) {
        System.out.print("Digite o número da conta de destino: ");
        int numeroContaDestino;
    
        try {
            numeroContaDestino = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
            return;
        }
        
        // Encontre a conta de destino
        Conta contaDestino = null;
        for (Conta conta : contas) {
            if (conta.getNumber() == numeroContaDestino) {
                contaDestino = conta;
                break;
            }
        }
        
        if (contaDestino != null) {
            System.out.print("\nDigite o valor a ser depositado: ");
            double valorDeposito;
            
            try {
                valorDeposito = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar um número válido.");
                return;
            }
            
            if (valorDeposito <= 0) {
                System.out.println("Valor de depósito inválido. O valor deve ser positivo.");
                return;
            }
            
            // Realize o depósito
            contaDestino.depositar(valorDeposito);
            
            System.out.println("Depósito de " + valorDeposito + " realizado na conta " + contaDestino.getNumber());
            System.out.println("Enviando notificação para o cliente... Sms enviado!");
            System.out.println("Enviando notificação para o cliente... Email enviado!");
            System.out.println("\nNovo saldo da conta: " + contaDestino.getSaldo());
        
        } else {
            System.out.println("Conta de destino não encontrada.");
        }
    }
    
    // Função para realizar saque em uma conta
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
            if (contaOrigem.sacar(valorSaque)) {
                System.out.println("\nSaque de " + valorSaque + " reais realizado com sucesso na conta número " + numeroContaOrigem);
                System.out.println("Enviando notificação para o cliente... Sms enviado!");
                System.out.println("Enviando notificação para o cliente... Email enviado!");
                System.out.println("\nSaldo atual da conta: " + contaOrigem.getSaldo() + " reais.");
            } else {
                System.out.println("Saldo insuficiente para o saque na conta número " + numeroContaOrigem);
            }
        } else {
            System.out.println("Conta com número " + numeroContaOrigem + " não encontrada.");
        }
    }

    // Função que permite realizar uma transferência entre contas
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
    
        if (contaOrigem.sacar(valorTransferencia)) {
            contaDestino.depositar(valorTransferencia);
            System.out.println("\nTransferência realizada com sucesso.");
            System.out.println("Enviando notificação para o cliente... Sms enviado!");
            System.out.println("Enviando notificação para o cliente... Email enviado!");
        } else {
            System.out.println("Saldo insuficiente na conta de origem para a transferência.");
        }
    }

    // Função usada para visualizar o extrato de uma conta
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
