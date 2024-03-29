package org.daniellpk.controller;

import org.daniellpk.model.grupo.Estrutura;
import org.daniellpk.service.EstruturaService;
import spark.Spark;

public class EstruturaController extends DbController<EstruturaService> {
    public EstruturaController(EstruturaService _dbService) {
        super(_dbService, Estrutura.class);
    }

    public void pathApi(String pathroot) {
        super.pathApi(pathroot);
        Spark.path(pathroot, () -> {
            Spark.get( "/:id/add_soldado/:idp/:qnt", (req, res) -> dbService.addSoldado( Integer.parseInt( req.params("id") ), Integer.parseInt( req.params("idp") ), Integer.parseInt( req.params("qnt") ) ));
            Spark.get( "/:id/add_veiculo/:idp/:qnt", (req, res) -> dbService.addSoldado( Integer.parseInt( req.params("id") ), Integer.parseInt( req.params("idp") ), Integer.parseInt( req.params("qnt") ) ));
        });
    }
}
