package org.dlpk.tp3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dlpk.tp3.value.Preco;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    String nome;
    Preco preco;
}
