package org.daniel.ddd_at.petfriends_pedidos.events;

import org.daniel.ddd_at.petfriends_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PedidoConsumer {
    @Autowired
    PedidoService pedidoService;

    @Bean
    public Consumer<PedidoAlmoxarifadoEvent> almoxarifadoDespache() {
        return pedido -> {
            pedidoService.despacharPedido(pedido.id());
        };
    }

    @Bean
    public Consumer<PedidoTransporteEvent> transporteEntregue() {
        return pedido -> {
            pedidoService.marcarPedidoComoRecebido(pedido.id());
        };
    }

    @Bean
    public Consumer<PedidoCancelarEvent> cancelarPedido() {
        return pedido -> {
            pedidoService.cancelarPedido(pedido.id());
        };
    }
}
