package org.daniel.ddd_at.petfriends_transporte.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Random;

@Getter
public class CEP {
    Integer prefixo;
    Integer sufixo;

    public CEP() {
        prefixo = 0;
        sufixo = 0;
    }
    public static CEP randomCEP() {
        CEP cep = new CEP();
        Random rand = new Random();
        cep.setPrefixo(rand.nextInt( 0, 99999 ) );
        cep.setSufixo(rand.nextInt( 0, 999 ) );
        return cep;
    }
    public void setPrefixo(Integer value) {
        this.prefixo = Math.min(99999, value);
    }

    public void setSufixo(Integer value) {
        this.sufixo = Math.min(999, value);
    }

    @Override
    public String toString() {
        return String.format("%05d-%03d", prefixo, sufixo);
    }
}
