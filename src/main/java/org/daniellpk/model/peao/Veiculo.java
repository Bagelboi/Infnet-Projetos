package org.daniellpk.model.peao;

import java.util.Arrays;
import java.util.List;

public class Veiculo extends Peao{
    public enum TIPO {AEREO, TERRESTRE, AQUATICO}
    private TIPO tipo = TIPO.TERRESTRE;
    private Integer passageiros_max = 0;
    private double velocidade_kmh = 0.0;
    private double custo_combustivel = 0.0; // mensal

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public Integer getPassageiros_max() {
        return passageiros_max;
    }

    public void setPassageiros_max(Integer passageiros_max) {
        this.passageiros_max = passageiros_max;
    }

    public double getVelocidade_kmh() {
        return velocidade_kmh;
    }

    public void setVelocidade_kmh(double velocidade_kmh) {
        this.velocidade_kmh = velocidade_kmh;
    }

    public double getCusto_combustivel() {
        return custo_combustivel;
    }

    public void setCusto_combustivel(double custo_combustivel) {
        this.custo_combustivel = custo_combustivel;
    }

    public  String getDataName() {
        return "Soldados";
    }
    public List<String> getTableHeaders() {
        List<String> membros = Arrays.asList(
                "ID",
                "Nome",
                "Tipo",
                "Assentos",
                "Velocidade",
                "Custo de Combustivel"
        );
        return membros;
    }

    public List<String> getAsTableItem() {
        List<String> membros = (List<String>) Arrays.asList(
                getId().toString(),
                getNome(),
                tipo.toString(),
                passageiros_max.toString(),
                String.format("%.4f", velocidade_kmh) + " km/h",
                String.format("%.2f", custo_combustivel)
        );
        return membros;
    }

}
