package com.example.userservices.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandDeleteUser {
    @TargetAggregateIdentifier
    private String id;
}
