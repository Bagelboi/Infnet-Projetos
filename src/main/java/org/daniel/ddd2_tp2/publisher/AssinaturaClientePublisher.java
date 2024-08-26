package org.daniel.ddd2_tp2.publisher;


import org.daniel.ddd2_tp2.domain.events.AssinaturaClienteEventPedir;
import org.daniel.ddd2_tp2.domain.events.AssinaturaClienteNovaEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssinaturaClientePublisher {
    @Autowired
    private RabbitTemplate template;
    static String PREFIX = "assinatura_cliente_";
    public void nova(AssinaturaClienteNovaEvent event) {
        template.convertAndSend(PREFIX + "nova_ex",
                PREFIX + "nova_rk",
                event.convertToPayload());
    }

    public void pedir(AssinaturaClienteEventPedir event) {
        template.convertAndSend(PREFIX + "pedir_ex",
                PREFIX + "pedir_rk",
                event.convertToPayload());
    }
}
