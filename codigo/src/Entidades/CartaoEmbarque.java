package Entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CartaoEmbarque {
    private String nome;
    private String sobrenome;
    private String origem;
    private String destino;
    private LocalDateTime horarioEmbarque;
    private LocalDateTime horarioVoo;
    private String assento;

    public CartaoEmbarque(String nome, String sobrenome, String origem, String destino, LocalDateTime horarioVoo, String assento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.origem = origem;
        this.destino = destino;
        this.horarioVoo = horarioVoo;
        this.horarioEmbarque = horarioVoo.minusMinutes(40);
        this.assento = assento;
    }

    // Método para formatar a data e hora
    private String formatarDataHora(LocalDateTime dataHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataHora.format(formatter);
    }

    @Override
    public String toString() {
        return "Cartão de Embarque:\n" +
                "Nome: " + nome + " " + sobrenome + "\n" +
                "Origem: " + origem + "\n" +
                "Destino: " + destino + "\n" +
                "Entidades.Assento: " + assento + "\n" +
                "Horário de Embarque: " + formatarDataHora(horarioEmbarque) + "\n" +
                "Horário do Entidades.Voo: " + formatarDataHora(horarioVoo) + "\n";
    }
}
