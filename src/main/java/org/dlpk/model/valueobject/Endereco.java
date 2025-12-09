package org.dlpk.model.valueobject;

public class Endereco {
    private final String value;

    public Endereco(String value) {
        value = value.trim();
        if (value.isBlank() || value.isEmpty())
            throw new IllegalArgumentException("Endereco Invalido");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
