import java.time.LocalDate;

public class Client {
    private String nome;
    private String cpf;
    private LocalDate dn;
    private String rua;
    private String bairro;
    private String cep;
    private int numero;

    public Client(String nome, String cpf, LocalDate dn, String rua, String bairro, String cep, int numero) {
        this.nome = nome;
        this.cpf = cpf;
        this.dn = dn;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDn() {
        return dn;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public int getNumero() {
        return numero;
    }
}
