package org.daniel.tp3.repository;

import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.domain.RobotArm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILinhaProdRep extends CrudRepository<LinhaProducao, Integer> {
}
