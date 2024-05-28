package org.daniel.suspediatria2.servicos.cadastro;

import jakarta.annotation.PostConstruct;
import org.daniel.suspediatria2.entidades.cadastro.Pediatra;
import org.daniel.suspediatria2.entidades.cadastro.Responsavel;
import org.daniel.suspediatria2.entidades.cadastro.UBS;
import org.daniel.suspediatria2.repos.cadastro.RepoResponsavel;
import org.daniel.suspediatria2.repos.cadastro.RepoUBS;
import org.daniel.suspediatria2.servicos.DBServiceFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UBSService {
    @Autowired
    private RepoUBS repoUBS;
    private final DBServiceFunc<UBS, Long> dbFuncs;

    @Autowired
    public UBSService(RepoUBS repoUBS) {
        this.dbFuncs = new DBServiceFunc<>(repoUBS);
    }

    public List<UBS> getAll() {
        return dbFuncs.findAll();
    }

    public Optional<UBS> getById(Long id) {
        return dbFuncs.findById(id);
    }


    public UBS create(UBS ubs) {
        return dbFuncs.save(ubs);
    }

    @PostConstruct
    public void populate() {
        this.create( new UBS(0L, "CMS Manoel Jose Ferreira", "R. Silveira Martins, 161 - Catete, Rio de Janeiro - RJ, 22221-000") );
        this.create (new UBS(1L, "CMS Oswaldo Cruz", "Av. Henrique Valadares, 151 - Centro, Rio de Janeiro - RJ, 20231-031"));
        this.create (new UBS(2L, "SMS CMS Dom Helder Camara Ap 21", "R. Voluntários da Pátria, 136 - Botafogo, Rio de Janeiro - RJ, 22270-010" ));
        this.create (new UBS(3L, "CMS Maria Augusta Estrella", "R. Visc. de Santa Isabel, 56 - Vila Isabel, Rio de Janeiro - RJ, 20560-121"));
    }
}
