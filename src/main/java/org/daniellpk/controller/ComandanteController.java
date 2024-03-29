package org.daniellpk.controller;

import org.daniellpk.model.misc.Comandante;
import org.daniellpk.service.ComandanteService;

public class ComandanteController extends DbController<ComandanteService> {
    public ComandanteController(ComandanteService _dbService) {
        super(_dbService, Comandante.class);
    }
}
