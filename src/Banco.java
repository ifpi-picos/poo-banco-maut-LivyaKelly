// import java.util.HashMap;
// import java.util.Map;

// public class Banco {
//     private Map<String, Conta> contas = new HashMap<>();

//     public void adicionarConta(Conta conta) {
//         contas.put(conta.getNumber(), conta);
//     }

//     public void transferir(String numeroContaOrigem, String contaDestino, double valor ){
//         Conta origem = contas.get(numeroContaOrigem);
//         Conta destino = contas.get(contaDestino);

//         if (origem == null) {
//             System.out.println("Conta de origem não encontrada.");
//         } else if (destino == null) {
//             System.out.println("Conta destino não encontrada.");
//         } else if (origem.getSaldo()< valor){
//             System.out.println("Você não possuí saldo suficiente na conta de origem.");
//         } else {
//             origem.depositar(-valor);
//             destino.depositar(valor);
//             System.out.println("Valor trasnferido com sucesso" + valor + "para a conta" + destino.getNumber());
//         }
//     }
// }
