import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrequenciaTest {

    private Voo voo;
    private Frequencia frequencia;

    @BeforeEach
    public void setUp() {
        voo =
                new Voo(new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                        new Aeroporto("Aeroporto do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ", "Brasil"),
                        LocalDateTime.now(), "AV123",
                        new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA",
                        "12345678000195", 50.0, 30.0),
                        new Aeronave("Boeing 737", 100, 180, 30),
                        200, 400, 600, "BRL");
    }

    @Test
    public void testGerarHorariosComDiasFuturos() {
        // Configura os dias da semana: segunda e quarta
        List<DayOfWeek> diasDaSemana = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        frequencia = new Frequencia(voo, diasDaSemana);

        List<LocalDateTime> horarios = frequencia.gerarHorarios();

        // Verifica se os horários gerados são para segunda e quarta da mesma semana
        assertEquals(2, horarios.size());

        for (LocalDateTime horario : horarios) {
            assertTrue(horario.getDayOfWeek() == DayOfWeek.MONDAY ||
                    horario.getDayOfWeek() == DayOfWeek.WEDNESDAY);
            assertTrue(horario.isAfter(voo.getDataHoraVoo())); // Deve ser após o horário do voo
        }
    }

    @Test
    public void testGerarHorariosComDiaPassado() {
        // Configura os dias da semana: domingo
        List<DayOfWeek> diasDaSemana = Arrays.asList(DayOfWeek.SUNDAY);
        frequencia = new Frequencia(voo, diasDaSemana);

        List<LocalDateTime> horarios = frequencia.gerarHorarios();

        // Verifica que o horário gerado não é para o próximo domingo
        assertEquals(1, horarios.size());
        assertEquals(DayOfWeek.SUNDAY, horarios.get(0).getDayOfWeek());
        assertFalse(horarios.get(0).isAfter(voo.getDataHoraVoo()));
    }

    @Test
    public void testGerarHorariosComDiasVazios() {
        // Configura uma Frequencia com dias vazios
        List<DayOfWeek> diasDaSemana = Arrays.asList();
        frequencia = new Frequencia(voo, diasDaSemana);

        List<LocalDateTime> horarios = frequencia.gerarHorarios();

        // Verifica que não há horários gerados
        assertTrue(horarios.isEmpty());
    }
}
