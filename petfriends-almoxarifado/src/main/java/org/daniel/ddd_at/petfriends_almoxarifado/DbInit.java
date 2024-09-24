package org.daniel.ddd_at.petfriends_almoxarifado;

import org.daniel.ddd_at.petfriends_almoxarifado.model.Item;
import org.daniel.ddd_at.petfriends_almoxarifado.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DbInit implements ApplicationRunner {
    @Autowired
    ItemService itemService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        itemService.create(Item.New(BigDecimal.valueOf(1), 500, "Ração Kobeni de Cachorro Adulto 4-12 anos", 209.99));
        itemService.create(Item.New(BigDecimal.valueOf(2), 300, "Ração Denji de Gato Filhote", 149.99));
        itemService.create(Item.New(BigDecimal.valueOf(3), 150, "Brinquedo Aki para Cães - Bola Mordedora", 39.99));
        itemService.create(Item.New(BigDecimal.valueOf(4), 100, "Brinquedo Power para Gatos - Vara com Pena", 29.99));
        itemService.create(Item.New(BigDecimal.valueOf(5), 200, "Shampoo Makima para Cães com Pelo Curto", 59.99));
        itemService.create(Item.New(BigDecimal.valueOf(6), 120, "Arranhador Himeno para Gatos", 89.99));
        itemService.create(Item.New(BigDecimal.valueOf(7), 180, "Coleira Angel para Cães - Tamanho M", 49.99));
        itemService.create(Item.New(BigDecimal.valueOf(8), 80, "Tapete Higiênico Kishibe para Gatos", 69.99));

    }
}
