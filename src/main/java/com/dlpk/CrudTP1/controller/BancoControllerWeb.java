package com.dlpk.CrudTP1.controller;

import com.dlpk.CrudTP1.model.Conta;
import com.dlpk.CrudTP1.model.DTO.ContaDTO;
import com.dlpk.CrudTP1.model.DTO.TransferenciaDTO;
import com.dlpk.CrudTP1.service.BancoService;
import com.dlpk.CrudTP1.service.ContaService;
import com.dlpk.CrudTP1.service.PasswordAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class BancoControllerWeb {

    @Autowired
    ContaService contaService;

    @Autowired
    BancoService bancoService;

    @Autowired
    PasswordAuthService passService;

    private void autenticarSenha(String senha_dada) {
        if (!passService.senhaCorreta(senha_dada))
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Senha inválida"
            );
    }

    private List<ContaDTO> contasToDTOList(List<Conta> contas) {
        List<ContaDTO> contasDTO = new ArrayList<>();
        contas.forEach( conta -> contasDTO.add( ContaDTO.fromConta(conta) ) );
        return contasDTO;
    }

    @PostMapping("")
    public ContaDTO criarConta(@RequestBody String nome, @RequestParam String senha_dada) {
        autenticarSenha(senha_dada);
        Long id = contaService.criarConta(nome.trim());
        bancoService.adicionarSaldoInicial(id);
        return ContaDTO.fromConta( contaService.getConta(id) );
    }


    @GetMapping("")
    public List<ContaDTO> getTodasContas() {
        return contasToDTOList(contaService.getAllContas());
    }

    @PutMapping("/transferir")
    public boolean transferir(@RequestBody TransferenciaDTO body) {
        return bancoService.transferir(body.id_src(), body.id_dest(), body.valor());
    }

    @GetMapping("/buscar")
    public List<ContaDTO> pesquisarContaPorNome(@RequestParam String nome) {
        return contasToDTOList(contaService.getContasPorNome(nome));
    }

    @GetMapping("/{id}")
    public ContaDTO getConta(@PathVariable Long id) {
        return ContaDTO.fromConta( contaService.getConta(id));
    }

    @DeleteMapping("/{id}")
    public void apagarConta(@PathVariable Long id, @RequestParam String senha_dada) {
        autenticarSenha(senha_dada);
        contaService.apagarConta(id);
    }


}
