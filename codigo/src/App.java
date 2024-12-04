import Entidades.*;
import Managers.*;
import dao.ILog;
import dao.LogDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
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
        FuncionarioManager funcionarioManager = new FuncionarioManager();

        registrarLog("Sistema iniciado.");
        iniciarDadosIniciais(aeroportoManager, companhiaAereaManager, passageiroManager, vooManager, funcionarioManager);

        JFrame frame = new JFrame("✈\uFE0F \uD83D\uDEEB Sistema de Gestão de Voos ✈\uFE0F \uD83D\uDEEC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 400);
        frame.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("✈\uFE0F \uD83D\uDEEB Sistema de Gestão de Voos ✈\uFE0F \uD83D\uDEEC");
        menuPanel.add(titleLabel);

        JButton cadastrarAeroportoButton = new JButton("1. Cadastrar Aeroporto");
        JButton alterarAeroportoButton = new JButton("2. Alterar Aeroporto");
        JButton excluirAeroportoButton = new JButton("3. Excluir Aeroporto");
        JButton listarAeroportosButton = new JButton("4. Listar Aeroportos");
        JButton cadastrarCompanhiaAereaButton = new JButton("5. Cadastrar Companhia Aérea");
        JButton alterarCompanhiaAereaButton = new JButton("6. Alterar Companhia Aérea");
        JButton excluirCompanhiaAereaButton = new JButton("7. Excluir Companhia Aérea");
        JButton listarCompanhiaAereaButton = new JButton("8. Listar Companhias Aéreas");
        JButton cadastrarPassageiroButton = new JButton("9. Cadastrar Passageiro");
        JButton alterarPassageiroButton = new JButton("10. Alterar Passageiro");
        JButton excluirPassageiroButton = new JButton("11. Excluir Passageiro");
        JButton listarPassageiroButton = new JButton("12. Listar Passageiros");
        JButton cadastrarVoosButton = new JButton("13. Cadastrar Voo");
        JButton alterarVooButton = new JButton("14. Alterar Voo");
        JButton excluirVooButton = new JButton("15. Excluir Voo");
        JButton listarVoosButton = new JButton("16. Listar Voos");
        JButton cadastrarFuncionarioButton = new JButton("17. Cadastrar Funcionario");
        JButton alterarFuncionarioButton = new JButton("18. Alterar Funcionario");
        JButton excluirFuncionarioButton = new JButton("19. Excluir Funcionario");
        JButton listarFuncionarioButton = new JButton("20. Listar Funcionarios");
        JButton emitirBilheteButton = new JButton("21. Emitir bilhete");
        JButton pesquisarVoosButton = new JButton("22. Pesquisar Voos");
        JButton sairButton = new JButton("23. Sair ✖\uFE0F");

        menuPanel.add(cadastrarAeroportoButton);
        menuPanel.add(alterarAeroportoButton);
        menuPanel.add(excluirAeroportoButton);
        menuPanel.add(listarAeroportosButton);
        menuPanel.add(cadastrarCompanhiaAereaButton);
        menuPanel.add(alterarCompanhiaAereaButton);
        menuPanel.add(excluirCompanhiaAereaButton);
        menuPanel.add(listarCompanhiaAereaButton);
        menuPanel.add(cadastrarPassageiroButton);
        menuPanel.add(alterarPassageiroButton);
        menuPanel.add(excluirPassageiroButton);
        menuPanel.add(listarPassageiroButton);
        menuPanel.add(cadastrarVoosButton);
        menuPanel.add(alterarVooButton);
        menuPanel.add(excluirVooButton);
        menuPanel.add(listarVoosButton);
        menuPanel.add(cadastrarFuncionarioButton);
        menuPanel.add(alterarFuncionarioButton);
        menuPanel.add(excluirFuncionarioButton);
        menuPanel.add(listarFuncionarioButton);
        menuPanel.add(emitirBilheteButton);
        menuPanel.add(pesquisarVoosButton);
        menuPanel.add(sairButton);


        cadastrarAeroportoButton.addActionListener(e -> cadastrarAeroporto(aeroportoManager));
        alterarAeroportoButton.addActionListener(e -> alterarAeroporto(aeroportoManager));
        excluirAeroportoButton.addActionListener(e -> excluirAeroporto(aeroportoManager));
        listarAeroportosButton.addActionListener(e -> listarAeroportos(aeroportoManager));
        cadastrarCompanhiaAereaButton.addActionListener(e -> cadastrarCompanhiaAerea(companhiaAereaManager));
        alterarCompanhiaAereaButton.addActionListener(e -> alterarCompanhiaAerea(companhiaAereaManager));
        excluirCompanhiaAereaButton.addActionListener(e -> excluirCompanhiaAerea(companhiaAereaManager));
        listarCompanhiaAereaButton.addActionListener(e -> listarCompanhiasAereas(companhiaAereaManager));
        cadastrarPassageiroButton.addActionListener(e -> cadastrarPassageiro(passageiroManager));
        alterarPassageiroButton.addActionListener(e -> alterarPassageiro(passageiroManager));
        excluirPassageiroButton.addActionListener(e -> excluirPassageiro(passageiroManager));
        listarPassageiroButton.addActionListener(e -> listarPassageiros(passageiroManager));
        cadastrarVoosButton.addActionListener(e -> cadastrarVoo(vooManager, aeroportoManager, companhiaAereaManager));
        alterarVooButton.addActionListener(e -> alterarVoo(vooManager, aeroportoManager));
        excluirVooButton.addActionListener(e -> excluirVoo(vooManager));
        listarVoosButton.addActionListener(e -> listarVoos(vooManager));
        cadastrarFuncionarioButton.addActionListener(e -> cadastrarFuncionario(funcionarioManager));
        alterarFuncionarioButton.addActionListener(e -> alterarFuncionario(funcionarioManager));
        excluirFuncionarioButton.addActionListener(e -> excluirFuncionario(funcionarioManager));
        listarFuncionarioButton.addActionListener(e -> listarFuncionarios(funcionarioManager));
        pesquisarVoosButton.addActionListener(e -> pesquisarVoos(vooManager, aeroportoManager));
        emitirBilheteButton.addActionListener(e -> emitirBilhete(vooManager, passageiroManager));
        sairButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
        frame.add(menuPanel);
    }

    private static void iniciarDadosIniciais(AeroportoManager aeroportoManager, CompanhiaAereaManager companhiaAereaManager,
                                             PassageiroManager passageiroManager, VooManager vooManager, FuncionarioManager funcionarioManager) {
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

        Funcionario funcionario1 = new Funcionario("João Silva", "10101010", "joao.silva@email.com", "joaosilva",
                "senha123");
        Funcionario funcionario2 = new Funcionario("Maria Oliveira", "1212121212", "maria.oliveira@email.com",
                "maria123", "senha456");
        funcionarioManager.adicionarFuncionario(funcionario1);
        funcionarioManager.adicionarFuncionario(funcionario2);

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
            try {
                String nome = nomeField.getText();
                String sigla = siglaField.getText();
                String cidade = cidadeField.getText();
                String estado = estadoField.getText();
                String pais = paisField.getText();
                double latitude = Double.parseDouble(latitudeField.getText());
                double longitude = Double.parseDouble(longitudeField.getText());

                Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais, latitude, longitude);
                if (aeroportoManager.adicionarAeroporto(aeroporto)) {
                    JOptionPane.showMessageDialog(dialog, "Aeroporto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Aeroporto com essa sigla já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao converter latitude ou longitude. Insira valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao cadastrar aeroporto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private static void alterarAeroporto(AeroportoManager aeroportoManager) {
        List<Aeroporto> aeroportos = aeroportoManager.listarAeroportos();

        String[] siglaAero = aeroportos.stream()
                .map(Aeroporto::getSigla)
                .toArray(String[]::new);

        JLabel siglaLabel = new JLabel("Selecione a Sigla do Aeroporto que deseja alterar:");
        JComboBox<String> comboBoxSigla = new JComboBox<>(siglaAero);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(siglaLabel);
        panel.add(comboBoxSigla);

        int result = JOptionPane.showConfirmDialog(null, panel, "Alterar Aeroporto", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String siglaSelecionada = (String) comboBoxSigla.getSelectedItem();

            try {
                Aeroporto aeroporto = aeroportoManager.buscarAeroportoPorSigla(siglaSelecionada);
                JOptionPane.showMessageDialog(null, "Aeroporto encontrado: " + aeroporto);

                String novoNome = JOptionPane.showInputDialog("Digite o novo nome do aeroporto:");
                String novaCidade = JOptionPane.showInputDialog("Digite a nova cidade:");
                String novoEstado = JOptionPane.showInputDialog("Digite o novo estado:");
                String novoPais = JOptionPane.showInputDialog("Digite o novo país:");

                boolean alterado = aeroportoManager.alterarAeroporto(siglaSelecionada, novoNome, novaCidade, novoEstado, novoPais);

                if (alterado) {
                    registrarLog("Aeroporto [" + siglaSelecionada + "] alterado para: Nome=[" + novoNome + "], Cidade=[" + novaCidade
                            + "], Estado=[" + novoEstado + "], País=[" + novoPais + "]");
                    JOptionPane.showMessageDialog(null, "Aeroporto alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível alterar o aeroporto.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao alterar aeroporto: " + e.getMessage());
            }
        }
    }

    private static void excluirAeroporto(AeroportoManager aeroportoManager) {
        List<Aeroporto> aeroportos = aeroportoManager.listarAeroportos();
        String[] siglaAero = aeroportos.stream()
                .map(Aeroporto::getSigla)
                .toArray(String[]::new);


        JLabel siglaLabel = new JLabel("Selecione a Sigla do Aeroporto que deseja excluir:");
        JComboBox<String> comboBoxSigla = new JComboBox<>(siglaAero);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(siglaLabel);
        panel.add(comboBoxSigla);

        int result = JOptionPane.showConfirmDialog(null, panel, "Alterar Aeroporto", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String siglaSelecionada = (String) comboBoxSigla.getSelectedItem();

            try {
                boolean removido = aeroportoManager.removerAeroporto(siglaSelecionada);

                if (removido) {
                    registrarLog("Aeroporto [" + siglaSelecionada + "] foi removido do sistema.");
                    JOptionPane.showMessageDialog(null, "Aeroporto removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Aeroporto não encontrado ou não pôde ser removido.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir aeroporto: " + e.getMessage());
            }
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

    private static void alterarCompanhiaAerea(CompanhiaAereaManager companhiaAereaManager) {
        String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da companhia aérea a ser alterada:");
        if (cnpj == null || cnpj.isBlank()) return;

        try {
            CompanhiaAerea antigaCompanhia = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);

            String novoNome = JOptionPane.showInputDialog("Digite o novo nome da companhia aérea:");
            String novaRazaoSocial = JOptionPane.showInputDialog("Digite a nova razão social:");
            String novaSigla = JOptionPane.showInputDialog("Digite a nova sigla:");
            String valorPrimeiraBagagemStr = JOptionPane.showInputDialog("Digite o novo valor da primeira bagagem:");
            String valorBagagensAdicionaisStr = JOptionPane.showInputDialog("Digite o novo valor das bagagens adicionais:");

            double novoValorPrimeiraBagagem = Double.parseDouble(valorPrimeiraBagagemStr);
            double novoValorBagagensAdicionais = Double.parseDouble(valorBagagensAdicionaisStr);

            boolean alterado = companhiaAereaManager.alterarCompanhia(cnpj, novoNome, novaRazaoSocial, novaSigla,
                    novoValorPrimeiraBagagem, novoValorBagagensAdicionais);

            if (alterado) {
                registrarLog(String.format(
                        "Companhia aérea alterada: Antes -> %s; Depois -> [Nome: %s, Razão Social: %s, Sigla: %s, Valor Primeira Bagagem: %.2f, Valor Bagagens Adicionais: %.2f]",
                        antigaCompanhia.toString(), novoNome, novaRazaoSocial, novaSigla, novoValorPrimeiraBagagem,
                        novoValorBagagensAdicionais));
                JOptionPane.showMessageDialog(null, "Companhia aérea alterada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar a companhia aérea.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void excluirCompanhiaAerea(CompanhiaAereaManager companhiaAereaManager) {
        String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da companhia aérea a ser excluída:");
        if (cnpj == null || cnpj.isBlank()) return;

        try {
            CompanhiaAerea companhiaAExcluir = companhiaAereaManager.buscarCompanhiaPorCnpj(cnpj);

            boolean excluido = companhiaAereaManager.excluirCompanhia(cnpj);

            if (excluido) {
                registrarLog(String.format("Companhia aérea excluída: %s", companhiaAExcluir.toString()));
                JOptionPane.showMessageDialog(null, "Companhia aérea excluída com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao excluir a companhia aérea.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

    private static void alterarPassageiro(PassageiroManager passageiroManager) {
        String documento = JOptionPane.showInputDialog("Digite o documento do passageiro que deseja alterar:");
        if (documento == null || documento.isBlank()) return;

        try {
            Passageiro passageiro = passageiroManager.buscarPassageiroPorDocumento(documento);

            String novoNome = JOptionPane.showInputDialog("Digite o novo nome do passageiro:");
            String novoSobrenome = JOptionPane.showInputDialog("Digite o novo sobrenome:");
            String novoEmail = JOptionPane.showInputDialog("Digite o novo email do passageiro:");

            boolean alterado = passageiroManager.alterarPassageiro(documento, novoNome, novoSobrenome, novoEmail);

            if (alterado) {
                registrarLog(String.format(
                        "Passageiro alterado: Antes -> %s; Depois -> Nome: %s, Sobrenome: %s, Email: %s",
                        passageiro.toString(), novoNome, novoSobrenome, novoEmail));
                JOptionPane.showMessageDialog(null, "Passageiro alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao alterar o passageiro.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar passageiro: " + e.getMessage());
        }
    }

    private static void excluirPassageiro(PassageiroManager passageiroManager) {
        String documento = JOptionPane.showInputDialog("Digite o documento do passageiro que deseja excluir:");
        if (documento == null || documento.isBlank()) return;

        try {
            Passageiro passageiro = passageiroManager.buscarPassageiroPorDocumento(documento);
            boolean removido = passageiroManager.excluirPassageiro(documento);

            if (removido) {
                registrarLog(String.format("Passageiro excluído: %s", passageiro.toString()));
                JOptionPane.showMessageDialog(null, "Passageiro excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao excluir o passageiro.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir passageiro: " + e.getMessage());
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

        String[] nomesAeroportosOrigem = aeroportoManager.listarAeroportos().stream()
                .map(Aeroporto::getSigla)
                .toArray(String[]::new);
        JLabel origemLabel = new JLabel("Sigla Origem:");
        JComboBox<String> comboBoxOrigem = new JComboBox<>(nomesAeroportosOrigem);

        String [] nomesAeroportosDestino = aeroportoManager.listarAeroportos().stream()
                .map(Aeroporto::getSigla)
                .toArray(String[]::new);

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
                Date date = (Date) spinnerHorario.getValue();
                LocalDateTime horario = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
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

    private static void alterarVoo(VooManager vooManager, AeroportoManager aeroportoManager) {
        try {

            List<Voo> voos = vooManager.listarTodosOsVoos();
            String[] codVoo = voos.stream()
                    .map(Voo::getCodigoVoo)
                    .toArray(String[]::new);


            JLabel codLabel = new JLabel("Selecione  o código do voo a ser alterado: ");
            JComboBox<String> comboBoxCod = new JComboBox<>(codVoo);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(codLabel);
            panel.add(comboBoxCod);

            int result = JOptionPane.showConfirmDialog(null, panel, "Alterar Voo", JOptionPane.OK_CANCEL_OPTION);


            if (result == JOptionPane.OK_OPTION) {

                String codigoSelecionado = (String) comboBoxCod.getSelectedItem();
                Voo voo = vooManager.buscarVooPorCodigo(codigoSelecionado);
                List<Aeroporto> aeroportos = aeroportoManager.listarAeroportos();
                String[] siglaAeroO = aeroportos.stream()
                        .map(Aeroporto::getSigla)
                        .toArray(String[]::new);

                JLabel siglaLabelO = new JLabel("Selecione a Sigla do Aeroporto que ele fará origem:");
                JComboBox<String> comboBoxSiglaO = new JComboBox<>(siglaAeroO);

                JPanel panel2 = new JPanel();
                panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
                panel2.add(siglaLabelO);
                panel2.add(comboBoxSiglaO);

                int result2 = JOptionPane.showConfirmDialog(null, panel2, "Alterar Aeroporto", JOptionPane.OK_CANCEL_OPTION);

                if (result2 == JOptionPane.OK_OPTION) {
                    String siglaSelecionadaO = (String) comboBoxSiglaO.getSelectedItem();
                    Aeroporto novaOrigem = aeroportoManager.buscarAeroportoPorSigla(siglaSelecionadaO);

                    String[] siglaAeroD = aeroportos.stream()
                            .map(Aeroporto::getSigla)
                            .toArray(String[]::new);

                    JLabel siglaLabelD = new JLabel("Selecione a Sigla do Aeroporto que ele fará destino:");
                    JComboBox<String> comboBoxSiglaD = new JComboBox<>(siglaAeroD);

                    JPanel panel3 = new JPanel();
                    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
                    panel3.add(siglaLabelD);
                    panel3.add(comboBoxSiglaD);

                    int result3 = JOptionPane.showConfirmDialog(null, panel3, "Alterar Aeroporto", JOptionPane.OK_CANCEL_OPTION);

                    if (result3 == JOptionPane.OK_OPTION) {
                        String siglaSelecionadaD = (String) comboBoxSiglaD.getSelectedItem();
                        Aeroporto novoDestino = aeroportoManager.buscarAeroportoPorSigla(siglaSelecionadaD);

                        String novoHorario = JOptionPane.showInputDialog("Digite o novo horário do voo (yyyy-MM-dd HH:mm):");
                        LocalDateTime novaDataHora = parseDateTime(novoHorario);

                        String tarifaBasicaStr = JOptionPane.showInputDialog("Digite o novo valor da tarifa básica:");
                        double novaTarifaBasica = Double.parseDouble(tarifaBasicaStr);

                        String tarifaBusinessStr = JOptionPane.showInputDialog("Digite o novo valor da tarifa business:");
                        double novaTarifaBusiness = Double.parseDouble(tarifaBusinessStr);

                        String tarifaPremiumStr = JOptionPane.showInputDialog("Digite o novo valor da tarifa premium:");
                        double novaTarifaPremium = Double.parseDouble(tarifaPremiumStr);

                        boolean alterado = vooManager.alterarVoo(
                                codigoSelecionado,
                                novaDataHora,
                                novaOrigem,
                                novoDestino,
                                novaTarifaBasica,
                                novaTarifaBusiness,
                                novaTarifaPremium
                        );

                        if (alterado) {
                            registrarLog(String.format(
                                    "Voo alterado: Antes -> %s; Depois -> Origem: %s, Destino: %s, Horário: %s, Tarifa Básica: %.2f, Tarifa Business: %.2f, Tarifa Premium: %.2f",
                                    voo.toString(),
                                    novaOrigem.getSigla(),
                                    novoDestino.getSigla(),
                                    novaDataHora,
                                    novaTarifaBasica,
                                    novaTarifaBusiness,
                                    novaTarifaPremium
                            ));
                            JOptionPane.showMessageDialog(null, "Voo alterado com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Falha ao alterar o voo.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar voo: " + e.getMessage());
        }

    }

    private static void excluirVoo(VooManager vooManager) {
        try {
            List<Voo> voos = vooManager.listarTodosOsVoos();
            String[] codigosVoo = new String[voos.size()];
            for (int i = 0; i < voos.size(); i++) {
                codigosVoo[i] = voos.get(i).getCodigoVoo();
            }

            JLabel vooLabel = new JLabel("Selecione o código do voo a ser excluído:");
            JComboBox<String> comboBoxVoos = new JComboBox<>(codigosVoo);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(vooLabel);
            panel.add(comboBoxVoos);

            int result = JOptionPane.showConfirmDialog(null, panel, "Excluir Voo", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String codigoSelecionado = (String) comboBoxVoos.getSelectedItem();
                Voo vooAExcluir = vooManager.buscarVooPorCodigo(codigoSelecionado);

                boolean excluido = vooManager.excluirVoo(codigoSelecionado);

                if (excluido) {
                    registrarLog(String.format("Voo excluído: %s", vooAExcluir.toString()));
                    JOptionPane.showMessageDialog(null, "Voo excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao excluir o voo.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir voo: " + e.getMessage());
        }
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

    private static void cadastrarFuncionario(FuncionarioManager funcionarioManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Cadastrar Funcionário");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel cpfLabel = new JLabel("CPF:");
        JTextField cpfField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel usuarioLabel = new JLabel("Usuário:");
        JTextField usuarioField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(usuarioLabel);
        panel.add(usuarioField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(cadastrarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        cadastrarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String email = emailField.getText();
            String usuario = usuarioField.getText();
            String senha = new String(senhaField.getPassword());

            Funcionario funcionario = new Funcionario(nome, cpf, email, usuario, senha);

            if (funcionarioManager.adicionarFuncionario(funcionario)) {
                registrarLog(String.format("Funcionário cadastrado: Nome=%s, CPF=%s, Email=%s, Usuário=%s", nome, cpf, email, usuario));
                JOptionPane.showMessageDialog(dialog, "Funcionário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                registrarLog(String.format("Falha ao cadastrar funcionário: CPF já cadastrado (%s).", cpf));
                JOptionPane.showMessageDialog(dialog, "Funcionário com o mesmo CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private static void alterarFuncionario(FuncionarioManager funcionarioManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Alterar Funcionário");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel cpfLabel = new JLabel("CPF do Funcionário:");
        JTextField cpfField = new JTextField();
        JLabel nomeLabel = new JLabel("Novo Nome:");
        JTextField nomeField = new JTextField();
        JLabel emailLabel = new JLabel("Novo Email:");
        JTextField emailField = new JTextField();
        JLabel usuarioLabel = new JLabel("Novo Usuário:");
        JTextField usuarioField = new JTextField();
        JLabel senhaLabel = new JLabel("Nova Senha:");
        JPasswordField senhaField = new JPasswordField();

        JButton alterarButton = new JButton("Alterar");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(usuarioLabel);
        panel.add(usuarioField);
        panel.add(senhaLabel);
        panel.add(senhaField);
        panel.add(alterarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        alterarButton.addActionListener(e -> {
            String cpf = cpfField.getText();
            try {
                Funcionario antigoFuncionario = funcionarioManager.buscarPorCpf(cpf);

                String novoNome = nomeField.getText();
                String novoEmail = emailField.getText();
                String novoUsuario = usuarioField.getText();
                String novaSenha = new String(senhaField.getPassword());

                boolean alterado = funcionarioManager.alterarFuncionario(cpf, novoNome, novoEmail, novoUsuario, novaSenha);

                if (alterado) {
                    registrarLog(String.format(
                            "Funcionário alterado: Antes -> Nome=%s, Email=%s, Usuário=%s; Depois -> Nome=%s, Email=%s, Usuário=%s",
                            antigoFuncionario.getNome(), antigoFuncionario.getEmail(), antigoFuncionario.getUsuario(),
                            novoNome, novoEmail, novoUsuario));
                    JOptionPane.showMessageDialog(dialog, "Funcionário alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Não foi possível alterar o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao alterar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private static void excluirFuncionario(FuncionarioManager funcionarioManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Excluir Funcionário");
        dialog.setSize(400, 200);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel cpfLabel = new JLabel("CPF do Funcionário:");
        JTextField cpfField = new JTextField();

        JButton excluirButton = new JButton("Excluir");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(excluirButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        excluirButton.addActionListener(e -> {
            String cpf = cpfField.getText();
            try {
                Funcionario funcionario = funcionarioManager.buscarPorCpf(cpf);
                if (funcionario == null) {
                    JOptionPane.showMessageDialog(dialog, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean excluido = funcionarioManager.excluirFuncionario(cpf);

                if (excluido) {
                    registrarLog("Funcionário excluído: " + funcionario.toString());
                    JOptionPane.showMessageDialog(dialog, "Funcionário excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Falha ao excluir o funcionário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private static void listarFuncionarios(FuncionarioManager funcionarioManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Lista de Funcionários");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        List<Funcionario> funcionarios = funcionarioManager.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            textArea.setText("Nenhum funcionário cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder("Lista de Funcionários:\n");
            funcionarios.forEach(funcionario -> sb.append(funcionario.toString()).append("\n"));
            textArea.setText(sb.toString());
        }

        panel.add(scrollPane, BorderLayout.CENTER);

        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> dialog.dispose());
        panel.add(fecharButton, BorderLayout.SOUTH);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private static void pesquisarVoos(VooManager vooManager, AeroportoManager aeroportoManager) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Pesquisar Voos");
        dialog.setSize(400, 300);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] nomesAeroportos = aeroportoManager.listarAeroportos().stream()
                .map(Aeroporto::getSigla)
                .toArray(String[]::new);
        JLabel origemLabel = new JLabel("Sigla Origem:");
        JComboBox<String> comboBoxOrigem = new JComboBox<>(nomesAeroportos);
        JLabel destinoLabel = new JLabel("Sigla Destino:");
        JComboBox<String> comboBoxDestino = new JComboBox<>(nomesAeroportos);

        JLabel horarioLabel = new JLabel("Horário (YYYY-MM-DD):");
        JSpinner spinnerHorario = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerHorario, "dd/MM/yyyy");
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

                Date date = (Date) spinnerHorario.getValue();
                LocalDateTime dataHoraPesquisa = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                System.out.println("Data de pesquisa: " + dataHoraPesquisa);

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
                JOptionPane.showMessageDialog(null, "Erro ao processar data e horário: " + ex.getMessage());
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


        String[] codigosVoo = new String[vooManager.listarTodosOsVoos().size()];
        for (int i = 0; i < vooManager.listarTodosOsVoos().size(); i++) {
            codigosVoo[i] = vooManager.listarTodosOsVoos().get(i).getCodigoVoo();
        }
        JLabel codigoVooLabel = new JLabel("Código do Voo:");
        JComboBox<String> comboBoxCodVoo = new JComboBox<>(codigosVoo);

        JLabel documentoLabel = new JLabel("Documento do Passageiro:");
        JTextField documentoField = new JTextField();

        JButton emitirButton = new JButton("Emitir");
        JButton cancelarButton = new JButton("Cancelar");

        panel.add(codigoVooLabel);
        panel.add(comboBoxCodVoo);
        panel.add(documentoLabel);
        panel.add(documentoField);
        panel.add(emitirButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        emitirButton.addActionListener(e -> {
            String codigoVoo = comboBoxCodVoo.getSelectedItem().toString();
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

    private static void registrarLog(String mensagem) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMensagem = String.format("[%s] %s", timestamp, mensagem);
        logDAO.salvarLog(logMensagem);
    }

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
}
