package org.daniellpk.controller;

import org.daniellpk.model.grupo.Pelotao;
import org.daniellpk.service.PelotaoService;
import spark.Spark;

public class PelotaoController extends DbController<PelotaoService> {
    public PelotaoController(PelotaoService _dbService) {
        super(_dbService, Pelotao.class);
    }

    public void pathApi(String pathroot) {
        super.pathApi(pathroot);
        Spark.path(pathroot, () -> {
            Spark.get( ":id/add_soldado/:idp/:qnt", (req, res) -> dbService.addSoldado( Integer.parseInt( req.params("id") ), Integer.parseInt( req.params("idp") ), Integer.parseInt( req.params("qnt") ) ));
            Spark.get( ":id/add_veiculo/:idp/:qnt", (req, res) -> dbService.addVeiculo( Integer.parseInt( req.params("id") ), Integer.parseInt( req.params("idp") ), Integer.parseInt( req.params("qnt") ) ));
        });
    }
}
