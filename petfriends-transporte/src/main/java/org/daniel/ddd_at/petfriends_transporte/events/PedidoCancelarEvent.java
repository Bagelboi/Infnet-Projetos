package org.daniel.ddd_at.petfriends_transporte.events;

import java.math.BigDecimal;

public record PedidoCancelarEvent(BigDecimal id, BigDecimal cliente_id) {
}
