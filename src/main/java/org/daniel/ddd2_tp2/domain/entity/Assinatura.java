package org.daniel.ddd2_tp2.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.daniel.ddd2_tp2.domain.value.AnimalSpec;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Assinatura {
    @Id
    Long id;
    AnimalSpec animalSpec;
    HashSet<Long> produtos;

    public boolean inserirProduto(Long _id) {
        return produtos.add(_id);
    }

    public boolean removerProduto(Long _id) {
        return produtos.remove(_id);
    }
}
