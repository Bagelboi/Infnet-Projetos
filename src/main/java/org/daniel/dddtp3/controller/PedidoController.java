package org.daniel.dddtp3.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.daniel.dddtp3.agregados.pedido.PedidoAgg;
import org.daniel.dddtp3.agregados.pedido.PedidoDTO;
import org.daniel.dddtp3.queries.PedidoQueryEvents;
import org.daniel.dddtp3.queries.PedidoQueryGet;
import org.daniel.dddtp3.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private QueryGateway queryGateway;

    @PostMapping("/criar")
    public ResponseEntity<String> criarPedido(@RequestBody PedidoDTO pedido) {
        return ResponseEntity.ok(pedidoService.createPedido(pedido.getItems()));
    }

    @PostMapping("/enviar/{id}")
    public ResponseEntity<?> enviarPedido(@PathVariable String id) {
        pedidoService.enviarPedidoSend(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarPedido(@PathVariable String id) {
        pedidoService.finalizarPedidoSend(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<PedidoAgg>> getAllPedidos() {
        return ResponseEntity.ok( pedidoService.getAllPedidos() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoAgg> getPedidoPorID(@PathVariable String id) {
        return ResponseEntity.ok( queryGateway.query(new PedidoQueryGet(id), ResponseTypes.instanceOf(PedidoAgg.class)).join() );
    }

    @GetMapping("/eventos/{id}")
    public ResponseEntity<String> getPedidoEvents(@PathVariable String id) {
        return ResponseEntity.ok( queryGateway.query(new PedidoQueryEvents(id), ResponseTypes.instanceOf(String.class)).join() );
    }
}
