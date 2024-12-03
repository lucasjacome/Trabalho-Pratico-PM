package Tests;

import Entidades.StatusPassagem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusPassagemTest {

    @Test
    void testGetDescricao() {
        assertEquals("Passagem adquirida", StatusPassagem.PASSAGEM_ADQUIRIDA.getDescricao());
        assertEquals("Passagem cancelada", StatusPassagem.PASSAGEM_CANCELADA.getDescricao());
        assertEquals("Check-in realizado", StatusPassagem.CHECK_IN_REALIZADO.getDescricao());
        assertEquals("Embarque realizado", StatusPassagem.EMBARQUE_REALIZADO.getDescricao());
        assertEquals("No-show", StatusPassagem.NO_SHOW.getDescricao());
    }

    @Test
    void testEnumValues() {
        StatusPassagem[] values = StatusPassagem.values();
        assertEquals(5, values.length);
        assertEquals(StatusPassagem.PASSAGEM_ADQUIRIDA, values[0]);
        assertEquals(StatusPassagem.PASSAGEM_CANCELADA, values[1]);
        assertEquals(StatusPassagem.CHECK_IN_REALIZADO, values[2]);
        assertEquals(StatusPassagem.EMBARQUE_REALIZADO, values[3]);
        assertEquals(StatusPassagem.NO_SHOW, values[4]);
    }

    @Test
    void testValueOf() {
        assertEquals(StatusPassagem.PASSAGEM_ADQUIRIDA, StatusPassagem.valueOf("PASSAGEM_ADQUIRIDA"));
        assertEquals(StatusPassagem.PASSAGEM_CANCELADA, StatusPassagem.valueOf("PASSAGEM_CANCELADA"));
        assertEquals(StatusPassagem.CHECK_IN_REALIZADO, StatusPassagem.valueOf("CHECK_IN_REALIZADO"));
        assertEquals(StatusPassagem.EMBARQUE_REALIZADO, StatusPassagem.valueOf("EMBARQUE_REALIZADO"));
        assertEquals(StatusPassagem.NO_SHOW, StatusPassagem.valueOf("NO_SHOW"));
    }
}
