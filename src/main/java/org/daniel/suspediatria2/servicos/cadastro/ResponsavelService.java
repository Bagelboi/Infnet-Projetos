package org.daniel.suspediatria2.servicos.cadastro;


import org.daniel.suspediatria2.entidades.cadastro.Responsavel;
import org.daniel.suspediatria2.repos.cadastro.RepoResponsavel;
import org.daniel.suspediatria2.servicos.DBServiceFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private RepoResponsavel repoResponsavel;
    private final DBServiceFunc<Responsavel, String> dbFuncs;

    @Autowired
    public ResponsavelService(RepoResponsavel repoResponsavel) {
        this.dbFuncs = new DBServiceFunc<>(repoResponsavel);
    }

    public List<Responsavel> getAll() {
        return dbFuncs.findAll();
    }

    public Optional<Responsavel> getById(String cpf) {
        return dbFuncs.findById(cpf);
    }

    public Responsavel create(Responsavel responsavel) {
        return dbFuncs.save(responsavel);
    }

    public Responsavel update(String cpf, Responsavel responsavel) {
        return dbFuncs.update(cpf, responsavel);
    }

    public void delete(String cpf) {
        dbFuncs.delete(cpf);
    }
}