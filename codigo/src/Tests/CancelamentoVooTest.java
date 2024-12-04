package Tests;

import Entidades.CancelamentoVoo;
import Entidades.Voo;
import Entidades.Aeroporto;
import Entidades.CompanhiaAerea;
import Entidades.Aeronave;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CancelamentoVooTest {

    @Test
    void testAdicionarVoo() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo = criarVoo();
        gerenciador.adicionarVoo(voo);
        assertEquals(1, gerenciador.listarTodosVoos().size());
    }

    @Test
    void testAdicionarVooNulo() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> gerenciador.adicionarVoo(null));
        assertEquals("O voo não pode ser nulo.", exception.getMessage());
    }

    @Test
    void testCancelarVoo() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo = criarVoo();
        gerenciador.adicionarVoo(voo);
        assertTrue(gerenciador.cancelarVoo("XY123"));
        assertTrue(voo.isCancelado());
    }

    @Test
    void testCancelarVooInexistente() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo = criarVoo();
        gerenciador.adicionarVoo(voo);
        assertFalse(gerenciador.cancelarVoo("XY999"));
    }

    @Test
    void testReverterCancelamento() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo = criarVoo();
        gerenciador.adicionarVoo(voo);
        gerenciador.cancelarVoo("XY123");
        assertTrue(gerenciador.reverterCancelamento("XY123"));
        assertFalse(voo.isCancelado());
    }

    @Test
    void testReverterCancelamentoInexistente() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo = criarVoo();
        gerenciador.adicionarVoo(voo);
        assertFalse(gerenciador.reverterCancelamento("XY999"));
    }

    @Test
    void testListarVoosCancelados() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo1 = criarVoo();
        Voo voo2 = criarVoo("XY124", "Aeroporto do Rio");
        gerenciador.adicionarVoo(voo1);
        gerenciador.adicionarVoo(voo2);
        gerenciador.cancelarVoo("XY123");
        List<Voo> voosCancelados = gerenciador.listarVoosCancelados();
        assertEquals(1, voosCancelados.size());
        assertEquals("XY123", voosCancelados.get(0).getCodigoVoo());
    }

    @Test
    void testListarTodosVoos() {
        CancelamentoVoo gerenciador = new CancelamentoVoo();
        Voo voo1 = criarVoo();
        Voo voo2 = criarVoo("XY124", "Aeroporto do Rio");
        gerenciador.adicionarVoo(voo1);
        gerenciador.adicionarVoo(voo2);
        List<Voo> todosVoos = gerenciador.listarTodosVoos();
        assertEquals(2, todosVoos.size());
    }

    private Voo criarVoo() {
        return criarVoo("XY123", "Aeroporto de São Paulo");
    }

    private Voo criarVoo(String codigoVoo, String origemNome) {
        Aeroporto origem = new Aeroporto(origemNome, "GRU", "São Paulo", "SP", "Brasil");
        Aeroporto destino = new Aeroporto("Aeroporto do Rio", "GIG", "Rio de Janeiro", "RJ", "Brasil");
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ", "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.now().plusDays(1), codigoVoo, companhia, aeronave, 500.0, 1000.0, 1500.0, "BRL");
    }
}
