package org.daniel.tp3.controller;

import org.daniel.tp3.domain.Caixa;
import org.daniel.tp3.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/crud/caixa")
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @PostMapping("/incluir")
    public boolean incluir(@RequestBody Caixa caixa) {
        return caixaService.incluir(caixa);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody Caixa caixa) {
        caixaService.atualizar(caixa);
    }

    @DeleteMapping("/excluir/{uid}")
    public boolean excluir(@PathVariable String uid) {
        return caixaService.excluir(uid);
    }

    @GetMapping("/obter")
    public Collection<Caixa> obter() {
        return caixaService.obter();
    }

    @GetMapping("/obter/{uid}")
    public Caixa obterId(@PathVariable String uid) {
        return caixaService.obterId(uid);
    }

    @PostMapping("/transferir/{id}/{linha_id}")
    public boolean transferirCaixa(@PathVariable String id, @PathVariable Integer linha_id) {
        return caixaService.transferirCaixa(id, linha_id);
    }
}
