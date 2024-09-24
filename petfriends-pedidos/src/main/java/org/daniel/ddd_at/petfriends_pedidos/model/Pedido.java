package org.daniel.ddd_at.petfriends_pedidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.daniel.ddd_at.petfriends_pedidos.events.PedidoAlmoxarifadoEvent;
import org.daniel.ddd_at.petfriends_pedidos.events.PedidoCancelarEvent;
import org.daniel.ddd_at.petfriends_pedidos.events.PedidoTransporteEvent;
import org.daniel.ddd_at.petfriends_pedidos.states.PedidoState;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigDecimal id;
    PedidoState estado;
    BigDecimal cliente_id;
    Set<ItemPedido> items;
    Timestamp data;

    public Pedido() {
        id = BigDecimal.valueOf(0);
        estado = PedidoState.NOVO;
        cliente_id = BigDecimal.valueOf(0);
        items = new HashSet<>();
        data = Timestamp.from(Instant.now());
    }

    public PedidoAlmoxarifadoEvent toAlmoxarifadoEventObject() {
        return new PedidoAlmoxarifadoEvent(id, items);
    }

    public PedidoTransporteEvent toTransporteEventObject() {
        return new PedidoTransporteEvent(id, cliente_id);
    }

    public PedidoCancelarEvent toSimpleEventObject() {
        return new PedidoCancelarEvent(id);
    }

    public boolean pagamentoConfirmado() {
        if (estado == PedidoState.NOVO) {
            estado = PedidoState.FECHADO;
            return true;
        }
        return false;
    }

    public boolean enviar() {
        if (estado == PedidoState.FECHADO) {
            estado = PedidoState.PREPARACAO;
            return true;
        }
        return false;
    }

    public boolean cancelar() {
        if (estado == PedidoState.FECHADO || estado == PedidoState.PREPARACAO) {
            estado = PedidoState.CANCELADO;
            return true;
        }
        return false;
    }

    public boolean despachar() {
        if (estado == PedidoState.PREPARACAO) {
            estado = PedidoState.TRANSITO;
            return true;
        }
        return false;
    }

    public boolean recebido() {
        if (estado == PedidoState.TRANSITO) {
            estado = PedidoState.ENTREGUE;
            return true;
        }
        return false;
    }

    public boolean podeModificar() {
        return estado != PedidoState.CANCELADO || estado != PedidoState.ENTREGUE;
    }

    public boolean podeProcessar() {
        return podeModificar() && !items.isEmpty();
    }
}
