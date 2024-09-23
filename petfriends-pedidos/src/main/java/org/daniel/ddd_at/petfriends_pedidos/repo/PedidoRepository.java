package org.daniel.ddd_at.petfriends_pedidos.repo;

import org.daniel.ddd_at.petfriends_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface PedidoRepository extends JpaRepository<Pedido, BigDecimal> {
}
