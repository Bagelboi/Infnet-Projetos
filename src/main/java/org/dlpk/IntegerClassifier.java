package org.dlpk;

public class IntegerClassifier {
    private enum CASO {
        ALTO,
        RARO,
        MEDIO
    }

    public static final int RARO_VAL = -9999;
    public static final int MEDIO_VAL = 10;

    private final int numero;

    private CASO classificarCaso() {
        if (numero > MEDIO_VAL) {
            return CASO.ALTO;
        } else if (numero == RARO_VAL) {
            return CASO.RARO;
        } else if (numero == MEDIO_VAL) {
            return CASO.MEDIO;
        }
        throw new RuntimeException("Caso desconhecido");
    }

    public IntegerClassifier(int numero) {
        this.numero = numero;
    }

    public void imprimir() {
        try {
            System.out.println(classificarCaso());
            System.out.println("Debug: numero = " + numero);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return classificarCaso().name();
    }
}