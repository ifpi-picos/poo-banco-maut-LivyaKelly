package br.edu.ifpi.poo.notificacao;

public class NotificacaoEmail implements Notificacao {

    // Usado para indicar que uma subclasse está substituindo um método herdado de sua superclasse
    @Override
    public void enviarNotificacao(String tipo) {
        System.out.println("Enviando notificação por email");
        System.out.println("Tipo: + tipo");
    }
    
}
