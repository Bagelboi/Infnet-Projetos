package org.daniel.suspediatria2.servicos.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Pediatra;
import org.daniel.suspediatria2.repos.cadastro.RepoCrianca;
import org.daniel.suspediatria2.repos.cadastro.RepoPediatra;
import org.daniel.suspediatria2.servicos.DBServiceFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PediatraService {
    @Autowired
    private RepoCrianca repoPediatra;
    private final DBServiceFunc<Pediatra, String> dbFuncs;

    @Autowired
    public PediatraService(RepoPediatra repoPediatra) {
        this.dbFuncs = new DBServiceFunc<>(repoPediatra);
    }

    public List<Pediatra> getAll() {
        return dbFuncs.findAll();
    }

    public Optional<Pediatra> getById(String cpf) {
        return dbFuncs.findById(cpf);
    }

    public Pediatra create(Pediatra pediatra) {
        return dbFuncs.save(pediatra);
    }

    public Pediatra update(String cpf, Pediatra pediatra) {
        return dbFuncs.update(cpf, pediatra);
    }

    public void delete(String cpf) {
        dbFuncs.delete(cpf);
    }
}
