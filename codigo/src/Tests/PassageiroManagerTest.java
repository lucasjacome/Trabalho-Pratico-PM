package Tests;

import Entidades.Passageiro;
import Entidades.PassageiroManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassageiroManagerTest {

    @Test
    void testAdicionarPassageiro() {
        PassageiroManager manager = new PassageiroManager();
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");

        assertTrue(manager.adicionarPassageiro(passageiro));
        assertFalse(manager.adicionarPassageiro(passageiro));
    }

    @Test
    void testListarPassageiros() {
        PassageiroManager manager = new PassageiroManager();
        Passageiro passageiro1 = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        Passageiro passageiro2 = new Passageiro("Maria", "Pereira", "98765432100", "maria@gmail.com");

        manager.adicionarPassageiro(passageiro1);
        manager.adicionarPassageiro(passageiro2);

        assertEquals(2, manager.listarPassageiros().size());
    }

    @Test
    void testBuscarPassageiroPorDocumento() {
        PassageiroManager manager = new PassageiroManager();
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");

        manager.adicionarPassageiro(passageiro);

        assertEquals(passageiro, manager.buscarPassageiroPorDocumento("12345678901"));

        assertThrows(IllegalArgumentException.class, () -> manager.buscarPassageiroPorDocumento("00000000000"));
    }

    @Test
    void testAlterarPassageiro() {
        PassageiroManager manager = new PassageiroManager();
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        manager.adicionarPassageiro(passageiro);

        boolean alterado = manager.alterarPassageiro(
                "12345678901",
                "João Atualizado",
                "Silva Atualizado",
                "joao.atualizado@gmail.com");

        assertTrue(alterado);

        Passageiro passageiroAlterado = manager.buscarPassageiroPorDocumento("12345678901");
        assertEquals("João Atualizado", passageiroAlterado.getNome());
        assertEquals("Silva Atualizado", passageiroAlterado.getSobrenome());
        assertEquals("joao.atualizado@gmail.com", passageiroAlterado.getEmail());
    }

    @Test
    void testExcluirPassageiro() {
        PassageiroManager manager = new PassageiroManager();
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        manager.adicionarPassageiro(passageiro);

        boolean excluido = manager.excluirPassageiro("12345678901");
        assertTrue(excluido);

        assertThrows(IllegalArgumentException.class, () -> manager.buscarPassageiroPorDocumento("12345678901"));
    }
}
