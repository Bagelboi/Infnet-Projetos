package org.daniel.ddd_at.petfriends_almoxarifado;

import org.daniel.ddd_at.petfriends_almoxarifado.model.Item;
import org.daniel.ddd_at.petfriends_almoxarifado.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("almoxarifado")
public class TestController {
    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getPedidos() {
        return ResponseEntity.ok(itemService.getAll());
    }
}
