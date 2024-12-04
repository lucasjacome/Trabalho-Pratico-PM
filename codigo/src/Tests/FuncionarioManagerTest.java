package Tests;

import Entidades.Funcionario;
import Managers.FuncionarioManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioManagerTest {

    @Test
    void testAdicionarFuncionarioValido() {
        FuncionarioManager manager = new FuncionarioManager();
        Funcionario funcionario = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");

        assertTrue(manager.adicionarFuncionario(funcionario));
        assertFalse(manager.adicionarFuncionario(funcionario));
    }

    @Test
    void testAdicionarFuncionarioNulo() {
        FuncionarioManager manager = new FuncionarioManager();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> manager.adicionarFuncionario(null));
        assertEquals("Funcionario não pode ser nulo.", exception.getMessage());
    }

    @Test
    void testAutenticarFuncionario() {
        FuncionarioManager manager = new FuncionarioManager();
        Funcionario funcionario = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        manager.adicionarFuncionario(funcionario);

        Funcionario autenticado = manager.autenticarFuncionario("joaosilva", "senha123");
        assertNotNull(autenticado);
        assertEquals(funcionario, autenticado);

        assertNull(manager.autenticarFuncionario("joaosilva", "senhaerrada"));
        assertNull(manager.autenticarFuncionario("usuarioerrado", "senha123"));
    }

    @Test
    void testAutenticarFuncionarioDadosInvalidos() {
        FuncionarioManager manager = new FuncionarioManager();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> manager.autenticarFuncionario(null, "senha123"));
        assertEquals("Usuario e senha não podem ser nulos ou vazios.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> manager.autenticarFuncionario("joaosilva", null));
        assertEquals("Usuario e senha não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testListarFuncionarios() {
        FuncionarioManager manager = new FuncionarioManager();
        Funcionario funcionario1 = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        Funcionario funcionario2 = new Funcionario("Maria Oliveira", "98765432100", "maria.oliveira@email.com",
                "mariaoliveira", "senha456");

        manager.adicionarFuncionario(funcionario1);
        manager.adicionarFuncionario(funcionario2);

        List<Funcionario> funcionarios = manager.listarFuncionarios();
        assertEquals(2, funcionarios.size());
        assertTrue(funcionarios.contains(funcionario1));
        assertTrue(funcionarios.contains(funcionario2));
    }

    @Test
    void testBuscarPorCpf() {
        FuncionarioManager manager = new FuncionarioManager();
        Funcionario funcionario = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        manager.adicionarFuncionario(funcionario);

        Funcionario encontrado = manager.buscarPorCpf("12345678901");
        assertNotNull(encontrado);
        assertEquals(funcionario, encontrado);

        assertNull(manager.buscarPorCpf("00000000000"));
    }

    @Test
    void testBuscarPorCpfInvalido() {
        FuncionarioManager manager = new FuncionarioManager();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> manager.buscarPorCpf(""));
        assertEquals("CPF não pode ser nulo ou vazio.", exception.getMessage());
    }
}
