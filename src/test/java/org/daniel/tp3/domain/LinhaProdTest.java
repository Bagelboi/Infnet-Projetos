package org.daniel.tp3.domain;

import org.daniel.tp3.domain.LinhaProducao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinhaProdTest {

    private LinhaProducao linhaProducao;

    @BeforeEach
    public void setUp() {
        linhaProducao = new LinhaProducao(1, "Montagem", true);
    }

    @Test
    public void testIsAtiva() {
        assertTrue(linhaProducao.isAtiva());
    }

    @Test
    public void testSetAtiva() {
        linhaProducao.setAtiva(false);
        assertFalse(linhaProducao.isAtiva());
    }
}
