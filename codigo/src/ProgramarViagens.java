import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ProgramarViagens {
    private static final int PERIODO_DIAS = 30;

    // Metodo para programar os voos para os próximos 30 dias

    public List<Voo> programarVoosPorPeriodo(Voo voo, Frequencia frequenciaVoo) {
        List<Voo> voosProgramados = new ArrayList<>();
        List<LocalDateTime> diasDaSemanaAtivos = frequenciaVoo.gerarHorarios();

        // Definindo o período de 30 dias a partir da data atual
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataFim = dataAtual.plusDays(PERIODO_DIAS);

        // Iterar por cada dia no período e programar o voo de acordo com a frequência
        while (dataAtual.isBefore(dataFim)) {
            for (LocalDateTime diaAtivo : diasDaSemanaAtivos) {
                // Ajusta a data do voo com o horário da frequência do voo original
                LocalDateTime horarioDoVoo = dataAtual.with(diaAtivo.toLocalTime());
                if (horarioDoVoo.isAfter(LocalDateTime.now())) { // Apenas programar voos futuros
                    Voo vooProgramado = new Voo(
                            voo.getOrigem(),
                            voo.getDestino(),
                            horarioDoVoo,
                            voo.getCodigoVoo(),
                            voo.getCompanhia(),
                            voo.getAeronave(),
                            voo.getTarifaBasica(),
                            voo.getTarifaBusiness(),
                            voo.getTarifaPremium(),
                            voo.getMoeda()
                    );
                    voosProgramados.add(vooProgramado);
                }
            }
            dataAtual = dataAtual.plus(1, ChronoUnit.DAYS);
        }

        return voosProgramados;
    }
}
