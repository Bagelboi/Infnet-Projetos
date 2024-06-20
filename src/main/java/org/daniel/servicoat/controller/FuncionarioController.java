package org.daniel.servicoat.controller;

import org.daniel.servicoat.model.Funcionario;
import org.daniel.servicoat.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<?>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.getAll();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFuncionarioById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getByID(id);
        return funcionario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.save(funcionario);
        return new ResponseEntity<>(savedFuncionario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        if (funcionarioService.getByID(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        funcionario.setId(id);
        Funcionario updatedFuncionario = funcionarioService.save(funcionario);
        return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable Long id) {
        if (funcionarioService.getByID(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        funcionarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}