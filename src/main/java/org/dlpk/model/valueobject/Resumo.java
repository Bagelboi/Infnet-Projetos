package org.dlpk.model.valueobject;

import org.dlpk.model.frete.TIPO_FRETE;

public record Resumo(Destinario destinario, TIPO_FRETE tipoFrete, double valor) {
}
