package org.daniel.dddtp3.eventos;

public class PedidoFinalizadoEvent extends BaseEvent {
    public PedidoFinalizadoEvent(String agg_id) {
        super(agg_id);
    }
}
