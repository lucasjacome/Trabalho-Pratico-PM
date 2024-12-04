package Facade;

import Entidades.*;
import Enums.StatusPassagem;
import Managers.*;
import dao.ILog;
import dao.LogDAOImpl;

import java.time.LocalDateTime;
import java.util.List;

public class SistemaFacade {
    private AeroportoManager aeroportoManager;
    private CompanhiaAereaManager companhiaAereaManager;
    private VooManager vooManager;
    private ILog log;

    public SistemaFacade() {
        this.aeroportoManager = new AeroportoManager();
        this.companhiaAereaManager = new CompanhiaAereaManager();
        this.vooManager = new VooManager();
        this.log = new LogDAOImpl();
    }

    // Método para realizar o cadastro básico
    // Método para realizar o cadastro básico
    public void cadastroBasico() {
        // Cadastro de aeroportos
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil",
                -23.5505, -46.6333);
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto Internacional do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ",
                "Brasil", -22.9094, -43.1737);
        Aeroporto aeroporto3 = new Aeroporto("Aeroporto Internacional de Belo Horizonte", "CNF", "Belo Horizonte", "MG",
                "Brasil", -19.6349, -43.9654);
        Aeroporto aeroporto4 = new Aeroporto("Aeroporto Internacional de Brasília", "BSB", "Brasília", "DF",
                "Brasil", -15.7941, -47.8825);
        Aeroporto aeroporto5 = new Aeroporto("Aeroporto Internacional de Salvador", "SSA", "Salvador", "BA",
                "Brasil", -12.9714, -38.5014);

        aeroportoManager.adicionarAeroporto(aeroporto1);
        aeroportoManager.adicionarAeroporto(aeroporto2);
        aeroportoManager.adicionarAeroporto(aeroporto3);
        aeroportoManager.adicionarAeroporto(aeroporto4);
        aeroportoManager.adicionarAeroporto(aeroporto5);
        log.salvarLog("Cadastro de aeroportos realizado.");

        // Cadastro das companhias aéreas
        CompanhiaAerea companhia1 = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0,
                30.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("ABC Airlines", "ABC", "Razão ABC", "98765432000123", 60.0,
                40.0);
        companhiaAereaManager.adicionarCompanhia(companhia1);
        companhiaAereaManager.adicionarCompanhia(companhia2);
        log.salvarLog("Cadastro de companhias aéreas realizado.");

        // Cadastro das aeronaves
        Aeronave aeronave1 = new Aeronave("Boeing 737", 20000, 180, 30, 850.0);

        // Cadastro de voos diretos entre GRU e GIG
        LocalDateTime dataHoraVooInicial = LocalDateTime.of(2024, 12, 1, 10, 0);
        for (int i = 0; i < 3; i++) {
            String codigoVooBase = "XYZ-GRU-GIG-" + (i + 1);
            for (int dia = 0; dia < 10; dia++) {
                String codigoVoo = codigoVooBase + String.format("%02d", dia + 1);
                LocalDateTime dataHoraVoo = dataHoraVooInicial.plusDays(dia).withHour(10 + i);
                Voo voo = new Voo(aeroporto1, aeroporto2, dataHoraVoo, codigoVoo, companhia1, aeronave1, 500.0, 1000.0,
                        1500.0, "BRL");
                vooManager.adicionarVoo(voo);
                log.salvarLog("Cadastro de voo realizado: " + voo);
            }
        }

        // Cadastro de voos com conexão
        // GRU -> CNF -> GIG
        for (int dia = 0; dia < 10; dia++) {
            LocalDateTime dataHoraVoo = dataHoraVooInicial.plusDays(dia).withHour(8);
            Voo voo1 = new Voo(aeroporto1, aeroporto3, dataHoraVoo, "GRU-CNF-" + dia, companhia1, aeronave1, 300.0,
                    600.0,
                    900.0, "BRL");
            Voo voo2 = new Voo(aeroporto3, aeroporto2, dataHoraVoo.plusHours(3), "CNF-GIG-" + dia, companhia1,
                    aeronave1,
                    300.0, 600.0, 900.0, "BRL");
            vooManager.adicionarVoo(voo1);
            vooManager.adicionarVoo(voo2);
            log.salvarLog("Cadastro de voo com conexão realizado: " + voo1 + " -> " + voo2);
        }

        // GRU -> BSB -> GIG
        for (int dia = 0; dia < 10; dia++) {
            LocalDateTime dataHoraVoo = dataHoraVooInicial.plusDays(dia).withHour(9);
            Voo voo1 = new Voo(aeroporto1, aeroporto4, dataHoraVoo, "GRU-BSB-" + dia, companhia2, aeronave1, 400.0,
                    700.0,
                    1000.0, "BRL");
            Voo voo2 = new Voo(aeroporto4, aeroporto2, dataHoraVoo.plusHours(4), "BSB-GIG-" + dia, companhia2,
                    aeronave1,
                    400.0, 700.0, 1000.0, "BRL");
            vooManager.adicionarVoo(voo1);
            vooManager.adicionarVoo(voo2);
            log.salvarLog("Cadastro de voo com conexão realizado: " + voo1 + " -> " + voo2);
        }

        // GRU -> SSA -> GIG
        for (int dia = 0; dia < 10; dia++) {
            LocalDateTime dataHoraVoo = dataHoraVooInicial.plusDays(dia).withHour(7);
            Voo voo1 = new Voo(aeroporto1, aeroporto5, dataHoraVoo, "GRU-SSA-" + dia, companhia1, aeronave1, 450.0,
                    800.0,
                    1200.0, "BRL");
            Voo voo2 = new Voo(aeroporto5, aeroporto2, dataHoraVoo.plusHours(5), "SSA-GIG-" + dia, companhia1,
                    aeronave1,
                    450.0, 800.0, 1200.0, "BRL");
            vooManager.adicionarVoo(voo1);
            vooManager.adicionarVoo(voo2);
            log.salvarLog("Cadastro de voo com conexão realizado: " + voo1 + " -> " + voo2);
        }

        log.salvarLog("Cadastro básico completo.");
    }

    // Método para o cenário 1: Buscar voos diretos e realizar operações de
    // check-in, embarque e geração de bilhete
    public void cenario1() {
        Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla("GRU");
        Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla("GIG");
        LocalDateTime dataPesquisa = LocalDateTime.of(2024, 12, 1, 10, 0);

        // Pesquisa por voos diretos
        List<Voo> voosDisponiveis = vooManager.pesquisarVoos(origem, destino, dataPesquisa);

        if (voosDisponiveis.isEmpty()) {
            log.salvarLog("Nenhum voo encontrado entre " + origem.getSigla() + " e " + destino.getSigla() + " na data "
                    + dataPesquisa);
            System.out.println("Nenhum voo encontrado.");
            return;
        }

        // Exibindo dados dos voos encontrados
        for (Voo voo : voosDisponiveis) {
            double duracaoVoo = voo.calcularTempoDeViagem();
            LocalDateTime horarioChegada = voo.calcularHorarioChegada();
            double valorPassagem = voo.getTarifaBasica(); // Exemplo com tarifa básica

            System.out.println("Voo encontrado: ");
            System.out.println("Código: " + voo.getCodigoVoo());
            System.out.println("Horário de decolagem: " + voo.getDataHoraVoo());
            System.out.println("Duração do voo: " + duracaoVoo + " horas");
            System.out.println("Horário de chegada: " + horarioChegada);
            System.out.println("Valor da passagem: " + valorPassagem + " " + voo.getMoeda());

            log.salvarLog("Voo encontrado: " + voo.getCodigoVoo() + ", Horário de decolagem: "
                    + voo.getDataHoraVoo() + ", Duração: " + duracaoVoo + " horas, Chegada: " + horarioChegada
                    + ", Valor: "
                    + valorPassagem + " " + voo.getMoeda());
        }

        // Criando passageiro e realizando a compra
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        Voo vooEscolhido = voosDisponiveis.get(0); // Escolhendo o primeiro voo disponível

        // Adicionando o passageiro ao voo
        vooEscolhido.adicionarPassageiro(passageiro);

        // Adquirindo a passagem
        Passagem passagem = new Passagem(50.0);
        passagem.adicionarVoo(vooEscolhido);
        log.salvarLog("Passagem adquirida para o passageiro " + passageiro.getNome() + " no voo "
                + vooEscolhido.getCodigoVoo());

        // Gerando bilhete
        Bilhete bilhete = new Bilhete(passageiro, vooEscolhido);
        if (bilhete.emitir()) {
            System.out.println("Bilhete gerado com sucesso.");
            log.salvarLog("Bilhete gerado para o passageiro " + passageiro.getNome() + " no voo "
                    + vooEscolhido.getCodigoVoo());
        } else {
            System.out.println("Erro ao gerar bilhete.");
            log.salvarLog("Erro ao gerar bilhete para o passageiro " + passageiro.getNome());
        }

        // Realizando o check-in e embarque
        vooEscolhido.registrarEmbarque(passageiro.getDocumento());
        System.out.println("Passageiro " + passageiro.getNome() + " realizado check-in e embarque.");
        log.salvarLog("Passageiro " + passageiro.getNome() + " realizou check-in e embarque no voo "
                + vooEscolhido.getCodigoVoo());

        // Exibindo cartão de embarque
        System.out.println("Cartão de embarque para " + passageiro.getNome() + ": ");
        System.out.println(bilhete.cartaoDeEmbarque());
        log.salvarLog("Cartão de embarque exibido para o passageiro " + passageiro.getNome());
    }

    // Método para o cenário 2: Buscar voos com conexão, realizar operações de
    // compra e cancelamento
    public void cenario2() {
        Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla("GRU");
        Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla("GIG");
        LocalDateTime dataPesquisa = LocalDateTime.of(2024, 12, 2, 7, 0);

        // Pesquisa por voos com conexão
        List<List<Voo>> voosComConexao = vooManager.pesquisarVoosComConexao(origem, destino, dataPesquisa);

        if (voosComConexao.isEmpty()) {
            log.salvarLog("Nenhuma conexão encontrada entre " + origem.getSigla() + " e " + destino.getSigla()
                    + " na data " + dataPesquisa);
            System.out.println("Nenhuma conexão encontrada.");
            return;
        }

        // Exibindo dados dos voos com conexão
        System.out.println("Conexões encontradas:");
        for (List<Voo> conexao : voosComConexao) {
            System.out.println("Conexão:");
            for (Voo voo : conexao) {
                double duracaoVoo = voo.calcularTempoDeViagem();
                LocalDateTime horarioChegada = voo.calcularHorarioChegada();
                double valorPassagem = voo.getTarifaBasica();

                System.out.println("Código: " + voo.getCodigoVoo());
                System.out.println("Horário de decolagem: " + voo.getDataHoraVoo());
                System.out.println("Duração do voo: " + duracaoVoo + " horas");
                System.out.println("Horário de chegada: " + horarioChegada);
                System.out.println("Valor da passagem: " + valorPassagem + " " + voo.getMoeda());

                log.salvarLog("Voo com conexão encontrado: " + voo.getCodigoVoo() + ", Horário de decolagem: "
                        + voo.getDataHoraVoo() + ", Duração: " + duracaoVoo + " horas, Chegada: " + horarioChegada
                        + ", Valor: " + valorPassagem + " " + voo.getMoeda());
            }
        }

        // Criando passageiro VIP
        Passageiro passageiroVip = new Passageiro("Carlos", "Alves", "98765432101", "carlos@gmail.com");
        passageiroVip.setVipStatus(true);

        // Escolhendo a primeira conexão encontrada
        List<Voo> conexaoEscolhida = voosComConexao.get(0);
        Passagem passagemVip = new Passagem(100.0);
        for (Voo voo : conexaoEscolhida) {
            passagemVip.adicionarVoo(voo);
        }

        // Exibindo detalhamento de custos
        double custoComBagagens = passagemVip.calcularCustoTotalComBagagens(0); // VIP sem custo para bagagem
        System.out.println("Custo total da passagem (VIP): " + custoComBagagens + " BRL");
        log.salvarLog("Passagem adquirida para passageiro VIP: " + passageiroVip.getNome() + ", Custo total: "
                + custoComBagagens);

        // Gerando bilhete para VIP
        Bilhete bilheteVip = new Bilhete(passageiroVip, conexaoEscolhida.get(0));
        if (bilheteVip.emitir()) {
            System.out.println("Bilhete VIP gerado com sucesso.");
            log.salvarLog("Bilhete VIP gerado para passageiro: " + passageiroVip.getNome());
        } else {
            System.out.println("Erro ao gerar bilhete VIP.");
            log.salvarLog("Erro ao gerar bilhete VIP para passageiro: " + passageiroVip.getNome());
        }

        // Cancelando o primeiro voo da conexão
        Voo vooCancelado = conexaoEscolhida.get(0);
        vooCancelado.cancelar();
        passagemVip.setStatus(StatusPassagem.PASSAGEM_CANCELADA);
        System.out.println("Voo cancelado: " + vooCancelado.getCodigoVoo());
        System.out.println("Passagem cancelada.");
        log.salvarLog("Voo " + vooCancelado.getCodigoVoo() + " cancelado. Passagem do passageiro VIP "
                + passageiroVip.getNome() + " cancelada.");
    }

    public static void main(String[] args) {
        SistemaFacade sistema = new SistemaFacade();
        sistema.cadastroBasico();
        sistema.cenario1();
        sistema.cenario2();
    }
}
