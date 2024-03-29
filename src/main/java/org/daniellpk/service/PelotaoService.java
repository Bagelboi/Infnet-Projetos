package org.daniellpk.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.daniellpk.Main;
import org.daniellpk.model.grupo.Pelotao;
import org.daniellpk.model.misc.InheritedKey;
import org.daniellpk.model.peao.Peao;
import org.daniellpk.model.peao.Soldado;
import org.daniellpk.model.peao.Veiculo;

import java.lang.reflect.Type;
import java.util.HashMap;

public class PelotaoService extends DbService<Pelotao> {


    public String addSoldado(Integer id, Integer peao_id, Integer quant) {
        Soldado p = Main.soldadoService.obter(peao_id);
        if (this.contemId(id) && p != null) {
            this.obter(id).addPeao(p, quant);
            return "Adicionado com sucesso";
        }
        return "Não teve sucesso em adicionar";
    }
    public String addVeiculo(Integer id, Integer peao_id, Integer quant) {
        Veiculo p = Main.veiculoService.obter(peao_id);
        if (this.contemId(id) && p != null) {
            this.obter(id).addPeao(p, quant);
            return "Adicionado com sucesso";
        }
        return "Não teve sucesso em adicionar";
    }
}
