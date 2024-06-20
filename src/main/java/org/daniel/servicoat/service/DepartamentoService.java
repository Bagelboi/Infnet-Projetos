package org.daniel.servicoat.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.daniel.servicoat.model.Departamento;
import org.daniel.servicoat.repo.DepartamentoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "depts")
public class DepartamentoService {
    @Autowired
    DepartamentoRepo repo;


    public List<Departamento> getAll() {
        return (List<Departamento>) repo.findAll();
    }

    //Cacheable não funciona com Optional
    public Optional<Departamento> getByID(Long id) {
        return repo.findById(id);
    }

    @CachePut
    public Departamento save(Departamento dept) {
        return repo.save(dept);
    }

    @CacheEvict(key="#id")
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
