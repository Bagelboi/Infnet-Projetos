package org.daniel.suspediatria2.servicos.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Crianca;
import org.daniel.suspediatria2.repos.cadastro.RepoCrianca;
import org.daniel.suspediatria2.servicos.DBServiceFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriancaService {
    @Autowired
    private RepoCrianca repoCrianca;

    private final DBServiceFunc<Crianca, String> dbFuncs;

    public CriancaService(RepoCrianca repoCrianca) {
        this.dbFuncs = new DBServiceFunc<>(repoCrianca);
    }
    public List<Crianca> getAll() {
        return dbFuncs.findAll();
    }

    public Optional<Crianca> getById(String cpf) {
        return dbFuncs.findById(cpf);
    }

    public Crianca create(Crianca crianca) {
        return dbFuncs.save(crianca);
    }

    public Crianca update(String cpf, Crianca crianca) {
        return dbFuncs.update(cpf, crianca);
    }

    public void delete(String cpf) {
        dbFuncs.delete(cpf);
    }
}
