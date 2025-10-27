package com.dlpk;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
public class Gerador {

    @Provide
    Arbitrary<Double> doubles() {
        // Generates doubles in a reasonable range (-1_000_000 to 1_000_000)
        return Arbitraries.doubles().between(0, 0.5);
    }

    @Property
    void testLimitrofe(@ForAll("doubles") double altura) {
        // Example assertion: double squared is always non-negative
        Assertions.assertSame("Limitrofe", CalculoIMC.categoriaDeEntrada(0.5, altura));
    }
}