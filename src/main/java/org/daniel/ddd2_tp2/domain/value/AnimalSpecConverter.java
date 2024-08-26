package org.daniel.ddd2_tp2.domain.value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AnimalSpecConverter implements AttributeConverter<AnimalSpec, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(AnimalSpec animalSpec) {
        try {
            return objectMapper.writeValueAsString(animalSpec);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public AnimalSpec convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, AnimalSpec.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }
}