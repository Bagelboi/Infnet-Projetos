package org.daniellpk.service;

import org.daniellpk.Main;
import org.daniellpk.model.api.Cidade;
import org.daniellpk.model.api.Estado;
import org.daniellpk.model.grupo.Estrutura;
import org.daniellpk.model.grupo.Pelotao;
import org.daniellpk.model.misc.Braco;
import org.daniellpk.model.misc.Comandante;
import org.daniellpk.model.peao.Soldado;
import org.daniellpk.model.peao.Veiculo;

import java.io.IOException;

public class BracoService extends DbService<Braco> {

    @Override
    public boolean verificarIncluir(Integer id, Braco dbObj) {
        Comandante b = Main.comandanteService.obter(dbObj.getComandante_id());
        if (b != null) {
            Braco oldc = this.obter(b.getBraco_id());
            if (oldc != null) //comandante ja assinalado ao braço
                oldc.setComandante_id(-1);
            b.setBraco_id(id);
        }
        return true;
    }

    @Override
    public boolean verificarAtualizar(Integer id, Braco dbObj, Braco antigo) {
        if (antigo.getComandante_id() != dbObj.getComandante_id()) {
            Comandante c = Main.comandanteService.obter(antigo.getComandante_id());
            if (c != null)
                c.setBraco_id(-1);
        }
        return verificarIncluir(id, dbObj);
    }
}

