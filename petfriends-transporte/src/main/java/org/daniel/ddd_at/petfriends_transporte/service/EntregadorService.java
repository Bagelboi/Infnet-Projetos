package org.daniel.ddd_at.petfriends_transporte.service;

import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.daniel.ddd_at.petfriends_transporte.model.Entregador;
import org.daniel.ddd_at.petfriends_transporte.repo.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class EntregadorService {

    @Autowired
    private EntregadorRepository repository;

    @Autowired
        EntregaService entregaService;

    public Entregador create(Entregador Entregador) {
        return repository.save(Entregador);
    }

    public Optional<Entregador> getById(BigDecimal id) {
        return repository.findById(id);
    }

    public Entregador update(BigDecimal id, Entregador updatedEntregador) {
        return repository.findById(id).map(entregador -> {
            entregador.setNome(updatedEntregador.getNome());
            return repository.save(entregador);
        }).orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
    }

    public boolean delete(BigDecimal id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Logica de Negócio

    public void encarregarseDeEntrega(BigDecimal entregador_id, BigDecimal entrega_id) {
        Entregador entregador = repository.findById(entregador_id).orElseThrow(() ->
                new NoSuchElementException("Entregador não encontrado"));
        Entrega entrega = entregaService.getById(entrega_id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));
        if (entrega.getEntregador() == null || !entrega.getEntregador().getId().equals(entregador_id)) {
            entrega.setEntregador(entregador);
            entregaService.update(entrega_id, entrega);
        } else {
            throw new IllegalStateException("Não é possível encarregar-se da entrega.");
        }
    }

    public List<Entregador> getEntregadoresDisponiveis() {
        return repository.findAll().stream().filter( entregador -> entregador.podeComecarPedido() ).toList();
    }

    public void iniciarEntregasAntigasEsperando(BigDecimal entregador_id) {
        Entregador entregador = repository.findById(entregador_id).orElseThrow(() ->
                new NoSuchElementException("Entregador não encontrado"));

        List<Entrega> entregasAntigas = entregador.getEntregasMaisAntigasEsperando();
        int max_entregas = Math.min( Entregador.max_entregas_ativas - entregador.getEntregasAtivas().size(), entregasAntigas.size() );

        if (max_entregas > 0) {
            entregasAntigas.subList(0, max_entregas).forEach(entrega -> {
                entregaService.iniciarEntrega(entrega.getId());
            });
        }

    }

}
