package org.daniel.dddtp3.agregados.pedido;

import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.daniel.dddtp3.comandos.CriarPedidoCommand;
import org.daniel.dddtp3.comandos.EnviarPedidoCommand;
import org.daniel.dddtp3.comandos.FinalizarPedidoCommand;
import org.daniel.dddtp3.eventos.PedidoCriadoEvent;
import org.daniel.dddtp3.eventos.PedidoEnviadoEvent;
import org.daniel.dddtp3.eventos.PedidoFinalizadoEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Aggregate
public class PedidoAgg {
    @AggregateIdentifier
    private String id;
    private Timestamp ultima_atualizacao;
    private PedidoState state = PedidoState.INVALIDO;
    private List<String> items;

    private void atualizarTimestamp() {
        ultima_atualizacao = Timestamp.from(Instant.now());
    }

    @CommandHandler
    public PedidoAgg(CriarPedidoCommand command) {
        AggregateLifecycle.apply(new PedidoCriadoEvent(command.getAgg_id(), command.getItem_ids()));
    }

    @EventSourcingHandler
    public void on(PedidoCriadoEvent event) {
        this.id = event.getAgg_id();
        this.items = event.getItem_ids();
        atualizarTimestamp();
        state = PedidoState.CRIADO;
    }

    @CommandHandler
    public void handle(EnviarPedidoCommand command) {
        if (state == PedidoState.CRIADO) {
            AggregateLifecycle.apply( new PedidoEnviadoEvent(command.getAgg_id()));
        }
    }

    @EventSourcingHandler
    public void on(PedidoEnviadoEvent event) {
        atualizarTimestamp();
        state = PedidoState.ENVIADO;
    }

    @CommandHandler
    public void handle(FinalizarPedidoCommand command) {
        AggregateLifecycle.apply( new PedidoFinalizadoEvent(command.getAgg_id()));
    }

    @EventSourcingHandler
    public void on(PedidoFinalizadoEvent event) {
        atualizarTimestamp();
        state = PedidoState.FINALIZADO;
    }

}
