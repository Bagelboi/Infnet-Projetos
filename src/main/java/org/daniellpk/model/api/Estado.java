package org.daniellpk.model.api;

import org.daniellpk.model.misc.DbObj;

public class Estado extends DbObj {
    private Integer id_api = -1;
    private String nome = "";

    public Integer getId_api() {
        return id_api;
    }

    public void setId_api(Integer id_api) {
        this.id_api = id_api;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
