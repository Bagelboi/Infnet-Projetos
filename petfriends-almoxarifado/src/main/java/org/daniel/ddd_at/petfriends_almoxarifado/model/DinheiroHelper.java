package org.daniel.ddd_at.petfriends_almoxarifado.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.javamoney.moneta.Money;

import java.io.IOException;

public class DinheiroHelper {
    public static Money build(Number valor) {
        return Money.of(valor, "BRL");
    }

    public static class MoneySerializer extends JsonSerializer<Money> {

        @Override
        public void serialize(Money money, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeStartObject();
            gen.writeStringField("moeda", money.getCurrency().getCurrencyCode());
            gen.writeNumberField("valor", money.getNumber().doubleValue());
            gen.writeEndObject();
        }
    }
}
