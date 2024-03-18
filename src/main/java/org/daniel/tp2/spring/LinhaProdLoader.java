package org.daniel.tp2.spring;

import org.daniel.tp2.domain.LinhaProducao;
import org.daniel.tp2.extra.Utils;
import org.daniel.tp2.service.LinhaProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sound.midi.SysexMessage;

@Order(2)
@Component
public class LinhaProdLoader implements ApplicationRunner {
    @Autowired
    private LinhaProdService linhaProdService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        linhaProdService.incluir(new LinhaProducao(1, "Montagem", true));
        linhaProdService.transferirRobo(1, 1);
        linhaProdService.transferirRobo(3, 1);
        linhaProdService.transferirCaixa("UID_2", 2);
        linhaProdService.transferirCaixa("UID_4", 2);

        linhaProdService.incluir(new LinhaProducao(2, "Descarte", true));
        linhaProdService.transferirRobo(2, 2);
        linhaProdService.transferirRobo(4, 2);
        linhaProdService.transferirCaixa("UID_1", 2);
        linhaProdService.transferirCaixa("UID_3", 2);

        for (LinhaProducao linha : linhaProdService.obter()) {
            System.out.println(linha.toString());
        }
        System.out.println(Utils.lineCut("Linhas de produção criadas"));
    }
}
