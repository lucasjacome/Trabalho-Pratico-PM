package Tests;

import Entidades.Aeronave;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class AeronaveTest {

    @Test
    void testAeronaveConstrutorValido() {
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        assertEquals("Boeing 737", aeronave.getModelo());
        assertEquals(20000, aeronave.getCapacidadeCarga());
        assertEquals(180, aeronave.getCapacidadePassageiros());
        assertEquals(30, aeronave.getNumeroFileiras());
    }

    @Test
    void testAeronaveConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Aeronave("", 20000, 180, 30));
        assertEquals("O modelo da aeronave não pode ser vazio.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Aeronave("Boeing 737", -100, 180, 30));
        assertEquals("Capacidades e número de fileiras devem ser maiores que zero.", exception.getMessage());
    }

    @Test
    void testGerarAssentos() {
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 30, 5);
        List<String> assentos = aeronave.gerarAssentos();
        assertEquals(30, assentos.size());
        assertTrue(assentos.contains("1A"));
        assertTrue(assentos.contains("5F"));
        assertFalse(assentos.contains("6A"));
    }

    @Test
    void testEqualsAndHashCode() {
        Aeronave aeronave1 = new Aeronave("Boeing 737", 20000, 180, 30);
        Aeronave aeronave2 = new Aeronave("Boeing 737", 30000, 200, 40);
        Aeronave aeronave3 = new Aeronave("Airbus A320", 20000, 180, 30);

        assertEquals(aeronave1, aeronave2);
        assertNotEquals(aeronave1, aeronave3);
        assertEquals(aeronave1.hashCode(), aeronave2.hashCode());
        assertNotEquals(aeronave1.hashCode(), aeronave3.hashCode());
    }

    @Test
    void testToString() {
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        assertEquals("Modelo: Boeing 737, Capacidade de Passageiros: 180, Capacidade de Carga: 20000kg, Fileiras: 30",
                aeronave.toString());
    }
}