package org.daniel.devops_tp1_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }


    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        boolean updated = clienteService.update(id, cliente);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deleted = clienteService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}