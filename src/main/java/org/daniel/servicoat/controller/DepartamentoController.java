package org.daniel.servicoat.controller;

import org.daniel.servicoat.model.Departamento;
import org.daniel.servicoat.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<?>> getAllDepartamentos() {
        List<Departamento> departamentos = departamentoService.getAll();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartamentoById(@PathVariable Long id) {
        Optional<Departamento> departamento = departamentoService.getByID(id);
        return departamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createDepartamento(@RequestBody Departamento departamento) {
        Departamento savedDepartamento = departamentoService.save(departamento);
        return new ResponseEntity<>(savedDepartamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        if (departamentoService.getByID(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departamento.setId(id);
        Departamento updatedDepartamento = departamentoService.save(departamento);
        return new ResponseEntity<>(updatedDepartamento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartamento(@PathVariable Long id) {
        if (departamentoService.getByID(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departamentoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
