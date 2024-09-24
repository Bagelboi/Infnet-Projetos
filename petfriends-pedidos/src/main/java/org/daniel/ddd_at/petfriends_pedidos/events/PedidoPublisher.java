package org.daniel.ddd_at.petfriends_pedidos.events;

import org.daniel.ddd_at.petfriends_pedidos.model.Pedido;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PedidoPublisher {
    @Bean
    public Function<PedidoEvent, Message<PedidoEvent>> pedidoEnviado() {
        return pedido -> MessageBuilder.withPayload(pedido).build();
    }
    @Bean
    public Function<PedidoEvent, Message<PedidoEvent>> pedidoDespache() {
        return pedido -> MessageBuilder.withPayload(pedido).build();
    }
}
