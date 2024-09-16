package org.daniel.devopstp3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepo produtoRepository;

    public Flux<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Mono<Produto> get(Long id) {
        return produtoRepository.findById(id);
    }

    public Mono<Produto> save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Mono<Produto> update(Long id, Produto produto) {
        return get(id).map( p -> {
            p.setNome(produto.getNome());
            p.setValor(produto.getValor());
            p.setFornecedor_id(produto.getFornecedor_id());
            save(p);
            return p;
        } );
    }

    public Mono<?> delete(Long id) {
        return produtoRepository.deleteById(id);
    }

}
