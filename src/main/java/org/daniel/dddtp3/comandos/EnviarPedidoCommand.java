package org.daniel.dddtp3.comandos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnviarPedidoCommand extends BaseCommand {
    public EnviarPedidoCommand(String agg_id) {
        super(agg_id);
    }
}
