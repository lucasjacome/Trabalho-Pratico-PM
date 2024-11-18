package Entidades;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioManager {
    private List<Funcionario> funcionarios;

    public FuncionarioManager() {
        this.funcionarios = new ArrayList<>();
    }

    public boolean adicionarFuncionario(Funcionario funcionario) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(funcionario.getCpf())) {
                System.out.println("Erro: Funcionário com o mesmo CPF já cadastrado.");
                return false;
            }
        }
        funcionarios.add(funcionario);
        System.out.println("Funcionário cadastrado com sucesso.");
        return true;
    }

    public Funcionario autenticarFuncionario(String usuario, String senha) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.autenticar(usuario, senha)) {
                return funcionario;
            }
        }
        return null;
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario f : funcionarios) {
                System.out.println(f.getNome() + " - CPF: " + f.getCpf() + " - E-mail: " + f.getEmail());
            }
        }
    }
}
