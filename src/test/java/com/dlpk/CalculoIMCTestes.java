package com.dlpk;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoIMCTestes {

    @Test
    void testClassificarIMC() {
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
    void testCalcularPeso() {
        assertEquals(21.224, CalculoIMC.calcularPeso(65, 1.75), 0.01);
        assertEquals(27.7777, CalculoIMC.calcularPeso(90, 1.80), 0.01);
    }

    @Test
    void testCategoriaDeEntrada() {
        assertEquals("Inválida", CalculoIMC.categoriaDeEntrada(-1, 1.75));
        assertEquals("Inválida", CalculoIMC.categoriaDeEntrada(65, -1));
        assertEquals("Limitrofe", CalculoIMC.categoriaDeEntrada(0, 1.75));
        assertEquals("Limitrofe", CalculoIMC.categoriaDeEntrada(65, 0.5));
        assertEquals("Válida", CalculoIMC.categoriaDeEntrada(65, 1.75));
    }
}