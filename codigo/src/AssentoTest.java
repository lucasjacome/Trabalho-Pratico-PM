import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssentoTest {

    private Assento assento;

    @BeforeEach
    void setUp() {
        assento = new Assento("1A");
    }

    @Test
    void testInicializarAssentos() {
        List<String> identificadores = Arrays.asList("1A", "1B", "1C", "2A", "2B", "2C");
        List<Assento> assentos = Assento.inicializarAssentos(identificadores);
        
        assertEquals(6, assentos.size(), "A lista de assentos deve ter 6 elementos.");
        assertEquals("1A", assentos.get(0).getNumeroAssento());
        assertTrue(assentos.get(0).isDisponivel());
    }

    @Test
    void testAssentoDisponivelAoCriar() {
        assertTrue(assento.isDisponivel(), "Novo assento deve estar disponível.");
    }

    @Test
    void testReservarAssento() {
        assento.reservar();
        assertFalse(assento.isDisponivel(), "Assento deve estar indisponível após a reserva.");
    }

    @Test
    void testReservarAssentoJaReservado() {
        assento.reservar();
        assertThrows(IllegalStateException.class, assento::reservar, "Reservar um assento já reservado deve lançar exceção.");
    }

    @Test
    void testLiberarAssento() {
        assento.reservar();
        assento.liberar();
        assertTrue(assento.isDisponivel(), "Assento deve estar disponível após ser liberado.");
    }

    @Test
    void testEncontrarAssentoExistente() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        Optional<Assento> encontrado = Assento.encontrarAssento(assentos, "1B");

        assertTrue(encontrado.isPresent(), "Assento '1B' deve estar presente na lista.");
        assertEquals("1B", encontrado.get().getNumeroAssento());
    }

    @Test
    void testEncontrarAssentoNaoExistente() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        Optional<Assento> encontrado = Assento.encontrarAssento(assentos, "2A");

        assertFalse(encontrado.isPresent(), "Assento '2A' não deve estar presente na lista.");
    }

    @Test
    void testVerificarDisponibilidadeAssento() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        assertTrue(Assento.verificarDisponibilidade(assentos, "1A"), "Assento '1A' deve estar disponível inicialmente.");
    }

    @Test
    void testVerificarDisponibilidadeAssentoReservado() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        Assento.encontrarAssento(assentos, "1A").ifPresent(Assento::reservar);

        assertFalse(Assento.verificarDisponibilidade(assentos, "1A"), "Assento '1A' não deve estar disponível após reserva.");
    }

    @Test
    void testReservarAssentoEspecifico() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        boolean reservado = Assento.reservarAssento(assentos, "1B");

        assertTrue(reservado, "Reserva de '1B' deve ser bem-sucedida.");
        assertFalse(Assento.verificarDisponibilidade(assentos, "1B"), "Assento '1B' não deve estar disponível após reserva.");
    }

    @Test
    void testFalhaReservaAssentoJaReservado() {
        List<Assento> assentos = Assento.inicializarAssentos(Arrays.asList("1A", "1B", "1C"));
        Assento.reservarAssento(assentos, "1B");
        boolean reservado = Assento.reservarAssento(assentos, "1B");

        assertFalse(reservado, "Reserva de '1B' deve falhar pois o assento já está reservado.");
    }
}
