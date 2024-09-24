package org.daniel.ddd_at.petfriends_transporte.repo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.daniel.ddd_at.petfriends_transporte.model.CEP;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class CEPConverter implements AttributeConverter<CEP, String> {

    @Override
    public String convertToDatabaseColumn(CEP cep) {
        return cep.toString();
    }

    @Override
    public CEP convertToEntityAttribute(String s) {
        String[] parts = s.split("-");
        CEP cep = new CEP();
        if (parts.length > 1) {
            cep.setPrefixo(Integer.parseInt(parts[0]));
            cep.setSufixo(Integer.parseInt(parts[1]));
        }
        return cep;
    }
}
