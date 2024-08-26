package org.daniel.ddd2_tp2;

import org.daniel.ddd2_tp2.domain.entity.Assinatura;
import org.daniel.ddd2_tp2.domain.entity.AssinaturaCliente;
import org.daniel.ddd2_tp2.domain.enumerator.AssinaturaClienteState;
import org.daniel.ddd2_tp2.domain.enumerator.Especie;
import org.daniel.ddd2_tp2.domain.enumerator.Porte;
import org.daniel.ddd2_tp2.domain.value.AnimalSpec;
import org.daniel.ddd2_tp2.domain.value.Periodicidade;
import org.daniel.ddd2_tp2.repo.AssinaturaRepo;
import org.daniel.ddd2_tp2.service.AssinaturaClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class TestRunner implements ApplicationRunner {
    @Autowired
    AssinaturaRepo assRepo;

    @Autowired
    AssinaturaClienteService assClService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        assRepo.save(new Assinatura(2L, new AnimalSpec(), new HashSet<>(Arrays.asList(1L,2L,3L,4L))));
        assClService.nova( new AssinaturaCliente(1L, AssinaturaClienteState.ATIVA, 343L, new Periodicidade(),  assRepo.findById(2L).get()));
        assClService.pedir(1L);
    }
}
