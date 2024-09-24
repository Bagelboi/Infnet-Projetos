package org.daniel.ddd_at.petfriends_pedidos.service;

import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_pedidos.events.PedidoCancelarEntregaEvent;
import org.daniel.ddd_at.petfriends_pedidos.events.PedidoPublisher;
import org.daniel.ddd_at.petfriends_pedidos.model.Pedido;
import org.daniel.ddd_at.petfriends_pedidos.repo.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    PedidoPublisher pedidoPublisher;

    public List<Pedido> getAll() {
        return repository.findAll();
    }

    public Pedido create(Pedido pedido) {
        Pedido pedido_novo = repository.save(pedido);
        return pedido_novo;
    }

    public Optional<Pedido> getById(BigDecimal id) {
        return repository.findById(id);
    }

    public Pedido update(BigDecimal id, Pedido updatedPedido) {
        return repository.findById(id).map(pedido -> {
            pedido.setEstado(updatedPedido.getEstado());
            pedido.setCliente_id(updatedPedido.getCliente_id());
            pedido.setItems(updatedPedido.getItems());
            return repository.save(pedido);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public boolean delete(BigDecimal id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    //Logica de Negócio

    public void confirmarPagamento(BigDecimal id) {
        Pedido pedido = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Pedido não encontrado"));

        if (pedido.podeProcessar() && pedido.pagamentoConfirmado()) {
            repository.save(pedido);
        } else {
            throw new IllegalStateException("Não é possível confirmar o pagamento.");
        }
    }

    public void enviarPedido(BigDecimal id) {
        Pedido pedido = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Pedido não encontrado"));

        if (pedido.podeProcessar() && pedido.enviar()) {
            Pedido pedido_env = repository.save(pedido);
           // pedidoPublisher.pedidoEnviado().apply(pedido_env.toAlmoxarifadoEventObject());
            pedidoPublisher.sendPedidoEnviado(pedido_env.toAlmoxarifadoEventObject());
        } else {
            throw new IllegalStateException("Não é possível enviar o pedido.");
        }
    }

    public void cancelarPedido(BigDecimal id) {
        Pedido pedido = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Pedido não encontrado"));

        if (pedido.podeProcessar() && pedido.cancelar()) {
            //pedidoPublisher.cancelarEntrega().apply( new PedidoCancelarEntregaEvent(pedido.getId(), pedido.getCliente_id()));
            pedidoPublisher.sendCancelarEntrega(new PedidoCancelarEntregaEvent(pedido.getId(), pedido.getCliente_id()));
            repository.save(pedido);
        } else {
            throw new IllegalStateException("Não é possível cancelar o pedido.");
        }
    }

    public void despacharPedido(BigDecimal id) {
        Pedido pedido = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Pedido não encontrado"));
        log.info("despachando {}", id);
        if (pedido.podeProcessar() && pedido.despachar()) {
            Pedido pedido_despachado = repository.save(pedido);
            pedido_despachado.setItems( new HashSet<>() );
            //pedidoPublisher.pedidoDespache().apply(pedido_despachado.toTransporteEventObject());
            pedidoPublisher.sendPedidoDespache(pedido_despachado.toTransporteEventObject());
        } else {
            throw new IllegalStateException("Não é possível despachar o pedido.");
        }
    }

    public void marcarPedidoComoRecebido(BigDecimal id) {
        Pedido pedido = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Pedido não encontrado"));

        if (pedido.podeProcessar() && pedido.recebido()) {
            repository.save(pedido);
        } else {
            throw new IllegalStateException("Não é possível marcar o pedido como recebido.");
        }
    }
}
