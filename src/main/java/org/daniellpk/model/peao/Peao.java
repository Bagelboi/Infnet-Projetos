package org.daniellpk.model.peao;

import org.daniellpk.model.misc.DbObj;

public class Peao extends DbObj {
    private String nome = "";
    private Integer braco_id = -1; //braço das forças armadas
    private Double custo_recursos = 0.0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getBraco_id() {
        return braco_id;
    }

    public void setBraco_id(Integer braco_id) {
        this.braco_id = braco_id;
    }

    public Double getCusto_recursos() {
        return custo_recursos;
    }

    public void setCusto_recursos(Double custo_recursos) {
        this.custo_recursos = custo_recursos;
    }
}
