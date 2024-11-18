package Tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Entidades.Funcionario;
import Entidades.FuncionarioManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FuncionarioManagerTest {
    private FuncionarioManager gerenteFuncionarios;

    @BeforeEach
    public void setup() {
        gerenteFuncionarios = new FuncionarioManager();
    }

    @Test
    public void testAdicionarFuncionario_Sucesso() {
        Funcionario f1 = new Funcionario("João Silva", "12345678900", "joao@gmail.com", "joaosilva", "senha123");
        boolean resultado = gerenteFuncionarios.adicionarFuncionario(f1);
        assertTrue(resultado, "Deve permitir adicionar um novo funcionário.");
    }

    @Test
    public void testAdicionarFuncionario_CPFRepetido() {
        Funcionario f1 = new Funcionario("João Silva", "12345678900", "joao@gmail.com", "joaosilva", "senha123");
        Funcionario f2 = new Funcionario("Maria Souza", "12345678900", "maria@gmail.com", "mariasouza", "senha456");

        gerenteFuncionarios.adicionarFuncionario(f1);
        boolean resultado = gerenteFuncionarios.adicionarFuncionario(f2);
        assertFalse(resultado, "Não deve permitir adicionar dois funcionários com o mesmo CPF.");
    }

    @Test
    public void testAutenticarFuncionario_Sucesso() {
        Funcionario f1 = new Funcionario("João Silva", "12345678900", "joao@gmail.com", "joaosilva", "senha123");
        gerenteFuncionarios.adicionarFuncionario(f1);

        Funcionario autenticado = gerenteFuncionarios.autenticarFuncionario("joaosilva", "senha123");
        assertNotNull(autenticado, "Deve autenticar o funcionário com sucesso.");
    }

    @Test
    public void testAutenticarFuncionario_Falha() {
        Funcionario f1 = new Funcionario("João Silva", "12345678900", "joao@gmail.com", "joaosilva", "senha123");
        gerenteFuncionarios.adicionarFuncionario(f1);

        Funcionario autenticado = gerenteFuncionarios.autenticarFuncionario("joaosilva", "senhaErrada");
        assertNull(autenticado, "Não deve autenticar com senha errada.");
    }
}
