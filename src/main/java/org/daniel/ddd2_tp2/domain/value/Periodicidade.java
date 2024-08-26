package org.daniel.ddd2_tp2.domain.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daniel.ddd2_tp2.domain.enumerator.TipoData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Periodicidade {
    int intervalo;
    TipoData tipoData;
}
