package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanhiaAereaManager {
    private List<CompanhiaAerea> companhias;

    public CompanhiaAereaManager() {
        this.companhias = new ArrayList<>();
    }

    public boolean adicionarCompanhia(CompanhiaAerea companhia) {
        if (companhia == null) {
            throw new IllegalArgumentException("A companhia aérea não pode ser nula.");
        }
        Optional<CompanhiaAerea> existente = companhias.stream()
                .filter(c -> c.getCnpj().equals(companhia.getCnpj()))
                .findFirst();
        if (existente.isPresent()) {
            return false;
        }
        companhias.add(companhia);
        return true;
    }

    public List<CompanhiaAerea> listarCompanhias() {
        return new ArrayList<>(companhias);
    }

    public CompanhiaAerea buscarCompanhiaPorCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio.");
        }
        return companhias.stream()
                .filter(c -> c.getCnpj().equals(cnpj))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Companhia aérea não encontrada com o CNPJ: " + cnpj));
    }
}
