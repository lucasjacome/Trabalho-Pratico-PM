package Tests;

import Enums.StatusPassageiro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusPassageiroTest {

    @Test
    void testGetDescricao() {
        assertEquals("Regular", StatusPassageiro.REGULAR.getDescricao());
        assertEquals("Vip", StatusPassageiro.VIP.getDescricao());
    }

    @Test
    void testEnumValues() {
        StatusPassageiro[] values = StatusPassageiro.values();
        assertEquals(2, values.length);
        assertEquals(StatusPassageiro.REGULAR, values[0]);
        assertEquals(StatusPassageiro.VIP, values[1]);
    }

    @Test
    void testValueOf() {
        assertEquals(StatusPassageiro.REGULAR, StatusPassageiro.valueOf("REGULAR"));
        assertEquals(StatusPassageiro.VIP, StatusPassageiro.valueOf("VIP"));
    }
}
