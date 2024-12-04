package Managers;

import Entidades.Passageiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassageiroManager {
    private List<Passageiro> passageiros;

    public PassageiroManager() {
        this.passageiros = new ArrayList<>();
    }

    public boolean adicionarPassageiro(Passageiro passageiro) {
        if (passageiro == null) {
            throw new IllegalArgumentException("O passageiro não pode ser nulo.");
        }
        Optional<Passageiro> existente = passageiros.stream()
                .filter(p -> p.getDocumento().equals(passageiro.getDocumento()))
                .findFirst();
        if (existente.isPresent()) {
            return false;
        }
        passageiros.add(passageiro);
        return true;
    }

    public List<Passageiro> listarPassageiros() {
        return new ArrayList<>(passageiros);
    }

    public Passageiro buscarPassageiroPorDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("Documento não pode ser nulo ou vazio.");
        }
        return passageiros.stream()
                .filter(p -> p.getDocumento().equals(documento))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Passageiro não encontrado com o documento: " + documento));
    }

    public boolean alterarPassageiro(String documento, String novoNome, String novoSobrenome, String novoEmail) {
        Passageiro passageiro = buscarPassageiroPorDocumento(documento);
        if (passageiro != null) {
            passageiro.setNome(novoNome);
            passageiro.setSobrenome(novoSobrenome);
            passageiro.setEmail(novoEmail);
            return true;
        }
        return false;
    }

    public boolean excluirPassageiro(String documento) {
        return passageiros.removeIf(p -> p.getDocumento().equals(documento));
    }
}
