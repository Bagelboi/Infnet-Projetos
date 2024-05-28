package org.daniel.suspediatria2.controllers.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Pediatra;
import org.daniel.suspediatria2.extras.ResourceNotFoundException;
import org.daniel.suspediatria2.servicos.cadastro.PediatraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pediatras")
public class PediatraController {

    @Autowired
    private PediatraService pediatraService;

    @GetMapping
    public List<Pediatra> getAll() {
        return pediatraService.getAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pediatra> getByCpf(@PathVariable String cpf) {
        return pediatraService.getById(cpf)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Pediatra não encontrada com CPF: " + cpf));
    }

    @PostMapping
    public Pediatra create(@RequestBody Pediatra pediatra) {
        return pediatraService.create(pediatra);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Pediatra> update(@PathVariable String cpf, @RequestBody Pediatra pediatra) {
        return ResponseEntity.ok(pediatraService.update(cpf, pediatra));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        pediatraService.delete(cpf);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
