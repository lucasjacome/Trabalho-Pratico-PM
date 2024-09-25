import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class VooManagerTest {

    private VooManager vooManager;

    @BeforeEach
    public void setup() {
        vooManager = new VooManager();

        // Voo de ida de São Paulo para Brasília
        Voo vooIda = new Voo(
                new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil"),
                LocalDateTime.of(2024, 10, 1, 15, 30),
                "XY1234",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA", "12345678000195", 50.0,
                        30.0),
                500.0,
                1000.0,
                1500.0,
                "BRL");

        // Voo de volta de Brasília para São Paulo
        Voo vooVolta = new Voo(
                new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil"),
                new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                LocalDateTime.of(2024, 10, 10, 18, 00),
                "XY9012",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA", "12345678000195", 50.0,
                        30.0),
                550.0,
                1050.0,
                1550.0,
                "BRL");

        // Adicionando os voos ao gerenciador
        vooManager.adicionarVoo(vooIda);
        vooManager.adicionarVoo(vooVolta); // Certifique-se de adicionar o voo de volta
    }

    @Test
    public void testListarTodosOsVoos() {
        List<Voo> voos = vooManager.listarTodosOsVoos();
        assertEquals(2, voos.size(), "Deve listar 2 voos (ida e volta)");
    }

    @Test
    public void testPesquisarVoos() {
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil");
        LocalDateTime dataPesquisa = LocalDateTime.of(2024, 10, 1, 0, 0); // Usando apenas a data, ignorando a hora

        List<Voo> voos = vooManager.pesquisarVoos(origem, destino, dataPesquisa);
        assertEquals(1, voos.size(), "Deve haver 1 voo de ida de São Paulo para Brasília na data específica");
    }

    @Test
    public void testPesquisarVoosComConexao() {
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil");
        LocalDateTime dataPesquisa = LocalDateTime.of(2024, 10, 1, 0, 0); // Usando apenas a data, ignorando a hora

        List<List<Voo>> conexoes = vooManager.pesquisarVoosComConexao(origem, destino, dataPesquisa);
        assertEquals(0, conexoes.size(), "Não deve haver conexões entre São Paulo e Brasília, apenas voos diretos");
    }

    @Test
    public void testPesquisarVoosIdaVolta() {
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil");
        LocalDateTime dataIda = LocalDateTime.of(2024, 10, 1, 0, 0); // Usando apenas a data, ignorando a hora
        LocalDateTime dataVolta = LocalDateTime.of(2024, 10, 10, 0, 0); // Usando apenas a data, ignorando a hora

        List<Voo> voosIdaVolta = vooManager.pesquisarVoosIdaVolta(origem, destino, dataIda, dataVolta);
        assertEquals(2, voosIdaVolta.size(),
                "Deve haver 2 voos (1 de ida e 1 de volta) entre São Paulo e Brasília nas datas específicas");
    }
}
