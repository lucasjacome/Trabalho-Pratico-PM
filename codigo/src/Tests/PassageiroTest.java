package Tests;

import Entidades.Passageiro;
import Entidades.CompanhiaAerea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassageiroTest {

    @Test
    void testConstrutorValido() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        assertEquals("João", passageiro.getNome());
        assertEquals("Silva", passageiro.getSobrenome());
        assertEquals("12345678901", passageiro.getDocumento());
        assertEquals("joao@email.com", passageiro.getEmail());
        assertFalse(passageiro.isVip());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Passageiro("", "Silva", "12345678901", "joao@email.com"));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Passageiro("João", "", "12345678901", "joao@email.com"));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Passageiro("João", "Silva", "", "joao@email.com"));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new Passageiro("João", "Silva", "12345678901", ""));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());
    }

    @Test
    void testSetVipStatus() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        passageiro.setVipStatus(true);
        assertTrue(passageiro.isVip());

        passageiro.setVipStatus(false);
        assertFalse(passageiro.isVip());
    }

    @Test
    void testCalcularValorBagagem() {
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia XYZ", "XYZ", "Razão Social XYZ", "12345678000123",
                50.0, 30.0);
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");

        assertEquals(100.0, passageiro.calcularValorBagagem(companhia, 2), 0.01);

        passageiro.setVipStatus(true);
        assertEquals(15.0, passageiro.calcularValorBagagem(companhia, 2), 0.01);
        assertEquals(0.0, passageiro.calcularValorBagagem(companhia, 1), 0.01);
    }

    @Test
    void testCalcularValorBagagemInvalido() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> passageiro.calcularValorBagagem(null, 2));
        assertEquals("Companhia não pode ser nula e a quantidade de bagagens deve ser positiva.",
                exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> passageiro.calcularValorBagagem(
                        new CompanhiaAerea("XYZ", "X", "XYZ LTDA", "12345678000123", 50.0, 30.0), -1));
        assertEquals("Companhia não pode ser nula e a quantidade de bagagens deve ser positiva.",
                exception.getMessage());
    }

    @Test
    void testToString() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        assertEquals("João Silva (Documento: 12345678901, Email: joao@email.com)", passageiro.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Passageiro passageiro1 = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        Passageiro passageiro2 = new Passageiro("Maria", "Oliveira", "12345678901", "maria@email.com");
        Passageiro passageiro3 = new Passageiro("Carlos", "Santos", "98765432100", "carlos@email.com");

        assertEquals(passageiro1, passageiro2);
        assertNotEquals(passageiro1, passageiro3);
        assertEquals(passageiro1.hashCode(), passageiro2.hashCode());
        assertNotEquals(passageiro1.hashCode(), passageiro3.hashCode());
    }
}
