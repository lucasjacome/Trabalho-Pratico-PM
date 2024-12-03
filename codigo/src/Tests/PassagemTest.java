package Tests;

import static org.junit.jupiter.api.Assertions.*;

import Entidades.*;
import Enums.StatusPassagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class PassagemTest {

    private Passagem passagem;
    private Voo voo;

    @BeforeEach
    public void setup() {
        passagem = new Passagem(100.0);

        voo = new Voo(
                new Aeroporto("Entidades.Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                new Aeroporto("Entidades.Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil"),
                LocalDateTime.now().plusHours(47), // Entidades.Voo em 47 horas
                "XY1234",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA", "12345678000195", 50.0,
                        30.0),
                new Aeronave("Boeing 747", 350, 400, 66),
                500.0,
                1000.0,
                1500.0,
                "BRL"
        );

        passagem.adicionarVoo(voo);
    }

    @Test
    public void testAdicionarVoo() {
        Assertions.assertNotNull(passagem.getVoo(), "A passagem deve conter um voo após a adição.");
        Assertions.assertEquals(voo, passagem.getVoo(), "O voo adicionado deve ser o esperado.");
    }

    @Test
    public void testCalcularPrecoTotal() {
        double precoTotal = passagem.calcularPrecoTotal();
        assertEquals(600.0, precoTotal, 0.01, "O preço total deve ser a soma da tarifa básica e da taxa da agência.");
    }

    @Test
    public void testCalcularCustoTotalComBagagens() {
        double custoTotal = passagem.calcularCustoTotalComBagagens(2);
        double esperado = 600.0 + 50.0 + (2 * 30.0); // Preço total + valor da primeira bagagem + adicionais
        assertEquals(esperado, custoTotal, 0.01, "O custo total com bagagens deve incluir os valores corretamente.");
    }

    @Test
    public void testRealizarCheckInDentroDoPeriodoPermitido() {
        boolean checkIn = passagem.realizarCheckIn();
        assertTrue(checkIn, "O check-in deve ser permitido dentro do período correto.");
        Assertions.assertEquals(StatusPassagem.CHECK_IN_REALIZADO, passagem.getStatus(), "O status deve ser atualizado para CHECK_IN_REALIZADO.");
    }

    @Test
    public void testRealizarCheckInForaDoPeriodoPermitido() {
        // Simula um voo fora do período permitido para check-in
        Voo vooForaPeriodo = new Voo(
                new Aeroporto("Entidades.Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                new Aeroporto("Entidades.Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil"),
                LocalDateTime.now().plusHours(50), // Fora do período de check-in
                "XY5678",
                new CompanhiaAerea("Companhia Aérea ABC", "ABC123", "Razão Social ABC LTDA", "98765432100195", 50.0,
                        30.0),
                new Aeronave("Boeing 737", 200, 300, 60),
                400.0,
                900.0,
                1400.0,
                "BRL"
        );

        Passagem passagemForaPeriodo = new Passagem(100.0);
        passagemForaPeriodo.adicionarVoo(vooForaPeriodo);

        boolean checkIn = passagemForaPeriodo.realizarCheckIn();
        assertFalse(checkIn, "O check-in não deve ser permitido fora do período correto.");
        Assertions.assertEquals(StatusPassagem.PASSAGEM_ADQUIRIDA, passagemForaPeriodo.getStatus(), "O status deve permanecer como PASSAGEM_ADQUIRIDA.");
    }

    @Test
    public void testRegistrarNoShow() {
        // Não realizar check-in
        passagem.registrarNoShow();
        Assertions.assertEquals(StatusPassagem.NO_SHOW, passagem.getStatus(), "O status deve ser atualizado para NO_SHOW.");
    }

    @Test
    public void testRegistrarNoShowAposCheckIn() {
        // Realiza o check-in
        passagem.realizarCheckIn();

        // Tenta registrar NO SHOW após o check-in
        passagem.registrarNoShow();
        Assertions.assertNotEquals(StatusPassagem.NO_SHOW, passagem.getStatus(), "O status não deve ser atualizado para NO_SHOW após check-in.");
    }

    @Test
    public void testGetVoo() {
        Assertions.assertEquals(voo, passagem.getVoo(), "O método getVoo deve retornar o primeiro voo da passagem.");
    }
}
