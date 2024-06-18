package org.daniel.servicetp3.controller;

import org.daniel.servicetp3.model.Aluno;
import org.daniel.servicetp3.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Endpoint(id="aluno")
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @GetMapping
    public ResponseEntity<List<?>> getAllAlunos() {
        List<Aluno> alunos = alunoService.getAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        Optional<Aluno> aluno = alunoService.getByID(id);
        return aluno.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createAluno(@RequestBody Aluno aluno) {
        Aluno savedAluno = alunoService.save(aluno);
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        if (!alunoService.getByID(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aluno.setId(id);
        Aluno updatedAluno = alunoService.save(aluno);
        return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id) {
        if (!alunoService.getByID(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
