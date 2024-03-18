package org.daniel.tp3;

import org.daniel.tp3.domain.Caixa;
import org.daniel.tp3.domain.RobotArm;
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
public class RobotArmLoader implements ApplicationRunner {

    @Autowired
    private RobotArmService roboService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        roboService.incluir(new RobotArm(1, new Vector3d(2.0, 3.0, 0.0), 45.0, false, 2.3));
        roboService.incluir(new RobotArm(2, new Vector3d(-12.0, -20.0, 0.0), 110.0, true, 4.0));
        roboService.incluir(new RobotArm(3, new Vector3d(2.0, 3.0, 0.0), 40.0, true, 2.5));
        roboService.incluir(new RobotArm(4, new Vector3d(-12.0, -20.0, 5.0), 100.0, true, 4.0));

        roboService.transferirRobo(1, 1);
        roboService.transferirRobo(3, 1);
        roboService.transferirRobo(2, 2);
        roboService.transferirRobo(4, 2);

        for (RobotArm robo : roboService.obter()) {
            System.out.println(robo.toString());
        }
        System.out.println(Utils.lineCut("Robos criados"));

    }
}
