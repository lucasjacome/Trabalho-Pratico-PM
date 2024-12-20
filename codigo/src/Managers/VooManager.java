package Managers;

import Entidades.Aeroporto;
import Entidades.Passageiro;
import Entidades.Voo;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<Voo> pesquisarVoos(Aeroporto origem, Aeroporto destino, LocalDateTime dataHoraPesquisa) {
        return voos.stream()
                .filter(voo -> voo.getOrigem().equals(origem)
                        && voo.getDestino().equals(destino)
                        && voo.getDataHoraVoo().equals(dataHoraPesquisa))
                .collect(Collectors.toList());
    }

    public List<Voo> pesquisarVoosIdaVolta(Aeroporto origem, Aeroporto destino, LocalDateTime dataIda,
            LocalDateTime dataVolta) {
        List<Voo> idaVolta = new ArrayList<>();
        idaVolta.addAll(pesquisarVoos(origem, destino, dataIda));
        idaVolta.addAll(pesquisarVoos(destino, origem, dataVolta));
        return idaVolta;
    }

    public List<List<Voo>> pesquisarVoosComConexao(Aeroporto origem, Aeroporto destino,
            LocalDateTime dataHoraPesquisa) {
        List<List<Voo>> conexoes = new ArrayList<>();

        List<Voo> voosIda = voos.stream()
                .filter(voo -> voo.getOrigem().equals(origem)
                        && voo.getDataHoraVoo().equals(dataHoraPesquisa))
                .collect(Collectors.toList());

        List<Voo> voosVolta = voos.stream()
                .filter(voo -> voo.getDestino().equals(destino)
                        && voo.getDataHoraVoo().isAfter(dataHoraPesquisa))
                .collect(Collectors.toList());

        for (Voo vooIda : voosIda) {
            for (Voo vooVolta : voosVolta) {
                if (vooIda.getDestino().equals(vooVolta.getOrigem())
                        && vooVolta.getDataHoraVoo().isAfter(vooIda.getDataHoraVoo())) {
                    List<Voo> conexao = new ArrayList<>();
                    conexao.add(vooIda);
                    conexao.add(vooVolta);
                    conexoes.add(conexao);
                }
            }
        }

        return conexoes;
    }

    public List<Voo> acessarHistoricoVoos(Passageiro passageiro) {
        return voos.stream()
                .filter(voo -> voo.getPassageiros().stream()
                        .anyMatch(p -> p.getPassageiro().equals(passageiro)))
                .sorted((v1, v2) -> v1.getDataHoraVoo().compareTo(v2.getDataHoraVoo()))
                .collect(Collectors.toList());
    }

    public boolean alterarVoo(String codigoVoo, LocalDateTime novoHorario, Aeroporto novaOrigem, Aeroporto novoDestino,
            double novaTarifaBasica, double novaTarifaBusiness, double novaTarifaPremium) {
        Voo voo = buscarVooPorCodigo(codigoVoo);
        if (voo != null) {
            voo.setDataHoraVoo(novoHorario);
            voo.setOrigem(novaOrigem);
            voo.setDestino(novoDestino);
            voo.setTarifaBasica(novaTarifaBasica);
            voo.setTarifaBusiness(novaTarifaBusiness);
            voo.setTarifaPremium(novaTarifaPremium);
            return true;
        }
        return false;
    }

    public boolean excluirVoo(String codigoVoo) {
        if (codigoVoo == null || codigoVoo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código do voo não pode ser nulo ou vazio.");
        }

        return voos.removeIf(voo -> voo.getCodigoVoo().equals(codigoVoo));
    }

    public Voo buscarVooPorCodigo(String codigoVoo) {
        if (codigoVoo == null || codigoVoo.trim().isEmpty()) {
            throw new IllegalArgumentException("O código do voo não pode ser nulo ou vazio.");
        }
        return voos.stream()
                .filter(voo -> voo.getCodigoVoo().equals(codigoVoo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Voo não encontrado com o código: " + codigoVoo));
    }

}
