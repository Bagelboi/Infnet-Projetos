package org.daniel.ddd_at.petfriends_almoxarifado.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemPedidoDTO implements Serializable {
    BigDecimal item_id;
    Integer quantia;
}
