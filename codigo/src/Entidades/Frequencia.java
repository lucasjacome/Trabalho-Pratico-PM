package Entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Frequencia {
    private List<DayOfWeek> diasDaSemana;
    private Voo voo;

    public Frequencia(Voo voo, List<DayOfWeek> diasDaSemana) {
        this.voo = voo;
        this.diasDaSemana = diasDaSemana;
    }

    public List<LocalDateTime> gerarHorarios() {
        List<LocalDateTime> horarios = new ArrayList<>();
        LocalDateTime dataHoraVoo = voo.getDataHoraVoo();

        for (DayOfWeek dia : diasDaSemana) {
            LocalDateTime proximoVoo = dataHoraVoo.with(dia);
            if (proximoVoo.isBefore(dataHoraVoo)) {
                proximoVoo = proximoVoo.plusWeeks(1);
            }
            horarios.add(proximoVoo);
        }

        return horarios;
    }
}