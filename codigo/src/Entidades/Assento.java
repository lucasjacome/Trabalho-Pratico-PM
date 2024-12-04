package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Assento {
    private String numeroAssento;
    private boolean disponivel;

    public Assento(String numeroAssento) {
        if (numeroAssento == null || numeroAssento.trim().isEmpty()) {
            throw new IllegalArgumentException("O número do assento não pode ser vazio.");
        }
        this.numeroAssento = numeroAssento;
        this.disponivel = true;
    }

    public String getNumeroAssento() {
        return numeroAssento;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void reservar() {
        if (disponivel) {
            disponivel = false;
        } else {
            throw new IllegalStateException("O assento já está reservado.");
        }
    }

    public void liberar() {
        disponivel = true;
    }

    public static List<Assento> inicializarAssentos(List<String> identificadores) {
        if (identificadores == null || identificadores.isEmpty()) {
            throw new IllegalArgumentException("A lista de identificadores não pode ser vazia.");
        }
        List<Assento> listaAssentos = new ArrayList<>();
        for (String id : identificadores) {
            listaAssentos.add(new Assento(id));
        }
        return listaAssentos;
    }

    public static Optional<Assento> encontrarAssento(List<Assento> assentos, String numeroAssento) {
        if (assentos == null || numeroAssento == null) {
            return Optional.empty();
        }
        return assentos.stream()
                .filter(a -> a.getNumeroAssento().equals(numeroAssento))
                .findFirst();
    }

    public static boolean verificarDisponibilidade(List<Assento> assentos, String numeroAssento) {
        return encontrarAssento(assentos, numeroAssento)
                .map(Assento::isDisponivel)
                .orElse(false);
    }

    public static boolean reservarAssento(List<Assento> assentos, String numeroAssento) {
        Optional<Assento> assento = encontrarAssento(assentos, numeroAssento);
        if (assento.isPresent() && assento.get().isDisponivel()) {
            assento.get().reservar();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Assento %s [%s]", numeroAssento, disponivel ? "Disponível" : "Reservado");
    }
}
