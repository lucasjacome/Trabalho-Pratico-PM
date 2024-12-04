package Tests;

import Enums.LetraAssento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LetraAssentoTest {

    @Test
    void testIsLetraValida() {
        assertTrue(LetraAssento.isLetraValida("A"));
        assertTrue(LetraAssento.isLetraValida("b"));
        assertTrue(LetraAssento.isLetraValida("F"));
        assertFalse(LetraAssento.isLetraValida("G"));
        assertFalse(LetraAssento.isLetraValida(""));
        assertFalse(LetraAssento.isLetraValida(null));
    }

    @Test
    void testFromStringValido() {
        assertEquals(LetraAssento.A, LetraAssento.fromString("A"));
        assertEquals(LetraAssento.B, LetraAssento.fromString("b"));
        assertEquals(LetraAssento.F, LetraAssento.fromString("F"));
    }

    @Test
    void testFromStringInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> LetraAssento.fromString("G"));
        assertEquals("Letra de assento inválida: G", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> LetraAssento.fromString(""));
        assertEquals("Letra de assento inválida: ", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> LetraAssento.fromString(null));
        assertEquals("Letra de assento inválida: null", exception.getMessage());
    }
}
