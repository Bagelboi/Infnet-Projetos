package org.daniel.ddd2_tp2.repo;

import org.daniel.ddd2_tp2.domain.entity.Assinatura;
import org.daniel.ddd2_tp2.domain.entity.AssinaturaCliente;
import org.springframework.data.repository.CrudRepository;

public interface AssinaturaRepo extends CrudRepository<Assinatura, Long> {
}
