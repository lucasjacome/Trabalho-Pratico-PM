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
        if (companhia.getCnpj() == null || companhia.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ da companhia aérea não pode ser nulo ou vazio.");
        }
        Optional<CompanhiaAerea> existente = companhias.stream()
                .filter(c -> c.getCnpj().equals(companhia.getCnpj()))
                .findFirst();
        if (existente.isPresent()) {
            return false; // Companhia já cadastrada
        }
        companhias.add(companhia);
        return true;
    }

    public List<CompanhiaAerea> listarCompanhias() {
        return new ArrayList<>(companhias); // Retorna uma cópia para proteger a lista original
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

    public boolean alterarCompanhia(String cnpj, String novoNome, String novaRazaoSocial, String novoCodigo,
            double novoValorPrimeiraBagagem, double novoValorBagagensAdicionais) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ não pode ser nulo ou vazio.");
        }

        CompanhiaAerea companhia = buscarCompanhiaPorCnpj(cnpj); 
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            companhia.setNome(novoNome);
        }
        if (novaRazaoSocial != null && !novaRazaoSocial.trim().isEmpty()) {
            companhia.setRazaoSocial(novaRazaoSocial);
        }
        if (novoCodigo != null && !novoCodigo.trim().isEmpty()) {
            companhia.setCodigo(novoCodigo);
        }
        if (novoValorPrimeiraBagagem >= 0) {
            companhia.setValorPrimeiraBagagem(novoValorPrimeiraBagagem);
        } else {
            throw new IllegalArgumentException("O valor da primeira bagagem deve ser maior ou igual a zero.");
        }
        if (novoValorBagagensAdicionais >= 0) {
            companhia.setValorBagagensAdicionais(novoValorBagagensAdicionais);
        } else {
            throw new IllegalArgumentException("O valor das bagagens adicionais deve ser maior ou igual a zero.");
        }
        return true;
    }

    public boolean excluirCompanhia(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ não pode ser nulo ou vazio.");
        }
        return companhias.removeIf(c -> c.getCnpj().equals(cnpj));
    }
}
