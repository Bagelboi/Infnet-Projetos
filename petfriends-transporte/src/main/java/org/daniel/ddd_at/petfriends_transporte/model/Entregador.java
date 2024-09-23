package org.daniel.ddd_at.petfriends_transporte.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.daniel.ddd_at.petfriends_transporte.states.EntregaState;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class Entregador {
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
    @OneToMany(mappedBy = "entregador")
    @Cascade( CascadeType.PERSIST )
    @Nullable
    Set<Entrega> entregas;

    public Entregador() {
        id = BigDecimal.ZERO;
        nome="";
        entregas = new HashSet<>();
    }

    public Set<Entrega> getEntregasAtivas() {
        if (entregas != null)
            return entregas.stream().filter(entrega -> entrega.getEstado() == EntregaState.INCIADA).collect(Collectors.toSet());
        return new HashSet<>();
    }

    public boolean podeComecarPedido() {
        return getEntregasAtivas().size() < max_entregas_ativas;
    }

    public List<Entrega> getEntregasMaisAntigasEsperando() {
        List<Entrega> entregaList = new ArrayList<>();
        if (entregas != null)
            return entregas.stream().filter(entrega -> entrega.getEstado() == EntregaState.ESPERANDO).sorted(new EntregaDataComparator()).toList();
        return new ArrayList<>();
    }


}
