package org.daniel.ddd_at.petfriends_transporte.repo;

import org.daniel.ddd_at.petfriends_transporte.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface EntregadorRepository extends JpaRepository<Entregador, BigDecimal> {
}
