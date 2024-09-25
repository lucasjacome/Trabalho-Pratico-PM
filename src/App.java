import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Criação de aeroportos
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        Aeroporto destinoConexao = new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil");
        Aeroporto destinoInternacional = new Aeroporto("Aeroporto de Nova York", "JFK", "Nova York", "NY", "EUA");

        // Criação de companhia aérea
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA",
                "12345678000195", 50.0, 30.0);

        // Criação do gerenciador de voos
        VooManager vooManager = new VooManager();

        // Criação dos voos
        Voo voo1 = new Voo(
                origem,
                destino,
                LocalDateTime.of(2024, 10, 1, 15, 30),
                "XY1234",
                companhia,
                500.0,
                1000.0,
                1500.0,
                "BRL");

        Voo voo2 = new Voo(
                destino,
                destinoConexao,
                LocalDateTime.of(2024, 10, 1, 18, 00),
                "XY5678",
                companhia,
                600.0,
                1100.0,
                1600.0,
                "BRL");

        Voo voo3 = new Voo(
                destinoConexao,
                origem,
                LocalDateTime.of(2024, 10, 10, 18, 00),
                "XY9012",
                companhia,
                550.0,
                1050.0,
                1550.0,
                "BRL");

        // Voo Internacional
        Voo vooInternacional = new Voo(
                origem,
                destinoInternacional,
                LocalDateTime.of(2024, 12, 1, 15, 00),
                "XY9999",
                companhia,
                1200.0,
                2500.0,
                3500.0,
                "USD");

        // Adicionar os voos ao gerenciador
        vooManager.adicionarVoo(voo1);
        vooManager.adicionarVoo(voo2);
        vooManager.adicionarVoo(voo3);
        vooManager.adicionarVoo(vooInternacional);

        // Listar todos os voos cadastrados
        System.out.println("Todos os voos disponíveis:");
        for (Voo voo : vooManager.listarTodosOsVoos()) {
            System.out.println(voo);
            // Verificar se o voo é internacional
            if (voo.isInternacional()) {
                System.out.println("Este voo é internacional.");
            } else {
                System.out.println("Este voo é nacional.");
            }
        }

        // Pesquisar voos diretos
        LocalDateTime dataPesquisa = LocalDateTime.of(2024, 10, 1, 0, 0);
        System.out.println("\nPesquisando voos de " + origem.getNome() + " para " + destino.getNome());
        for (Voo voo : vooManager.pesquisarVoos(origem, destino, dataPesquisa)) {
            System.out.println(voo);
        }

        // Pesquisar voos de ida e volta
        LocalDateTime dataVolta = LocalDateTime.of(2024, 10, 10, 0, 0);
        System.out.println("\nPesquisando voos de ida e volta:");
        for (Voo voo : vooManager.pesquisarVoosIdaVolta(origem, destino, dataPesquisa, dataVolta)) {
            System.out.println(voo);
        }

        // Pesquisar voos com conexão
        System.out.println("\nPesquisando voos com conexão:");
        for (List<Voo> conexao : vooManager.pesquisarVoosComConexao(origem, destinoConexao, dataPesquisa)) {
            System.out.println("Conexão:");
            for (Voo voo : conexao) {
                System.out.println(voo);
            }
        }

        // Emissão de bilhetes

        // Passageiro com documento válido para voo nacional (CPF)
        Passageiro passageiroNacional = new Passageiro("João", "Silva", "12345678901");

        // Passageiro com passaporte válido (duas letras e seis dígitos)
        Passageiro passageiroComPassaporteValido = new Passageiro("Maria", "Pereira", "AA123456");

        // Passageiro com passaporte inválido (não segue o padrão de duas letras e seis
        // dígitos)
        Passageiro passageiroComPassaporteInvalido = new Passageiro("Carlos", "Souza", "A1234567");

        // Tentativa de emitir bilhete para voo nacional
        System.out.println("\nEmitindo bilhete para voo nacional:");
        Bilhete bilheteNacional = new Bilhete(passageiroNacional, voo1);
        bilheteNacional.emitir(); // Deve emitir com sucesso (documento CPF)

        // Tentativa de emitir bilhete para voo internacional com passaporte válido
        System.out.println("\nEmitindo bilhete para voo internacional com passaporte válido:");
        Bilhete bilheteValido = new Bilhete(passageiroComPassaporteValido, vooInternacional);
        bilheteValido.emitir(); // Deve emitir com sucesso (passaporte válido)

        // Tentativa de emitir bilhete para voo internacional com passaporte inválido
        System.out.println("\nTentativa de emitir bilhete com passaporte inválido:");
        Bilhete bilheteInvalido = new Bilhete(passageiroComPassaporteInvalido, vooInternacional);
        bilheteInvalido.emitir(); // Deve falhar (passaporte inválido)
    }
}
