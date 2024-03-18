package org.daniel.tp3.domain;

import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

public class PokemonCard {

    private String id;
    private String name;
    private String rarity;
    private String supertype;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getSupertype() {
        return supertype;
    }

    public void setSupertype(String supertype) {
        this.supertype = supertype;
    }
}
