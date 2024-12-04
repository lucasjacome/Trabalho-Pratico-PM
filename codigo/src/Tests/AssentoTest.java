package Tests;

import Entidades.Assento;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssentoTest {

    @Test
    void testConstrutorValido() {
        Assento assento = new Assento("1A");
        assertEquals("1A", assento.getNumeroAssento());
        assertTrue(assento.isDisponivel());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Assento(""));
        assertEquals("O número do assento não pode ser vazio.", exception.getMessage());
    }

    @Test
    void testReservarAssento() {
        Assento assento = new Assento("1A");
        assento.reservar();
        assertFalse(assento.isDisponivel());
        IllegalStateException exception = assertThrows(IllegalStateException.class, assento::reservar);
        assertEquals("O assento já está reservado.", exception.getMessage());
    }

    @Test
    void testLiberarAssento() {
        Assento assento = new Assento("1A");
        assento.reservar();
        assento.liberar();
        assertTrue(assento.isDisponivel());
    }

    @Test
    void testInicializarAssentos() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        assertEquals(3, assentos.size());
        assertEquals("1A", assentos.get(0).getNumeroAssento());
        assertTrue(assentos.get(0).isDisponivel());
    }

    @Test
    void testEncontrarAssento() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        Optional<Assento> assento = Assento.encontrarAssento(assentos, "1B");
        assertTrue(assento.isPresent());
        assertEquals("1B", assento.get().getNumeroAssento());
    }

    @Test
    void testEncontrarAssentoInexistente() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        Optional<Assento> assento = Assento.encontrarAssento(assentos, "1D");
        assertFalse(assento.isPresent());
    }

    @Test
    void testVerificarDisponibilidade() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        assertTrue(Assento.verificarDisponibilidade(assentos, "1A"));
        Assento.reservarAssento(assentos, "1A");
        assertFalse(Assento.verificarDisponibilidade(assentos, "1A"));
    }

    @Test
    void testReservarAssentoEstatico() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        assertTrue(Assento.reservarAssento(assentos, "1B"));
        assertFalse(Assento.reservarAssento(assentos, "1B"));
    }

    @Test
    void testToString() {
        Assento assento = new Assento("1A");
        assertEquals("Assento 1A [Disponível]", assento.toString());
        assento.reservar();
        assertEquals("Assento 1A [Reservado]", assento.toString());
    }
}
