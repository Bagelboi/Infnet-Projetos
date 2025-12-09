package org.dlpk.model.frete;

public interface ICalculadoraFrete {
    double calcular(double peso);

    boolean tipoFreteCompativel(TIPO_FRETE tipoFrete);
}
