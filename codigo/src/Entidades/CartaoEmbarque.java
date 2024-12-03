package Entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CartaoEmbarque {
    private String nome;
    private String sobrenome;
    private String origem;
    private String destino;
    private LocalDateTime horarioEmbarque;
    private LocalDateTime horarioVoo;
    private String assento;

    public CartaoEmbarque(String nome, String sobrenome, String origem, String destino, LocalDateTime horarioVoo,
            String assento) {
        if (nome == null || nome.trim().isEmpty() ||
                sobrenome == null || sobrenome.trim().isEmpty() ||
                origem == null || origem.trim().isEmpty() ||
                destino == null || destino.trim().isEmpty() ||
                horarioVoo == null || assento == null || assento.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios e devem ser válidos.");
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.origem = origem;
        this.destino = destino;
        this.horarioVoo = horarioVoo;
        this.horarioEmbarque = horarioVoo.minusMinutes(40);
        this.assento = assento;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getHorarioEmbarque() {
        return horarioEmbarque;
    }

    public LocalDateTime getHorarioVoo() {
        return horarioVoo;
    }

    public String getAssento() {
        return assento;
    }

    private String formatarDataHora(LocalDateTime dataHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }

    @Override
    public String toString() {
        return String.format(
                "Cartão de Embarque:\nNome: %s %s\nOrigem: %s\nDestino: %s\nAssento: %s\nHorário de Embarque: %s\nHorário do Voo: %s\n",
                nome, sobrenome, origem, destino, assento,
                formatarDataHora(horarioEmbarque), formatarDataHora(horarioVoo));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CartaoEmbarque that = (CartaoEmbarque) obj;
        return nome.equals(that.nome) &&
                sobrenome.equals(that.sobrenome) &&
                origem.equals(that.origem) &&
                destino.equals(that.destino) &&
                horarioVoo.equals(that.horarioVoo) &&
                assento.equals(that.assento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome, origem, destino, horarioVoo, assento);
    }
}
