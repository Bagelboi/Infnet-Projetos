package com.dlpk.CrudTP1;

import com.dlpk.CrudTP1.repository.ContaRepository;
import com.dlpk.CrudTP1.service.ContaService;
import com.dlpk.CrudTP1.utils.FormattingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ContaServiceTest {
    @Autowired
    ContaRepository contaRepository;

    @Autowired
    ContaService contaService;

    @BeforeEach
    void setUp() {
        contaRepository.deleteAll();
    }

    @Test
    void criarContaExiste() {
        String nome = "teste";
        Long id = contaService.criarConta(nome);
        assertThat(contaService.getConta(id)).isNotNull();
    }

    @Test
    void acharContaPorNome() {
        String nome = "teste";
        Long id = contaService.criarConta(nome);
        String nome_formatado = FormattingUtil.formatNome(nome);
        assertThat(contaService.getContasPorNome(nome_formatado).stream()
            .anyMatch(conta -> conta.getNome().equals(nome_formatado))).isTrue();
    }

    @Test
    void adicionarSaldo() {
        Double quantidade = 100.0;
        Long id = contaService.criarConta("Teste");
        System.out.println("Id é " + id);
        contaService.adicionarSaldo(id, quantidade);
        assertThat(contaService.getConta(id).getSaldo()).isEqualTo(quantidade);
    }

    @Test
    void removerSaldo() {
        Double quantidade = 100.0;
        Long id = contaService.criarConta("Teste");
        System.out.println("Id é " + id);
        contaService.removerSaldo(id, quantidade);
        assertThat(contaService.getConta(id).getSaldo()).isEqualTo(-1*quantidade);
    }
}