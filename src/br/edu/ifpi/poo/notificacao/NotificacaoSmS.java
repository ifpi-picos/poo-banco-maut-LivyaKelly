package br.edu.ifpi.poo.notificacao;

public class NotificacaoSmS implements Notificacao{

    @Override
    public void enviarNotificacao(String tipo){
    System.out.println("Enviando notificação por SmS");
    System.out.println("Tipo: " + tipo);
    }
}
