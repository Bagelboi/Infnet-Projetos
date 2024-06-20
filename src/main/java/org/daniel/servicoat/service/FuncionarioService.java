package org.daniel.servicoat.service;

import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.daniel.servicoat.model.Departamento;
import org.daniel.servicoat.model.Funcionario;
import org.daniel.servicoat.repo.DepartamentoRepo;
import org.daniel.servicoat.repo.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "funcionarios")
public class FuncionarioService {
    @Autowired
    FuncionarioRepository repo;

    public List<Funcionario> getAll() {
        return (List<Funcionario>) repo.findAll();
    }

    //Cacheable não funciona com Optional
    public Optional<Funcionario> getByID(Long id) {
        return repo.findById(id);
    }

    @CachePut
    public Funcionario save(Funcionario fun) {
        return repo.save(fun);
    }

    @CacheEvict(key="#id")
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
