package com.example.bookservices.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandUpdateBook {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String author;
    private boolean ready;
}
