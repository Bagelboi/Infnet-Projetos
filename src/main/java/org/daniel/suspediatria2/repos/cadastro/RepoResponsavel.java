package org.daniel.suspediatria2.repos.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Crianca;
import org.daniel.suspediatria2.entidades.cadastro.Responsavel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoResponsavel extends CrudRepository<Responsavel, String> {
}
