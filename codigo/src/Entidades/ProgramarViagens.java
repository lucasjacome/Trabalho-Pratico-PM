package Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramarViagens {
    private static final int PERIODO_DIAS = 30;

    // Metodo para programar os voos para os próximos 30 dias

    public List<Voo> programarVoosPorPeriodo(Voo vooOriginal, Frequencia frequencia) {
        List<Voo> voosProgramados = new ArrayList<>();
        List<LocalDateTime> horarios = frequencia.gerarHorarios();
        for (LocalDateTime horario : horarios) {
            Voo vooProgramado = new Voo(
                    vooOriginal.getOrigem(),
                    vooOriginal.getDestino(),
                    horario,
                    vooOriginal.getCodigoVoo(),
                    vooOriginal.getCompanhia(),
                    vooOriginal.getAeronave(),
                    vooOriginal.getTarifaBasica(),
                    vooOriginal.getTarifaBusiness(),
                    vooOriginal.getTarifaPremium(),
                    vooOriginal.getMoeda()
            );
            voosProgramados.add(vooProgramado);
        }
        return voosProgramados;
    }

}
