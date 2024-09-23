package org.daniel.ddd_at.petfriends_almoxarifado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.spi.MoneyAmountFactory;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    BigDecimal id;
    Integer estoque;
    String nome;
    Money preco;

    public Item() {
        id = BigDecimal.ZERO;
        estoque = 0;
        nome = "";
        preco = DinheiroHelper.build(0.0);
    }

    public boolean emEstoque() {
        return estoque > 0;
    }

    public boolean foraDeEstoque() {
        return estoque < 1;
    }

    public Money precoXQuant(Integer quant) {
        return preco.multiply( quant );
    }

    public boolean possivelRetirarEstoque(Integer quant) {
        return estoque - quant > -1;
    }

}
