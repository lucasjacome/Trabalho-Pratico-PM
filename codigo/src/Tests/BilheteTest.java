package Tests;

import Entidades.Bilhete;
import Entidades.Passageiro;
import Entidades.Voo;
import Entidades.Aeroporto;
import Entidades.CompanhiaAerea;
import Entidades.Aeronave;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BilheteTest {

    @Test
    void testConstrutorValido() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        Voo voo = criarVooNacional();
        Bilhete bilhete = new Bilhete(passageiro, voo);
        assertEquals(passageiro, bilhete.getPassageiro());
        assertEquals(voo, bilhete.getVoo());
    }

    @Test
    void testConstrutorInvalido() {
        Voo voo = criarVooNacional();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Bilhete(null, voo));
        assertEquals("Passageiro e voo não podem ser nulos.", exception.getMessage());
    }

    @Test
    void testEmitirBilheteNacionalValido() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        Voo voo = criarVooNacional();
        Bilhete bilhete = new Bilhete(passageiro, voo);
        assertTrue(bilhete.emitir());
    }

    @Test
    void testEmitirBilheteNacionalInvalido() {
        Passageiro passageiro = new Passageiro("Maria", "Oliveira", "123", "maria@gmail.com");
        Voo voo = criarVooNacional();
        Bilhete bilhete = new Bilhete(passageiro, voo);
        assertFalse(bilhete.emitir());
    }

    @Test
    void testEmitirBilheteInternacionalValido() {
        Passageiro passageiro = new Passageiro("Ana", "Pereira", "AB123456", "ana@gmail.com");
        Voo voo = criarVooInternacional();
        Bilhete bilhete = new Bilhete(passageiro, voo);
        assertTrue(bilhete.emitir());
    }

    @Test
    void testEmitirBilheteInternacionalInvalido() {
        Passageiro passageiro = new Passageiro("Carlos", "Santos", "A1234567", "carlos@gmail.com");
        Voo voo = criarVooInternacional();
        Bilhete bilhete = new Bilhete(passageiro, voo);
        assertFalse(bilhete.emitir());
    }

    @Test
    void testToString() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        Voo voo = criarVooNacional();
        Bilhete bilhete = new Bilhete(passageiro, voo);
        assertEquals(String.format("Bilhete [Passageiro: %s, Voo: %s]", passageiro, voo), bilhete.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Passageiro passageiro = new Passageiro("João", "Silva", "12345678901", "joao@gmail.com");
        Voo voo = criarVooNacional();
        Bilhete bilhete1 = new Bilhete(passageiro, voo);
        Bilhete bilhete2 = new Bilhete(passageiro, voo);
        assertEquals(bilhete1, bilhete2);
        assertEquals(bilhete1.hashCode(), bilhete2.hashCode());
    }

    private Voo criarVooNacional() {
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        Aeroporto destino = new Aeroporto("Aeroporto do Rio", "GIG", "Rio de Janeiro", "RJ", "Brasil", -22.9094,
                -43.1737);
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ",
                "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 737", 20000, 180, 30);
        return new Voo(origem, destino, LocalDateTime.now().plusDays(1), "XY123", companhia, aeronave, 500.0, 1000.0,
                1500.0, "BRL");
    }

    private Voo criarVooInternacional() {
        Aeroporto origem = new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil", -23.5505,
                -46.6333);
        Aeroporto destino = new Aeroporto("Aeroporto JFK", "JFK", "Nova York", "NY", "EUA", 40.6413, -73.7781);
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ",
                "12345678000123", 50.0, 30.0);
        Aeronave aeronave = new Aeronave("Boeing 747", 30000, 400, 50);
        return new Voo(origem, destino, LocalDateTime.now().plusDays(1), "XY456", companhia, aeronave, 1200.0, 2500.0,
                3500.0, "USD");
    }
}
