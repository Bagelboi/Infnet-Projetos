package org.dlpk.calculadora_web;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    Calculadora calc = new Calculadora();

    @Test
    public void somaTest() {
        assertEquals( calc.soma(2,2), 4 );
        assertEquals( calc.soma(2, -2), 0 );
        assertEquals( calc.soma( -2, -2 ), -4 );
    }

    @Test
    public void subtrairTest() {
        assertEquals( calc.subtrair(2,2), 0 );
        assertEquals( calc.subtrair(2, -2), 4 );
        assertEquals( calc.subtrair( -2, -2 ), 0 );
    }

    @Test
    public void multTest() {
        assertEquals( calc.multiplicar(2,2), 4 );
        assertEquals( calc.multiplicar(2, -2), -4 );
        assertEquals( calc.multiplicar( -2, -2 ), 4 );
    }

    @Test
    public void divTest() {
        assertEquals( calc.dividir(2,2), 1 );
        assertEquals( calc.dividir(2, -2), -1 );
        assertEquals( calc.dividir( -2, -2 ), 1 );
        assertThrows( RuntimeException.class, () -> calc.dividir(2, 0) );
    }

}
