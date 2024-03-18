package org.daniel.tp3.service;

import org.daniel.tp3.domain.Caixa;
import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.domain.RobotArm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.vecmath.Vector3d;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LinhaProdServiceTest {

    private LinhaProdService linhaProdService;

    private LinhaProducao linha;

    private List<Caixa> caixas; // "mock" da lista vista em robotarmservice
    private List<RobotArm> robos; //"mock" da lista vista em caixaservice

    @BeforeAll
    public void setUp() {
        linhaProdService = new LinhaProdService();
        caixas = new ArrayList<>();
        robos = new ArrayList<>();
        robos.add(new RobotArm(1, new Vector3d(2.0, 3.0, 0.0), 45.0, false, 2.0));
        robos.add(new RobotArm(2, new Vector3d(-12.0, -20.0, 0.0), 100.0, true, 4.0));
        caixas.add(new Caixa("Caixa A", 2.0, 3.0, new Vector3d(1.0, 1.0, 1.0), true));
        caixas.add(new Caixa("Caixa B", 4.0, 2.0, new Vector3d(8.0, 6.0, 1.0), false));
        linha = new LinhaProducao(1, "Montagem", true);
    }

    @BeforeEach
    public void setEachUp() {
        linhaProdService.excluir(1);
    }

    @Test
    public void testIncluirLinhaProducao() {
        assertTrue(linhaProdService.incluir(linha));
        assertTrue(linhaProdService.registrado(linha));
        assertNotNull(linhaProdService.obterId(linha.getId()));
    }

    @Test
    public void testExcluirLinhaProducao() {
        linhaProdService.incluir(linha);
        assertTrue(linhaProdService.excluir(1));
        assertFalse(linhaProdService.registrado(linha));
        assertNull(linhaProdService.obterId(linha.getId()));
    }

}