package org.dlpk.model.frete;

public class FretePadrao implements ICalculadoraFrete {
    public double calcular(double peso) {
        return peso * 1.2;
    }

    public boolean tipoFreteCompativel(TIPO_FRETE tipoFrete) {
        return tipoFrete == TIPO_FRETE.PADRAO;
    }
}
