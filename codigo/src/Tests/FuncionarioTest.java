package Tests;

import Entidades.Funcionario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    @Test
    void testConstrutorValido() {
        Funcionario funcionario = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        assertEquals("João Silva", funcionario.getNome());
        assertEquals("12345678901", funcionario.getCpf());
        assertEquals("joao.silva@email.com", funcionario.getEmail());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Funcionario("", "12345678901", "joao.silva@email.com", "joaosilva", "senha123"));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Funcionario("João Silva", "", "joao.silva@email.com", "joaosilva", "senha123"));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());
    }

    @Test
    void testAutenticar() {
        Funcionario funcionario = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        assertTrue(funcionario.autenticar("joaosilva", "senha123"));
        assertFalse(funcionario.autenticar("joaosilva", "senhaerrada"));
        assertFalse(funcionario.autenticar("usuarioerrado", "senha123"));
    }

    @Test
    void testToString() {
        Funcionario funcionario = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        String esperado = "Funcionario: João Silva, CPF: 12345678901, E-mail: joao.silva@email.com";
        assertEquals(esperado, funcionario.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Funcionario funcionario1 = new Funcionario("João Silva", "12345678901", "joao.silva@email.com", "joaosilva",
                "senha123");
        Funcionario funcionario2 = new Funcionario("Maria Oliveira", "12345678901", "maria.oliveira@email.com",
                "mariaoliveira", "senha456");
        Funcionario funcionario3 = new Funcionario("Carlos Santos", "98765432100", "carlos.santos@email.com",
                "carlossantos", "senha789");

        assertEquals(funcionario1, funcionario2);
        assertNotEquals(funcionario1, funcionario3);
        assertEquals(funcionario1.hashCode(), funcionario2.hashCode());
        assertNotEquals(funcionario1.hashCode(), funcionario3.hashCode());
    }
}
