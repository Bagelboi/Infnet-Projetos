package org.daniel.suspediatria2.repos.cadastro;




import org.daniel.suspediatria2.entidades.cadastro.Crianca;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Annotation;


@Repository
public interface RepoCrianca extends CrudRepository<Crianca, String> {
}
