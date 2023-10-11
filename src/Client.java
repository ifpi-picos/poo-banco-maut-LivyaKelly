import java.time.LocalDate;

public class Client {
    private String nome;
    private String cpf;
    private LocalDate dn;
    private Endereco endereco;

    public Client(String nome, String cpf, LocalDate dn, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dn = dn;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDn() {
        return dn;
    }
    
    public void setDn(LocalDate dn) {
        this.dn = dn;
    }

    public Endereco getEndereco(){
        return endereco;
    }
}
