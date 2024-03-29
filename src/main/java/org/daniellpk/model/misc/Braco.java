package org.daniellpk.model.misc;

import org.daniellpk.Main;
import org.daniellpk.model.api.Cidade;

import java.util.Arrays;
import java.util.List;

public class Braco extends DbObj {
    public enum SUPERTIPO {MARINHA, AERONAUTICA, EXERCITO}
    private SUPERTIPO supertipo = SUPERTIPO.EXERCITO;
    private String nome = "";
    private Integer comandante_id = -1;


    public SUPERTIPO getSupertipo() {
        return supertipo;
    }

    public void setSupertipo(SUPERTIPO supertipo) {
        this.supertipo = supertipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getComandante_id() {
        return comandante_id;
    }

    public void setComandante_id(Integer comandante_id) {
        this.comandante_id = comandante_id;
    }

    public  String getDataName() {
        return "Braços do Exército";
    }
    public List<String> getTableHeaders() {
        List<String> membros = Arrays.asList(
                "ID",
                "Nome",
                "Ramo",
                "Comandante"
        );
        return membros;
    }

    public List<String> getAsTableItem() {
        Comandante _commander = Main.comandanteService.obter(comandante_id);
        List<String> membros = (List<String>) Arrays.asList(
                getId().toString(),
                getSupertipo().toString(),
                getNome(),
                _commander != null ? _commander.getNome() : "N/A"
        );
        return membros;
    }
}
