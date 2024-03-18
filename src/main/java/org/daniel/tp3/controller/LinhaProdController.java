package org.daniel.tp3.controller;

import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.service.LinhaProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/crud/linha")
public class LinhaProdController {

    @Autowired
    private LinhaProdService linhaProducaoService;

    @PostMapping("/incluir")
    public boolean incluir(@RequestBody LinhaProducao linha) {
        return linhaProducaoService.incluir(linha);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody LinhaProducao linha) {
        linhaProducaoService.atualizar(linha);
    }

    @DeleteMapping("/excluir/{id}")
    public boolean excluir(@PathVariable Integer id) {
        return linhaProducaoService.excluir(id);
    }

    @GetMapping("/obter")
    public Collection<LinhaProducao> obter() {
        return linhaProducaoService.obter();
    }

    @GetMapping("/obter/{id}")
    public LinhaProducao obterId(@PathVariable Integer id) {
        return linhaProducaoService.obterId(id);
    }
}
