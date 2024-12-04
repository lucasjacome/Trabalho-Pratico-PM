package Tests;

import Entidades.Passageiro;
import Entidades.PassageiroEmbarque;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassageiroEmbarqueTest {

    @Test
    void testConstrutorValido() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        PassageiroEmbarque passageiroEmbarque = new PassageiroEmbarque(passageiro);

        assertEquals(passageiro, passageiroEmbarque.getPassageiro());
        assertFalse(passageiroEmbarque.isEmbarcado());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new PassageiroEmbarque(null));
        assertEquals("Passageiro não pode ser nulo.", exception.getMessage());
    }

    @Test
    void testSetEmbarcado() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        PassageiroEmbarque passageiroEmbarque = new PassageiroEmbarque(passageiro);

        passageiroEmbarque.setEmbarcado(true);
        assertTrue(passageiroEmbarque.isEmbarcado());

        passageiroEmbarque.setEmbarcado(false);
        assertFalse(passageiroEmbarque.isEmbarcado());
    }

    @Test
    void testToString() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        PassageiroEmbarque passageiroEmbarque = new PassageiroEmbarque(passageiro);

        String esperado = "Passageiro: João Silva (Documento: 12345678901, Email: joao@email.com), Embarcado: Não";
        assertEquals(esperado, passageiroEmbarque.toString());

        passageiroEmbarque.setEmbarcado(true);
        esperado = "Passageiro: João Silva (Documento: 12345678901, Email: joao@email.com), Embarcado: Sim";
        assertEquals(esperado, passageiroEmbarque.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Passageiro passageiro1 = new Passageiro("João", "Silva", "12345678901", "joao@email.com");
        Passageiro passageiro2 = new Passageiro("Maria", "Oliveira", "98765432100", "maria@email.com");

        PassageiroEmbarque embarque1 = new PassageiroEmbarque(passageiro1);
        PassageiroEmbarque embarque2 = new PassageiroEmbarque(passageiro1);
        PassageiroEmbarque embarque3 = new PassageiroEmbarque(passageiro2);

        assertEquals(embarque1, embarque2);
        assertNotEquals(embarque1, embarque3);

        assertEquals(embarque1.hashCode(), embarque2.hashCode());
        assertNotEquals(embarque1.hashCode(), embarque3.hashCode());
    }
}
