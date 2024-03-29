package org.daniellpk.controller;

import org.daniellpk.model.peao.Soldado;
import org.daniellpk.service.SoldadoService;

public class SoldadoController extends DbController<SoldadoService> {
    public SoldadoController(SoldadoService _dbService) {
        super(_dbService, Soldado.class);
    }
}
