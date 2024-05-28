package org.daniel.suspediatria2.servicos;

import org.daniel.suspediatria2.extras.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DBServiceFunc<T, ID> {

    private final CrudRepository<T, ID> repository;

    public DBServiceFunc(CrudRepository repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        for (T item : repository.findAll() )
            list.add(item);
        return list;
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T update(ID id, T entity) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        } else {
            throw new ResourceNotFoundException("Entidade não encontrada com ID: " + id);
        }
    }

    public void delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Entidade não encontrada com ID: " + id);
        }
    }
}