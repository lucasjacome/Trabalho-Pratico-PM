package Tests;

import Entidades.CompanhiaAerea;
import Entidades.CompanhiaAereaManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanhiaAereaManagerTest {

    @Test
    void testAdicionarCompanhia() {
        CompanhiaAereaManager manager = new CompanhiaAereaManager();
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);

        assertTrue(manager.adicionarCompanhia(companhia));

        assertFalse(manager.adicionarCompanhia(companhia));
    }

    @Test
    void testListarCompanhias() {
        CompanhiaAereaManager manager = new CompanhiaAereaManager();
        CompanhiaAerea companhia1 = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0,
                30.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("ABC Airlines", "ABC", "Razão ABC", "98765432000123", 60.0,
                40.0);

        manager.adicionarCompanhia(companhia1);
        manager.adicionarCompanhia(companhia2);

        assertEquals(2, manager.listarCompanhias().size());
    }

    @Test
    void testBuscarCompanhiaPorCnpj() {
        CompanhiaAereaManager manager = new CompanhiaAereaManager();
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);

        manager.adicionarCompanhia(companhia);

        assertEquals(companhia, manager.buscarCompanhiaPorCnpj("12345678000123"));

        assertThrows(IllegalArgumentException.class, () -> manager.buscarCompanhiaPorCnpj("00000000000000"));
    }
}
