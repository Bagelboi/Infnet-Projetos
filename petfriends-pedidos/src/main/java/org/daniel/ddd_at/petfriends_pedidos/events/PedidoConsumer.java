package org.daniel.ddd_at.petfriends_pedidos.events;

import org.daniel.ddd_at.petfriends_pedidos.model.Pedido;
import org.daniel.ddd_at.petfriends_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class PedidoConsumer {
    @Autowired
    PedidoService pedidoService;

    @Bean
    public Consumer<Pedido> almoxarifadoDespache() {
        return pedido -> {
            pedidoService.despacharPedido(pedido.getId());
        };
    }

    @Bean
    public Consumer<Pedido> transporteEntregue() {
        return pedido -> {
            pedidoService.marcarPedidoComoRecebido(pedido.getId());
        };
    }

    @Bean
    public Consumer<Pedido> cancelarPedido() {
        return pedido -> {
            pedidoService.cancelarPedido(pedido.getId());
        };
    }
}
