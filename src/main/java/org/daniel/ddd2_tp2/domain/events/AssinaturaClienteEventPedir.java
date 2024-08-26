package org.daniel.ddd2_tp2.domain.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.daniel.ddd2_tp2.domain.entity.AssinaturaCliente;

import java.util.HashMap;
import java.util.Map;

public class AssinaturaClienteEventPedir extends AssinaturaClienteEvent {
    public AssinaturaClienteEventPedir(AssinaturaCliente _ass) {
        super(_ass);
    }

    @Override
    public String convertToPayload() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> obj = new HashMap<>(compressed());
            obj.put("produtos", assinatura.getAssinatura().getProdutos());
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
