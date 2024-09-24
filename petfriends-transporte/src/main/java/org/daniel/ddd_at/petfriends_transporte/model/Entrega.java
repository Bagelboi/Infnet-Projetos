package org.daniel.ddd_at.petfriends_transporte.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.daniel.ddd_at.petfriends_transporte.states.EntregaState;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigDecimal id;
    EntregaState estado;
    BigDecimal cliente_id;
    BigDecimal pedido_id;
    Timestamp data;
    CEP cep;
    @ManyToOne
    @JoinColumn(name = "entregador_id")
    //@Nullable
    Entregador entregador;

    public Entrega() {
        id = BigDecimal.ZERO;
        pedido_id = BigDecimal.ZERO;
        entregador = null;
        estado = EntregaState.ESPERANDO;
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
