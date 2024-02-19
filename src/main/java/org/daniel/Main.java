package org.daniel;

import javax.vecmath.Vector3d;

public class Main {
    public static void main(String[] args) {
        RobotArm braco1 = new RobotArm(1, new Vector3d(1, 2, 3), 45.0, false);
        braco1.setAngulo(60.0);
        braco1.abrirGarra();
        braco1.mover(new Vector3d(5, 6, 7));
        System.out.println(braco1.status());

        RobotArm braco2 = new RobotArm(2, new Vector3d(3, 4, 5), 150.0, true);
        braco2.setAngulo(100.0);
        braco2.fecharGarra();
        braco2.mover(new Vector3d(8, 9, 10));
        System.out.println(braco2.status());
    }
}