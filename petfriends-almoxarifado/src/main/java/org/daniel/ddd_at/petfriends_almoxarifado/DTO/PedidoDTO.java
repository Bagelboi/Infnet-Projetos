package org.daniel.ddd_at.petfriends_almoxarifado.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PedidoDTO implements Serializable {
    BigDecimal id;
    Set<ItemPedidoDTO> items;
}