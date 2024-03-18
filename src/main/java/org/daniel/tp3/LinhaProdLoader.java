package org.daniel.tp3;

import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.extra.Utils;
import org.daniel.tp3.service.LinhaProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sound.midi.SysexMessage;

@Order(1)
@Component
public class LinhaProdLoader implements ApplicationRunner {
    @Autowired
    private LinhaProdService linhaProdService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        linhaProdService.incluir(new LinhaProducao(1, "Montagem", true));
        linhaProdService.incluir(new LinhaProducao(2, "Descarte", true));


        for (LinhaProducao linha : linhaProdService.obter()) {
            System.out.println(linha.toString());
        }
        System.out.println(Utils.lineCut("Linhas de produção criadas"));
    }
}
