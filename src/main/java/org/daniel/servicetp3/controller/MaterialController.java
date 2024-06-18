package org.daniel.servicetp3.controller;

import org.daniel.servicetp3.model.Curso;
import org.daniel.servicetp3.model.Material;
import org.daniel.servicetp3.service.CursoService;
import org.daniel.servicetp3.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Endpoint(id="material")
@RequestMapping("/api/materiais")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    @GetMapping
    public ResponseEntity<List<?>> getAllMaterials() {
        List<Material> materiais = materialService.getAll();
        return new ResponseEntity<>(materiais, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable String id) {
        Optional<Material> material = materialService.getByID(id);
        return material.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createMaterial(@RequestBody Material material) {
        Material savedMaterial = materialService.save(material);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMaterial(@PathVariable String id, @RequestBody Material material) {
        if (!materialService.getByID(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        material.setId(id);
        Material updatedMaterial = materialService.save(material);
        return new ResponseEntity<>(updatedMaterial, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable String id) {
        if (!materialService.getByID(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        materialService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
