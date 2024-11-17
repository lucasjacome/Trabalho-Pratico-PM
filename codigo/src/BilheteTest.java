import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class BilheteTest {

    @Test
    public void testEmitirBilheteNacional() {
        // Configurar voo nacional
        Voo vooNacional = new Voo(
                new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                new Aeroporto("Aeroporto do Rio de Janeiro", "GIG", "Rio de Janeiro", "RJ", "Brasil"),
                LocalDateTime.of(2024, 10, 1, 15, 30),
                "XY1234",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA", "12345678000195", 50.0,
                        30.0),
                new Aeronave("Boeing 747", 350, 400, 66),
                500.0,
                1000.0,
                1500.0,
                "BRL");

        // Configurar passageiro com CPF válido
        Passageiro passageiroNacional = new Passageiro("João", "Silva", "12345678901", "silvajoao@gmail.com");

        // Emitir bilhete
        Bilhete bilhete = new Bilhete(passageiroNacional, vooNacional);
        assertTrue(bilhete.emitir(), "Bilhete nacional deve ser emitido com sucesso");
    }

    @Test
    public void testEmitirBilheteInternacionalValido() {
        // Configurar voo internacional
        Voo vooInternacional = new Voo(
                new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                new Aeroporto("Aeroporto de Nova York", "JFK", "Nova York", "NY", "EUA"),
                LocalDateTime.of(2024, 12, 1, 15, 00),
                "XY9999",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA", "12345678000195", 50.0,
                        30.0),
                new Aeronave("Boeing 747", 350, 400, 66),
                1200.0,
                2500.0,
                3500.0,
                "USD");

        // Configurar passageiro com passaporte válido
        Passageiro passageiroInternacional = new Passageiro("Maria", "Pereira", "AA123456", "mariapereira@gmail.com");

        // Emitir bilhete
        Bilhete bilhete = new Bilhete(passageiroInternacional, vooInternacional);
        assertTrue(bilhete.emitir(), "Bilhete internacional com passaporte válido deve ser emitido com sucesso");
    }

    @org.junit.Test
    @Test
    public void testEmitirBilheteInternacionalInvalido() {
        // Configurar voo internacional
        Voo vooInternacional = new Voo(
                new Aeroporto("Aeroporto de São Paulo", "GRU", "São Paulo", "SP", "Brasil"),
                new Aeroporto("Aeroporto de Nova York", "JFK", "Nova York", "NY", "EUA"),
                LocalDateTime.of(2024, 12, 1, 15, 00),
                "XY9999",
                new CompanhiaAerea("Companhia Aérea XYZ", "XYZ123", "Razão Social XYZ LTDA", "12345678000195", 50.0,
                        30.0),
                new Aeronave("Boeing 747", 350, 400, 66),
                1200.0,
                2500.0,
                3500.0,
                "USD");

        // Configurar passageiro com passaporte inválido
        Passageiro passageiroInternacional = new Passageiro("Carlos", "Souza", "A1234567", "souzacarlos@gmail.com");

        // Emitir bilhete
        Bilhete bilhete = new Bilhete(passageiroInternacional, vooInternacional);
        assertFalse(bilhete.emitir(), "Bilhete internacional com passaporte inválido não deve ser emitido");
    }
}
