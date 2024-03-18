package org.daniel.tp3;

import org.daniel.tp3.domain.Caixa;
import org.daniel.tp3.extra.Utils;
import org.daniel.tp3.service.CaixaService;
import org.daniel.tp3.service.RobotArmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.vecmath.Vector3d;

@Order(2)
@Component
public class CaixasLoader implements ApplicationRunner {
    @Autowired
    private CaixaService caixaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        caixaService.incluir(new Caixa("UID_1", 2.0, 3.0, new Vector3d(1.0, 1.0, 1.0), true, "xy10-6"));
        caixaService.incluir(new Caixa("UID_2", 4.0, 2.0, new Vector3d(8.0, 6.0, 1.0), false, "xy10-6"));
        caixaService.incluir(new Caixa("UID_3", 2.5, 3.5, new Vector3d(1.0, 1.0, 0.0), true, "xy10-12"));
        caixaService.incluir(new Caixa("UID_4", 4.5, 2.5, new Vector3d(8.0, 6.0, 3.0), false, "xy10-12"));

        caixaService.transferirCaixa("UID_1", 1);
        caixaService.transferirCaixa("UID_3", 2);
        caixaService.transferirCaixa("UID_2", 2);
        caixaService.transferirCaixa("UID_4", 1);


        for (Caixa caixa : caixaService.obter()) {
            System.out.println(caixa.toString());
        }
        System.out.println(Utils.lineCut("Caixas criadas"));
    }
}
