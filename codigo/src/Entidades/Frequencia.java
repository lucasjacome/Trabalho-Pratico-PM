package Entidades;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frequencia {
    private List<DayOfWeek> diasDaSemana;
    private Voo voo;

    public Frequencia(Voo voo, List<DayOfWeek> diasDaSemana) {
        if (voo == null || diasDaSemana == null || diasDaSemana.isEmpty()) {
            throw new IllegalArgumentException("Voo e dias da semana não podem ser nulos ou vazios.");
        }
        this.voo = voo;
        this.diasDaSemana = new ArrayList<>(diasDaSemana);
    }

    public List<DayOfWeek> getDiasDaSemana() {
        return new ArrayList<>(diasDaSemana);
    }

    public Voo getVoo() {
        return voo;
    }

    public List<LocalDateTime> gerarHorarios(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo) {
        if (inicioPeriodo == null || fimPeriodo == null || !fimPeriodo.isAfter(inicioPeriodo)) {
            throw new IllegalArgumentException("Período inválido. O fim deve ser posterior ao início.");
        }

        List<LocalDateTime> horarios = new ArrayList<>();
        LocalDateTime dataAtual = inicioPeriodo;

        while (!dataAtual.isAfter(fimPeriodo)) {
            if (diasDaSemana.contains(dataAtual.getDayOfWeek())) {
                horarios.add(dataAtual.withHour(voo.getDataHoraVoo().getHour())
                        .withMinute(voo.getDataHoraVoo().getMinute()));
            }
            dataAtual = dataAtual.plusDays(1);
        }

        return horarios;
    }

    @Override
    public String toString() {
        return String.format("Frequencia do Voo %s em dias: %s", voo.getCodigoVoo(), diasDaSemana);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Frequencia that = (Frequencia) obj;
        return voo.equals(that.voo) && diasDaSemana.equals(that.diasDaSemana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voo, diasDaSemana);
    }
}
