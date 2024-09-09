package org.daniel.dddtp3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.daniel.dddtp3.agregados.pedido.PedidoAgg;
import org.daniel.dddtp3.comandos.CriarPedidoCommand;
import org.daniel.dddtp3.comandos.EnviarPedidoCommand;
import org.daniel.dddtp3.comandos.FinalizarPedidoCommand;
import org.daniel.dddtp3.queries.PedidoQueryEvents;
import org.daniel.dddtp3.queries.PedidoQueryGet;
import org.daniel.dddtp3.repos.PedidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class PedidoService {
    //repository
    @Autowired
    private PedidoRepo repo;

    public String createPedido(List<String> items) {
        String id = UUID.randomUUID().toString();

        criarPedidoSend(id, items);

        return id;
    }

    public Optional<PedidoAgg> getPedidoById(String id) {
        return repo.findById(id);
    }

    public List<PedidoAgg> getAllPedidos() {
        return repo.findAll();
    }

    //commands
    @Autowired
    private CommandGateway commandGateway;

    public void criarPedidoSend(String agg_id, List<String> items) {
        commandGateway.send(new CriarPedidoCommand(agg_id, items.stream().toList()));
    }

    public void enviarPedidoSend(String agg_id) {
        commandGateway.send(new EnviarPedidoCommand(agg_id));
    }

    public void finalizarPedidoSend(String agg_id) {
        commandGateway.send(new FinalizarPedidoCommand(agg_id));
    }

    //queries

    @QueryHandler
    public Optional<PedidoAgg> handle(PedidoQueryGet query) {
        return getPedidoById(query.getId());
    }

    @QueryHandler
    public String handle(PedidoQueryEvents query) {
        return String.join( "\n", getEventos(query.getId()) );
    }

    //event store
    @Autowired
    private EventStore eventStore;

    public List<String> getEventos(String agg_id) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> events = new ArrayList<>();
        eventStore.readEvents(agg_id).asStream().forEach( ev -> {
            try {
                events.add(objectMapper.writeValueAsString( ev.toString() ) );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return events;
    }

}
