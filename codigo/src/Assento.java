import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Assento {
    private String numeroAssento;
    private boolean disponivel;

    public Assento(String numeroAssento) {
        this.numeroAssento = numeroAssento;
        this.disponivel = true; // Assento está disponível por padrão, até ser ocupado
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
            throw new IllegalStateException("Assento já está reservado.");
        }
    }

    public void liberar() {
        disponivel = true;
    }

    // Inicializa a lista de assentos com base nos identificadores
    public static List<Assento> inicializarAssentos(List<String> identificadores) {
        List<Assento> listaAssentos = new ArrayList<>();
        for (String id : identificadores) {
            listaAssentos.add(new Assento(id));
        }
        return listaAssentos;
    }

    // Encontra um assento específico em uma lista de assentos
    public static Optional<Assento> encontrarAssento(List<Assento> assentos, String numeroAssento) {
        return assentos.stream()
                .filter(a -> a.getNumeroAssento().equals(numeroAssento))
                .findFirst();
    }

    // Verifica disponibilidade de um assento específico em uma lista de assentos
    public static boolean verificarDisponibilidade(List<Assento> assentos, String numeroAssento) {
        return encontrarAssento(assentos, numeroAssento)
                .map(Assento::isDisponivel)
                .orElse(false);
    }

    // Tenta reservar um assento específico em uma lista de assentos
    public static boolean reservarAssento(List<Assento> assentos, String numeroAssento) {
        Optional<Assento> assento = encontrarAssento(assentos, numeroAssento);
        if (assento.isPresent() && assento.get().isDisponivel()) {
            assento.get().reservar();
            return true;
        }
        return false;
    }
}
