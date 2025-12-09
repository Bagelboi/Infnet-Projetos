package org.dlpk.Exercicio3;

public record Endereco(String UF, String cidade, String rua) {
    public String toPath() {
        return String.join( "/", UF, cidade, rua ).replace(" ", "%20");
    }
}
