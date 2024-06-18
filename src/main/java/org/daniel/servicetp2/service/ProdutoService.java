package org.daniel.servicetp2.service;

import org.daniel.servicetp2.model.Produto;
import org.daniel.servicetp2.repo.ProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepo produtoRepository;


    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Produto produto) {
        getProdutoById(produto.getId()).ifPresent( (prod) -> {
            produto.setName(prod.getName());
        } );
        return produtoRepository.save(produto);
    }

    public List<Produto> getAllProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}

