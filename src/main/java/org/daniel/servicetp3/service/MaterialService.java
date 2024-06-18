package org.daniel.servicetp3.service;

import org.daniel.servicetp3.model.Material;
import org.daniel.servicetp3.repo.MaterialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    MaterialRepo repo;


    public List<Material> getAll() {
        return (List<Material>) repo.findAll();
    }

    public Optional<Material> getByID(String id) {
        return repo.findById(id);
    }

    public Material save(Material material) {
        return repo.save(material);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

}
