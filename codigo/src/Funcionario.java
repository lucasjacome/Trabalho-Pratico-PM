public class Funcionario {
    private String nome;
    private String cpf;
    private String email;
    private String usuario;
    private String senha;

    public Funcionario(String nome, String cpf, String email, String usuario, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public boolean autenticar(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }
}
