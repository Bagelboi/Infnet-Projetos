package org.daniel.ddd_at.petfriends_almoxarifado.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Configuration
public class AlmoxarifadoPublisher {

    @Bean
    public Function<PedidoAlmoxarifadoEvent, Message<PedidoAlmoxarifadoEvent>> almoxarifadoDespache() {
        return pedidoAlmoxarifadoEvent -> MessageBuilder.withPayload(pedidoAlmoxarifadoEvent).build();
    }

    @Bean
    public Function<PedidoAlmoxarifadoCancelarEvent, Message<PedidoAlmoxarifadoCancelarEvent>> cancelarPedido() {
        return pedidoAlmoxarifadoEvent -> MessageBuilder.withPayload(pedidoAlmoxarifadoEvent).build();
    }

}
