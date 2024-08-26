package org.daniel.ddd2_tp2.domain.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.tools.json.JSONUtil;
import lombok.Getter;
import org.daniel.ddd2_tp2.domain.entity.Assinatura;
import org.daniel.ddd2_tp2.domain.entity.AssinaturaCliente;

import java.util.Collection;
import java.util.Map;

@Getter
public class AssinaturaClienteEvent extends DomainEvent {
    AssinaturaCliente assinatura;
    public AssinaturaClienteEvent(AssinaturaCliente _ass) {
        super("Assinatura", _ass.getId());
        assinatura = _ass;
    }


    protected Map<String, Object> compressed() {
        return Map.of (
                "agg_type", agg_type,
                "agg_id", agg_id,
                "state", assinatura.getState(),
                "assinatura_id", assinatura.getAssinatura().getId(),
                "cliente_id", assinatura.getCliente_id()
        );
    }

    @Override
    public String convertToPayload() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(compressed());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
