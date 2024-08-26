package org.daniel.ddd2_tp2.domain.value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class PeriodicidadeConverter  implements AttributeConverter<Periodicidade, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Periodicidade periodicidade) {
        try {
            return objectMapper.writeValueAsString(periodicidade);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Periodicidade convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, Periodicidade.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }
}
