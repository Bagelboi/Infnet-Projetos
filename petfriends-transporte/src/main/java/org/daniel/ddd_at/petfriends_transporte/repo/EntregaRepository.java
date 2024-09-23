package org.daniel.ddd_at.petfriends_transporte.repo;

import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface EntregaRepository extends JpaRepository<Entrega, BigDecimal> {
}
