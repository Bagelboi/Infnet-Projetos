package org.daniel.tp2.spring;

import org.daniel.tp2.domain.Caixa;
import org.daniel.tp2.domain.LinhaProducao;
import org.daniel.tp2.domain.RobotArm;
import org.daniel.tp2.extra.Utils;
import org.daniel.tp2.service.CaixaService;
import org.daniel.tp2.service.RobotArmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.vecmath.Vector3d;

@Order(1)
@Component
public class ObjectsLoader implements ApplicationRunner {

    @Autowired
    private RobotArmService roboService;

    @Autowired
    private CaixaService caixaService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        roboService.incluir(new RobotArm(1, new Vector3d(2.0, 3.0, 0.0), 45.0, false, 2.0));
        roboService.incluir(new RobotArm(2, new Vector3d(-12.0, -20.0, 0.0), 100.0, true, 4.0));
        roboService.incluir(new RobotArm(3, new Vector3d(2.0, 3.0, 0.0), 45.0, false, 2.0));
        roboService.incluir(new RobotArm(4, new Vector3d(-12.0, -20.0, 5.0), 100.0, true, 4.0));


        for (RobotArm robo : roboService.obter()) {
            System.out.println(robo.toString());
        }
        System.out.println(Utils.lineCut("Robos criados"));

        caixaService.incluir(new Caixa("UID_1", 2.0, 3.0, new Vector3d(1.0, 1.0, 1.0), true));
        caixaService.incluir(new Caixa("UID_2", 4.0, 2.0, new Vector3d(8.0, 6.0, 1.0), false));
        caixaService.incluir(new Caixa("UID_3", 2.5, 3.5, new Vector3d(1.0, 1.0, 0.0), true));
        caixaService.incluir(new Caixa("UID_4", 4.5, 2.5, new Vector3d(8.0, 6.0, 3.0), false));

        for (Caixa caixa : caixaService.obter()) {
            System.out.println(caixa.toString());
        }
        System.out.println(Utils.lineCut("Caixas criadas"));

    }
}
