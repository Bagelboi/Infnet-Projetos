package org.daniel.suspediatria2.controllers.cadastro;

import org.daniel.suspediatria2.entidades.cadastro.Responsavel;
import org.daniel.suspediatria2.extras.ResourceNotFoundException;
import org.daniel.suspediatria2.servicos.cadastro.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping
    public List<Responsavel> getAll() {
        return responsavelService.getAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Responsavel> getById(@PathVariable String cpf) {
        return responsavelService.getById(cpf)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Responsavel não encontrada com CPF: " + cpf));
    }

    @PostMapping
    public Responsavel create(@RequestBody Responsavel responsavel) {
        return responsavelService.create(responsavel);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Responsavel> update(@PathVariable String cpf, @RequestBody Responsavel responsavel) {
        return ResponseEntity.ok(responsavelService.update(cpf, responsavel));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> delete(@PathVariable String cpf) {
        responsavelService.delete(cpf);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
