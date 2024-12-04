package Managers;

import Entidades.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioManager {
    private List<Funcionario> funcionarios;

    public FuncionarioManager() {
        this.funcionarios = new ArrayList<>();
    }

    public boolean adicionarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionario não pode ser nulo.");
        }
        Optional<Funcionario> existente = funcionarios.stream()
                .filter(f -> f.getCpf().equals(funcionario.getCpf()))
                .findFirst();
        if (existente.isPresent()) {
            return false;
        }
        funcionarios.add(funcionario);
        return true;
    }

    public Funcionario autenticarFuncionario(String usuario, String senha) {
        if (usuario == null || senha == null || usuario.trim().isEmpty() || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Usuario e senha não podem ser nulos ou vazios.");
        }
        return funcionarios.stream()
                .filter(f -> f.autenticar(usuario, senha))
                .findFirst()
                .orElse(null);
    }

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public Funcionario buscarPorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        return funcionarios.stream()
                .filter(f -> f.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
