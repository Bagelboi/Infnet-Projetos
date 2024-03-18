package org.daniel.tp2.service;

import org.daniel.tp2.domain.Caixa;
import org.daniel.tp2.domain.LinhaProducao;
import org.daniel.tp2.domain.RobotArm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.vecmath.Vector3d;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObjectsServiceTest {

    private LinhaProdService linhaProdService;

    private RobotArmService roboService;

    private CaixaService caixaService;

    @BeforeAll
    public void setUp() {
        linhaProdService = new LinhaProdService();
        roboService = new RobotArmService();
        caixaService = new CaixaService();

        roboService.setService(linhaProdService);
        caixaService.setService(linhaProdService);

        roboService.incluir(new RobotArm(1, new Vector3d(2.0, 3.0, 0.0), 45.0, false, 2.0));
        roboService.incluir(new RobotArm(2, new Vector3d(-12.0, -20.0, 0.0), 100.0, true, 4.0));
        caixaService.incluir(new Caixa("UID_1", 2.0, 3.0, new Vector3d(1.0, 1.0, 1.0), true));
        caixaService.incluir(new Caixa("UID_2", 4.0, 2.0, new Vector3d(8.0, 6.0, 1.0), false));
        linhaProdService.incluir( new LinhaProducao(1, "Montagem", true) );
    }

    @Test
    public void roboTest() {
        assertNotNull(roboService.obterId(2));
        assertNull(roboService.obterId(3));
        assertTrue(linhaProdService.roboRegistrado(2));
        assertFalse(linhaProdService.roboRegistrado(3));
        assertTrue( roboService.excluir(2) );
        assertFalse( roboService.excluir(2) );
        assertTrue( roboService.incluir(new RobotArm(2, new Vector3d(-12.0, -20.0, 0.0), 100.0, true, 4.0)) );
    }

    @Test
    public void caixaTest() {
        assertNotNull(caixaService.obterId("UID_2"));
        assertNull(caixaService.obterId("UID_3"));
        assertTrue(linhaProdService.caixaRegistrado("UID_2"));
        assertFalse(linhaProdService.caixaRegistrado("UID_3"));
        assertTrue(caixaService.excluir("UID_2"));
        assertFalse(caixaService.excluir("UID_2"));
        assertTrue(caixaService.incluir(new Caixa("UID_3", 4.0, 2.0, new Vector3d(-12.0, -20.0, 0.0), true)));
    }

}
