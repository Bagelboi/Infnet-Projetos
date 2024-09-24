package org.daniel.ddd_at.petfriends_transporte.events;

import org.daniel.ddd_at.petfriends_transporte.service.EntregaService;
import org.daniel.ddd_at.petfriends_transporte.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Configuration
public class TransportePublisher {
    @Bean
    public Function<PedidoTransporteEvent, Message<PedidoTransporteEvent>> transporteEntregue() {
        return pedido -> MessageBuilder.withPayload(pedido).build();
    }
}
