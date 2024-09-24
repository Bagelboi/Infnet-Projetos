package org.daniel.ddd_at.petfriends_transporte.events;

import org.daniel.ddd_at.petfriends_transporte.service.EntregaService;
import org.daniel.ddd_at.petfriends_transporte.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Configuration
public class TransportePublisher {
    @Autowired
    private StreamBridge streamBridge;

    public void sendTransporteEntregue(PedidoTransporteEvent pedido) {
        Message<PedidoTransporteEvent> message = MessageBuilder.withPayload(pedido).build();
        streamBridge.send("transporteEntregue-out-0", message);
    }
}
