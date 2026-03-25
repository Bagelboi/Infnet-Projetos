package com.dlpk.CrudTP1;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppConfig {

    @Value("${app.taxa.transferencia}")
    private double TAXA_TRANSFERENCIA;

    @Value("${app.saldo.inicial}")
    private double SALDO_INICIAL;

    @Value("${app.senha}")
    private String senha;

}
