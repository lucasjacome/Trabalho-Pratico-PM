package Tests;

import Entidades.Aeronave;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class AeronaveTest {

    @Test
    void testAeronaveConstrutorValido() {
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30, 850.0);
        assertEquals("Boeing 737", aeronave.getModelo());
        assertEquals(20000, aeronave.getCapacidadeCarga());
        assertEquals(180, aeronave.getCapacidadePassageiros());
        assertEquals(30, aeronave.getNumeroFileiras());
        assertEquals(850.0, aeronave.getVelocidadeMedia(), 0.01);
    }

    @Test
    void testAeronaveConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Aeronave("", 20000, 180, 30, 850.0));
        assertEquals("O modelo da aeronave não pode ser vazio.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Aeronave("Boeing 737", -100, 180, 30, 850.0));
        assertEquals("Capacidades, número de fileiras e velocidade média devem ser maiores que zero.",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Aeronave("Boeing 737", 20000, 180, 30, -50.0));
        assertEquals("Capacidades, número de fileiras e velocidade média devem ser maiores que zero.",
                exception.getMessage());
    }

    @Test
    void testGerarAssentos() {
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 30, 5, 850.0);
        List<String> assentos = aeronave.gerarAssentos();
        assertEquals(30, assentos.size());
        assertTrue(assentos.contains("1A"));
        assertTrue(assentos.contains("5F"));
        assertFalse(assentos.contains("6A"));
    }

    @Test
    void testEqualsAndHashCode() {
        Aeronave aeronave1 = new Aeronave("Boeing 737", 20000, 180, 30, 850.0);
        Aeronave aeronave2 = new Aeronave("Boeing 737", 30000, 200, 40, 900.0);
        Aeronave aeronave3 = new Aeronave("Airbus A320", 20000, 180, 30, 850.0);

        assertEquals(aeronave1, aeronave2);
        assertNotEquals(aeronave1, aeronave3);
        assertEquals(aeronave1.hashCode(), aeronave2.hashCode());
        assertNotEquals(aeronave1.hashCode(), aeronave3.hashCode());
    }

    @Test
    void testToString() {
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30, 850.0);
        assertEquals(
                "Modelo: Boeing 737, Capacidade de Passageiros: 180, Capacidade de Carga: 20000kg, Fileiras: 30, Velocidade Média: 850,00 km/h",
                aeronave.toString());
    }
}
