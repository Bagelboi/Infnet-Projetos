package com.dlpk;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;

public class CalculoIMCPropertyTests {



    @Property
    void imcNuncaDeveSerNegativo(@ForAll @Positive double peso, @ForAll @Positive double altura) {
        assertTrue(CalculoIMC.calcularPeso(peso, altura) >= 0);
    }

    @Property
    void imcNuncaDeveSerNegativoRuim(@ForAll double peso, @ForAll double altura) {
        assertTrue(CalculoIMC.calcularPeso(peso, altura) >= 0);
    }
}
