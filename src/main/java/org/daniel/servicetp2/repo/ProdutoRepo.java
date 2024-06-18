package org.daniel.servicetp2.repo;

import org.daniel.servicetp2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepo extends CrudRepository<Produto, Long> {
}
