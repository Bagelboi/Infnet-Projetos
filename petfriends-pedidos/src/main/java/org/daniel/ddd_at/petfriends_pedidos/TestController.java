package org.daniel.ddd_at.petfriends_pedidos;

import org.daniel.ddd_at.petfriends_pedidos.model.Pedido;
import org.daniel.ddd_at.petfriends_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("pedido")
public class TestController {
    @Autowired
    PedidoService pedidoService;
    @Autowired
    TestService testService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos() {
        return ResponseEntity.ok(pedidoService.getAll());
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.create(pedido));
    }

    @GetMapping("/send/{id}")
    public void confirmarPagamento(@PathVariable Integer id) throws InterruptedException {
        testService.createPedido(BigDecimal.valueOf(id));
    }
}
