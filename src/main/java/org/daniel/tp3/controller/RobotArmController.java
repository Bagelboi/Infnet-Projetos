package org.daniel.tp3.controller;

import org.daniel.tp3.domain.RobotArm;
import org.daniel.tp3.service.RobotArmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/crud/robo")
public class RobotArmController {

    @Autowired
    private RobotArmService robotArmService;

    @PostMapping("/incluir")
    public boolean incluir(@RequestBody RobotArm robo) {
        return robotArmService.incluir(robo);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody RobotArm robo) {
        robotArmService.atualizar(robo);
    }

    @DeleteMapping("/excluir/{id}")
    public boolean excluir(@PathVariable Integer id) {
        return robotArmService.excluir(id);
    }

    @GetMapping("/obter")
    public Collection<RobotArm> obter() {
        return robotArmService.obter();
    }

    @GetMapping("/obter/{id}")
    public RobotArm obterId(@PathVariable Integer id) {
        return robotArmService.obterId(id);
    }

    @PostMapping("/transferir/{id}/{linha_id}")
    public boolean transferirRobo(@PathVariable Integer id, @PathVariable Integer linha_id) {
        return robotArmService.transferirRobo(id, linha_id);
    }
}
