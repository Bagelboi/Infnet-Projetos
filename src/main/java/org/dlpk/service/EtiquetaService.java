package org.dlpk.service;

import org.dlpk.model.Entrega;
import org.dlpk.model.frete.ICalculadoraFrete;
import org.dlpk.model.frete.TipoFreteIncompativelException;
import org.dlpk.model.valueobject.Etiqueta;
import org.dlpk.model.valueobject.Resumo;

public class EtiquetaService {

    ICalculadoraFrete calculadoraFrete;

    public EtiquetaService(ICalculadoraFrete calculadoraFrete) {
        this.calculadoraFrete = calculadoraFrete;
    }

    public double calcularFrete(Entrega entrega) {
        if (calculadoraFrete.tipoFreteCompativel(entrega.getTipoFrete()))
            return calculadoraFrete.calcular(entrega.getPeso());
        throw new TipoFreteIncompativelException();
    }

    public Resumo gerarResumoEntrega(Entrega entrega) {
        return new Resumo(entrega.getDestinatario(), entrega.getTipoFrete(), calcularFrete(entrega));
    }

    public Etiqueta gerarEtiqueta(Entrega entrega) {
        return new Etiqueta(entrega.getDestinatario(), entrega.getEndereco(), calcularFrete(entrega));
    }

}
