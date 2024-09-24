package org.daniel.ddd_at.petfriends_pedidos.repo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.daniel.ddd_at.petfriends_pedidos.model.ItemPedido;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class ItemPedidoConverter implements AttributeConverter<Set<ItemPedido>, String> {

    private static final String ITEM_DELIMITER = ";";
    private static final String FIELD_DELIMITER = "/";

    @Override
    public String convertToDatabaseColumn(Set<ItemPedido> itemPedidoSet) {
        if (itemPedidoSet == null || itemPedidoSet.isEmpty()) {
            return null;
        }

        return itemPedidoSet.stream()
                .map(itemPedido ->
                        itemPedido.getItem_id() + FIELD_DELIMITER +
                        itemPedido.getQuantia())
                .collect(Collectors.joining(ITEM_DELIMITER));
    }

    @Override
    public Set<ItemPedido> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new HashSet<>();
        }

        String[] items = dbData.split(ITEM_DELIMITER);
        Set<ItemPedido> itemPedidoSet = new HashSet<>();

        for (String item : items) {
            String[] data = item.split(FIELD_DELIMITER);
            ItemPedido newItem = new ItemPedido();
            newItem.setItem_id(new BigDecimal(data[0]));
            newItem.setQuantia(Integer.parseInt(data[1]));
            itemPedidoSet.add(newItem);
        }

        return itemPedidoSet;
    }
}
