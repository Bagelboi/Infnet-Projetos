package org.daniel.ddd_at.petfriends_almoxarifado.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Configuration
public class AlmoxarifadoPublisher {

    @Autowired
    private StreamBridge streamBridge;

    public void sendAlmoxarifadoDespache(PedidoAlmoxarifadoEvent pedido) {
        Message<PedidoAlmoxarifadoEvent> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("almoxarifadoDespache-out-0", message);
    }

    public void sendCancelarPedido(PedidoAlmoxarifadoCancelarEvent pedido) {
        Message<PedidoAlmoxarifadoCancelarEvent> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("cancelarPedido-out-0", message);
    }
}
