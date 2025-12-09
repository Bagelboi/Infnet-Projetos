package org.dlpk.model.frete;

public class TipoFreteIncompativelException extends RuntimeException{
    public TipoFreteIncompativelException() {
        super("Tipo de frete incompativel");
    }
}
