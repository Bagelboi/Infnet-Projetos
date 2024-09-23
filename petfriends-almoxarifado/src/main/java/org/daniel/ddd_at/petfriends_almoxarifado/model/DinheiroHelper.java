package org.daniel.ddd_at.petfriends_almoxarifado.model;

import org.javamoney.moneta.Money;

public class DinheiroHelper {
    public static Money build(Number valor) {
        return Money.of(valor, "BRL");
    }
}
