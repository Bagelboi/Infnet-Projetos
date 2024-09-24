package org.daniel.ddd_at.petfriends_transporte.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.daniel.ddd_at.petfriends_transporte.states.EntregaState;
import org.hibernate.annotations.Cascade;
import jakarta.persistence.CascadeType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class Entregador implements Serializable {
    private static class EntregaDataComparator implements Comparator<Entrega> {
        @Override
        public int compare(Entrega e1, Entrega e2) {
            return e1.getData().compareTo(e2.getData());
        }
    }

    public static int max_entregas_ativas = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigDecimal id;
    String nome;

    public Entregador() {
        id = BigDecimal.ZERO;
        nome="";
    }

    public static Entregador New(BigDecimal id, String nome) {
        Entregador entregador = new Entregador();
        entregador.setId(id);
        entregador.setNome(nome);
        return entregador;
    }

    public Set<Entrega> getEntregasAtivas(Set<Entrega> entregas) {
        if (entregas != null)
            return entregas.stream().filter(entrega -> entrega.getEstado() == EntregaState.INCIADA).collect(Collectors.toSet());
        return new HashSet<>();
    }

    public boolean podeComecarPedido(Set<Entrega> entregas) {
        return getEntregasAtivas(entregas).size() < max_entregas_ativas;
    }

    public List<Entrega> getEntregasMaisAntigasEsperando(Set<Entrega> entregas) {
        List<Entrega> entregaList = new ArrayList<>();
        if (entregas != null)
            return entregas.stream().filter(entrega -> entrega.getEstado() == EntregaState.ESPERANDO).sorted(new EntregaDataComparator()).toList();
        return new ArrayList<>();
    }


}
