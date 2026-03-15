package org.dlpk.tp3.model;

import org.dlpk.tp3.value.Preco;

public interface IDiscountPolicy {
    Preco calculateDiscount(Preco amount, double rate) ;
}
