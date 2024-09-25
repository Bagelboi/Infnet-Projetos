package org.daniel.ddd_at.petfriends_transporte.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_transporte.events.PedidoEvent;
import org.daniel.ddd_at.petfriends_transporte.events.PedidoTransporteEvent;
import org.daniel.ddd_at.petfriends_transporte.events.TransportePublisher;
import org.daniel.ddd_at.petfriends_transporte.model.CEP;
import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.daniel.ddd_at.petfriends_transporte.repo.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class EntregaService {

    @Autowired
    private EntregaRepository repository;

    @Autowired
    TransportePublisher transportePublisher;

    public List<Entrega> getAll() {
        return repository.findAll();
    }

    public Entrega create(Entrega entrega) {
        return repository.save(entrega);
    }

    public Optional<Entrega> getById(BigDecimal id) {
        return repository.findById(id);
    }

    public Entrega update(BigDecimal id, Entrega updatedEntrega) {
        return repository.findById(id).map(entrega -> {
            entrega.setEstado(updatedEntrega.getEstado());
            entrega.setEntregador(updatedEntrega.getEntregador());
            return repository.save(entrega);
        }).orElseThrow(() -> new RuntimeException("Entrega não encontrado"));
    }

    public boolean delete(BigDecimal id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Set<Entrega> getEntregasByEntregadorId(BigDecimal id) {
        return repository.findByEntregador_Id(id);
    }

    //Logica de Negócio

    public Entrega criarEntregaParaPedido(PedidoEvent pedido, CEP cep) {
        Entrega entrega = new Entrega();
        entrega.setPedido_id(pedido.id());
        entrega.setCep(cep);
        entrega.setCliente_id(pedido.cliente_id());
        Entrega entrega_criada = create(entrega);
        log.info("Entrega criada {}", entrega_criada);
        return entrega_criada;
    }

    public void iniciarEntrega(BigDecimal id) {
        Entrega entrega = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));

        if (entrega.podeAlterar() && entrega.iniciarEntrega()) {
            log.info("Entrega iniciada {}", id);
            repository.save(entrega);
        } else {
            throw new IllegalStateException("Não é possível iniciar a entrega.");
        }
    }

    public void cancelarEntrega(BigDecimal id) {
        Entrega entrega = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));

        if (entrega.podeAlterar() && entrega.cancelarEntrega()) {
            log.info("Entrega cancelada {}", id);
            repository.save(entrega);
        } else {
            throw new IllegalStateException("Não é possível cancelar a entrega.");
        }
    }

    public void finalizarEntrega(BigDecimal id) {
        Entrega entrega = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));

        if (entrega.podeAlterar() && entrega.finalizarEntrega()) {
            //transportePublisher.transporteEntregue().apply( new PedidoTransporteEvent( entrega.getPedido_id() ));
            log.info("Entrega finalizada {}", id);
            transportePublisher.sendTransporteEntregue(new PedidoTransporteEvent(entrega.getPedido_id()));
            repository.save(entrega);
        } else {
            throw new IllegalStateException("Não é possível finalizar a entrega.");
        }
    }

}
