package org.daniel.ddd_at.petfriends_transporte;

import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.daniel.ddd_at.petfriends_transporte.service.EntregaService;
import org.daniel.ddd_at.petfriends_transporte.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class TestService {
    @Autowired
    EntregadorService entregadorService;
    @Autowired
    EntregaService entregaService;

    public void transportarEntrega(BigDecimal id) throws InterruptedException {
        Set<Entrega> entregas = new HashSet<>(Arrays.asList(entregaService.getById(id).get()));
        entregadorService.assinalarEntregadores( entregas );
        Thread.sleep(500);
        entregaService.iniciarEntrega(id);
        Thread.sleep(1000);
        entregaService.finalizarEntrega(id);
    }

}
