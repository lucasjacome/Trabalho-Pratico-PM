import java.time.LocalDateTime;
import java.util.List;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Criação de aeroportos
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        Aeroporto destinoConexao = new Aeroporto("Aeroporto de Brasília", "BSB", "Brasília", "DF", "Brasil");
        Aeroporto destinoInternacional = new Aeroporto("Aeroporto de Nova York", "JFK", "Nova York", "NY", "EUA");
        Aeronave aeronave = new Aeronave("Boeing 737", 150, 200, 33);
        Aeronave aeronave2 = new Aeronave("Boeing 747", 300, 400, 66);

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
                aeronave,
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
                aeronave,
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
                aeronave,
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
                aeronave,
                1200.0,
                2500.0,
                3500.0,
                "USD");

        //vooTeste para testar o status "NO SHOW"
        Voo vooTeste = new Voo(
                origem,
                destino,
                LocalDateTime.of(2024, 11, 17, 10, 0),
                "XY1234",
                companhia,
                aeronave,
                500.0,
                1000.0,
                1500.0,
                "BRL");
                vooTeste.adicionarPassageiro(new Passageiro("Ana", "Silva", "12345678901", "ana@gmail.com"));
                vooTeste.adicionarPassageiro(new Passageiro("Carlos", "Santos", "98765432100", "carlos@gmail.com"));
                vooTeste.registrarEmbarque("12345678901");
                vooTeste.verificarNoShow();


        // Definindo a frequência para o voo AD4114 (diariamente às 10:30)
        List<DayOfWeek> diasDaSemana = Arrays.asList(
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

        Frequencia frequenciaVoo = new Frequencia(voo1, diasDaSemana);


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

        // Gerar e exibir os horários do voo com base na frequência
        List<LocalDateTime> horarios = frequenciaVoo.gerarHorarios();
        System.out.println("\n Horários para o voo " + voo1.getCodigoVoo() + ":");
        for (LocalDateTime horario : horarios) {
            System.out.println(horario + " -- GRU -> GIG -- Duração: 1:30");
        }

        // Instanciando o programador de viagens e programando os voos
        ProgramarViagens programador = new ProgramarViagens();
        List<Voo> voosProgramados = programador.programarVoosPorPeriodo(voo1, frequenciaVoo);


        // Exibindo os voos programados
        System.out.println("\nVoos programados para os próximos 30 dias:");
        for (Voo voo : voosProgramados) {
            System.out.println("Código do voo: " + voo.getCodigoVoo());
            System.out.println("Origem: " + voo.getOrigem().getNome());
            System.out.println("Destino: " + voo.getDestino().getNome());
            System.out.println("Data e Hora: " + voo.getDataHoraVoo());
            System.out.println("Aeronave: " + voo.getAeronave().getModelo());
            System.out.println("Tarifa: " + voo.getTarifaBasica() + " " + voo.getMoeda());
            System.out.println("----");
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
        Passageiro passageiroNacionalVip = new Passageiro("João", "Silva", "12345678901", "joao.silva@gmail.com");
        Passageiro passageiroNacionalRegular = new Passageiro("João", "Pobre", "12345936901", "joao.pobre@gmail.com");

        passageiroNacionalVip.setVipStatus(true);

        // Passageiro com passaporte válido (duas letras e seis dígitos)
        Passageiro passageiroComPassaporteValido = new Passageiro("Maria", "Pereira", "AA123456", "mariapereira@outlook.com");

        // Passageiro com passaporte inválido (não segue o padrão de duas letras e seis dígitos)
        Passageiro passageiroComPassaporteInvalido = new Passageiro("Carlos", "Souza", "A1234567", "souzacarlos@icloud.com");

        // Tentativa de emitir bilhete para voo nacional
        System.out.println("\nEmitindo bilhete para voo nacional:");
        Bilhete bilheteNacional = new Bilhete(passageiroNacionalVip, voo1);
        bilheteNacional.emitir(); // Deve emitir com sucesso (documento CPF)

        System.out.println("\nEmitindo bilhete para voo nacional:");
        Bilhete bilheteNacional2 = new Bilhete(passageiroNacionalRegular, voo1);
        bilheteNacional2.emitir(); // Deve emitir com sucesso (documento CPF)


        // Testar cancelamento/alteração
        System.out.println("\nAlteração/Cancelamento de voo:");
        System.out.print("Pedido de cancelamento de Voo para Passageiro Regular -- ");
        passageiroNacionalRegular.cancelarVoo(null);
        System.out.print("Pedido de cancelamento de Voo para Passageiro Vip -- ");
        passageiroNacionalVip.cancelarVoo(null);

        // Testar custo de bagagem
        System.out.println("\nCusto de bagagem:");
        System.out.println("Regular, 2 bagagens: " + passageiroNacionalRegular.calcularValorBagagem(companhia, 2) + " BRL");
        System.out.println("VIP, 2 bagagens: " + passageiroNacionalVip.calcularValorBagagem(companhia, 2) + " BRL");
        System.out.println("VIP, 1 bagagem: " + passageiroNacionalVip.calcularValorBagagem(companhia, 1) + " BRL");

        // Desativar benefícios VIP
        companhia.setVipBeneficioAtivo(false);
        System.out.println("\nBenefícios VIP desativados:");
        System.out.println("VIP, 2 bagagens: " + passageiroNacionalVip.calcularValorBagagem(companhia, 2) + " BRL");


        // Tentativa de emitir bilhete para voo internacional com passaporte válido
        System.out.println("\nEmitindo bilhete para voo internacional com passaporte válido:");
        Bilhete bilheteValido = new Bilhete(passageiroComPassaporteValido, vooInternacional);
        bilheteValido.emitir(); // Deve emitir com sucesso (passaporte válido)

        // Tentativa de emitir bilhete para voo internacional com passaporte inválido
        System.out.println("\nTentativa de emitir bilhete com passaporte inválido:");
        Bilhete bilheteInvalido = new Bilhete(passageiroComPassaporteInvalido, vooInternacional);
        bilheteInvalido.emitir(); // Deve falhar (passaporte inválido)



        // Menu
        while (true) {
            System.out.println("\n--- Sistema de Gerenciamento de Viagens ---");
            System.out.println("1. Listar todos os voos");
            System.out.println("2. Pesquisar voos diretos");
            System.out.println("3. Emitir bilhete");
            System.out.println("4. Calcular custo de bagagem");
            System.out.println("5. Alterar status VIP de passageiro");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    System.out.println("\nTodos os voos disponíveis:");
                    for (Voo voo : vooManager.listarTodosOsVoos()) {
                        System.out.println(voo);
                    }
                    break;

                case 2:
                    System.out.print("\nDigite a data para a pesquisa (dd/MM/yyyy HH:mm): ");
                    String dataStr = scanner.nextLine();
                    LocalDateTime dateTime = LocalDateTime.parse(dataStr, formatter);

                    System.out.println("\nPesquisando voos de " + origem.getNome() + " para " + destino.getNome());
                    List<Voo> voosDiretos = vooManager.pesquisarVoos(origem, destino, dataPesquisa);
                    if (voosDiretos.isEmpty()) {
                        System.out.println("Nenhum voo encontrado.");
                    } else {
                        for (Voo voo : voosDiretos) {
                            System.out.println(voo);
                        }
                    }
                    break;

                case 3:
                    System.out.print("\nDigite o nome do passageiro: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o documento (CPF ou passaporte): ");
                    String documento = scanner.nextLine();

                    Passageiro passageiro = new Passageiro(nome, "Sobrenome", documento, nome.toLowerCase() + "@gmail.com");

                    System.out.println("\nSelecione um voo para emitir bilhete:");
                    List<Voo> todosVoos = vooManager.listarTodosOsVoos();
                    for (int i = 0; i < todosVoos.size(); i++) {
                        System.out.println((i + 1) + ". " + todosVoos.get(i));
                    }
                    System.out.print("Escolha o número do voo: ");
                    int vooEscolhido = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha

                    Voo vooSelecionado = todosVoos.get(vooEscolhido - 1);
                    Bilhete bilhete = new Bilhete(passageiro, vooSelecionado);
                    bilhete.emitir();
                    break;

                case 4:
                    System.out.print("\nDigite o número de bagagens: ");
                    int qtdBagagens = scanner.nextInt();
                    scanner.nextLine(); // Consumir quebra de linha
                    System.out.println("Custo total: " + companhia.getValorPrimeiraBagagem() * qtdBagagens + " BRL");
                    break;

                case 5:
                    System.out.print("\nDigite o nome do passageiro: ");
                    String nomeVip = scanner.nextLine();
                    System.out.print("Deseja ativar o status VIP? (true/false): ");
                    boolean ativarVip = scanner.nextBoolean();
                    scanner.nextLine(); // Consumir quebra de linha

                    Passageiro passageiroVip = new Passageiro(nomeVip, "Sobrenome", "12345678901", nomeVip.toLowerCase() + "@gmail.com");
                    passageiroVip.setVipStatus(ativarVip);
                    System.out.println("Status VIP atualizado: " + (passageiroVip.isVip() ? "VIP" : "Regular"));
                    break;

                case 6:
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }


    }
}
