package Facade;

import Entidades.*;
import java.time.LocalDateTime;
import java.util.List;

public class Facade {

    private AeroportoManager aeroportoManager;
    private CompanhiaAereaManager companhiaAereaManager;
    private VooManager vooManager;
    private PassageiroManager passageiroManager;
    private FuncionarioManager funcionarioManager;

    public Facade() {
        this.aeroportoManager = new AeroportoManager();
        this.companhiaAereaManager = new CompanhiaAereaManager();
        this.vooManager = new VooManager();
        this.passageiroManager = new PassageiroManager();
        this.funcionarioManager = new FuncionarioManager();
    }

    public boolean registrarAeroporto(String nome, String sigla, String cidade, String estado, String pais, double latitude, double longitude) {
        Aeroporto aeroporto = new Aeroporto(nome, sigla, cidade, estado, pais, latitude, longitude);
        return aeroportoManager.adicionarAeroporto(aeroporto);
    }

    public boolean registrarCompanhia(String nome, String codigo, String razaoSocial, String cnpj, double valorPrimeiraBagagem, double valorBagagensAdicionais) {
        CompanhiaAerea companhia = new CompanhiaAerea(nome, codigo, razaoSocial, cnpj, valorPrimeiraBagagem, valorBagagensAdicionais);
        return companhiaAereaManager.adicionarCompanhia(companhia);
    }

    public void adicionarVoo(String codigoVoo, Aeroporto origem, Aeroporto destino, LocalDateTime dataHoraVoo, 
                             CompanhiaAerea companhia, Aeronave aeronave, double tarifaBasica, double tarifaBusiness, 
                             double tarifaPremium, String moeda) {
        Voo voo = new Voo(origem, destino, dataHoraVoo, codigoVoo, companhia, aeronave, tarifaBasica, tarifaBusiness, tarifaPremium, moeda);
        vooManager.adicionarVoo(voo);
    }

    public boolean registrarPassageiro(String nome, String sobrenome, String documento, String email) {
        Passageiro passageiro = new Passageiro(nome, sobrenome, documento, email);
        return passageiroManager.adicionarPassageiro(passageiro);
    }

    public boolean registrarFuncionario(String nome, String cpf, String email, String usuario, String senha) {
        Funcionario funcionario = new Funcionario(nome, cpf, email, usuario, senha);
        return funcionarioManager.adicionarFuncionario(funcionario);
    }

    public List<Aeroporto> listarAeroportos() {
        return aeroportoManager.listarAeroportos();
    }

    public List<CompanhiaAerea> listarCompanhias() {
        return companhiaAereaManager.listarCompanhias();
    }

    public List<Voo> listarVoos() {
        return vooManager.listarTodosOsVoos();
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroManager.listarPassageiros();
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioManager.listarFuncionarios();
    }

    public Voo pesquisarVoo(String origemSigla, String destinoSigla, LocalDateTime dataHora) {
        Aeroporto origem = aeroportoManager.buscarAeroportoPorSigla(origemSigla);
        Aeroporto destino = aeroportoManager.buscarAeroportoPorSigla(destinoSigla);
        return vooManager.pesquisarVoos(origem, destino, dataHora).stream().findFirst().orElse(null);
    }

    
}
