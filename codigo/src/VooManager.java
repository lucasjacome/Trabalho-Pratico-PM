import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VooManager {
    private List<Voo> voos;


    public VooManager() {
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    public List<Voo> listarTodosOsVoos() {
        return voos;
    }

    // Pesquisa de voos diretos (comparação de datas sem horas)
    public List<Voo> pesquisarVoos(Aeroporto origem, Aeroporto destino, LocalDateTime dataHoraPesquisa) {
        List<Voo> resultado = new ArrayList<>();
        LocalDate dataPesquisa = dataHoraPesquisa.toLocalDate(); // Considerar apenas a data

        for (Voo voo : voos) {
            if (voo.getOrigem().equals(origem) && voo.getDestino().equals(destino) &&
                    voo.getDataHoraVoo().toLocalDate().equals(dataPesquisa)) {
                resultado.add(voo);
            }
        }
        return resultado;
    }

    // Pesquisa de voos de ida e volta
    public List<Voo> pesquisarVoosIdaVolta(Aeroporto origem, Aeroporto destino, LocalDateTime dataIda,
            LocalDateTime dataVolta) {
        List<Voo> idaVolta = new ArrayList<>();

        // Pesquisar voo de ida
        for (Voo voo : voos) {
            if (voo.getOrigem().equals(origem) && voo.getDestino().equals(destino) &&
                    voo.getDataHoraVoo().toLocalDate().equals(dataIda.toLocalDate())) {
                idaVolta.add(voo); // Adicionar voo de ida
            }
        }

        // Pesquisar voo de volta
        for (Voo voo : voos) {
            if (voo.getOrigem().equals(destino) && voo.getDestino().equals(origem) &&
                    voo.getDataHoraVoo().toLocalDate().equals(dataVolta.toLocalDate())) {
                idaVolta.add(voo); // Adicionar voo de volta
            }
        }

        return idaVolta;
    }

    // Pesquisa de voos com conexão
    public List<List<Voo>> pesquisarVoosComConexao(Aeroporto origem, Aeroporto destino,
            LocalDateTime dataHoraPesquisa) {
        List<List<Voo>> conexoes = new ArrayList<>();
        LocalDate dataPesquisa = dataHoraPesquisa.toLocalDate(); // Considerar apenas a data

        for (Voo voo1 : voos) {
            if (voo1.getOrigem().equals(origem) && voo1.getDataHoraVoo().toLocalDate().equals(dataPesquisa)) {
                for (Voo voo2 : voos) {
                    if (voo1.getDestino().equals(voo2.getOrigem()) && voo2.getDestino().equals(destino) &&
                            voo2.getDataHoraVoo().toLocalDate().equals(dataPesquisa)) {
                        List<Voo> conexao = new ArrayList<>();
                        conexao.add(voo1);
                        conexao.add(voo2);
                        conexoes.add(conexao);
                    }
                }
            }
        }
        return conexoes;
    }
}
