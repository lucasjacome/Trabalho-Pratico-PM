package Tests;

import Entidades.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VooManagerTest {

        @Test
        void testAdicionarVoo() {
                VooManager vooManager = new VooManager();
                Aeroporto aeroporto1 = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                                -46.6333);
                Aeroporto aeroporto2 = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                                -43.1737);
                CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123",
                                50.0, 30.0);
                Voo voo = new Voo(aeroporto1, aeroporto2, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia,
                                new Aeronave("Boeing 737", 20000, 180, 30, 850.0), 500.0, 1000.0, 1500.0, "BRL");

                vooManager.adicionarVoo(voo);

                assertEquals(1, vooManager.listarTodosOsVoos().size());
        }

        @Test
        void testPesquisarVoos() {
                VooManager vooManager = new VooManager();
                Aeroporto aeroporto1 = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                                -46.6333);
                Aeroporto aeroporto2 = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                                -43.1737);
                CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123",
                                50.0, 30.0);
                Voo voo = new Voo(aeroporto1, aeroporto2, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia,
                                new Aeronave("Boeing 737", 20000, 180, 30, 850.0), 500.0, 1000.0, 1500.0, "BRL");

                vooManager.adicionarVoo(voo);

                LocalDateTime dataHoraPesquisa = LocalDateTime.of(2024, 12, 25, 14, 0);
                var voosEncontrados = vooManager.pesquisarVoos(aeroporto1, aeroporto2, dataHoraPesquisa);

                assertEquals(1, voosEncontrados.size());
                assertEquals("XYZ123", voosEncontrados.get(0).getCodigoVoo());
        }

        @Test
        void testPesquisarVoosComConexao() {
                VooManager vooManager = new VooManager();
                Aeroporto aeroporto1 = new Aeroporto("Aeroporto SP", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                                -46.6333);
                Aeroporto aeroporto2 = new Aeroporto("Aeroporto RJ", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                                -43.1737);
                Aeroporto aeroporto3 = new Aeroporto("Aeroporto BH", "CNF", "Belo Horizonte", "MG", "Brasil", -19.8522,
                                -43.9714);

                CompanhiaAerea companhia = new CompanhiaAerea("XYZ Airlines", "XYZ", "Razão XYZ", "12345678000123",
                                50.0, 30.0);

                Voo voo1 = new Voo(aeroporto1, aeroporto2, LocalDateTime.of(2024, 12, 25, 14, 0), "XYZ123", companhia,
                                new Aeronave("Boeing 737", 20000, 180, 30, 850.0), 500.0, 1000.0, 1500.0, "BRL");
                Voo voo2 = new Voo(aeroporto2, aeroporto3, LocalDateTime.of(2024, 12, 25, 18, 0), "XYZ456", companhia,
                                new Aeronave("Boeing 737", 20000, 180, 30, 850.0), 500.0, 1000.0, 1500.0, "BRL");

                vooManager.adicionarVoo(voo1);
                vooManager.adicionarVoo(voo2);

                LocalDateTime dataHoraPesquisa = LocalDateTime.of(2024, 12, 25, 14, 0);
                var conexoes = vooManager.pesquisarVoosComConexao(aeroporto1, aeroporto3, dataHoraPesquisa);

                assertEquals(1, conexoes.size());
                assertEquals("XYZ123", conexoes.get(0).get(0).getCodigoVoo());
                assertEquals("XYZ456", conexoes.get(0).get(1).getCodigoVoo());
        }
}
