package org.daniellpk.model.grupo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.daniellpk.Main;
import org.daniellpk.model.misc.DbObj;
import org.daniellpk.model.misc.InheritedKey;
import org.daniellpk.model.misc.VelocityManager;
import org.daniellpk.model.peao.Peao;
import org.daniellpk.model.peao.Soldado;
import org.daniellpk.model.peao.Veiculo;
import org.daniellpk.service.SoldadoService;
import org.w3c.dom.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grupo extends DbObj {

    private String nome = "";
    private HashMap<InheritedKey, Integer> peaos = new HashMap(); //id de peao, quantidade de peaos desse id
    private Integer cidade_id = -1;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPeaoQuant(InheritedKey pk) {
        return peaos.get(pk);
    }

    public HashMap<InheritedKey, Integer> getPeaos() {
        return peaos;
    }

    public void setPeaos(HashMap<InheritedKey, Integer> peaos) {
        this.peaos = peaos;
    }

    public void addPeao(Peao p, Integer qnt) {
        InheritedKey ik = InheritedKey.parse(p);
        if ( qnt != 0)
            peaos.put(ik, Math.abs(qnt));
        else if ( qnt == 0 && peaos.containsKey(ik))
            peaos.remove(ik);
    }

    public Integer getCidade_id() {
        return cidade_id;
    }

    public void setCidade_id(Integer cidade_id) {
        this.cidade_id = cidade_id;
    }


    public Peao getPeaoByType(InheritedKey ik) {
        if (ik.classname == Soldado.class.getName())
            return Main.soldadoService.obter(id);
        else if (ik.classname == Veiculo.class.getName())
            return Main.veiculoService.obter(id);
        return null;
    }

    // Funções HTML
    protected String getPeaosAsTable() {
        List<String> rows = new ArrayList<>();
        for (Map.Entry<InheritedKey, Integer> p : peaos.entrySet()) {
            Peao p_ref = getPeaoByType(p.getKey());
            if (p_ref != null)
                rows.add( p.getValue() + " " + p_ref.getNome() + "(s)" );
        }
        return VelocityManager.formatAsInnerTable(rows);
    }
}

