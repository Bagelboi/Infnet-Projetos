package org.daniel.dddtp3.repos;

import org.daniel.dddtp3.agregados.pedido.PedidoAgg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepo extends JpaRepository<PedidoAgg, String> {
}
