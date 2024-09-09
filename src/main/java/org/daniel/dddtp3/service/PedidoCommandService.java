package org.daniel.dddtp3.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.daniel.dddtp3.comandos.CriarPedidoCommand;
import org.daniel.dddtp3.comandos.EnviarPedidoCommand;
import org.daniel.dddtp3.comandos.FinalizarPedidoCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PedidoCommandService {
    @Autowired
    private CommandGateway commandGateway;

    public void criarPedido(String agg_id, Set<String> items) {
        commandGateway.send(new CriarPedidoCommand(agg_id, items.stream().toList()));
    }

    public void enviarPedido(String agg_id) {
        commandGateway.send(new EnviarPedidoCommand(agg_id));
    }

    public void finalizarPedido(String agg_id) {
        commandGateway.send(new FinalizarPedidoCommand(agg_id));
    }

}
