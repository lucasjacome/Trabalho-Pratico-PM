package Tests;

import Entidades.Passagem;
import Entidades.StatusPassagem;
import Entidades.Voo;
import Entidades.Aeroporto;
import Entidades.CompanhiaAerea;
import Entidades.Aeronave;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PassagemTest {

    @Test
    void testConstrutorValido() {
        Passagem passagem = new Passagem(100.0);
        assertEquals(100.0, passagem.calcularPrecoTotal());
        assertEquals(StatusPassagem.PASSAGEM_ADQUIRIDA, passagem.getStatus());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Passagem(-10.0));
        assertEquals("A taxa da agência não pode ser negativa.", exception.getMessage());
    }

    @Test
    void testAdicionarVoo() {
        Passagem passagem = new Passagem(100.0);
        Voo voo = criarVoo();
        passagem.adicionarVoo(voo);
        assertEquals(voo, passagem.getVooPrincipal());
    }

    @Test
    void testAdicionarVooInvalido() {
        Passagem passagem = new Passagem(100.0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> passagem.adicionarVoo(null));
        assertEquals("O voo não pode ser nulo.", exception.getMessage());
    }

    @Test
    void testCalcularPrecoTotal() {
        Passagem passagem = new Passagem(50.0);
        Voo voo = criarVoo();
        passagem.adicionarVoo(voo);
        assertEquals(550.0, passagem.calcularPrecoTotal(), 0.01);
    }

    @Test
    void testCalcularCustoTotalComBagagens() {
        Passagem passagem = new Passagem(50.0);
        Voo voo = criarVoo();
        passagem.adicionarVoo(voo);

        assertEquals(600.0, passagem.calcularCustoTotalComBagagens(1), 0.01);
        assertEquals(630.0, passagem.calcularCustoTotalComBagagens(2), 0.01);
    }

    @Test
    void testSetStatus() {
        Passagem passagem = new Passagem(100.0);
        passagem.setStatus(StatusPassagem.CHECK_IN_REALIZADO);
        assertEquals(StatusPassagem.CHECK_IN_REALIZADO, passagem.getStatus());
    }

    private Voo criarVoo() {
        Aeroporto origem = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia, aeronave, 500.0,
                1000.0, 1500.0, "BRL");
    }
}
