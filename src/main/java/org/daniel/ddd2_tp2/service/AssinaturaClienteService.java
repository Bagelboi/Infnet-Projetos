package org.daniel.ddd2_tp2.service;

import org.daniel.ddd2_tp2.domain.entity.AssinaturaCliente;
import org.daniel.ddd2_tp2.domain.enumerator.AssinaturaClienteState;
import org.daniel.ddd2_tp2.domain.events.AssinaturaClienteEventPedir;
import org.daniel.ddd2_tp2.domain.events.AssinaturaClienteNovaEvent;
import org.daniel.ddd2_tp2.publisher.AssinaturaClientePublisher;
import org.daniel.ddd2_tp2.repo.AssinaturaClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AssinaturaClienteService {
    @Autowired
    AssinaturaClienteRepo repo;

    @Autowired
    AssinaturaClientePublisher publisher;

    public AssinaturaCliente nova(AssinaturaCliente _ass) {
        publisher.nova( new AssinaturaClienteNovaEvent(_ass) );
        return repo.save(_ass);
    }

    public Optional<AssinaturaCliente> findById(Long id) {
        return repo.findById(id);
    }

    public void pedir(Long id) {
        findById(id).ifPresent( ass ->  {
            if (ass.getState() == AssinaturaClienteState.ATIVA) {
                ass.pedirProdutos();
                publisher.pedir(new AssinaturaClienteEventPedir(ass));
            }
        } );
    }
}
