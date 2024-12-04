import Entidades.*;
import Managers.AeroportoManager;
import Managers.CompanhiaAereaManager;
import Managers.PassageiroManager;
import Managers.VooManager;
import dao.ILog;
import dao.LogDAOImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final ILog logDAO = new LogDAOImpl(); // Instância do DAO de logs

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AeroportoManager aeroportoManager = new AeroportoManager();
        CompanhiaAereaManager companhiaAereaManager = new CompanhiaAereaManager();
        PassageiroManager passageiroManager = new PassageiroManager();
        VooManager vooManager = new VooManager();

        registrarLog("Sistema iniciado.");
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
                    alterarAeroporto(scanner, aeroportoManager);
                    break;
                case 12:
                    excluirAeroporto(scanner, aeroportoManager);
                    break;
                case 13:
                    alterarCompanhiaAerea(scanner, companhiaAereaManager);
                    break;
                case 14:
                    excluirCompanhiaAerea(scanner, companhiaAereaManager);
                    break;
                case 15:
                    alterarPassageiro(scanner, passageiroManager);
                    break;
                case 16:
                    excluirPassageiro(scanner, passageiroManager);
                    break;
                case 17:
                    alterarVoo(scanner, vooManager, aeroportoManager, companhiaAereaManager);
                    break;
                case 18:
                    excluirVoo(scanner, vooManager);
                    break;
                case 19:
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
        System.out.println("11. Alterar Aeroporto");
        System.out.println("12. Excluir Aeroporto");
        System.out.println("13. Alterar Companhia Aérea");
        System.out.println("14. Excluir Companhia Aérea");
        System.out.println("15. Alterar Passageiro");
        System.out.println("16. Excluir Passageiro");
        System.out.println("17. Alterar Voo");
        System.out.println("18. Excluir Voo");
        System.out.println("19. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void iniciarDadosIniciais(AeroportoManager aeroportoManager,
            CompanhiaAereaManager companhiaAereaManager,
            PassageiroManager passageiroManager, VooManager vooManager) {
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil",
                -23.5505, -46.6333);
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto Internacional do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ",
                "Brasil", -22.9094, -43.1737);

        aeroportoManager.adicionarAeroporto(aeroporto1);
        aeroportoManager.adicionarAeroporto(aeroporto2);

        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        companhiaAereaManager.adicionarCompanhia(companhia);

        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        passageiroManager.adicionarPassageiro(passageiro);

        Voo voo = new Voo(aeroporto1, aeroporto2, LocalDateTime.of(2024, 12, 1, 10, 0), "XYZ123", companhia,
                new Aeronave("Boeing 737", 20000, 180, 30, 850.0),
                500.0, 1000.0, 1500.0, "BRL");
        vooManager.adicionarVoo(voo);

        registrarLog("Dados iniciais carregados com sucesso.");
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
        System.out.print("Digite a latitude do aeroporto: ");
        double latitude = scanner.nextDouble();
        System.out.print("Digite a longitude do aeroporto: ");
        double longitude = scanner.nextDouble();
        scanner.nextLine();

        Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais, latitude, longitude);
        if (aeroportoManager.adicionarAeroporto(aeroporto)) {
            registrarLog(String.format("Aeroporto '%s' (%s) cadastrado.", nome, sigla));
            System.out.println("Aeroporto cadastrado com sucesso!");
        } else {
            registrarLog(String.format("Falha ao cadastrar aeroporto '%s' (%s): Sigla já existente.", nome, sigla));
            System.out.println("Aeroporto com essa sigla já cadastrado.");
        }
    }

    private static void alterarAeroporto(Scanner scanner, AeroportoManager aeroportoManager) {
        System.out.print("Digite a sigla do aeroporto que deseja alterar: ");
        String sigla = scanner.nextLine();

        try {
            Aeroporto aeroporto = aeroportoManager.buscarAeroportoPorSigla(sigla);
            System.out.println("Aeroporto encontrado: " + aeroporto);

            System.out.print("Digite o novo nome do aeroporto: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite a nova cidade: ");
            String novaCidade = scanner.nextLine();
            System.out.print("Digite o novo estado: ");
            String novoEstado = scanner.nextLine();
            System.out.print("Digite o novo país: ");
            String novoPais = scanner.nextLine();

            boolean alterado = aeroportoManager.alterarAeroporto(sigla, novoNome, novaCidade, novoEstado, novoPais);

            if (alterado) {
                registrarLog("Aeroporto [" + sigla + "] alterado para: Nome=[" + novoNome + "], Cidade=[" + novaCidade
                        + "], Estado=[" + novoEstado + "], País=[" + novoPais + "]");
                System.out.println("Aeroporto alterado com sucesso!");
            } else {
                System.out.println("Não foi possível alterar o aeroporto.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar aeroporto: " + e.getMessage());
        }
    }

    private static void excluirAeroporto(Scanner scanner, AeroportoManager aeroportoManager) {
        System.out.print("Digite a sigla do aeroporto que deseja excluir: ");
        String sigla = scanner.nextLine();

        try {
            boolean removido = aeroportoManager.removerAeroporto(sigla);

            if (removido) {
                registrarLog("Aeroporto [" + sigla + "] foi removido do sistema.");
                System.out.println("Aeroporto removido com sucesso!");
            } else {
                System.out.println("Aeroporto não encontrado ou não pôde ser removido.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir aeroporto: " + e.getMessage());
        }
    }

    private static void registrarLog(String mensagem) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMensagem = String.format("[%s] %s", timestamp, mensagem);
        logDAO.salvarLog(logMensagem);
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
            registrarLog(String.format("Companhia aérea '%s' (CNPJ: %s) cadastrada com sucesso.", nome, cnpj));
            System.out.println("Companhia Aérea cadastrada com sucesso!");
        } else {
            registrarLog(String.format("Falha ao cadastrar companhia aérea '%s' (CNPJ: %s): CNPJ já existente.", nome,
                    cnpj));
            System.out.println("Companhia Aérea com o mesmo CNPJ já cadastrada.");
        }
    }

    private static void alterarCompanhiaAerea(Scanner scanner, CompanhiaAereaManager companhiaAereaManager) {
        System.out.print("Digite o CNPJ da companhia aérea a ser alterada: ");
        String cnpj = scanner.nextLine();

        try {
            CompanhiaAerea antigaCompanhia = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);

            System.out.print("Digite o novo nome da companhia aérea: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite a nova razão social: ");
            String novaRazaoSocial = scanner.nextLine();
            System.out.print("Digite a nova sigla: ");
            String novaSigla = scanner.nextLine();
            System.out.print("Digite o novo valor da primeira bagagem: ");
            double novoValorPrimeiraBagagem = scanner.nextDouble();
            System.out.print("Digite o novo valor das bagagens adicionais: ");
            double novoValorBagagensAdicionais = scanner.nextDouble();
            scanner.nextLine();

            boolean alterado = companhiaAereaManager.alterarCompanhia(cnpj, novoNome, novaRazaoSocial, novaSigla,
                    novoValorPrimeiraBagagem, novoValorBagagensAdicionais);

            if (alterado) {
                registrarLog(String.format(
                        "Companhia aérea alterada: Antes -> %s; Depois -> [Nome: %s, Razão Social: %s, Sigla: %s, Valor Primeira Bagagem: %.2f, Valor Bagagens Adicionais: %.2f]",
                        antigaCompanhia.toString(), novoNome, novaRazaoSocial, novaSigla, novoValorPrimeiraBagagem,
                        novoValorBagagensAdicionais));
                System.out.println("Companhia aérea alterada com sucesso.");
            } else {
                System.out.println("Falha ao alterar a companhia aérea.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirCompanhiaAerea(Scanner scanner, CompanhiaAereaManager companhiaAereaManager) {
        System.out.print("Digite o CNPJ da companhia aérea a ser excluída: ");
        String cnpj = scanner.nextLine();

        try {
            CompanhiaAerea companhiaAExcluir = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);

            boolean excluido = companhiaAereaManager.excluirCompanhia(cnpj);

            if (excluido) {
                registrarLog(String.format("Companhia aérea excluída: %s", companhiaAExcluir.toString()));
                System.out.println("Companhia aérea excluída com sucesso.");
            } else {
                System.out.println("Falha ao excluir a companhia aérea.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            registrarLog(String.format("Passageiro cadastrado: Nome: %s, Sobrenome: %s, Documento: %s, Email: %s",
                    nome, sobrenome, documento, email));
            System.out.println("Passageiro cadastrado com sucesso!");
        } else {
            registrarLog(String.format("Falha ao cadastrar passageiro: Documento já existente (%s)", documento));
            System.out.println("Passageiro já cadastrado.");
        }
    }

    private static void alterarPassageiro(Scanner scanner, PassageiroManager passageiroManager) {
        System.out.print("Digite o documento do passageiro que deseja alterar: ");
        String documento = scanner.nextLine();

        try {
            Passageiro passageiro = passageiroManager.buscarPassageiroPorDocumento(documento);
            System.out.println("Passageiro encontrado: " + passageiro);

            System.out.print("Digite o novo nome do passageiro: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo sobrenome: ");
            String novoSobrenome = scanner.nextLine();
            System.out.print("Digite o novo email do passageiro: ");
            String novoEmail = scanner.nextLine();

            boolean alterado = passageiroManager.alterarPassageiro(documento, novoNome, novoSobrenome, novoEmail);

            if (alterado) {
                registrarLog(String.format(
                        "Passageiro alterado: Antes -> %s; Depois -> Nome: %s, Sobrenome: %s, Email: %s",
                        passageiro.toString(), novoNome, novoSobrenome, novoEmail));
                System.out.println("Passageiro alterado com sucesso!");
            } else {
                System.out.println("Falha ao alterar o passageiro.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao alterar passageiro: " + e.getMessage());
        }
    }

    private static void excluirPassageiro(Scanner scanner, PassageiroManager passageiroManager) {
        System.out.print("Digite o documento do passageiro que deseja excluir: ");
        String documento = scanner.nextLine();

        try {
            Passageiro passageiro = passageiroManager.buscarPassageiroPorDocumento(documento);
            boolean removido = passageiroManager.excluirPassageiro(documento);

            if (removido) {
                registrarLog(String.format("Passageiro excluído: %s", passageiro.toString()));
                System.out.println("Passageiro excluído com sucesso!");
            } else {
                System.out.println("Falha ao excluir o passageiro.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir passageiro: " + e.getMessage());
        }
    }

    private static void cadastrarVoo(Scanner scanner, VooManager vooManager, AeroportoManager aeroportoManager,
            CompanhiaAereaManager companhiaAereaManager) {
        try {
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
                    new Aeronave("Boeing 737", 20000, 180, 30, 850.0),
                    tarifaBasica, tarifaBusiness, tarifaPremium, "BRL");
            vooManager.adicionarVoo(voo);
            registrarLog(String.format("Voo cadastrado: %s", voo.toString()));
            System.out.println("Voo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar voo: " + e.getMessage());
        }
    }

    private static void alterarVoo(Scanner scanner, VooManager vooManager, AeroportoManager aeroportoManager,
            CompanhiaAereaManager companhiaAereaManager) {
        System.out.print("Digite o código do voo a ser alterado: ");
        String codigoVoo = scanner.nextLine();

        try {
            Voo voo = vooManager.buscarVooPorCodigo(codigoVoo);
            System.out.println("Voo encontrado: " + voo);

            System.out.print("Digite a nova sigla do aeroporto de origem: ");
            String novaSiglaOrigem = scanner.nextLine();
            Aeroporto novaOrigem = aeroportoManager.buscarAeroportoPorSigla(novaSiglaOrigem);

            System.out.print("Digite a nova sigla do aeroporto de destino: ");
            String novaSiglaDestino = scanner.nextLine();
            Aeroporto novoDestino = aeroportoManager.buscarAeroportoPorSigla(novaSiglaDestino);

            System.out.print("Digite o novo horário do voo (yyyy-MM-dd HH:mm): ");
            String novoHorario = scanner.nextLine();
            LocalDateTime novaDataHora = parseDateTime(novoHorario);

            System.out.print("Digite o novo valor da tarifa básica: ");
            double novaTarifaBasica = scanner.nextDouble();
            System.out.print("Digite o novo valor da tarifa business: ");
            double novaTarifaBusiness = scanner.nextDouble();
            System.out.print("Digite o novo valor da tarifa premium: ");
            double novaTarifaPremium = scanner.nextDouble();
            scanner.nextLine();

            boolean alterado = vooManager.alterarVoo(codigoVoo, novaDataHora, novaOrigem, novoDestino, novaTarifaBasica,
                    novaTarifaBusiness, novaTarifaPremium);

            if (alterado) {
                registrarLog(String.format(
                        "Voo alterado: Antes -> %s; Depois -> Origem: %s, Destino: %s, Horário: %s, Tarifa Básica: %.2f, Tarifa Business: %.2f, Tarifa Premium: %.2f",
                        voo.toString(),
                        novaOrigem.getSigla(),
                        novoDestino.getSigla(),
                        novaDataHora,
                        novaTarifaBasica,
                        novaTarifaBusiness,
                        novaTarifaPremium));
                System.out.println("Voo alterado com sucesso.");
            } else {
                System.out.println("Falha ao alterar o voo.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void excluirVoo(Scanner scanner, VooManager vooManager) {
        System.out.print("Digite o código do voo que deseja excluir: ");
        String codigoVoo = scanner.nextLine();

        try {
            Voo vooAExcluir = vooManager.buscarVooPorCodigo(codigoVoo);

            boolean excluido = vooManager.excluirVoo(codigoVoo);

            if (excluido) {
                registrarLog(String.format("Voo excluído: %s", vooAExcluir.toString()));
                System.out.println("Voo excluído com sucesso.");
            } else {
                System.out.println("Falha ao excluir o voo.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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
