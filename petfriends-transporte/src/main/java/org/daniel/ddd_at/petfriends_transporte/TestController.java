package org.daniel.ddd_at.petfriends_transporte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.daniel.ddd_at.petfriends_transporte.model.CEP;
import org.daniel.ddd_at.petfriends_transporte.model.Entrega;
import org.daniel.ddd_at.petfriends_transporte.model.Entregador;
import org.daniel.ddd_at.petfriends_transporte.service.EntregaService;
import org.daniel.ddd_at.petfriends_transporte.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @Autowired
    EntregadorService entregadorService;
    @Autowired
    EntregaService entregaService;
    @GetMapping
    public ResponseEntity<Entrega> get() {
        return entregaService.getById(BigDecimal.ONE).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("init")
    public void init() throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Entrega entrega = new Entrega();
        Entregador entregador = new Entregador();
        entregador.setNome("Wilson");
        log.info( obj.writeValueAsString( entregadorService.create(entregador) ) );
        CEP cep = new CEP();
        cep.setSufixo(50);
        cep.setPrefixo(22010);
        entrega.setCep( cep );
        entrega.setPedido_id(BigDecimal.ONE);
        //entrega.setEntregador(entregador);
        log.info( obj.writeValueAsString( entregaService.create(entrega) ) );
        entregadorService.encarregarseDeEntrega(BigDecimal.ONE, BigDecimal.ONE);
        log.info(obj.writeValueAsString( entregadorService.getEntregadoresDisponiveis() ));
    }
}
