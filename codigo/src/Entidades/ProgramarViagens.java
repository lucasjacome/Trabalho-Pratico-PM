package Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgramarViagens {

    public List<Voo> programarVoosPorPeriodo(Voo vooOriginal, Frequencia frequencia, LocalDateTime inicio,
            LocalDateTime fim) {
        if (vooOriginal == null || frequencia == null || inicio == null || fim == null) {
            throw new IllegalArgumentException("Os parâmetros não podem ser nulos.");
        }

        if (fim.isBefore(inicio)) {
            throw new IllegalArgumentException("A data de término deve ser posterior à data de início.");
        }

        List<Voo> voosProgramados = new ArrayList<>();
        List<LocalDateTime> horarios = frequencia.gerarHorarios(inicio, fim);

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
                    vooOriginal.getMoeda());
            voosProgramados.add(vooProgramado);
        }
        return voosProgramados;
    }
}
