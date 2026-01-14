package com.example.commonservices.command.book;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandUpdateSttBook {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String author;
    private boolean ready;
}
