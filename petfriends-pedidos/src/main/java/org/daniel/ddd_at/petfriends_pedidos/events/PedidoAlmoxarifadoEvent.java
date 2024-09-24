package org.daniel.ddd_at.petfriends_pedidos.events;

import org.daniel.ddd_at.petfriends_pedidos.model.ItemPedido;
import org.daniel.ddd_at.petfriends_pedidos.states.PedidoState;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

public record PedidoAlmoxarifadoEvent(BigDecimal id,
                                      Set<ItemPedido>items){
}
