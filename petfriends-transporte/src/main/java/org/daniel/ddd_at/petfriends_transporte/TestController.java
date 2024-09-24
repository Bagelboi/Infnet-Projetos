package org.daniel.ddd_at.petfriends_transporte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_transporte.model.CEP;
import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.daniel.ddd_at.petfriends_transporte.model.Entregador;
import org.daniel.ddd_at.petfriends_transporte.service.EntregaService;
import org.daniel.ddd_at.petfriends_transporte.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("transporte")
public class TestController {
    @Autowired
    EntregadorService entregadorService;
    @Autowired
    EntregaService entregaService;
    @Autowired
    TestService testService;
    @GetMapping("/entregas")
    public ResponseEntity<List<Entrega>> getEntregas() {
        return ResponseEntity.ok(entregaService.getAll());
    }

    @GetMapping("/entregadores")
    public ResponseEntity<List<Entregador>> getEntregadores() {
        return ResponseEntity.ok(entregadorService.getAll());
    }

    @GetMapping("/send/{id}")
    public void transportar(@PathVariable Integer id) throws InterruptedException {
        testService.transportarEntrega(BigDecimal.valueOf(id));
    }
}
