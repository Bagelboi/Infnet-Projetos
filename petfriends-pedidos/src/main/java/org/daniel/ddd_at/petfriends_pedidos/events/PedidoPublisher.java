package org.daniel.ddd_at.petfriends_pedidos.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Configuration
public class PedidoPublisher {

    @Autowired
    private StreamBridge streamBridge;

    public void sendPedidoEnviado(PedidoAlmoxarifadoEvent pedido) {
        Message<PedidoAlmoxarifadoEvent> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("pedidoEnviado-out-0", message);
    }

    public void sendPedidoDespache(PedidoTransporteEvent pedido) {
        Message<PedidoTransporteEvent> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("pedidoDespache-out-0", message);
    }

    public void sendCancelarEntrega(PedidoCancelarEntregaEvent pedido) {
        Message<PedidoCancelarEntregaEvent> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("cancelarEntrega-out-0", message);
    }
}
