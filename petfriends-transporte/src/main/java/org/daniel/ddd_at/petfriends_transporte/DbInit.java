package org.daniel.ddd_at.petfriends_transporte;

import org.daniel.ddd_at.petfriends_transporte.model.Entregador;
import org.daniel.ddd_at.petfriends_transporte.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DbInit implements ApplicationRunner {
    @Autowired
    EntregadorService entregadorService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] nomes = {"Jefferson", "Maria", "Carlos", "Ana", "Roberto", "Sofia"};

        for (int i = 0; i < nomes.length; i++) {
            entregadorService.create(Entregador.New(BigDecimal.valueOf(i+1), nomes[i]));
        }
    }
}
