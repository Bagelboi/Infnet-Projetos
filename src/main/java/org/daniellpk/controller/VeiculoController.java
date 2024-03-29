package org.daniellpk.controller;

import org.daniellpk.model.peao.Veiculo;
import org.daniellpk.service.VeiculoService;

public class VeiculoController extends DbController<VeiculoService> {
    public VeiculoController(VeiculoService _dbService) {
        super(_dbService, Veiculo.class);
    }
}
