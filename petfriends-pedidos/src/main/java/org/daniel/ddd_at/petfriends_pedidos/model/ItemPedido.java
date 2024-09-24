package org.daniel.ddd_at.petfriends_pedidos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemPedido implements Serializable {
    BigDecimal item_id;
    Integer quantia;
}
