package org.daniel.servicetp3.controller;

import org.daniel.servicetp3.model.Aluno;
import org.daniel.servicetp3.model.Curso;
import org.daniel.servicetp3.service.AlunoService;
import org.daniel.servicetp3.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Endpoint(id="curso")
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<?>> getAllCursos() {
        List<Curso> cursos = cursoService.getAll();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCursoById(@PathVariable Long id) {
        Optional<Curso> curso = cursoService.getByID(id);
        return curso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createCurso(@RequestBody Curso curso) {
        Curso savedCurso = cursoService.save(curso);
        return new ResponseEntity<>(savedCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        if (!cursoService.getByID(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        curso.setId(id);
        Curso updatedCurso = cursoService.save(curso);
        return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        if (!cursoService.getByID(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
