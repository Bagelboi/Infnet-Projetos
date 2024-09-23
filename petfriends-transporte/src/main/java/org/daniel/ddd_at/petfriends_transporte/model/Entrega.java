package org.daniel.ddd_at.petfriends_transporte.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.daniel.ddd_at.petfriends_transporte.states.EntregaState;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigDecimal id;
    EntregaState estado;
    BigDecimal pedido_id;
    Timestamp data;
    @ManyToOne
    @JoinColumn(name = "entregador_id")
    @Nullable
    Entregador entregador;

    public Entrega() {
        id = BigDecimal.ZERO;
        estado = EntregaState.ESPERANDO;
        pedido_id = BigDecimal.ZERO;
        entregador = null;
        data = Timestamp.from(Instant.now());
    }

    public boolean iniciarEntrega() {
        if (estado == EntregaState.ESPERANDO) {
            estado = EntregaState.INCIADA;
            return true;
        }
        return false;
    }

    public boolean cancelarEntrega() {
        if (estado == EntregaState.INCIADA || estado == EntregaState.ESPERANDO) {
            estado = EntregaState.CANCELADA;
            return true;
        }
        return false;
    }

    public boolean finalizarEntrega() {
        if (estado == EntregaState.INCIADA) {
            estado = EntregaState.FINALIZADA;
            return true;
        }
        return false;
    }

    public boolean podeAlterar() {
        return estado != EntregaState.CANCELADA || estado != EntregaState.FINALIZADA;
    }
}
