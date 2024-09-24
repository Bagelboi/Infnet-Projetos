package org.daniel.ddd_at.petfriends_transporte.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_transporte.model.CEP;
import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.daniel.ddd_at.petfriends_transporte.repo.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class EntregaService {

    @Autowired
    private EntregaRepository repository;

    public Entrega create(Entrega Entrega) {
        return repository.save(Entrega);
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

    public void iniciarEntrega(BigDecimal id) {
        Entrega entrega = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));

        if (entrega.podeAlterar() && entrega.iniciarEntrega()) {
            repository.save(entrega);
        } else {
            throw new IllegalStateException("Não é possível iniciar a entrega.");
        }
    }

    public void cancelarEntrega(BigDecimal id) {
        Entrega entrega = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));

        if (entrega.podeAlterar() && entrega.cancelarEntrega()) {
            repository.save(entrega);
        } else {
            throw new IllegalStateException("Não é possível cancelar a entrega.");
        }
    }

    public void finalizarEntrega(BigDecimal id) {
        Entrega entrega = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Entrega não encontrada"));

        if (entrega.podeAlterar() && entrega.finalizarEntrega()) {
            repository.save(entrega);
        } else {
            throw new IllegalStateException("Não é possível finalizar a entrega.");
        }
    }

}
