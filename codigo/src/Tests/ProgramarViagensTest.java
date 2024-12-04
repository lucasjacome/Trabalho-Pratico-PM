package Tests;

import Entidades.*;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramarViagensTest {

        @Test
        void testProgramarVoosPorPeriodoValido() {
                Aeroporto origem = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                                -46.6333);
                Aeroporto destino = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                                -43.1737);
                CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123",
                                50.0, 30.0);
                Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
                Voo vooOriginal = new Voo(origem, destino, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia,
                                aeronave,
                                500.0, 1000.0, 1500.0, "BRL");

                List<DayOfWeek> diasDaSemana = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
                Frequencia frequencia = new Frequencia(vooOriginal, diasDaSemana);

                ProgramarViagens programador = new ProgramarViagens();
                List<Voo> voosProgramados = programador.programarVoosPorPeriodo(vooOriginal, frequencia,
                                LocalDateTime.of(2024, 12, 1, 0, 0), LocalDateTime.of(2024, 12, 31, 23, 59));

                assertFalse(voosProgramados.isEmpty());
                assertEquals(13, voosProgramados.size()); // Assumindo 3 dias por semana em dezembro de 2024
                assertEquals("GRU", voosProgramados.get(0).getOrigem().getSigla());
                assertEquals("GIG", voosProgramados.get(0).getDestino().getSigla());
        }

        @Test
        void testProgramarVoosPorPeriodoInvalido() {
                Aeroporto origem = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                                -46.6333);
                Aeroporto destino = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                                -43.1737);
                CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123",
                                50.0, 30.0);
                Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
                Voo vooOriginal = new Voo(origem, destino, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia,
                                aeronave,
                                500.0, 1000.0, 1500.0, "BRL");

                List<DayOfWeek> diasDaSemana = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
                Frequencia frequencia = new Frequencia(vooOriginal, diasDaSemana);

                ProgramarViagens programador = new ProgramarViagens();

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                () -> programador.programarVoosPorPeriodo(vooOriginal, frequencia,
                                                LocalDateTime.of(2024, 12, 31, 23, 59),
                                                LocalDateTime.of(2024, 12, 1, 0, 0)));
                assertEquals("A data de término deve ser posterior à data de início.", exception.getMessage());
        }

        @Test
        void testProgramarVoosComParametrosNulos() {
                ProgramarViagens programador = new ProgramarViagens();

                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                                () -> programador.programarVoosPorPeriodo(null, null, null, null));
                assertEquals("Os parâmetros não podem ser nulos.", exception.getMessage());
        }
}
