import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgramarViagensTest {

    private ProgramarViagens programarViagens;
    private Voo vooOriginal;
    private Frequencia frequencia;

    @BeforeEach
    void setUp() {
        // Inicializa os objetos necessários antes de cada teste
        programarViagens = new ProgramarViagens();

        // Exemplo de origem e destino
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ", "Brasil");

        // Aeronave exemplo
        Aeronave aeronave = new Aeronave("Boeing 747", 300, 400, 66);

        // Voo original com data base
        vooOriginal = new Voo(
                origem,
                destino,
                LocalDateTime.of(2024, Month.NOVEMBER, 1, 10, 30),
                "XY1234",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA",
                        "12345678000195", 50.0, 30.0),
                aeronave,
                500.00,
                1000.00,
                1500.00,
                "BRL"
        );

        // Frequência para os próximos 30 dias, voo diário
        List<DayOfWeek> diasDaSemana = List.of(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY
        );

        Frequencia frequencia = new Frequencia(vooOriginal, diasDaSemana);
    }

    @Test
    void testProgramarVoosPorPeriodo() {
        List<Voo> voosProgramados = programarViagens.programarVoosPorPeriodo(vooOriginal, frequencia);

        // Verifica se o número de voos programados está correto
        assertEquals(30, voosProgramados.size(), "Deve haver 30 voos programados.");

        // Verifica se os voos têm horários únicos e progressivos
        for (int i = 0; i < voosProgramados.size(); i++) {
            Voo voo = voosProgramados.get(i);
            assertEquals(vooOriginal.getCodigoVoo(), voo.getCodigoVoo(), "Os códigos de voo devem ser iguais.");
            assertEquals(vooOriginal.getOrigem(), voo.getOrigem(), "As origens devem ser iguais.");
            assertEquals(vooOriginal.getDestino(), voo.getDestino(), "Os destinos devem ser iguais.");
            assertEquals(vooOriginal.getAeronave(), voo.getAeronave(), "As aeronaves devem ser iguais.");

            // Verifica se o horário do voo está correto
            LocalDateTime horarioEsperado = vooOriginal.getDataHoraVoo().plusDays(i);
            assertEquals(horarioEsperado, voo.getDataHoraVoo(), "O horário do voo deve corresponder à sequência esperada.");
        }
    }
}
