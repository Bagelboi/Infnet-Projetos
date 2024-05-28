package org.daniel.suspediatria2.repos.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Crianca;
import org.daniel.suspediatria2.entidades.cadastro.UBS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUBS extends CrudRepository<UBS, Long> {
}
