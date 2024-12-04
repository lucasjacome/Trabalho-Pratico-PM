package Tests;

import Entidades.Aeroporto;
import Entidades.AeroportoManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AeroportoManagerTest {

    @Test
    void testAdicionarAeroporto() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil");

        assertTrue(manager.adicionarAeroporto(aeroporto));
        assertFalse(manager.adicionarAeroporto(aeroporto));
    }

    @Test
    void testListarAeroportos() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil");

        manager.adicionarAeroporto(aeroporto1);
        manager.adicionarAeroporto(aeroporto2);

        assertEquals(2, manager.listarAeroportos().size());
    }

    @Test
    void testBuscarAeroportoPorSigla() {
        AeroportoManager manager = new AeroportoManager();
        Aeroporto aeroporto = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil");

        manager.adicionarAeroporto(aeroporto);

        assertEquals(aeroporto, manager.buscarAeroportoPorSigla("GRU"));

        assertThrows(IllegalArgumentException.class, () -> manager.buscarAeroportoPorSigla("XXX"));
    }
}
