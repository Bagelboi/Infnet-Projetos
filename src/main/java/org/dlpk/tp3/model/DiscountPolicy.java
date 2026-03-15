package org.dlpk.tp3.model;

import org.dlpk.tp3.value.Preco;

public class DiscountPolicy implements IDiscountPolicy {
    public Preco calculateDiscount(Preco amount, double rate) {
        return new Preco( amount.getValor() * rate );
    }
}