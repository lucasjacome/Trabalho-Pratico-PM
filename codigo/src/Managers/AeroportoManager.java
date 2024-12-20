package Managers;

import Entidades.Aeroporto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AeroportoManager {
    private List<Aeroporto> aeroportos;

    public AeroportoManager() {
        this.aeroportos = new ArrayList<>();
    }

    public boolean adicionarAeroporto(Aeroporto aeroporto) {
        if (aeroporto == null) {
            throw new IllegalArgumentException("O aeroporto não pode ser nulo.");
        }
        Optional<Aeroporto> existente = aeroportos.stream()
                .filter(a -> a.getSigla().equals(aeroporto.getSigla()))
                .findFirst();
        if (existente.isPresent()) {
            return false;
        }
        aeroportos.add(aeroporto);
        return true;
    }

    public List<Aeroporto> listarAeroportos() {
        return new ArrayList<>(aeroportos);
    }

    public Aeroporto buscarAeroportoPorSigla(String sigla) {
        if (sigla == null || sigla.trim().isEmpty()) {
            throw new IllegalArgumentException("Sigla não pode ser nula ou vazia.");
        }
        return aeroportos.stream()
                .filter(a -> a.getSigla().equals(sigla))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Aeroporto não encontrado com a sigla: " + sigla));
    }

    public boolean alterarAeroporto(String sigla, String novoNome, String novaCidade, String novoEstado,
            String novoPais) {
        if (sigla == null || sigla.trim().isEmpty()) {
            throw new IllegalArgumentException("A sigla não pode ser nula ou vazia.");
        }
        Aeroporto aeroporto = buscarAeroportoPorSigla(sigla);
        if (aeroporto != null) {
            aeroporto.setNome(novoNome);
            aeroporto.setCidade(novaCidade);
            aeroporto.setEstado(novoEstado);
            aeroporto.setPais(novoPais);
            return true;
        }
        return false;
    }

    public boolean removerAeroporto(String sigla) {
        if (sigla == null || sigla.trim().isEmpty()) {
            throw new IllegalArgumentException("A sigla não pode ser nula ou vazia.");
        }
        Aeroporto aeroporto = buscarAeroportoPorSigla(sigla);
        return aeroportos.remove(aeroporto);
    }
}
