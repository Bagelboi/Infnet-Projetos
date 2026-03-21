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

    public int dividir(int a, int b) { //hi dude
        if (b == 0)
            throw new RuntimeException("Divisão por zero!");
        return a / b;
    }

    public double sqrt(double a) { //hi dude
        if (a < 0)
            throw new RuntimeException("Raiz quadrada negativa complexa demais!");
        return Math.sqrt( a );
    }
}
