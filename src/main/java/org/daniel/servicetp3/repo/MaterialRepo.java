package org.daniel.servicetp3.repo;

import org.daniel.servicetp3.model.Material;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends MongoRepository<Material, String> {
}
