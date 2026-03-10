package org.dlpk.calculadora_web;

public class Calculadora {
    public int soma(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return soma(a, b * -1);
    }

    public int multiplicar(int a, int b) {
        return a * b; //h
    }

    public int dividir(int a, int b) {
        if (b == 0)
            throw new RuntimeException("Divisão por zero!");
        return a / b;
    }
}
