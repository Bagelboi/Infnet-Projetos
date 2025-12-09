package org.dlpk.model;

import org.dlpk.model.frete.TIPO_FRETE;
import org.dlpk.model.valueobject.Destinario;
import org.dlpk.model.valueobject.Endereco;

public class Entrega {

    private final Endereco endereco;
    private final double peso;
    private final TIPO_FRETE tipoFrete;
    private final Destinario destinatario;


    public Entrega(Endereco endereco, double peso, TIPO_FRETE tipoFrete, Destinario destinatario) {
        this.endereco = endereco;
        this.tipoFrete = tipoFrete;
        this.destinatario = destinatario;
        if (peso > 0)
            this.peso = peso;
        else
            throw new IllegalArgumentException("Peso zero ou negativo");
    }


    public Endereco getEndereco() {
        return endereco;
    }

    public double getPeso() {
        return peso;
    }

    public TIPO_FRETE getTipoFrete() {
        return tipoFrete;
    }

    public Destinario getDestinatario() {
        return destinatario;
    }
}
