import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PassageiroTest {

    @Test
    public void testPassageiroNomeSobrenomeDocumento() {
        Passageiro passageiro = new Passageiro("Ana", "Moura", "12345678901", "ana.moura@gmail.com");
        assertEquals("Ana", passageiro.getNome());
        assertEquals("Moura", passageiro.getSobrenome());
        assertEquals("12345678901", passageiro.getDocumento());
        assertEquals("ana.moura@gmail.com", passageiro.getEmail());
    }
}