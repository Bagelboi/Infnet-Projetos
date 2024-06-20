package org.daniel.servicoat.repo;


import org.daniel.servicoat.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepo extends CrudRepository<Departamento, Long> {
}
