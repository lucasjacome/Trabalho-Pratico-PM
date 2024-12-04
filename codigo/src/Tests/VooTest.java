package Tests;

import Entidades.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VooTest {

    @Test
    void testConstrutorValido() {
        Voo voo = criarVoo();
        assertEquals("GRU", voo.getOrigem().getSigla());
        assertEquals("GIG", voo.getDestino().getSigla());
        assertEquals("XYZ123", voo.getCodigoVoo());
        assertEquals(500.0, voo.getTarifaBasica());
        assertEquals("Boeing 737", voo.getAeronave().getModelo());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Voo(null, null, null, null, null, null, 0, 0, 0, null));
        assertEquals("Nenhum campo pode ser nulo.", exception.getMessage());
    }

    @Test
    void testAdicionarPassageiro() {
        Voo voo = criarVoo();
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        voo.adicionarPassageiro(passageiro);
        assertEquals(1, voo.getPassageiros().size());
    }

    @Test
    void testRegistrarEmbarque() {
        Voo voo = criarVoo();
        Passageiro passageiro = new Passageiro("Maria", "Pereira", "98765432100", "maria@gmail.com");
        voo.adicionarPassageiro(passageiro);
        voo.registrarEmbarque("98765432100");
        assertTrue(voo.getPassageiros().get(0).isEmbarcado());
    }

    @Test
    void testCancelarEReverter() {
        Voo voo = criarVoo();
        voo.cancelar();
        assertTrue(voo.isCancelado());
        voo.reverterCancelamento();
        assertFalse(voo.isCancelado());
    }

    private Voo criarVoo() {
        Aeroporto origem = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505, -46.6333);
        Aeroporto destino = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094, -43.1737);
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia, aeronave, 500.0,
                1000.0, 1500.0, "BRL");
    }
}
