package org.daniellpk.model.grupo;

import org.daniellpk.Main;
import org.daniellpk.model.api.Cidade;
import org.daniellpk.model.api.IbgeApi;
import org.daniellpk.model.misc.Braco;
import org.daniellpk.model.misc.DbObj;
import org.daniellpk.model.misc.VelocityManager;

import javax.swing.text.DateFormatter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Pelotao extends Grupo {
    private Integer braco_id = -1; //braço das forças armadas
    private Date data_criacao = new Date();

    public Integer getBraco_id() {
        return braco_id;
    }

    public void setBraco_id(Integer braco_id) {
        this.braco_id = braco_id;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    //Funções HTML

    public  String getDataName() {
        return "Pelotões";
    }
    public  List<String> getTableHeaders() {
        List<String> membros = Arrays.asList(
                "ID",
                "Nome",
                "Braço",
                "Unidades",
                "Cidade",
                "Data de Formação"
        );
        return membros;
    }

    public List<String> getAsTableItem() {
        Braco _braco = Main.bracoService.obter(braco_id);
        Cidade _cidade = IbgeApi.getCidade(getCidade_id());
        List<String> membros = (List<String>) Arrays.asList(
                getId().toString(),
                getNome(),
                _braco != null ? _braco.getNome() : "N/A",
                getPeaosAsTable(),
                _cidade != null ? _cidade.getNome() : "N/A",
                data_criacao.toString()
        );
        return membros;
    }

    /*
    public List<String> getFormInputs() {
        return (List<String>) Arrays.asList(
                VelocityManager.createInputField("id", "number"),
                VelocityManager.createInputField("nome", "text"),
                VelocityManager.createInputField("braco_id", "Braço Id", "number"),
                VelocityManager.createInputField("cidade_id", "Cidade Id", "number"),
                VelocityManager.createInputField("data_criacao", "Data de Formação", "date")
        );
    }*/
}
