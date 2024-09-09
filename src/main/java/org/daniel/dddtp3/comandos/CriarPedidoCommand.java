package org.daniel.dddtp3.comandos;

import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class CriarPedidoCommand extends BaseCommand {
    private final List<String> item_ids;
    public CriarPedidoCommand(String agg_id, List<String> _item_ids) {
        super(agg_id);
        item_ids = _item_ids;
    }
}
