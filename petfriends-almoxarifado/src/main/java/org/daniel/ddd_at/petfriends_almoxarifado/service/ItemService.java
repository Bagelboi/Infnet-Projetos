package org.daniel.ddd_at.petfriends_almoxarifado.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_almoxarifado.model.DinheiroHelper;
import org.daniel.ddd_at.petfriends_almoxarifado.model.Item;
import org.daniel.ddd_at.petfriends_almoxarifado.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Item create(Item item) {
        return repository.save(item);
    }

    public Optional<Item> getById(BigDecimal id) {
        return repository.findById(id);
    }

    public Item update(BigDecimal id, Item updatedItem) {
        return repository.findById(id).map(item -> {
                item.setEstoque(updatedItem.getEstoque());
                item.setPreco(updatedItem.getPreco());
            return repository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public boolean delete(BigDecimal id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    
    //Logica de Negócio
    
    public void retirarEstoque(BigDecimal id, Integer quant) {
        Item item = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Item não encontrado"));

        if (item.possivelRetirarEstoque(quant)) {
            item.setEstoque( item.getEstoque() - quant );
            repository.save(item);
        } else {
            throw new IllegalStateException("Não é retirar o estoque.");
        }
    }

    @PostConstruct
    public void test() throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Item item = new Item();
        item.setEstoque(1);
        item.setPreco(DinheiroHelper.build(20.50));
        item.setNome("Porra");
        log.info( obj.writeValueAsString(create(item)));
    }
}
