package org.daniel.ddd2_tp2.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daniel.ddd2_tp2.domain.enumerator.AssinaturaClienteState;
import org.daniel.ddd2_tp2.domain.enumerator.TipoData;
import org.daniel.ddd2_tp2.domain.value.Periodicidade;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssinaturaCliente {
    @Id
    Long id;
    AssinaturaClienteState state = AssinaturaClienteState.ATIVA;
    Long cliente_id;
    Periodicidade periodicidade;
    @ManyToOne
    Assinatura assinatura;

    public void alterarPeriodicidade(TipoData _tipodata, int _intervalo) {
        periodicidade = new Periodicidade( Math.max(1, _intervalo), _tipodata );
    }

    public void ativar() {
        if (state == AssinaturaClienteState.INATIVA)
            state = AssinaturaClienteState.ATIVA;
    }

    public void desativar() {
        if (state == AssinaturaClienteState.ATIVA)
            state = AssinaturaClienteState.INATIVA;
    }

    public void pedirProdutos() {
        if (state == AssinaturaClienteState.ATIVA)
            state = AssinaturaClienteState.ESPERANDO_PRODUTOS;
    }

    public void produtosRecebidos() {
        state = AssinaturaClienteState.ATIVA;
    }

}
