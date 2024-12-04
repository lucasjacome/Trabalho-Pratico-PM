package Entidades;

import java.util.Objects;

public class Funcionario {
    private String nome;
    private String cpf;
    private String email;
    private String usuario;
    private String senha;

    public Funcionario(String nome, String cpf, String email, String usuario, String senha) {
        if (nome == null || nome.trim().isEmpty() ||
                cpf == null || cpf.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                usuario == null || usuario.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios e devem ser válidos.");
        }
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

    public String getUsuario() {
        return usuario;
    }

    public boolean autenticar(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser nulo ou vazio.");
        }
        this.email = email;
    }

    public void setUsuario(String usuario) {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new IllegalArgumentException("O usuário não pode ser nulo ou vazio.");
        }
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia.");
        }
        this.senha = senha;
    }

    @Override
    public String toString() {
        return String.format("Funcionario: %s, CPF: %s, E-mail: %s", nome, cpf, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Funcionario that = (Funcionario) obj;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}