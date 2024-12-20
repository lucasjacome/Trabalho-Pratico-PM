package Tests;

import Entidades.Aeroporto;
import Managers.AeroportoManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AeroportoManagerTest {

    @Test
    void testAdicionarAeroporto() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505, -46.6333);

        assertTrue(manager.adicionarAeroporto(aeroporto));
        assertFalse(manager.adicionarAeroporto(aeroporto));
    }

    @Test
    void testListarAeroportos() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505, -46.6333);
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                -43.1737);

        manager.adicionarAeroporto(aeroporto1);
        manager.adicionarAeroporto(aeroporto2);

        assertEquals(2, manager.listarAeroportos().size());
    }

    @Test
    void testBuscarAeroportoPorSigla() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505, -46.6333);

        manager.adicionarAeroporto(aeroporto);

        assertEquals(aeroporto, manager.buscarAeroportoPorSigla("GRU"));

        assertThrows(IllegalArgumentException.class, () -> manager.buscarAeroportoPorSigla("XXX"));
    }

    @Test
    void testAlterarAeroporto() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505, -46.6333);
        manager.adicionarAeroporto(aeroporto);

        boolean alterado = manager.alterarAeroporto("GRU", "Aeroporto Alterado", "Cidade Alterada", "Estado Alterado",
                "País Alterado");
        assertTrue(alterado);

        Aeroporto aeroportoAlterado = manager.buscarAeroportoPorSigla("GRU");
        assertEquals("Aeroporto Alterado", aeroportoAlterado.getNome());
        assertEquals("Cidade Alterada", aeroportoAlterado.getCidade());
        assertEquals("Estado Alterado", aeroportoAlterado.getEstado());
        assertEquals("País Alterado", aeroportoAlterado.getPais());
    }

    @Test
    void testExcluirAeroporto() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505, -46.6333);
        manager.adicionarAeroporto(aeroporto);

        boolean excluido = manager.removerAeroporto("GRU");
        assertTrue(excluido);

        assertThrows(IllegalArgumentException.class, () -> manager.buscarAeroportoPorSigla("GRU"));
    }
}
