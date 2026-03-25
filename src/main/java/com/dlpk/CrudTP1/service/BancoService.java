package com.dlpk.CrudTP1.service;

import com.dlpk.CrudTP1.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BancoService {

    @Autowired
    ContaService contaService;

    @Autowired
    AppConfig appconfig;

    public boolean transferir(Long id_source, Long id_dest, Double quantidade) {
        if (saldoSuficiente(id_source, quantidade)) {
            contaService.removerSaldo(id_source, quantidade * appconfig.getTAXA_TRANSFERENCIA());
            contaService.adicionarSaldo(id_dest, quantidade);
            return true;
        }
        return false;
    }

    public void adicionarSaldoInicial(Long id) {
        contaService.adicionarSaldo(id, appconfig.getSALDO_INICIAL());
    }

    public boolean saldoSuficiente(Long id, Double quantidade) {
        return contaService.getConta(id).getSaldo() >= quantidade * appconfig.getTAXA_TRANSFERENCIA();
    }


}
