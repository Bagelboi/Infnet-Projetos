package org.daniel.ddd_at.petfriends_almoxarifado.repo;

import org.daniel.ddd_at.petfriends_almoxarifado.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ItemRepository extends JpaRepository<Item, BigDecimal> {
}
