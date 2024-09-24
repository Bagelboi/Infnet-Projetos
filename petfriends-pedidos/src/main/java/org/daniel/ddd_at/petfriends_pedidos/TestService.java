package org.daniel.ddd_at.petfriends_pedidos;

import jakarta.annotation.PostConstruct;
import org.daniel.ddd_at.petfriends_pedidos.model.Pedido;
import org.daniel.ddd_at.petfriends_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TestService {
    @Autowired
    PedidoService pedidoService;

    public void createPedido(BigDecimal id) throws InterruptedException {
        pedidoService.confirmarPagamento(id);
        Thread.sleep(500);
        pedidoService.enviarPedido(id);
        //Thread.sleep(500);
    }
}
