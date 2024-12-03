package Tests;

import Entidades.Aeronave;
import Enums.LetraAssento;
import org.junit.jupiter.api.Assertions;
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
        Assertions.assertEquals("Boeing 737", aeronave.getModelo());
    }

    @Test
    public void testGetNumeroFileiras() {
        Assertions.assertEquals(30, aeronave.getNumeroFileiras());
    }

    @Test
    public void testGetCapacidadeCarga() {
        Assertions.assertEquals(10000, aeronave.getCapacidadeCarga());
    }

    @Test
    public void testGetCapacidadePassageiros() {
        Assertions.assertEquals(180, aeronave.getCapacidadePassageiros());
    }

    @Test
    public void testGerarAssentos() {
        List<String> assentos = aeronave.gerarAssentos();
        Assertions.assertEquals(30 * LetraAssento.values().length, assentos.size()); // Verifica a quantidade total de assentos

        // Verifica se os assentos estão no formato correto
        for (int i = 1; i <= 30; i++) {
            for (LetraAssento letra : LetraAssento.values()) {
                assertTrue(assentos.contains(i + String.valueOf(letra)));
            }
        }
    }
}
