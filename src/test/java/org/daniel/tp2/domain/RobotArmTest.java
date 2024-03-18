import javax.vecmath.Vector3d;

import org.daniel.tp2.domain.RobotArm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RobotArmTest {

    private RobotArm arm;
    private RobotArm arm2;

    @BeforeEach
    public void setUp() {
        arm = new RobotArm(1, new Vector3d(2.0, 3.0, 0.0), 45.0, false, 2.0);
        arm2 = new RobotArm(2, new Vector3d(-12.0, -20.0, 0.0), 140.0, false, 4.0);
    }

    @Test
    public void testVerificarManutencao() {
        assertFalse(arm.verificarManutencao());
        assertTrue(arm2.verificarManutencao());
    }

    @Test
    public void testObterInclinacao() {
        assertEquals(4.5, arm.obterInclinacao());
        assertEquals(14.0, arm2.obterInclinacao());
    }

    @Test
    public void testVerificarEstado() {
        assertEquals("Normal", arm.verificarEstado());
        assertEquals("Angulo elevado demais!", arm2.verificarEstado());
    }
    @Test
    public void testObterAlcance() {
        assertEquals(2.0 * Math.cos(45.0) +  arm.obterInclinacao(), arm.obterAlcance());
        assertEquals(4.0 * Math.cos(140.0) +  arm2.obterInclinacao(), arm2.obterAlcance());
    }

    @Test
    public void testMover() {
        Vector3d novaPosicao = new Vector3d(1.0, 1.0, 1.0);

        arm.mover(novaPosicao);
        assertEquals(novaPosicao, arm.getPosicao());

        Vector3d novaPosicao2 = new Vector3d(-5.0, -5.0, -5.0);
        arm2.mover(novaPosicao2);
        assertEquals(novaPosicao2, arm2.getPosicao());
    }

    @Test
    public void testAbrirGarra() {
        arm.abrirGarra();
        assertTrue(arm.getEstadoGarra());
        arm.fecharGarra();
        assertFalse(arm.getEstadoGarra());
    }
}