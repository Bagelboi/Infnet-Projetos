package org.daniellpk.service;

import org.daniellpk.Main;
import org.daniellpk.model.misc.Braco;
import org.daniellpk.model.misc.Comandante;

import java.io.IOException;

public class ComandanteService extends DbService<Comandante> {

    @Override
    public boolean verificarIncluir(Integer id, Comandante dbObj) {
        Braco b = Main.bracoService.obter(dbObj.getBraco_id());
        if (b != null) {
            Comandante oldc = this.obter(b.getComandante_id());
            if (oldc != null) //comandante ja assinalado ao braço
                oldc.setBraco_id(-1);
            b.setComandante_id(id);
        }
        return true;
    }

    @Override
    public boolean verificarAtualizar(Integer id, Comandante dbObj, Comandante antigo) {
        if (antigo.getBraco_id() != dbObj.getBraco_id()) {
            Braco c = Main.bracoService.obter(antigo.getBraco_id());
            if (c != null)
                c.setComandante_id(-1);
        }
        return verificarIncluir(id, dbObj);
    }
}
