package org.daniel.ddd2_tp2.domain.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.daniel.ddd2_tp2.domain.entity.AssinaturaCliente;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Getter
public class AssinaturaClienteNovaEvent extends AssinaturaClienteEvent {

    Timestamp time_created;
    public AssinaturaClienteNovaEvent(AssinaturaCliente _ass) {
        super(_ass);
        time_created = new Timestamp(Instant.now().toEpochMilli());
    }

    @Override
    public String convertToPayload() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> obj = new HashMap<>(compressed());
            obj.put("time_created", new SimpleDateFormat("dd-MM-yyyy").format( time_created) );
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
