package org.daniel;

import javax.vecmath.Vector3d;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotArmTest {


        @Test
        public void testEstadoGarra() {
            // aberta
            RobotArm braco1 = new RobotArm(1, new Vector3d(1, 6, 2), 20.0, false);
            braco1.abrirGarra();
            assertTrue(braco1.getEstadoGarra());
            //fechada
            RobotArm braco2 = new RobotArm(1, new Vector3d(2,6,0), 60.0, true);
            braco2.fecharGarra();
            assertFalse(braco2.getEstadoGarra());
        }

        @Test
        public void testVerificarManutencao() {
            // angulo < 90 não precisa consertar
            RobotArm braco1 = new RobotArm(1, new Vector3d(1, 2, 3), 80.0, false);
            assertFalse(braco1.verificarManutencao());

            // angulo > 90 precisa consertar
            RobotArm braco2 = new RobotArm(2, new Vector3d(3, 4, 5), 120.0, false);
            assertTrue(braco2.verificarManutencao());
        }

        @Test
        public void testObterInclinacao() {
            // inclinação == 3
            RobotArm braco1 = new RobotArm(1, new Vector3d(1, 2, 3), 30.0, false);
            assertEquals(3.0, braco1.obterInclinacao(), 0.01);

            // inclinação == 9
            RobotArm braco2 = new RobotArm(2, new Vector3d(3, 4, 5), 90.0, false);
            assertEquals(9.0, braco2.obterInclinacao(), 0.01);
        }

        @Test
        public void testVerificarEstado() {
            // estado == normal
            RobotArm braco1 = new RobotArm(1, new Vector3d(1, 2, 3), 100.0, false);
            assertEquals("Normal", braco1.verificarEstado());

            // estado == elevado d+
            RobotArm braco2 = new RobotArm(2, new Vector3d(3, 4, 5), 130.0, false);
            assertEquals("Angulo elevado demais!", braco2.verificarEstado());

            // estado == normal
            RobotArm braco3 = new RobotArm(3, new Vector3d(5, 6, 7), 110.0, true);
            assertEquals("Normal", braco3.verificarEstado());
    }

}