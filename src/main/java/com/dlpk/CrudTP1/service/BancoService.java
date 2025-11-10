package com.dlpk.CrudTP1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoService {
    final double TAXA_TRANSFERENCIA = 1.15; //15%

    @Autowired
    ContaService contaService;

    public boolean transferir(Long id_source, Long id_dest, Double quantidade) {
        if (saldoSuficiente(id_source, quantidade)) {
            contaService.removerSaldo(id_source, quantidade * TAXA_TRANSFERENCIA);
            contaService.adicionarSaldo(id_dest, quantidade);
            return true;
        }
        return false;
    }

    public boolean saldoSuficiente(Long id, Double quantidade) {
        return contaService.getConta(id).getSaldo() >= quantidade * TAXA_TRANSFERENCIA;
    }


}
