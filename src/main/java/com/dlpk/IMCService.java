package com.dlpk;

import java.util.function.Predicate;

public class IMCService {
    public double calcularIMC(double peso, double altura) {
        return CalculoIMC.calcularPeso(peso, altura);
    }
}
