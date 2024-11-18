package Tests;

import Entidades.Passageiro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PassageiroTest {

    @Test
    public void testPassageiroNomeSobrenomeDocumento() {
        Passageiro passageiro = new Passageiro("Ana", "Moura", "12345678901", "ana.moura@gmail.com");
        Assertions.assertEquals("Ana", passageiro.getNome());
        Assertions.assertEquals("Moura", passageiro.getSobrenome());
        Assertions.assertEquals("12345678901", passageiro.getDocumento());
        Assertions.assertEquals("ana.moura@gmail.com", passageiro.getEmail());
    }
}