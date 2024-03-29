package org.daniellpk.model.misc;

import org.daniellpk.Main;

import java.util.Arrays;
import java.util.List;

public class Comandante extends DbObj{
    private String nome = "";
    private Integer braco_id = -1;
    private Integer idade = 0;

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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public  String getDataName() {
        return "Comandantes";
    }
    public List<String> getTableHeaders() {
        List<String> membros = Arrays.asList(
                "ID",
                "Nome",
                "Braço",
                "Idade"
        );
        return membros;
    }

    public List<String> getAsTableItem() {
        Braco _braco = Main.bracoService.obter(braco_id);
        List<String> membros = (List<String>) Arrays.asList(
                getId().toString(),
                getNome(),
                _braco != null ? _braco.getNome() : "N/A",
                idade.toString() + " anos"
        );
        return membros;
    }
}
