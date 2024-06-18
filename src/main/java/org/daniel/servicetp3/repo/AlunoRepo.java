package org.daniel.servicetp3.repo;

import org.daniel.servicetp3.model.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepo extends CrudRepository<Aluno, Long> {
}
