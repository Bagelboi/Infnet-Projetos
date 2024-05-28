package org.daniel.suspediatria2.controllers.cadastro;

import jakarta.annotation.PostConstruct;
import org.daniel.suspediatria2.entidades.cadastro.UBS;
import org.daniel.suspediatria2.extras.ResourceNotFoundException;
import org.daniel.suspediatria2.servicos.cadastro.UBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubs")
public class UBSController {

    @Autowired
    private UBSService ubsService;

    @GetMapping
    public List<UBS> getAll() {
        return ubsService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UBS> getById(@PathVariable Long id) {
        return ubsService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("UBS não encontrada com ID: " + id));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
