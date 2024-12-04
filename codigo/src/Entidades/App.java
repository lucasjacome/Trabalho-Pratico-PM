package Entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AeroportoManager aeroportoManager = new AeroportoManager();
        CompanhiaAereaManager companhiaAereaManager = new CompanhiaAereaManager();
        PassageiroManager passageiroManager = new PassageiroManager();
        VooManager vooManager = new VooManager();


        iniciarDadosIniciais(aeroportoManager, companhiaAereaManager, passageiroManager, vooManager);

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAeroporto(scanner, aeroportoManager);
                    break;
                case 2:
                    cadastrarCompanhiaAerea(scanner, companhiaAereaManager);
                    break;
                case 3:
                    cadastrarPassageiro(scanner, passageiroManager);
                    break;
                case 4:
                    cadastrarVoo(scanner, vooManager, aeroportoManager, companhiaAereaManager);
                    break;
                case 5:
                    listarAeroportos(aeroportoManager);
                    break;
                case 6:
                    listarCompanhiasAereas(companhiaAereaManager);
                    break;
                case 7:
                    listarPassageiros(passageiroManager);
                    break;
                case 8:
                    listarVoos(vooManager);
                    break;
                case 9:
                    pesquisarVoos(scanner, vooManager, aeroportoManager);
                    break;
                case 10:
                    emitirBilhete(scanner, vooManager, passageiroManager);
                    break;
                case 11:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Gestão de Voos ---");
        System.out.println("1. Cadastrar Aeroporto");
        System.out.println("2. Cadastrar Companhia Aérea");
        System.out.println("3. Cadastrar Passageiro");
        System.out.println("4. Cadastrar Voo");
        System.out.println("5. Listar Aeroportos");
        System.out.println("6. Listar Companhias Aéreas");
        System.out.println("7. Listar Passageiros");
        System.out.println("8. Listar Voos");
        System.out.println("9. Pesquisar Voos");
        System.out.println("10. Emitir Bilhete");
        System.out.println("11. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void iniciarDadosIniciais(AeroportoManager aeroportoManager,
                                             CompanhiaAereaManager companhiaAereaManager,
                                             PassageiroManager passageiroManager, VooManager vooManager) {
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP",
                "Brasil");
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto Internacional do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ",
                "Brasil");

        aeroportoManager.adicionarAeroporto(aeroporto1);
        aeroportoManager.adicionarAeroporto(aeroporto2);

        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        companhiaAereaManager.adicionarCompanhia(companhia);

        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        passageiroManager.adicionarPassageiro(passageiro);

        Voo voo = new Voo(aeroporto1, aeroporto2, LocalDateTime.of(2024, 12, 1, 10, 0), "XYZ123", companhia,
                new Aeronave("Boeing 737", 20000, 180, 30),
                500.0, 1000.0, 1500.0, "BRL");
        vooManager.adicionarVoo(voo);
    }

    private static void cadastrarAeroporto(Scanner scanner, AeroportoManager aeroportoManager) {
        System.out.print("Digite o nome do aeroporto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a sigla do aeroporto: ");
        String sigla = scanner.nextLine();
        System.out.print("Digite a cidade do aeroporto: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o estado do aeroporto: ");
        String estado = scanner.nextLine();
        System.out.print("Digite o país do aeroporto: ");
        String pais = scanner.nextLine();

        Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais);
        if (aeroportoManager.adicionarAeroporto(aeroporto)) {
            System.out.println("Aeroporto cadastrado com sucesso!");
        } else {
            System.out.println("Aeroporto com essa sigla já cadastrado.");
        }
    }

    private static void cadastrarCompanhiaAerea(Scanner scanner, CompanhiaAereaManager companhiaAereaManager) {
        System.out.print("Digite o nome da companhia aérea: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a sigla da companhia aérea: ");
        String sigla = scanner.nextLine();
        System.out.print("Digite a razão social da companhia aérea: ");
        String razaoSocial = scanner.nextLine();
        System.out.print("Digite o CNPJ da companhia aérea: ");
        String cnpj = scanner.nextLine();
        System.out.print("Digite o valor da primeira bagagem: ");
        double valorPrimeiraBagagem = scanner.nextDouble();
        System.out.print("Digite o valor das bagagens adicionais: ");
        double valorBagagensAdicionais = scanner.nextDouble();
        scanner.nextLine();

        CompanhiaAerea companhiaAerea = new CompanhiaAerea(nome, sigla, razaoSocial, cnpj, valorPrimeiraBagagem,
                valorBagagensAdicionais);
        if (companhiaAereaManager.adicionarCompanhia(companhiaAerea)) {
            System.out.println("Companhia Aérea cadastrada com sucesso!");
        } else {
            System.out.println("Companhia Aérea com o mesmo CNPJ já cadastrada.");
        }
    }

    private static void cadastrarPassageiro(Scanner scanner, PassageiroManager passageiroManager) {
        System.out.print("Digite o nome do passageiro: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o sobrenome do passageiro: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Digite o número do documento (CPF ou passaporte): ");
        String documento = scanner.nextLine();
        System.out.print("Digite o e-mail do passageiro: ");
        String email = scanner.nextLine();

        Passageiro passageiro = new Passageiro(nome, sobrenome, documento, email);
        if (passageiroManager.adicionarPassageiro(passageiro)) {
            System.out.println("Passageiro cadastrado com sucesso!");
        } else {
            System.out.println("Passageiro já cadastrado.");
        }
    }

    private static void cadastrarVoo(Scanner scanner, VooManager vooManager, AeroportoManager aeroportoManager,
                                     CompanhiaAereaManager companhiaAereaManager) {
        System.out.print("Digite a sigla do aeroporto de origem: ");
        String origemSigla = scanner.nextLine();
        Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla(origemSigla);

        System.out.print("Digite a sigla do aeroporto de destino: ");
        String destinoSigla = scanner.nextLine();
        Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla(destinoSigla);

        System.out.print("Digite o código do voo: ");
        String codigoVoo = scanner.nextLine();
        System.out.print("Digite o horário do voo (YYYY-MM-DD HH:MM): ");
        String dataHora = scanner.nextLine();
        LocalDateTime dataHoraVoo = parseDateTime(dataHora);

        System.out.print("Digite o CNPJ da companhia aérea: ");
        String cnpj = scanner.nextLine();
        CompanhiaAerea companhiaAerea = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);

        System.out.print("Digite o valor da tarifa básica: ");
        double tarifaBasica = scanner.nextDouble();
        System.out.print("Digite o valor da tarifa business: ");
        double tarifaBusiness = scanner.nextDouble();
        System.out.print("Digite o valor da tarifa premium: ");
        double tarifaPremium = scanner.nextDouble();
        scanner.nextLine();

        Voo voo = new Voo(origem, destino, dataHoraVoo, codigoVoo, companhiaAerea,
                new Aeronave("Boeing 737", 20000, 180, 30),
                tarifaBasica, tarifaBusiness, tarifaPremium, "BRL");
        vooManager.adicionarVoo(voo);
        System.out.println("Voo cadastrado com sucesso!");
    }

    private static LocalDateTime parseDateTime(String dataHora) {
        DateTimeFormatter[] dateFormats = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")
        };

        for (DateTimeFormatter format : dateFormats) {
            try {
                return LocalDateTime.parse(dataHora, format);
            } catch (DateTimeParseException e) {
            }
        }
        throw new IllegalArgumentException(
                "Formato de data inválido. Use os formatos: yyyy-MM-dd HH:mm, dd/MM/yyyy HH:mm, MM/dd/yyyy HH:mm.");
    }

    private static void listarAeroportos(AeroportoManager aeroportoManager) {
        List<Aeroporto> aeroportos = aeroportoManager.listarAeroportos();
        if (aeroportos.isEmpty()) {
            System.out.println("Nenhum aeroporto cadastrado.");
        } else {
            aeroportos.forEach(aeroporto -> System.out.println(aeroporto));
        }
    }

    private static void listarCompanhiasAereas(CompanhiaAereaManager companhiaAereaManager) {
        List<CompanhiaAerea> companhiasAereas = companhiaAereaManager.listarCompanhias();
        if (companhiasAereas.isEmpty()) {
            System.out.println("Nenhuma companhia aérea cadastrada.");
        } else {
            companhiasAereas.forEach(companhia -> System.out.println(companhia));
        }
    }

    private static void listarPassageiros(PassageiroManager passageiroManager) {
        List<Passageiro> passageiros = passageiroManager.listarPassageiros();
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado.");
        } else {
            passageiros.forEach(passageiro -> System.out.println(passageiro));
        }
    }

    private static void listarVoos(VooManager vooManager) {
        List<Voo> voos = vooManager.listarTodosOsVoos();
        if (voos.isEmpty()) {
            System.out.println("Nenhum voo cadastrado.");
        } else {
            voos.forEach(voo -> System.out.println(voo));
        }
    }

    private static void pesquisarVoos(Scanner scanner, VooManager vooManager, AeroportoManager aeroportoManager) {
        System.out.print("Digite a sigla do aeroporto de origem: ");
        String origemSigla = scanner.nextLine();
        Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla(origemSigla);

        System.out.print("Digite a sigla do aeroporto de destino: ");
        String destinoSigla = scanner.nextLine();
        Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla(destinoSigla);

        System.out.print("Digite a data e hora para pesquisa (yyyy-MM-dd HH:mm): ");
        String dataHora = scanner.nextLine();
        LocalDateTime dataHoraPesquisa = parseDateTime(dataHora);

        List<Voo> voos = vooManager.pesquisarVoos(origem, destino, dataHoraPesquisa);
        if (voos.isEmpty()) {
            System.out.println("Nenhum voo encontrado.");
        } else {
            voos.forEach(voo -> System.out.println(voo.getCodigoVoo()));
        }
    }

    private static void emitirBilhete(Scanner scanner, VooManager vooManager, PassageiroManager passageiroManager) {
        System.out.print("Digite o número do voo para emitir bilhete: ");
        String codigoVoo = scanner.nextLine();
        Voo voo = vooManager.listarTodosOsVoos().stream()
                .filter(v -> v.getCodigoVoo().equals(codigoVoo))
                .findFirst()
                .orElse(null);

        if (voo == null) {
            System.out.println("Voo não encontrado.");
            return;
        }

        System.out.print("Digite o documento do passageiro: ");
        String documento = scanner.nextLine();
        Passageiro passageiro = passageiroManager.listarPassageiros().stream()
                .filter(p -> p.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);

        if (passageiro == null) {
            System.out.println("Passageiro não encontrado.");
            return;
        }

        Bilhete bilhete = new Bilhete(passageiro, voo);
        if (bilhete.emitir()) {
            System.out.println("Bilhete emitido com sucesso!");
        } else {
            System.out.println("Erro ao emitir bilhete.");
        }
    }
}