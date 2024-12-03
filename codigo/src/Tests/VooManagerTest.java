package Tests;

import Entidades.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VooManagerTest {

    @Test
    void testAdicionarVoo() {
        VooManager manager = new VooManager();
        Voo voo = criarVoo();
        manager.adicionarVoo(voo);
        assertEquals(1, manager.listarTodosOsVoos().size());
    }

    @Test
    void testPesquisarVoos() {
        VooManager manager = new VooManager();
        Voo voo = criarVoo();
        manager.adicionarVoo(voo);

        List<Voo> encontrados = manager.pesquisarVoos(
                voo.getOrigem(), voo.getDestino(), voo.getDataHoraVoo().toLocalDate());

        assertEquals(1, encontrados.size());
        assertEquals(voo, encontrados.get(0));
    }

    @Test
    void testPesquisarVoosIdaVolta() {
        VooManager manager = new VooManager();
        Voo ida = criarVoo();
        Voo volta = criarVooVolta();
        manager.adicionarVoo(ida);
        manager.adicionarVoo(volta);

        List<Voo> encontrados = manager.pesquisarVoosIdaVolta(
                ida.getOrigem(), ida.getDestino(),
                ida.getDataHoraVoo().toLocalDate(),
                volta.getDataHoraVoo().toLocalDate());

        assertEquals(2, encontrados.size());
        assertTrue(encontrados.contains(ida));
        assertTrue(encontrados.contains(volta));
    }

    @Test
    void testPesquisarVoosComConexao() {
        VooManager manager = new VooManager();
        Voo voo1 = criarVoo();
        Voo conexao = criarConexao();
        manager.adicionarVoo(voo1);
        manager.adicionarVoo(conexao);

        List<List<Voo>> encontrados = manager.pesquisarVoosComConexao(
                voo1.getOrigem(), conexao.getDestino(), voo1.getDataHoraVoo().toLocalDate());

        assertEquals(1, encontrados.size());
        assertEquals(voo1, encontrados.get(0).get(0));
        assertEquals(conexao, encontrados.get(0).get(1));
    }

    @Test
    void testAcessarHistoricoVoos() {
        VooManager manager = new VooManager();
        Voo voo = criarVoo();
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        voo.adicionarPassageiro(passageiro);
        manager.adicionarVoo(voo);

        List<Voo> historico = manager.acessarHistoricoVoos(passageiro);

        assertEquals(1, historico.size());
        assertEquals(voo, historico.get(0));
    }

    private Voo criarVoo() {
        Aeroporto origem = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia, aeronave, 500.0,
                1000.0, 1500.0, "BRL");
    }

    private Voo criarVooVolta() {
        Aeroporto origem = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil");
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.of(2024, 12, 30, 14, 0), "XYZ456", companhia, aeronave, 500.0,
                1000.0, 1500.0, "BRL");
    }

    private Voo criarConexao() {
        Aeroporto origem = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto MG", "CNF", "Belo Horizonte", "MG", "Brasil");
        CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.of(2024, 12, 25, 18, 0), "XYZ789", companhia, aeronave, 500.0,
                1000.0, 1500.0, "BRL");
    }
}
