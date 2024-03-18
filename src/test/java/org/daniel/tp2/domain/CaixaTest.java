import javax.vecmath.Vector3d;

import org.daniel.tp2.domain.Caixa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CaixaTest {

    private Caixa caixa;

    @BeforeEach
    public void setUp() {
        caixa = new Caixa("Caixa A", 2.0, 3.0, new Vector3d(1.0, 1.0, 1.0), true);
    }

    @Test
    public void testMover() {
        Vector3d novaPosicao = new Vector3d(2.0, 2.0, 2.0);
        caixa.mover(novaPosicao);
        assertEquals(novaPosicao, caixa.getPosicao());
    }

    @Test
    public void testGetPerimetro() {
        assertEquals(4.0, caixa.getPerimetro());
    }

    @Test
    public void testGetDimensoes() {
        assertEquals(new Vector3d(2.0, 3.0, 2.0), caixa.getDimensoes());
    }
}