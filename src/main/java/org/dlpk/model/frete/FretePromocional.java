package org.dlpk.model.frete;

public class FretePromocional implements ICalculadoraFrete {
    public static class PromocaoPesoBaixoDemaisException extends RuntimeException {
        public PromocaoPesoBaixoDemaisException() {
            super("Peso baixo demais para aplicar promoção");
        }
    }

    private final ICalculadoraFrete calculadora;

    public FretePromocional(ICalculadoraFrete calculadora) {
        this.calculadora = calculadora;
    }

    public double calcular(double peso) {
        if (peso > 10)
            return calculadora.calcular(peso - 1);
        throw new PromocaoPesoBaixoDemaisException();
    }

    public boolean tipoFreteCompativel(TIPO_FRETE tipoFrete) {
        return calculadora.tipoFreteCompativel(tipoFrete);
    }
}
