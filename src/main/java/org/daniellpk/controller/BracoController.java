package org.daniellpk.controller;

import org.daniellpk.model.api.Estado;
import org.daniellpk.model.grupo.Estrutura;
import org.daniellpk.model.grupo.Pelotao;
import org.daniellpk.model.misc.Braco;
import org.daniellpk.model.peao.Soldado;
import org.daniellpk.model.peao.Veiculo;
import org.daniellpk.service.*;

public class BracoController extends DbController<BracoService>{
    public BracoController(BracoService _dbService) {
        super(_dbService, Braco.class);
    }
}


