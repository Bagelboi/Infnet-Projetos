package org.daniel.devops_tp1_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepo repo;

    Optional<Cliente> getById(Long id) {
        return repo.findById(id);
    }

    List<Cliente> getAll() {
        return (List<Cliente>)repo.findAll();
    }

    Cliente save(Cliente c) {
        return repo.save(c);
    }

    boolean update(Long id, Cliente cup) {
        var c = getById(id);
        if (c.isPresent()) {
            cup.setId(id);
            save(cup);
            return true;
        }
        return false;
    }

    boolean delete(Long id) {
        var c = getById(id);
        if (c.isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
