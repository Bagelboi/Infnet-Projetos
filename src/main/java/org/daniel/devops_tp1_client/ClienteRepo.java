package org.daniel.devops_tp1_client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends CrudRepository<Cliente, Long> {
}
