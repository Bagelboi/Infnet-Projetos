package org.daniel.servicetp3.service;

import org.daniel.servicetp3.model.Aluno;
import org.daniel.servicetp3.model.Curso;
import org.daniel.servicetp3.repo.AlunoRepo;
import org.daniel.servicetp3.repo.CursoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "cursos")
public class CursoService {
    @Autowired
    CursoRepo repo;

    public List<Curso> getAll() {
        return (List<Curso>) repo.findAll();
    }

    @Cacheable
    public Optional<Curso> getByID(Long id) {
        return repo.findById(id);
    }

    @CachePut
    public Curso save(Curso curso) {
        return repo.save(curso);
    }

    @CacheEvict(key="#id")
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
