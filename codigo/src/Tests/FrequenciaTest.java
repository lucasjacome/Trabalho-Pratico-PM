package Tests;

import Entidades.Frequencia;
import Entidades.Voo;
import Entidades.Aeroporto;
import Entidades.CompanhiaAerea;
import Entidades.Aeronave;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrequenciaTest {

    @Test
    void testConstrutorValido() {
        Voo voo = criarVoo();
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        Frequencia frequencia = new Frequencia(voo, dias);

        assertEquals(voo, frequencia.getVoo());
        assertEquals(dias, frequencia.getDiasDaSemana());
    }

    @Test
    void testConstrutorInvalido() {
        Voo voo = criarVoo();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Frequencia(voo, null));
        assertEquals("Voo e dias da semana não podem ser nulos ou vazios.", exception.getMessage());
    }

    @Test
    void testGerarHorariosValido() {
        Voo voo = criarVoo();
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        Frequencia frequencia = new Frequencia(voo, dias);

        LocalDateTime inicio = LocalDateTime.of(2024, 12, 1, 0, 0);
        LocalDateTime fim = LocalDateTime.of(2024, 12, 7, 23, 59);

        List<LocalDateTime> horarios = frequencia.gerarHorarios(inicio, fim);

        assertEquals(2, horarios.size());
        assertEquals(LocalDateTime.of(2024, 12, 2, voo.getDataHoraVoo().getHour(), voo.getDataHoraVoo().getMinute()),
                horarios.get(0));
        assertEquals(LocalDateTime.of(2024, 12, 4, voo.getDataHoraVoo().getHour(), voo.getDataHoraVoo().getMinute()),
                horarios.get(1));
    }

    @Test
    void testGerarHorariosPeriodoInvalido() {
        Voo voo = criarVoo();
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        Frequencia frequencia = new Frequencia(voo, dias);

        LocalDateTime inicio = LocalDateTime.of(2024, 12, 7, 23, 59);
        LocalDateTime fim = LocalDateTime.of(2024, 12, 1, 0, 0);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> frequencia.gerarHorarios(inicio, fim));
        assertEquals("Período inválido. O fim deve ser posterior ao início.", exception.getMessage());
    }

    @Test
    void testToString() {
        Voo voo = criarVoo();
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        Frequencia frequencia = new Frequencia(voo, dias);

        String esperado = "Frequencia do Voo XY123 em dias: [MONDAY, WEDNESDAY]";
        assertEquals(esperado, frequencia.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Voo voo = criarVoo();
        List<DayOfWeek> dias = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        Frequencia frequencia1 = new Frequencia(voo, dias);
        Frequencia frequencia2 = new Frequencia(voo, dias);

        assertEquals(frequencia1, frequencia2);
        assertEquals(frequencia1.hashCode(), frequencia2.hashCode());
    }

    private Voo criarVoo() {
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        Aeroporto destino = new Aeroporto("Aeroporto do Rio", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                -43.1737);
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ",
                "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30, 850.0);
        return new Voo(origem, destino, LocalDateTime.of(2024, 12, 1, 10, 0), "XY123", companhia, aeronave, 500.0,
                1000.0, 1500.0, "BRL");
    }
}
