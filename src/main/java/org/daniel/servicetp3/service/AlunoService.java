package org.daniel.servicetp3.service;

import jakarta.annotation.PostConstruct;
import org.daniel.servicetp3.model.Aluno;
import org.daniel.servicetp3.repo.AlunoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    AlunoRepo repo;


    public List<Aluno> getAll() {
        return (List<Aluno>) repo.findAll();
    }

    public Optional<Aluno> getByID(Long id) {
        return repo.findById(id);
    }

    public Aluno save(Aluno aluno) {
        return repo.save(aluno);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
