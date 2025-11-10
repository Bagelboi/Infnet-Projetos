package com.dlpk.CrudTP1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dlpk.CrudTP1.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dlpk.CrudTP1.model.Conta;
import com.dlpk.CrudTP1.service.BancoService;
import com.dlpk.CrudTP1.service.ContaService;

@SpringBootTest
public class BancoServiceTest {

    @Autowired
    private BancoService bancoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
        contaRepository.deleteAll();
    }

    @Test
    void transferir() {
        Long id_src = contaService.criarConta("teste src");
        Long id_dst = contaService.criarConta("teste dst");
        Double quantidade = 100.0;

        contaService.adicionarSaldo(id_src, 100.0);

        boolean resultado = bancoService.transferir(id_src, id_dst, 50.0);
        assertTrue(resultado);
        assertThat(contaService.getConta(id_src).getSaldo()).isLessThan(50.0);
        assertEquals(contaService.getConta(id_dst).getSaldo(), 50.0);
    }
}
