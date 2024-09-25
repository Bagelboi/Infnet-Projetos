package org.daniel.ddd_at.petfriends_almoxarifado.service;

import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_almoxarifado.DTO.ItemPedidoDTO;
import org.daniel.ddd_at.petfriends_almoxarifado.DTO.PedidoDTO;
import org.daniel.ddd_at.petfriends_almoxarifado.events.AlmoxarifadoPublisher;
import org.daniel.ddd_at.petfriends_almoxarifado.events.PedidoAlmoxarifadoCancelarEvent;
import org.daniel.ddd_at.petfriends_almoxarifado.events.PedidoAlmoxarifadoEvent;
import org.daniel.ddd_at.petfriends_almoxarifado.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Handler;

@Service
@Slf4j
public class PedidoService {
    @Autowired
    ItemService itemService;

    @Autowired
    AlmoxarifadoPublisher almoxarifadoPublisher;

    public void despacharPedido(PedidoDTO pedido) {
        HashMap<BigDecimal, Integer> itemsToRetirar = new HashMap<>();
        for (ItemPedidoDTO item_pedido : pedido.getItems()) {
            Optional<Item> optionalItem = itemService.getById(item_pedido.getItem_id());
            if (optionalItem.isPresent()) {
                Integer quant = item_pedido.getQuantia();
                BigDecimal id = item_pedido.getItem_id();
                if (itemService.podeRetirarEstoque(id, quant))
                    itemsToRetirar.put(id, quant);
                else
                    cancelarPedido(pedido);
            }
            else
                cancelarPedido(pedido);
        }
        for (BigDecimal item_id : itemsToRetirar.keySet()) {
            itemService.retirarEstoque(item_id, itemsToRetirar.get(item_id));
        }
        despacharPedidoEvento(pedido);
    }

    public void despacharPedidoEvento(PedidoDTO pedido) {
       //almoxarifadoPublisher.almoxarifadoDespache().apply( new PedidoAlmoxarifadoEvent(pedido.getId()));
        log.info("Despachando pedido {}", pedido);
        almoxarifadoPublisher.sendAlmoxarifadoDespache(new PedidoAlmoxarifadoEvent(pedido.getId()));
    }

    public void cancelarPedido(PedidoDTO pedido) {
       // almoxarifadoPublisher.cancelarPedido().apply( new PedidoAlmoxarifadoCancelarEvent(pedido.getId()));
        log.info("Cancelando pedido {}", pedido);
        almoxarifadoPublisher.sendCancelarPedido(new PedidoAlmoxarifadoCancelarEvent(pedido.getId()));
    }
}

