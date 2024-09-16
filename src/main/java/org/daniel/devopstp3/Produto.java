package org.daniel.devopstp3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    @Id
    private Long id;
    private String nome;
    private double valor;
    private Long fornecedor_id;
}
