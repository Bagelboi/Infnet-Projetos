package org.daniel.ddd_at.petfriends_almoxarifado.repo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.daniel.ddd_at.petfriends_almoxarifado.model.DinheiroHelper;
import org.javamoney.moneta.Money;

import javax.money.NumberValue;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class DinheiroConverter implements AttributeConverter<Money, String> {


    @Override
    public String convertToDatabaseColumn(Money dinheiro) {
        if (dinheiro == null) {
            return null;
        }

        return NumberFormat.getInstance().format(dinheiro.getNumber() );
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return DinheiroHelper.build(0.0);
        }


        try {
            return DinheiroHelper.build(NumberFormat.getInstance().parse(dbData) );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
