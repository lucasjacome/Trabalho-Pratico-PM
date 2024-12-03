package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VooManager {
    private List<Voo> voos;

    public VooManager() {
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        if (voo == null) {
            throw new IllegalArgumentException("O voo não pode ser nulo.");
        }
        voos.add(voo);
    }

    public List<Voo> listarTodosOsVoos() {
        return new ArrayList<>(voos);
    }

    public List<Voo> pesquisarVoos(Aeroporto origem, Aeroporto destino, LocalDate dataPesquisa) {
        return voos.stream()
                .filter(voo -> voo.getOrigem().equals(origem) && voo.getDestino().equals(destino)
                        && voo.getDataHoraVoo().toLocalDate().equals(dataPesquisa))
                .collect(Collectors.toList());
    }

    public List<Voo> pesquisarVoosIdaVolta(Aeroporto origem, Aeroporto destino, LocalDate dataIda,
            LocalDate dataVolta) {
        List<Voo> idaVolta = new ArrayList<>();
        idaVolta.addAll(pesquisarVoos(origem, destino, dataIda));
        idaVolta.addAll(pesquisarVoos(destino, origem, dataVolta));
        return idaVolta;
    }

    public List<List<Voo>> pesquisarVoosComConexao(Aeroporto origem, Aeroporto destino, LocalDate dataPesquisa) {
        List<List<Voo>> conexoes = new ArrayList<>();
        voos.stream()
                .filter(voo -> voo.getOrigem().equals(origem)
                        && voo.getDataHoraVoo().toLocalDate().equals(dataPesquisa))
                .forEach(voo1 -> voos.stream()
                        .filter(voo2 -> voo1.getDestino().equals(voo2.getOrigem())
                                && voo2.getDestino().equals(destino)
                                && !voo1.getDestino().equals(destino)
                                && voo2.getDataHoraVoo().toLocalDate().equals(dataPesquisa)
                                && voo2.getDataHoraVoo().isAfter(voo1.getDataHoraVoo()))
                        .forEach(voo2 -> {
                            List<Voo> conexao = new ArrayList<>();
                            conexao.add(voo1);
                            conexao.add(voo2);
                            conexoes.add(conexao);
                        }));
        return conexoes;
    }

    public List<Voo> acessarHistoricoVoos(Passageiro passageiro) {
        return voos.stream()
                .filter(voo -> voo.getPassageiros().stream()
                        .anyMatch(p -> p.getPassageiro().equals(passageiro)))
                .sorted(Comparator.comparing(Voo::getDataHoraVoo))
                .collect(Collectors.toList());
    }
}
