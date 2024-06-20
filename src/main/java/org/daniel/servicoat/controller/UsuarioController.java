package org.daniel.servicoat.controller;

import org.daniel.servicoat.model.Usuario;
import org.daniel.servicoat.repo.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/user")
public class UsuarioController {
    @Autowired
    UsuarioRepo repo;

    @GetMapping
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Usuario user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new ResponseEntity<>(repo.save(user), HttpStatus.CREATED);
    }
}
