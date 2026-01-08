package com.example.userservices.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandCreateUser {
    @TargetAggregateIdentifier
    private String id;
    private String fName;
    private String lName;
    private String kin; // mnv
    private boolean discipline; // bi ky luat
}
