package Tests;

import Entidades.CompanhiaAerea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanhiaAereaTest {

    @Test
    void testConstrutorValido() {
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ LTDA",
                "12345678000123", 50.0, 30.0);
        assertEquals("Companhia Aérea XYZ", companhia.getNome());
        assertEquals("XYZ", companhia.getCodigo());
        assertEquals("Razão Social XYZ LTDA", companhia.getRazaoSocial());
        assertEquals("12345678000123", companhia.getCnpj());
        assertEquals(50.0, companhia.getValorPrimeiraBagagem());
        assertEquals(30.0, companhia.getValorBagagensAdicionais());
        assertTrue(companhia.isVipBeneficioAtivo());
    }

    @Test
    void testConstrutorInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new CompanhiaAerea("", "XYZ", "Razão Social XYZ LTDA", "12345678000123", 50.0, 30.0));
        assertEquals("Dados inválidos para criação da companhia aérea.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "", "12345678000123", 50.0, 30.0));
        assertEquals("Dados inválidos para criação da companhia aérea.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ LTDA", "", 50.0, 30.0));
        assertEquals("Dados inválidos para criação da companhia aérea.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class,
                () -> new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ LTDA", "12345678000123", -10.0,
                        30.0));
        assertEquals("Dados inválidos para criação da companhia aérea.", exception.getMessage());
    }

    @Test
    void testSetVipBeneficioAtivo() {
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ LTDA",
                "12345678000123", 50.0, 30.0);
        companhia.setVipBeneficioAtivo(false);
        assertFalse(companhia.isVipBeneficioAtivo());
        companhia.setVipBeneficioAtivo(true);
        assertTrue(companhia.isVipBeneficioAtivo());
    }

    @Test
    void testToString() {
        CompanhiaAerea companhia = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ LTDA",
                "12345678000123", 50.0, 30.0);
        String esperado = "Companhia Aérea: Companhia Aérea XYZ [XYZ], Razão Social: Razão Social XYZ LTDA, CNPJ: 12345678000123, Valor Primeira Bagagem: 50,00, Valor Bagagens Adicionais: 30,00, Benefício VIP: Ativo";
        assertEquals(esperado, companhia.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        CompanhiaAerea companhia1 = new CompanhiaAerea("Companhia Aérea XYZ", "XYZ", "Razão Social XYZ LTDA",
                "12345678000123", 50.0, 30.0);
        CompanhiaAerea companhia2 = new CompanhiaAerea("Companhia Aérea ABC", "ABC", "Razão Social ABC LTDA",
                "12345678000123", 60.0, 35.0);
        CompanhiaAerea companhia3 = new CompanhiaAerea("Companhia Aérea DEF", "DEF", "Razão Social DEF LTDA",
                "98765432000198", 40.0, 20.0);

        assertEquals(companhia1, companhia2);
        assertNotEquals(companhia1, companhia3);
        assertEquals(companhia1.hashCode(), companhia2.hashCode());
        assertNotEquals(companhia1.hashCode(), companhia3.hashCode());
    }
}
