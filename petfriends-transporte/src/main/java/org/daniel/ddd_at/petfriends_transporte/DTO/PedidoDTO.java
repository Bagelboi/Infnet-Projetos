package org.daniel.ddd_at.petfriends_transporte.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.daniel.ddd_at.petfriends_transporte.states.PedidoState;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoDTO implements Serializable {
    BigDecimal id;
    PedidoState estado;
    BigDecimal cliente_id;
}