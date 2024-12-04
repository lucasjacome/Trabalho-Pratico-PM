package Facade;

import Entidades.*;
import Managers.*;
import dao.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Facade {
    private VooManager vooManager;
    private PassageiroManager passageiroManager;
    private AeroportoManager aeroportoManager;
    private LogDAOImpl logDAO;
    private CompanhiaAereaManager companhiaAereaManager;
    private List<Aeronave> aeronaves;  // Lista de aeronaves

    public Facade() {
        vooManager = new VooManager();
        passageiroManager = new PassageiroManager();
        aeroportoManager = new AeroportoManager();
        companhiaAereaManager = new CompanhiaAereaManager();
        aeronaves = new ArrayList<>();  // Inicializando a lista de aeronaves
    }

    public VooManager getVooManager() {
        return vooManager;
    }

    public PassageiroManager getPassageiroManager() {
        return passageiroManager;
    }

    public void adicionarAeronave(Aeronave aeronave) {
        if (!aeronaves.contains(aeronave)) {
            aeronaves.add(aeronave);
        }
    }

    // Método para adicionar uma aeronave
    public void adicionarAeronave(String modelo, int capacidadeCarga, int capacidadePassageiros,
                                  int numeroFileiras, double velocidadeMedia) {
        Aeronave aeronave = new Aeronave(modelo, capacidadeCarga, capacidadePassageiros, numeroFileiras, velocidadeMedia);
        aeronaves.add(aeronave);
        logDAO.salvarLog("Aeronave adicionada: " + modelo);
    }

    // Método para listar todas as aeronaves
    public List<Aeronave> listarAeronaves() {
        return new ArrayList<>(aeronaves); // Retorna uma cópia da lista
    }

    // Método para buscar uma aeronave pelo modelo
    public Aeronave buscarAeronavePorModelo(String modelo) {
        for (Aeronave aeronave : aeronaves) {
            if (aeronave.getModelo().equalsIgnoreCase(modelo)) {
                return aeronave;
            }
        }
        throw new IllegalArgumentException("Aeronave com modelo " + modelo + " não encontrada.");
    }

    // Adicionar Aeroporto
    public void adicionarAeroporto(String nome, String sigla, String cidade, String estado, String pais,
                                    double latitude, double longitude) {
        Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais, latitude, longitude);
        aeroportoManager.adicionarAeroporto(aeroporto);
        logDAO.salvarLog("Aeroporto adicionado: " + nome);
    }

    // Adicionar Companhia Aérea
    public void adicionarCompanhiaAerea(String nome, String codigo, String razaoSocial, String cnpj,
                                        double valorPrimeiraBagagem, double valorBagagensAdicionais) {
        CompanhiaAerea companhia = new CompanhiaAerea(nome, codigo, razaoSocial, cnpj, valorPrimeiraBagagem, valorBagagensAdicionais);
        companhiaAereaManager.adicionarCompanhia(companhia);
        logDAO.salvarLog("Companhia Aérea adicionada: " + nome);
    }

    // Adicionar Passageiro
    public void adicionarPassageiro(String nome, String sobrenome, String documento, String email, boolean vipStatus) {
        Passageiro passageiro = new Passageiro(nome, sobrenome, documento, email);
        passageiro.setVipStatus(vipStatus); // Definir o status VIP
        passageiroManager.adicionarPassageiro(passageiro);
        logDAO.salvarLog("Passageiro adicionado: " + nome + " " + sobrenome);
    }

    // Adicionar Voo
    public void adicionarVoo(Aeroporto origem, Aeroporto destino, String codigoVoo, LocalDateTime dataHoraVoo, 
                             CompanhiaAerea companhia, Aeronave aeronave, double tarifaBasica, double tarifaBusiness,
                             double tarifaPremium, String moeda) {
        Voo voo = new Voo(origem, destino, dataHoraVoo, codigoVoo, companhia, aeronave, tarifaBasica, tarifaBusiness, tarifaPremium, moeda);
        vooManager.adicionarVoo(voo);
        logDAO.salvarLog("Voo adicionado: " + codigoVoo);
    }

    // Verificar e simular Voo
    public void simularVoo(String modeloAeronave, String codigoVoo, String documentoPassageiro) {
        try {
            Aeronave aeronave = buscarAeronavePorModelo(modeloAeronave);
            Voo voo = vooManager.buscarVooPorCodigo(codigoVoo);
            Passageiro passageiro = passageiroManager.buscarPassageiroPorDocumento(documentoPassageiro);

            if (voo != null && aeronave != null) {
                System.out.println("Simulando voo: " + voo.getCodigoVoo());
                // Implementar a lógica da simulação do voo, incluindo embarque, reserva de assentos, etc.
            }
        } catch (Exception e) {
            System.err.println("Erro ao simular voo: " + e.getMessage());
        }
    }

    // Outros métodos necessários para gerenciar voos, passageiros, aeroportos, etc.
}
