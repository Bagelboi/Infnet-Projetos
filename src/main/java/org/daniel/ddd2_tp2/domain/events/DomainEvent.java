package org.daniel.ddd2_tp2.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
abstract class DomainEvent {
    final String agg_type;
    final Object agg_id;

    abstract public String convertToPayload();

}
