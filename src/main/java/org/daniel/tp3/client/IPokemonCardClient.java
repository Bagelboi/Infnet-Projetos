package org.daniel.tp3.client;

import org.daniel.tp3.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@FeignClient(url="https://api.pokemontcg.io/v2/cards", name="pokemonCardClient")
public interface IPokemonCardClient {
    @GetMapping(value="/{id}", headers = {"X-Api-Key: " + Application.POKEAPI_KEY})
    public String obter(@PathVariable String id);
}
