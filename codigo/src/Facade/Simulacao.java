package Facade;

import Entidades.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Simulacao {

    private Facade facade;
    private List<Aeroporto> aeroportos;
    private List<CompanhiaAerea> companhiasAereas;
    private List<Aeronave> aeronaves;
    private List<Passageiro> passageiros;

    public Simulacao() {
        this.facade = new Facade();  // Inicializa a Facade para gerenciar todas as entidades
        this.aeroportos = new ArrayList<>();
        this.companhiasAereas = new ArrayList<>();
        this.aeronaves = new ArrayList<>();
        this.passageiros = new ArrayList<>();
    }

    // Método para realizar o cadastro básico
    public void realizarCadastroBasico() {
        // Cadastrar aeroportos
        Aeroporto aeroporto1 = new Aeroporto("Aeroporto Internacional de São Paulo", "GRU", "São Paulo", "SP", "Brasil", -23.4356, -46.4731);
        Aeroporto aeroporto2 = new Aeroporto("Aeroporto Internacional do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9119, -43.1632);
        facade.adicionarAeroporto(aeroporto1.getNome(), aeroporto1.getSigla(), aeroporto1.getCidade(), aeroporto1.getEstado(), aeroporto1.getPais(), aeroporto1.getLatitude(), aeroporto1.getLongitude());
        facade.adicionarAeroporto(aeroporto2.getNome(), aeroporto2.getSigla(), aeroporto2.getCidade(), aeroporto2.getEstado(), aeroporto2.getPais(), aeroporto2.getLatitude(), aeroporto2.getLongitude());

        // Cadastrar companhias aéreas
        CompanhiaAerea companhiaAerea1 = new CompanhiaAerea("Companhia Aérea Azul", "AZ", "Azul Linhas Aéreas", "12.345.678/0001-90", 30.0, 50.0);
        CompanhiaAerea companhiaAerea2 = new CompanhiaAerea("Companhia Aérea Gol", "G3", "Gol Linhas Aéreas", "23.456.789/0001-11", 35.0, 55.0);
        facade.adicionarCompanhiaAerea(companhiaAerea1.getNome(), companhiaAerea1.getCodigo(), companhiaAerea1.getRazaoSocial(), companhiaAerea1.getCnpj(), companhiaAerea1.getValorPrimeiraBagagem(), companhiaAerea1.getValorBagagensAdicionais());
        facade.adicionarCompanhiaAerea(companhiaAerea2.getNome(), companhiaAerea2.getCodigo(), companhiaAerea2.getRazaoSocial(), companhiaAerea2.getCnpj(), companhiaAerea2.getValorPrimeiraBagagem(), companhiaAerea2.getValorBagagensAdicionais());

        // Cadastrar aeronaves
        Aeronave aeronave1 = new Aeronave("Boeing 737", 20000, 180, 30, 850.0);
        Aeronave aeronave2 = new Aeronave("Airbus A320", 19000, 170, 28, 840.0);
        facade.adicionarAeronave(aeronave1.getModelo(), aeronave1.getCapacidadeCarga(), aeronave1.getCapacidadePassageiros(), aeronave1.getNumeroFileiras(), aeronave1.getVelocidadeMedia());
        facade.adicionarAeronave(aeronave2.getModelo(), aeronave2.getCapacidadeCarga(), aeronave2.getCapacidadePassageiros(), aeronave2.getNumeroFileiras(), aeronave2.getVelocidadeMedia());

        // Cadastrar passageiros
        Passageiro passageiroVip = new Passageiro("Maria", "Oliveira", "98765432101", "maria@gmail.com");
        passageiroVip.setVipStatus(true);
        Passageiro passageiroComum = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        facade.adicionarPassageiro(passageiroComum.getNome(), passageiroComum.getSobrenome(), passageiroComum.getDocumento(), passageiroComum.getEmail(), false);
        facade.adicionarPassageiro(passageiroVip.getNome(), passageiroVip.getSobrenome(), passageiroVip.getDocumento(), passageiroVip.getEmail(), true);

        // Cadastrar voos (de São Paulo para Rio de Janeiro)
        LocalDateTime dataHoraVoo1 = LocalDateTime.now().plusDays(2);  // Voo 2 dias após a data atual
        facade.adicionarVoo(aeroporto1, aeroporto2, "AZ1234", dataHoraVoo1, companhiaAerea1, aeronave1, 500.0, 800.0, 1200.0, "BRL");

        LocalDateTime dataHoraVoo2 = LocalDateTime.now().plusDays(3);  // Voo 3 dias após a data atual
        facade.adicionarVoo(aeroporto1, aeroporto2, "GOL5678", dataHoraVoo2, companhiaAerea2, aeronave2, 400.0, 600.0, 900.0, "BRL");
    }

    // Método para simular a viagem de um passageiro em um voo
    public void simularViagem(String modeloAeronave, String codigoVoo, String documentoPassageiro) {
        try {
            Aeronave aeronave = facade.buscarAeronavePorModelo(modeloAeronave);
            Voo voo = facade.getVooManager().buscarVooPorCodigo(codigoVoo);  // Usando o getter para acessar vooManager
            Passageiro passageiro = facade.getPassageiroManager().buscarPassageiroPorDocumento(documentoPassageiro);  // Usando o getter para acessar passageiroManager
    
            if (voo != null && aeronave != null && passageiro != null) {
                // Realizar o processo de simulação: check-in, alocação de assento, etc.
                System.out.println("Simulando voo: " + voo.getCodigoVoo());
                List<String> assentosDisponiveis = aeronave.gerarAssentos();
                System.out.println("Assentos disponíveis: " + assentosDisponiveis);
    
                // Atribuir um assento ao passageiro
                if (!assentosDisponiveis.isEmpty()) {
                    String assento = assentosDisponiveis.get(0);  // Assumindo que o passageiro vai pegar o primeiro assento disponível
                    System.out.println("Passageiro " + passageiro.getNome() + " alocado no assento: " + assento);
                    // Lógica adicional de embarque pode ser colocada aqui
                } else {
                    System.out.println("Não há assentos disponíveis no voo.");
                }
            } else {
                System.out.println("Não foi possível encontrar o voo ou o passageiro.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao simular viagem: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Simulacao simulacao = new Simulacao();

        // Realiza o cadastro básico de aeroportos, companhias aéreas, aeronaves, passageiros e voos
        simulacao.realizarCadastroBasico();

        // Simular a viagem de um passageiro VIP
        simulacao.simularViagem("Boeing 737", "AZ1234", "98765432101");

        // Simular a viagem de um passageiro comum
        simulacao.simularViagem("Airbus A320", "GOL5678", "12345678901");
    }
}
