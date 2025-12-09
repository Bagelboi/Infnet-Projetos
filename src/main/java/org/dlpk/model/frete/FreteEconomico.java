package org.dlpk.model.frete;

public class FreteEconomico implements ICalculadoraFrete{
    public double calcular(double peso) {
        return Math.max(0, peso * 1.1 - 5);
    }

    public boolean tipoFreteCompativel(TIPO_FRETE tipoFrete) {
        return tipoFrete == TIPO_FRETE.ECONOMICO;
    }
}
