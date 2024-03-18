package org.daniel.tp3.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.daniel.tp3.client.IPokemonCardClient;
import org.daniel.tp3.domain.PokemonCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PokemonCardService {
    @Autowired
    IPokemonCardClient pokeClient;

    public PokemonCard obter(String id) {
        String json = pokeClient.obter(id);
        PokemonCard card = null;
        if (json != null) {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            // Extract the "name" field from the nested "data" object
            JsonObject data = jsonObject.getAsJsonObject("data");
            card = new PokemonCard();
            card.setId(id);
            card.setName(data.get("name").getAsString());
            card.setRarity(data.get("rarity").getAsString());
            card.setSupertype(data.get("supertype").getAsString());
        }
        return card;
    }
}
