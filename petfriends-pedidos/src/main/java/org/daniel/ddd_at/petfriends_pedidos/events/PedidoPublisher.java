package org.daniel.ddd_at.petfriends_pedidos.events;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Configuration
public class PedidoPublisher {
    @Bean
    public Function<PedidoAlmoxarifadoEvent, Message<PedidoAlmoxarifadoEvent>> pedidoEnviado() {
        return pedido -> MessageBuilder.withPayload(pedido).build();
    }
    @Bean
    public Function<PedidoTransporteEvent, Message<PedidoTransporteEvent>> pedidoDespache() {
        return pedido -> MessageBuilder.withPayload(pedido).build();
    }
    @Bean
    public Function<PedidoCancelarEntregaEvent, Message<PedidoCancelarEntregaEvent>> cancelarEntrega() {
        return pedido -> MessageBuilder.withPayload(pedido).build();
    }
}
