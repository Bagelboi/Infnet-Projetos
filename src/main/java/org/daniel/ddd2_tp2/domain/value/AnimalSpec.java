package org.daniel.ddd2_tp2.domain.value;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.daniel.ddd2_tp2.domain.enumerator.Especie;
import org.daniel.ddd2_tp2.domain.enumerator.Porte;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalSpec {
    int idade_min;
    int idade_max;
    Porte porte;
    Especie especie;
}
