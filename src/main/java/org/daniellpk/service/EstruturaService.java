package org.daniellpk.service;

import org.daniellpk.Main;
import org.daniellpk.model.grupo.Estrutura;
import org.daniellpk.model.peao.Soldado;
import org.daniellpk.model.peao.Veiculo;

public class EstruturaService extends DbService<Estrutura> {

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
