import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AeronaveTest {

    private Aeronave aeronave;

    @BeforeEach
    public void setUp() {
        aeronave = new Aeronave("Boeing 737", 10000, 180, 30);
    }

    @Test
    public void testGetModelo() {
        assertEquals("Boeing 737", aeronave.getModelo());
    }

    @Test
    public void testGetNumeroFileiras() {
        assertEquals(30, aeronave.getNumeroFileiras());
    }

    @Test
    public void testGetCapacidadeCarga() {
        assertEquals(10000, aeronave.getCapacidadeCarga());
    }

    @Test
    public void testGetCapacidadePassageiros() {
        assertEquals(180, aeronave.getCapacidadePassageiros());
    }

    @Test
    public void testGerarAssentos() {
        List<String> assentos = aeronave.gerarAssentos();
        assertEquals(30 * LetraAssento.values().length, assentos.size()); // Verifica a quantidade total de assentos

        // Verifica se os assentos estão no formato correto
        for (int i = 1; i <= 30; i++) {
            for (LetraAssento letra : LetraAssento.values()) {
                assertTrue(assentos.contains(i + String.valueOf(letra)));
            }
        }
    }
}
