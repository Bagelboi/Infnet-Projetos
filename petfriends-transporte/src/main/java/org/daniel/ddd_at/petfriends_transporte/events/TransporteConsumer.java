package org.daniel.ddd_at.petfriends_transporte.events;

import org.daniel.ddd_at.petfriends_transporte.model.CEP;
import org.daniel.ddd_at.petfriends_transporte.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class TransporteConsumer {
    @Autowired
    EntregaService entregaService;


    @Bean
    public Consumer<PedidoEvent> pedidoDespache() {
        return pedido -> {
           entregaService.criarEntregaParaPedido(pedido, CEP.randomCEP());
        };
    }

    @Bean
    public Consumer<PedidoCancelarEvent> cancelarEntrega() {
        return pedido -> {
            entregaService.cancelarEntrega(pedido.id());
        };
    }
}
