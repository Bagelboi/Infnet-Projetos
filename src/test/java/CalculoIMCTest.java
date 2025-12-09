package com.dlpk;
import org.dlpk.Exercicio1.CalculoIMC;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoIMCTest {

    @Test
    void RF04_OMS() {
        //valor limite
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(15.0));
        assertEquals("Magreza moderada", CalculoIMC.classificarIMC(16.0));
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(17.0));
        assertEquals("Saudável", CalculoIMC.classificarIMC(18.5));
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(25.0));
        assertEquals("Obesidade Grau I", CalculoIMC.classificarIMC(30.0));
        assertEquals("Obesidade Grau II", CalculoIMC.classificarIMC(35.0));
        assertEquals("Obesidade Grau III", CalculoIMC.classificarIMC(40.0));
    }

    @Test
    void RF03_IMC() {
        assertEquals(21.224, CalculoIMC.calcularPeso(65, 1.75), 0.01);
        assertEquals(27.7777, CalculoIMC.calcularPeso(90, 1.80), 0.01);
    }

    //@Test
    void RN01_NegativoNemZero() {
        assertThrows( IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(-20, -40) );
        assertThrows( IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(0, 0) );
        assertThrows( IllegalArgumentException.class, () -> CalculoIMC.classificarIMC(0) );
        assertThrows( IllegalArgumentException.class, () -> CalculoIMC.classificarIMC(-20) );
    }

}