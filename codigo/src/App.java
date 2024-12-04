import Entidades.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AeroportoManager aeroportoManager = new AeroportoManager();
        CompanhiaAereaManager companhiaAereaManager = new CompanhiaAereaManager();
        PassageiroManager passageiroManager = new PassageiroManager();
        VooManager vooManager = new VooManager();


        iniciarDadosIniciais(aeroportoManager, companhiaAereaManager, passageiroManager, vooManager);

        JFrame frame = new JFrame("Sistema de Gerenciamento de Viagens");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton cadastrarCompanhiaAereaButton = new JButton("1. Cadastrar Companhia Aerea");
        JButton cadastrarVoosButton = new JButton("4. Cadastrar Voo");
        JButton listarVoosButton = new JButton("Listar todos os voos");
        JButton pesquisarVoosButton = new JButton("Pesquisar voos diretos");
        JButton emitirBilheteButton = new JButton("Emitir bilhete");
        JButton calcularBagagemButton = new JButton("Calcular custo de bagagem");
        JButton alterarVipButton = new JButton("Alterar status VIP de passageiro");
        JButton sairButton = new JButton("Sair");

        menuPanel.add(cadastrarCompanhiaAereaButton);
        menuPanel.add(cadastrarVoosButton);
        menuPanel.add(listarVoosButton);
        menuPanel.add(pesquisarVoosButton);
        menuPanel.add(emitirBilheteButton);
        menuPanel.add(calcularBagagemButton);
        menuPanel.add(alterarVipButton);
        menuPanel.add(sairButton);


        cadastrarCompanhiaAereaButton.addActionListener(e -> cadastrarCompanhiaAerea(companhiaAereaManager));
//        cadastrarVoosButton.addActionListener(e -> cadastrarVoo(scanner, vooManager, aeroportoManager, companhiaAereaManager));
        listarVoosButton.addActionListener(e -> listarVoos(vooManager));


        frame.add(menuPanel);

        frame.setVisible(true);


//        while (true) {
//            exibirMenu();
//            int opcao = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    cadastrarAeroporto(scanner, aeroportoManager);
//                    break;
//                case 2:
//                    cadastrarCompanhiaAerea(scanner, companhiaAereaManager);
//                    break;
//                case 3:
//                    cadastrarPassageiro(scanner, passageiroManager);
//                    break;
//                case 4:
//                    cadastrarVoo(scanner, vooManager, aeroportoManager, companhiaAereaManager);
//                    break;
//                case 5:
//                    listarAeroportos(aeroportoManager);
//                    break;
//                case 6:
//                    listarCompanhiasAereas(companhiaAereaManager);
//                    break;
//                case 7:
//                    listarPassageiros(passageiroManager);
//                    break;
//                case 8:
//                    listarVoos(vooManager);
//                    break;
//                case 9:
//                    pesquisarVoos(scanner, vooManager, aeroportoManager);
//                    break;
//                case 10:
//                    emitirBilhete(scanner, vooManager, passageiroManager);
//                    break;
//                case 11:
//                    System.out.println("Saindo do sistema...");
//                    scanner.close();
//                    return;
//                default:
//                    System.out.println("Opção inválida, tente novamente.");
//            }
//        }
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

    private static void cadastrarCompanhiaAerea(CompanhiaAereaManager companhiaAereaManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Companhia Aérea");
        dialog.setSize(400, 400);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel siglaLabel = new JLabel("Sigla:");
        JTextField siglaField = new JTextField();

        JLabel razaoSocialLabel = new JLabel("Razão Social:");
        JTextField razaoSocialField = new JTextField();

        JLabel cnpjLabel = new JLabel("CNPJ:");
        JTextField cnpjField = new JTextField();

        JLabel valorPrimeiraBagagemLabel = new JLabel("Valor Primeira Bagagem:");
        JTextField valorPrimeiraBagagemField = new JTextField();

        JLabel valorBagagensAdicionaisLabel = new JLabel("Valor Bagagens Adicionais:");
        JTextField valorBagagensAdicionaisField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(siglaLabel);
        panel.add(siglaField);
        panel.add(razaoSocialLabel);
        panel.add(razaoSocialField);
        panel.add(cnpjLabel);
        panel.add(cnpjField);
        panel.add(valorPrimeiraBagagemLabel);
        panel.add(valorPrimeiraBagagemField);
        panel.add(valorBagagensAdicionaisLabel);
        panel.add(valorBagagensAdicionaisField);
        panel.add(salvarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        salvarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String sigla = siglaField.getText();
            String razaoSocial = razaoSocialField.getText();
            String cnpj = cnpjField.getText();
            double valorPrimeiraBagagem;
            double valorBagagensAdicionais;

            try {
                valorPrimeiraBagagem = Double.parseDouble(valorPrimeiraBagagemField.getText());
                valorBagagensAdicionais = Double.parseDouble(valorBagagensAdicionaisField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Por favor, insira valores válidos para os preços.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CompanhiaAerea companhiaAerea = new CompanhiaAerea(nome, sigla, razaoSocial, cnpj, valorPrimeiraBagagem, valorBagagensAdicionais);
            if (companhiaAereaManager.adicionarCompanhia(companhiaAerea)) {
                JOptionPane.showMessageDialog(dialog, "Companhia Aérea cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Companhia Aérea com o mesmo CNPJ já cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
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

//    private static void cadastrarVoo(Scanner scanner, VooManager vooManager, AeroportoManager aeroportoManager,
//            CompanhiaAereaManager companhiaAereaManager) {
//        String origemSigla = JOptionPane.showInputDialog("Digite a sigla do aeroporto de origem: ");
//        Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla(origemSigla);
//
//        String destinoSigla = JOptionPane.showInputDialog("Digite a sigla do aeroporto de destino: ");
//        Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla(destinoSigla);
//
//        String codigoVoo = JOptionPane.showInputDialog("Digite o código do voo: ");
//        String dataHora = JOptionPane.showMessageDialog(JSpinner.DateEditor,"Digite a data de hora: ");
//                //("Digite o horário do voo (YYYY-MM-DD HH:MM): ");
//        LocalDateTime dataHoraVoo = parseDateTime(dataHora);
//
//        String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da companhia aérea: ");
//        CompanhiaAerea companhiaAerea = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);
//
//        double tarifaBasica = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da tarifa básica: "));
//        double tarifaBusiness = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da tarifa business: "));
//        double tarifaPremium = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da tarifa premium: "));
//        scanner.nextLine();
//
//        Voo voo = new Voo(origem, destino, dataHoraVoo, codigoVoo, companhiaAerea,
//                new Aeronave("Boeing 737", 20000, 180, 30),
//                tarifaBasica, tarifaBusiness, tarifaPremium, "BRL");
//        vooManager.adicionarVoo(voo);
//
//        JOptionPane.showMessageDialog(null, "Voo cadastrado com sucesso!");
//    }

    private static LocalDateTime parseDateTime(String dataHora) {
        DateTimeFormatter[] dateFormats = new DateTimeFormatter[]{
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

        JOptionPane.showMessageDialog(null, voos.toString(), "Lista de Voos", JOptionPane.INFORMATION_MESSAGE);
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



