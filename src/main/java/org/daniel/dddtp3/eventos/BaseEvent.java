package org.daniel.dddtp3.eventos;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@RequiredArgsConstructor
public class BaseEvent {
    @TargetAggregateIdentifier
    private final String agg_id;
}
