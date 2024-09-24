package org.daniel.ddd_at.petfriends_almoxarifado.events;

import org.daniel.ddd_at.petfriends_almoxarifado.DTO.PedidoDTO;
import org.daniel.ddd_at.petfriends_almoxarifado.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class AlmoxarifadoConsumer {
    @Autowired
    PedidoService pedidoService;

    @Bean
    public Consumer<PedidoDTO> pedidoEnviado() {
        return pedido -> {
            pedidoService.despacharPedido(pedido);
        };
    }

}
