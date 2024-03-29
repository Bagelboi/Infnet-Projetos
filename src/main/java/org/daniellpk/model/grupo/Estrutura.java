package org.daniellpk.model.grupo;

import org.daniellpk.Main;
import org.daniellpk.model.api.Cidade;
import org.daniellpk.model.api.IbgeApi;
import org.daniellpk.model.misc.Braco;
import org.daniellpk.model.misc.VelocityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Estrutura extends Grupo{
    private double largura = 0.0;
    private double altura = 0.0;
    private double comprimento = 0.0;
    private double patrimonio = 0.0;
    private int nivel_seguranca = 1;

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(double patrimonio) {
        this.patrimonio = patrimonio;
    }

    public int getNivel_seguranca() {
        return nivel_seguranca;
    }

    public void setNivel_seguranca(int nivel_seguranca) {
        this.nivel_seguranca = Math.max(0, Math.min(5, nivel_seguranca));
    }

    public  String getDataName() {
        return "Pelotões";
    }
    public  List<String> getTableHeaders() {
        List<String> membros = Arrays.asList(
                "ID",
                "Nome",
                "Unidades",
                "Patrimonio",
                "Segurança",
                "Cidade",
                "Dimensões"
        );
        return membros;
    }

    public List<String> getAsTableItem() {
        Cidade _cidade = IbgeApi.getCidade(getCidade_id());
        List<String> membros = (List<String>) Arrays.asList(
                getId().toString(),
                getNome(),
                getPeaosAsTable(),
                String.format("%.2f", patrimonio ) + "R$",
                "&#9734".repeat((int) nivel_seguranca),
                _cidade != null ? _cidade.getNome() : "N/A",
                largura + "x" + altura + "x" + comprimento
        );
        return membros;
    }
/*
    public List<String> getFormInputs() {
        return (List<String>) Arrays.asList(
                VelocityManager.createInputField("id", "number"),
                VelocityManager.createInputField("nome", "text"),
                VelocityManager.createInputField("largura", "number"),
                VelocityManager.createInputField("altura", "number"),
                VelocityManager.createInputField("comprimento", "number"),
                VelocityManager.createInputField("patrimonio", "number"),
                VelocityManager.createInputField("nivel_seguranca", "Nivel de Segurança", "number"),
                VelocityManager.createInputField("cidade_id", "Cidade Id", "number")
        );
    }*/

}
