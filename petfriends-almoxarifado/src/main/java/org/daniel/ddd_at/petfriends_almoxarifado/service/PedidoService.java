package org.daniel.ddd_at.petfriends_almoxarifado.service;

import org.daniel.ddd_at.petfriends_almoxarifado.DTO.ItemPedidoDTO;
import org.daniel.ddd_at.petfriends_almoxarifado.DTO.PedidoDTO;
import org.daniel.ddd_at.petfriends_almoxarifado.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    ItemService itemService;
    public void despacharPedido(PedidoDTO pedido) {
        for (ItemPedidoDTO item_pedido : pedido.getItems()) {
            Optional<Item> optionalItem = itemService.getById(item_pedido.getItem_id());
            if (optionalItem.isPresent()) {
                try {
                    itemService.retirarEstoque(item_pedido.getItem_id(), item_pedido.getQuantia());
                    //mandar evento de despachar
                } catch(Exception e) {
                    //mandar evento de cancelar
                }
            }
            else
                System.out.println();
                //mandar evento de cancelar
        }
    }
}
