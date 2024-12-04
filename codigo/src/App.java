import Entidades.*;

import java.awt.*;
import java.time.LocalDateTime;
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
        panel.add(salvarButton);
        panel.add(cancelarButton);

        dialog.add(panel);

        salvarButton.addActionListener(e -> {
            String nome = nomeField.getText();
            String sigla = siglaField.getText();
            String cidade = cidadeField.getText();
            String estado = estadoField.getText();
            String pais = paisField.getText();

            Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais);
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
                        new Aeronave("Boeing 737", 20000, 180, 30),
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