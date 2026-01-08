package com.example.bookservices.command.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelUpdateBook {
    private String id;
    private String name;
    private String author;
    private boolean ready;
}
