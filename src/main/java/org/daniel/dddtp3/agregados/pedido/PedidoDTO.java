package org.daniel.dddtp3.agregados.pedido;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class PedidoDTO {
    private List<String> items;
}
