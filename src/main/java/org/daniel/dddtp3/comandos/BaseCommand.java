package org.daniel.dddtp3.comandos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseCommand {
    @TargetAggregateIdentifier
    private final String agg_id;
}
