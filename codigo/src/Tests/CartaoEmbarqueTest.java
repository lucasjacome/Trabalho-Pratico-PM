package Tests;

import Entidades.CartaoEmbarque;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CartaoEmbarqueTest {

    @Test
    void testConstrutorValido() {
        LocalDateTime horarioVoo = LocalDateTime.of(2024, 12, 1, 15, 0);
        CartaoEmbarque cartao = new CartaoEmbarque("João", "Silva", "São Paulo", "Rio de Janeiro", horarioVoo, "12A");
        assertEquals("João", cartao.getNome());
        assertEquals("Silva", cartao.getSobrenome());
        assertEquals("São Paulo", cartao.getOrigem());
        assertEquals("Rio de Janeiro", cartao.getDestino());
        assertEquals(horarioVoo.minusMinutes(40), cartao.getHorarioEmbarque());
        assertEquals(horarioVoo, cartao.getHorarioVoo());
        assertEquals("12A", cartao.getAssento());
    }

    @Test
    void testConstrutorInvalido() {
        LocalDateTime horarioVoo = LocalDateTime.of(2024, 12, 1, 15, 0);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new CartaoEmbarque("", "Silva", "São Paulo", "Rio de Janeiro", horarioVoo, "12A"));
        assertEquals("Todos os campos são obrigatórios e devem ser válidos.", exception.getMessage());
    }

    @Test
    void testToString() {
        LocalDateTime horarioVoo = LocalDateTime.of(2024, 12, 1, 15, 0);
        CartaoEmbarque cartao = new CartaoEmbarque("João", "Silva", "São Paulo", "Rio de Janeiro", horarioVoo, "12A");
        String esperado = "Cartão de Embarque:\n" +
                "Nome: João Silva\n" +
                "Origem: São Paulo\n" +
                "Destino: Rio de Janeiro\n" +
                "Assento: 12A\n" +
                "Horário de Embarque: 01/12/2024 14:20\n" +
                "Horário do Voo: 01/12/2024 15:00\n";
        assertEquals(esperado, cartao.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime horarioVoo = LocalDateTime.of(2024, 12, 1, 15, 0);
        CartaoEmbarque cartao1 = new CartaoEmbarque("João", "Silva", "São Paulo", "Rio de Janeiro", horarioVoo, "12A");
        CartaoEmbarque cartao2 = new CartaoEmbarque("João", "Silva", "São Paulo", "Rio de Janeiro", horarioVoo, "12A");
        CartaoEmbarque cartao3 = new CartaoEmbarque("Maria", "Oliveira", "São Paulo", "Salvador", horarioVoo, "3B");

        assertEquals(cartao1, cartao2);
        assertNotEquals(cartao1, cartao3);
        assertEquals(cartao1.hashCode(), cartao2.hashCode());
        assertNotEquals(cartao1.hashCode(), cartao3.hashCode());
    }
}
