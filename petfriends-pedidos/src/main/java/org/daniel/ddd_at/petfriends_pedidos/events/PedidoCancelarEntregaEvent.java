package org.daniel.ddd_at.petfriends_pedidos.events;

import java.math.BigDecimal;

public record PedidoCancelarEntregaEvent(BigDecimal id,
                                         BigDecimal cliente_id) {
}
