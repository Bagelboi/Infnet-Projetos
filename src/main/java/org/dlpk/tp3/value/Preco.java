package org.dlpk.tp3.value;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@NoArgsConstructor
public class Preco {
    @Getter
    double valor;

    public void setValor(double valor) {
        //https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
        BigDecimal decimal = BigDecimal.valueOf(valor);
        decimal = decimal.setScale(2, RoundingMode.HALF_UP);
        this.valor = decimal.doubleValue();
    }

    @Override
    public String toString() {
        return "R$ " + String.format("%.2f", this.valor);
    }
}
