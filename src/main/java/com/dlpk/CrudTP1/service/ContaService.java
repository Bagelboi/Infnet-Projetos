package com.dlpk.CrudTP1.service;

import com.dlpk.CrudTP1.model.Conta;
import com.dlpk.CrudTP1.repository.ContaRepository;
import com.dlpk.CrudTP1.utils.FormattingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public ContaService(ContaRepository repository) {
        this.contaRepository = repository;
    }
    //commands

    public Long criarConta(String nome) throws RuntimeException {
        Conta conta_nova = new Conta();
        conta_nova.setNome(nome);
        conta_nova.setSaldo(0.0);
        return contaRepository.save(conta_nova).getId();
    }

    public void apagarConta(Long id) {
        contaRepository.deleteById(id);
    }

    public void atualizarSaldo(Long id, Double saldo) {
        Conta conta = getConta(id);
        conta.setSaldo(saldo);
        contaRepository.save(conta);
    }

    public void adicionarSaldo(Long id, Double quantidade) {
        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade negativa!");
        this.atualizarSaldo( id, this.getConta(id).getSaldo() + quantidade );
    }

    public void removerSaldo(Long id, Double quantidade) {
        if (quantidade < 0)
            throw new IllegalArgumentException("Quantidade negativa!");
        this.atualizarSaldo( id, this.getConta(id).getSaldo() - quantidade );
    }

    //queries

    public List<Conta> getAllContas() {
        return contaRepository.findAll();
    }

    public List<Conta> getContasPorNome(String nome) throws RuntimeException {
        return contaRepository.findByNomeContaining(FormattingUtil.formatNome(nome));
    }

    public Conta getConta(Long id) throws NoSuchElementException {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

}
