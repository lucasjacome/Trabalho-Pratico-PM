package Tests;

import Entidades.Aeroporto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AeroportoTest {

    @Test
    void testAeroportoConstrutorValido() {
        Aeroporto aeroporto = new Aeroporto("Aeroporto Internacional", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        assertEquals("Aeroporto Internacional", aeroporto.getNome());
        assertEquals("GRU", aeroporto.getSigla());
        assertEquals("São Paulo", aeroporto.getCidade());
        assertEquals("SP", aeroporto.getEstado());
        assertEquals("Brasil", aeroporto.getPais());
        assertEquals(-23.5505, aeroporto.getLatitude(), 0.0001);
        assertEquals(-46.6333, aeroporto.getLongitude(), 0.0001);
    }

    @Test
    void testAeroportoConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Aeroporto("Aeroporto Internacional", "GR", "São Paulo", "SP", "Brasil", -23.5505, -46.6333));
        assertEquals("A sigla deve conter exatamente 3 caracteres.", exception.getMessage());
    }

    @Test
    void testAeroportoConstrutorValoresPadrao() {
        Aeroporto aeroporto = new Aeroporto("", "GRU", "", "", "", 0.0, 0.0);
        assertEquals("Não definido", aeroporto.getNome());
        assertEquals("GRU", aeroporto.getSigla());
        assertEquals("Não definida", aeroporto.getCidade());
        assertEquals("Não definido", aeroporto.getEstado());
        assertEquals("Não definido", aeroporto.getPais());
        assertEquals(0.0, aeroporto.getLatitude(), 0.0001);
        assertEquals(0.0, aeroporto.getLongitude(), 0.0001);
    }

    @Test
    void testEqualsAndHashCode() {
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto Internacional", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto Internacional", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        Aeroporto aeroporto3 = new Aeroporto("Aeroporto do Rio", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9068,
                -43.1729);

        assertEquals(aeroporto1, aeroporto2);
        assertNotEquals(aeroporto1, aeroporto3);
        assertEquals(aeroporto1.hashCode(), aeroporto2.hashCode());
        assertNotEquals(aeroporto1.hashCode(), aeroporto3.hashCode());
    }

    @Test
    void testToString() {
        Aeroporto aeroporto = new Aeroporto("Aeroporto Internacional", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        assertEquals(
                "Aeroporto Internacional (GRU), São Paulo, SP, Brasil - Latitude: -23.550500, Longitude: -46.633300",
                aeroporto.toString());
    }
}
