package org.daniel.tp3.controller;

import org.daniel.tp3.domain.PokemonCard;
import org.daniel.tp3.service.CaixaService;
import org.daniel.tp3.service.PokemonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartas")
@ComponentScan("org.daniel.tp3.client")
public class PokemonCardController {
    @Autowired
    PokemonCardService pokeService;
    @Autowired
    CaixaService caixaService;

    @GetMapping(value="/{id}")
    public PokemonCard obter(@PathVariable String id) {
        return pokeService.obter(id);
    }

    @GetMapping(value="/total/{id}")
    public String obterTotal(@PathVariable String id) {
        return "Total de unidades empacotadas para ID=" + id + ": " + caixaService.quantCartas(id).toString();
    }


}
