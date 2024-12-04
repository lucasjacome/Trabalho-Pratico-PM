import Entidades.*;
import Managers.AeroportoManager;
import Managers.CompanhiaAereaManager;
import Managers.PassageiroManager;
import Managers.VooManager;
import dao.ILog;
import dao.LogDAOImpl;

import javax.swing.*;
import java.awt.*;
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

        JFrame frame = new JFrame("✈\uFE0F \uD83D\uDEEB Sistema de Gestão de Voos ✈\uFE0F \uD83D\uDEEC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("✈\uFE0F \uD83D\uDEEB Sistema de Gestão de Voos ✈\uFE0F \uD83D\uDEEC");
        menuPanel.add(titleLabel);

        JButton cadastrarAeroportoButton = new JButton("1. Cadastrar Aeroporto");
        JButton cadastrarCompanhiaAereaButton = new JButton("2. Cadastrar Companhia Aérea");
        JButton cadastrarPassageiroButton = new JButton("3. Cadastrar Passageiro");
        JButton cadastrarVoosButton = new JButton("4. Cadastrar Voo");
        JButton listarAeroportosButton = new JButton("5. Listar todos os Aeroportos");
        JButton listarCompanhiaAereaButton = new JButton("6. Listar todas as Companhias Aéreas");
        JButton listarPassageiroButton = new JButton("7. Listar todos os Passageiros");
        JButton listarVoosButton = new JButton("8. Listar todos os voos");
        JButton pesquisarVoosButton = new JButton("9. Pesquisar voos diretos");
        JButton emitirBilheteButton = new JButton("10. Emitir bilhete");
        JButton sairButton = new JButton("Sair ✖\uFE0F");

        menuPanel.add(cadastrarAeroportoButton);
        menuPanel.add(cadastrarCompanhiaAereaButton);
        menuPanel.add(cadastrarPassageiroButton);
        menuPanel.add(cadastrarVoosButton);
        menuPanel.add(listarAeroportosButton);
        menuPanel.add(listarCompanhiaAereaButton);
        menuPanel.add(listarPassageiroButton);
        menuPanel.add(listarVoosButton);
        menuPanel.add(pesquisarVoosButton);
        menuPanel.add(emitirBilheteButton);
        menuPanel.add(sairButton);


        cadastrarAeroportoButton.addActionListener(e -> cadastrarAeroporto(aeroportoManager));
        cadastrarCompanhiaAereaButton.addActionListener(e -> cadastrarCompanhiaAerea(companhiaAereaManager));
        cadastrarPassageiroButton.addActionListener(e -> cadastrarPassageiro(passageiroManager));
        cadastrarVoosButton.addActionListener(e -> cadastrarVoo(vooManager, aeroportoManager, companhiaAereaManager));
        listarAeroportosButton.addActionListener(e -> listarAeroportos(aeroportoManager));
        listarCompanhiaAereaButton.addActionListener(e -> listarCompanhiasAereas(companhiaAereaManager));
        listarPassageiroButton.addActionListener(e -> listarPassageiros(passageiroManager));
        listarVoosButton.addActionListener(e -> listarVoos(vooManager));
        pesquisarVoosButton.addActionListener(e -> pesquisarVoos(vooManager, aeroportoManager));
        emitirBilheteButton.addActionListener(e -> emitirBilhete(vooManager, passageiroManager));
        sairButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
        frame.add(menuPanel);


        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAeroporto(aeroportoManager);
                    break;
                case 2:
                    cadastrarCompanhiaAerea(companhiaAereaManager);
                    break;
                case 3:
                    cadastrarPassageiro(passageiroManager);
                    break;
                case 4:
                    cadastrarVoo(vooManager, aeroportoManager, companhiaAereaManager);
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
                    pesquisarVoos(vooManager, aeroportoManager);
                    break;
                case 10:
                    emitirBilhete(vooManager, passageiroManager);
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

    private static void cadastrarAeroporto(AeroportoManager aeroportoManager) {

        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Aeroporto");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel siglaLabel = new JLabel("Sigla:");
        JTextField siglaField = new JTextField();

        JLabel cidadeLabel = new JLabel("Cidade:");
        JTextField cidadeField = new JTextField();

        JLabel estadoLabel = new JLabel("Estado:");
        JTextField estadoField = new JTextField();

        JLabel paisLabel = new JLabel("País:");
        JTextField paisField = new JTextField();

        JLabel latitudeLabel = new JLabel("Latitude:");
        JTextField latitudeField = new JTextField();

        JLabel longitudeLabel = new JLabel("Longitude:");
        JTextField longitudeField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(siglaLabel);
        panel.add(siglaField);
        panel.add(cidadeLabel);
        panel.add(cidadeField);
        panel.add(estadoLabel);
        panel.add(estadoField);
        panel.add(paisLabel);
        panel.add(paisField);
        panel.add(latitudeLabel);
        panel.add(latitudeField);
        panel.add(longitudeLabel);
        panel.add(longitudeField);
        panel.add(salvarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        salvarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String sigla = siglaField.getText();
            String cidade = cidadeField.getText();
            String estado = estadoField.getText();
            String pais = paisField.getText();
            double latitude = Double.parseDouble(paisField.getText());
            double longitude = Double.parseDouble(paisField.getText());

            Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais, latitude, longitude);
            if (aeroportoManager.adicionarAeroporto(aeroporto)) {
                JOptionPane.showMessageDialog(dialog, "Aeroporto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Aeroporto com essa sigla já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
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

    private static void cadastrarPassageiro(PassageiroManager passageiroManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Passageiro");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();

        JLabel sobrenomeLabel = new JLabel("Sobrenome:");
        JTextField sobrenomeField = new JTextField();

        JLabel documentoLabel = new JLabel("Documento:");
        JTextField documentoField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(sobrenomeLabel);
        panel.add(sobrenomeField);
        panel.add(documentoLabel);
        panel.add(documentoField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(salvarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        salvarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            String documento = documentoField.getText();
            String email = emailField.getText();

            Passageiro passageiro = new Passageiro(nome, sobrenome, documento, email);
            if (passageiroManager.adicionarPassageiro(passageiro)) {
                JOptionPane.showMessageDialog(dialog, "Passageiro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Passageiro já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
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

    private static void cadastrarVoo(VooManager vooManager, AeroportoManager aeroportoManager, CompanhiaAereaManager companhiaAereaManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Voo");
        dialog.setSize(500, 400);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] nomesAeroportosOrigem = new String[aeroportoManager.listarAeroportos().size()];
        for (int i = 0; i < aeroportoManager.listarAeroportos().size(); i++) {
            nomesAeroportosOrigem[i] = aeroportoManager.listarAeroportos().get(i).getSigla();
        }
        JLabel origemLabel = new JLabel("Sigla Origem:");
        JComboBox<String> comboBoxOrigem = new JComboBox<>(nomesAeroportosOrigem);

        String[] nomesAeroportosDestino = new String[aeroportoManager.listarAeroportos().size()];
        for (int i = 0; i < aeroportoManager.listarAeroportos().size(); i++) {
            nomesAeroportosDestino[i] = aeroportoManager.listarAeroportos().get(i).getSigla();
        }
        JLabel destinoLabel = new JLabel("Sigla Destino:");
        JComboBox<String> comboBoxDestino = new JComboBox<>(nomesAeroportosDestino);

        JLabel codigoLabel = new JLabel("Código do Voo:");
        JTextField codigoField = new JTextField();

        JLabel horarioLabel = new JLabel("Horário (YYYY-MM-DD HH:MM):");
        JSpinner spinnerHorario = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHorario, "dd/MM/yyyy HH:MM");
        spinnerHorario.setEditor(editor);

        JLabel cnpjLabel = new JLabel("CNPJ da Companhia:");
        JTextField cnpjField = new JTextField();

        JLabel tarifaBasicaLabel = new JLabel("Tarifa Básica:");
        JTextField tarifaBasicaField = new JTextField();

        JLabel tarifaBusinessLabel = new JLabel("Tarifa Business:");
        JTextField tarifaBusinessField = new JTextField();

        JLabel tarifaPremiumLabel = new JLabel("Tarifa Premium:");
        JTextField tarifaPremiumField = new JTextField();

        JButton salvarButton = new JButton("Salvar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(origemLabel);
        panel.add(comboBoxOrigem);
        panel.add(destinoLabel);
        panel.add(comboBoxDestino);
        panel.add(codigoLabel);
        panel.add(codigoField);
        panel.add(horarioLabel);
        panel.add(spinnerHorario);
        panel.add(cnpjLabel);
        panel.add(cnpjField);
        panel.add(tarifaBasicaLabel);
        panel.add(tarifaBasicaField);
        panel.add(tarifaBusinessLabel);
        panel.add(tarifaBusinessField);
        panel.add(tarifaPremiumLabel);
        panel.add(tarifaPremiumField);
        panel.add(salvarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        salvarButton.addActionListener(e -> {
            try {
                String origemSigla = comboBoxOrigem.getSelectedItem().toString();
                String destinoSigla = comboBoxDestino.getSelectedItem().toString();
                String codigo = codigoField.getText();
                LocalDateTime horario = (LocalDateTime) spinnerHorario.getValue();
                String cnpj = cnpjField.getText();
                double tarifaBasica = Double.parseDouble(tarifaBasicaField.getText());
                double tarifaBusiness = Double.parseDouble(tarifaBusinessField.getText());
                double tarifaPremium = Double.parseDouble(tarifaPremiumField.getText());

                Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla(origemSigla);
                Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla(destinoSigla);
                CompanhiaAerea companhia = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);

                Voo voo = new Voo(origem, destino, horario, codigo, companhia,
                        new Aeronave("Boeing 737", 20000, 180, 30, 850.0),
                        tarifaBasica, tarifaBusiness, tarifaPremium, "BRL");

                vooManager.adicionarVoo(voo);
                JOptionPane.showMessageDialog(dialog, "Voo cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar voo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
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
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JLabel labelMensagem = new JLabel("Nenhum aeroporto cadastrado.");
            panel.add(labelMensagem);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Aeroporto aeroporto : aeroportos) {
                sb.append(aeroporto.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Aeroportos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void listarCompanhiasAereas(CompanhiaAereaManager companhiaAereaManager) {
        List<CompanhiaAerea> companhiasAereas = companhiaAereaManager.listarCompanhias();
        if (companhiasAereas.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JLabel labelMensagem = new JLabel("Nenhuma companhia aérea cadastrada.");
            panel.add(labelMensagem);
        } else {
            StringBuilder sb = new StringBuilder();
            for (CompanhiaAerea companhiaAerea : companhiasAereas) {
                sb.append(companhiaAerea.toString()).append("\n");
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Companhias Aéreas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void listarPassageiros(PassageiroManager passageiroManager) {
        List<Passageiro> passageiros = passageiroManager.listarPassageiros();
        if (passageiros.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JLabel labelMensagem = new JLabel("Nenhum passageiro cadastrado.");
            panel.add(labelMensagem);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Passageiro passageiro : passageiros) {
                sb.append(passageiro.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Passageiros", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void listarVoos(VooManager vooManager) {
        List<Voo> voos = vooManager.listarTodosOsVoos();
        if (voos.isEmpty()) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            JLabel labelMensagem = new JLabel("Nenhum voo cadastrado.");
            panel.add(labelMensagem);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Voo voo : voos) {
                sb.append(voo.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Voos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void pesquisarVoos(VooManager vooManager, AeroportoManager aeroportoManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Pesquisar Voos");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] nomesAeroportosOrigem = new String[aeroportoManager.listarAeroportos().size()];
        for (int i = 0; i < aeroportoManager.listarAeroportos().size(); i++) {
            nomesAeroportosOrigem[i] = aeroportoManager.listarAeroportos().get(i).getSigla();
        }
        JLabel origemLabel = new JLabel("Sigla Origem:");
        JComboBox<String> comboBoxOrigem = new JComboBox<>(nomesAeroportosOrigem);

        String[] nomesAeroportosDestino = new String[aeroportoManager.listarAeroportos().size()];
        for (int i = 0; i < aeroportoManager.listarAeroportos().size(); i++) {
            nomesAeroportosDestino[i] = aeroportoManager.listarAeroportos().get(i).getSigla();
        }
        JLabel destinoLabel = new JLabel("Sigla Destino:");
        JComboBox<String> comboBoxDestino = new JComboBox<>(nomesAeroportosDestino);

        JLabel horarioLabel = new JLabel("Horário (YYYY-MM-DD HH:MM):");
        JSpinner spinnerHorario = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHorario, "dd/MM/yyyy HH:MM");
        spinnerHorario.setEditor(editor);

        JButton pesquisarButton = new JButton("Pesquisar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(origemLabel);
        panel.add(comboBoxOrigem);
        panel.add(destinoLabel);
        panel.add(comboBoxDestino);
        panel.add(horarioLabel);
        panel.add(spinnerHorario);
        panel.add(pesquisarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        pesquisarButton.addActionListener(e -> {
            try {
                String origemSigla = comboBoxOrigem.getSelectedItem().toString();
                Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla(origemSigla);

                String destinoSigla = comboBoxDestino.getSelectedItem().toString();
                Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla(destinoSigla);

                LocalDateTime dataHoraPesquisa = (LocalDateTime) spinnerHorario.getValue();

                List<Voo> voos = vooManager.pesquisarVoos(origem, destino, dataHoraPesquisa);
                if (voos.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Nenhum voo encontrado.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder resultado = new StringBuilder("Voos encontrados:\n");
                    voos.forEach(voo -> resultado.append(voo.getCodigoVoo()).append("\n"));
                    JOptionPane.showMessageDialog(dialog, resultado.toString(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao pesquisar voos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private static void emitirBilhete(VooManager vooManager, PassageiroManager passageiroManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Emitir Bilhete");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel codigoVooLabel = new JLabel("Código do Voo:");
        JTextField codigoVooField = new JTextField();

        JLabel documentoLabel = new JLabel("Documento do Passageiro:");
        JTextField documentoField = new JTextField();

        JButton emitirButton = new JButton("Emitir");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(codigoVooLabel);
        panel.add(codigoVooField);
        panel.add(documentoLabel);
        panel.add(documentoField);
        panel.add(emitirButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        emitirButton.addActionListener(e -> {
            String codigoVoo = codigoVooField.getText();
            Voo voo = vooManager.listarTodosOsVoos().stream()
                    .filter(v -> v.getCodigoVoo().equals(codigoVoo))
                    .findFirst()
                    .orElse(null);

            if (voo == null) {
                JOptionPane.showMessageDialog(dialog, "Voo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String documento = documentoField.getText();
            Passageiro passageiro = passageiroManager.listarPassageiros().stream()
                    .filter(p -> p.getDocumento().equals(documento))
                    .findFirst()
                    .orElse(null);

            if (passageiro == null) {
                JOptionPane.showMessageDialog(dialog, "Passageiro não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Bilhete bilhete = new Bilhete(passageiro, voo);
            if (bilhete.emitir()) {
                JOptionPane.showMessageDialog(dialog, "Bilhete emitido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Erro ao emitir bilhete.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }
}
