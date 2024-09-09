package org.daniel.dddtp3.agregados.pedido;

import io.axoniq.axonserver.connector.AxonServerException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.axonserver.connector.command.AxonServerCommandDispatchException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.daniel.dddtp3.comandos.CriarPedidoCommand;
import org.daniel.dddtp3.comandos.EnviarPedidoCommand;
import org.daniel.dddtp3.comandos.FinalizarPedidoCommand;
import org.daniel.dddtp3.eventos.BaseEvent;
import org.daniel.dddtp3.eventos.PedidoCriadoEvent;
import org.daniel.dddtp3.eventos.PedidoEnviadoEvent;
import org.daniel.dddtp3.eventos.PedidoFinalizadoEvent;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@Getter
@Aggregate
@Entity
@Table(name = "AGG_PEDIDOS")
@NoArgsConstructor
public class PedidoAgg {
    @AggregateIdentifier
    @Id
    private String id;
    private Timestamp ultima_atualizacao;
    private PedidoState state = PedidoState.INVALIDO;
    private List<String> items;

    private void atualizarTimestamp() {
        ultima_atualizacao = Timestamp.from(Instant.now());
    }


    private HashMap<String, Integer> getItemQuants() {
        // retorna a quantidade de cada item
        HashMap<String, Integer> map = new HashMap<>();
        for (String item : items) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }
        return map;
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

    @ExceptionHandler(resultType = AxonServerCommandDispatchException.class)
    public void error(AxonServerCommandDispatchException exp) {
       throw(exp);
    }
}
