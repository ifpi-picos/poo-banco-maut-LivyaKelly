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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep){
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }
}
