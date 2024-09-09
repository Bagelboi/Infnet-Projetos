package org.daniel.dddtp3.eventos;

import lombok.Getter;

import java.util.List;

@Getter
public class PedidoCriadoEvent extends BaseEvent {
    private final List<String> item_ids;
    public PedidoCriadoEvent(String agg_id, List<String> itemIds) {
        super(agg_id);
        item_ids = itemIds;
    }
}
