package org.daniel.tp3.repository;

import org.daniel.tp3.domain.Caixa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICaixaRep extends CrudRepository<Caixa, String> {
}
