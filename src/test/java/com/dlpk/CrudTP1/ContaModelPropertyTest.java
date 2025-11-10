package com.dlpk.CrudTP1;

import com.dlpk.CrudTP1.utils.FormattingUtil;
import com.dlpk.CrudTP1.model.Conta;
import net.jqwik.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ContaModelPropertyTest {

    @Property
    void nomeFormatados(@ForAll("validNames") String nome) {
        Conta conta = new Conta();
        conta.setNome(nome);
        System.out.println(conta.getNome());
        assertThat(conta.getNome()).isEqualTo(FormattingUtil.formatNome(nome));
    }

    @Provide
    Arbitrary<String> validNames() {
        Arbitrary<String> nomes1 = Arbitraries.of("joao ribeiro pinto", "ana maria braga", "carlos silva", "maria clara");
        Arbitrary<String> nomes2 = Arbitraries.strings()
                .withCharRange('a', 'z')
                .ofMinLength(2)
                .ofMaxLength(20);
        return Arbitraries.oneOf(nomes1,nomes2);
    }
}

