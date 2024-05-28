package org.daniel.suspediatria2.controllers.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Crianca;
import org.daniel.suspediatria2.extras.ResourceNotFoundException;
import org.daniel.suspediatria2.servicos.cadastro.CriancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequestMapping("/criancas")
public class CriancaController {

    @Autowired
    private CriancaService criancaService;

    @GetMapping
    public List<Crianca> getAll() {
        return criancaService.getAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Crianca> getById(@PathVariable String cpf) {
        return criancaService.getById(cpf)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Crianca não encontrada com CPF: " + cpf));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Crianca create(@RequestBody Crianca crianca) {
        return criancaService.create(crianca);
    }

    @PutMapping(value="/{cpf}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Crianca> update(@PathVariable String cpf, @RequestBody Crianca crianca) {
        return ResponseEntity.ok(criancaService.update(cpf, crianca));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        criancaService.delete(cpf);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
