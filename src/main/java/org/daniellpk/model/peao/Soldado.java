package org.daniellpk.model.peao;

import org.daniellpk.Main;
import org.daniellpk.model.misc.Comandante;

import java.util.Arrays;
import java.util.List;

public class Soldado extends Peao {
    public enum TIPO {INFANTARIA, ARTILHEIRO, ESCOTEIRO, INFILTRADOR, MEDICO, ATIRADOR, PILOTO, ESTRATEGISTA}
    private TIPO tipo = TIPO.INFANTARIA;
    private double salario = 0.0;
    private Integer nivel_risco = 0;



    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Integer getNivel_risco() {
        return nivel_risco;
    }

    public void setNivel_risco(Integer nivel_risco) {
        this.nivel_risco = Math.max(0, nivel_risco);
    }

    public  String getDataName() {
        return "Soldados";
    }
    public List<String> getTableHeaders() {
        List<String> membros = Arrays.asList(
                "ID",
                "Nome",
                "Tipo",
                "Salario",
                "Risco"
        );
        return membros;
    }

    public List<String> getAsTableItem() {
        List<String> membros = (List<String>) Arrays.asList(
                getId().toString(),
                getNome(),
                tipo.toString(),
                String.format("%.2f", salario),
                "&#9734".repeat(nivel_risco)
        );
        return membros;
    }
}
